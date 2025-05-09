package com.dao;

import com.config.Koneks;
import com.model.ModelKaryawan;
import com.model.ModelSupplier;
import com.model.ModelPembelian;
import com.service.ServicePembelian;
import java.util.List;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PembelianDAO implements ServicePembelian{

    private final Connection conn;
    
    public PembelianDAO(){
        conn = Koneks.getConnection();
    }
    
    @Override
    public void tambahData(ModelPembelian model) {
        PreparedStatement st = null;
        try{
            String sql = "INSERT INTO pembelian(id_pembelian, id_supplier, tanggal, "
                    + " total_harga, id_karyawan) VALUES (?,?,?,?,?)";

            st = conn.prepareStatement(sql);
            st.setString    (1, model.getIdPembelian());
            st.setInt       (2, model.getModelSupplier().getIdSupplier());
            st.setString    (3, model.getTanggal());
            st.setDouble    (4, model.getTotalHarga());
            st.setInt       (5, model.getModelKaryawan().getIdKaryawan());

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelPembelian> tampilData(int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT pb.id_pembelian, sup.id_supplier, sup.nama_supplier, pb.tanggal, pb.total_harga,"
                     + " ky.id_karyawan, ky.nama_karyawan\n"+
                     "FROM pembelian pb\n"+
                     "INNER JOIN Supplier sup ON sup.id_Supplier = pb.id_Supplier\n"+
                     "INNER JOIN karyawan ky ON ky.id_karyawan = pb.id_karyawan "
                    + "ORDER BY pb.id_pembelian ASC LIMIT ? OFFSET ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelPembelian pb = new ModelPembelian();
                ModelSupplier sup = new ModelSupplier();
                ModelKaryawan ky = new ModelKaryawan();
                
                pb.setIdPembelian       (rs.getString("id_pembelian"));
                sup.setIdSupplier       (rs.getInt("id_supplier"));
                sup.setNamaSupplier     (rs.getString("nama_supplier"));
                pb.setTanggal           (rs.getString("tanggal"));
                pb.setTotalHarga        (rs.getDouble("total_harga"));
                ky.setIdKaryawan        (rs.getInt("id_karyawan"));
                ky.setNamaKaryawan      (rs.getString("nama_karyawan"));

                pb.setModelSupplier(sup);
                pb.setModelKaryawan(ky);
                
                list.add(pb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelPembelian> pencarianData(String id, int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT pb.id_pembelian, sup.id_Supplier, sup.nama_Supplier, pb.tanggal, pb.total_harga,"
                     + " ky.id_karyawan, ky.nama_karyawan\n"+
                     "FROM pembelian pb\n"+
                     "INNER JOIN Supplier sup ON sup.id_Supplier = pb.id_Supplier\n"+
                     "INNER JOIN karyawan ky ON ky.id_karyawan = pb.id_karyawan "
                + "WHERE pb.id_pembelian LIKE '%"+id+"%' "
                + "OR sup.id_Supplier LIKE '%"+id+"%' "
                + "OR sup.nama_Supplier LIKE '%"+id+"%' "
                + "OR pb.tanggal LIKE '%"+id+"%' "
                + "OR ky.id_karyawan LIKE '%"+id+"%' "
                + "OR ky.nama_karyawan LIKE '%"+id+"%' "
                + "ORDER BY pb.id_pembelian ASC LIMIT ? OFFSET ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelPembelian pb = new ModelPembelian();
                ModelSupplier sup = new ModelSupplier();
                ModelKaryawan ky = new ModelKaryawan();
                
                pb.setIdPembelian       (rs.getString("id_pembelian"));
                sup.setIdSupplier       (rs.getInt("id_supplier"));
                sup.setNamaSupplier     (rs.getString("nama_supplier"));
                pb.setTanggal           (rs.getString("tanggal"));
                pb.setTotalHarga        (rs.getDouble("total_harga"));
                ky.setIdKaryawan        (rs.getInt("id_karyawan"));
                ky.setNamaKaryawan      (rs.getString("nama_karyawan"));

                pb.setModelSupplier(sup);
                pb.setModelKaryawan(ky);
                
                list.add(pb);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private String noTransaksi;
    
    @Override
    public String generateNoTransaksi() {
        if(noTransaksi != null){
            return noTransaksi;
        }
        PreparedStatement st = null;
        ResultSet rs = null;
        String urutan = null;
        
        Date now = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat noformat = new SimpleDateFormat("yyMMdd");
        String tgl = tanggal.format(now);
        String no = noformat.format(now);
        
        String sql = "SELECT nomor FROM nomor_pembelian ORDER BY id DESC " +
                     "LIMIT 1";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            int nomor;
            if(rs.next()){
               nomor = rs.getInt("nomor") + 1;
            } else{
               nomor = 1;
            }
            
            urutan = "TPM" + no + String.format("%04d", nomor);
            noTransaksi = urutan;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            if(st != null){
                try{
                    st.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return urutan;
    }

    @Override
    public void hapusData(ModelPembelian model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM pembelian WHERE id_pembelian=?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, model.getIdPembelian());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void simpanNoTransaksi() {
        if(noTransaksi != null){
            PreparedStatement st = null;
            String sql = "INSERT INTO nomor_pembelian(tanggal, nomor) VALUES(?,?)";
            try {
                st = conn.prepareStatement(sql);
                st.setString(1, noTransaksi.substring(3,9));
                st.setInt(2, Integer.parseInt(noTransaksi.substring(9)));
                st.executeUpdate();
                noTransaksi = null;
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Nomor Transaksi gagal disimpan");                
            }
        }else{
            JOptionPane.showMessageDialog(null, "Tidak ada nomor transaksi yang disimpan");
        }
    }
    
    @Override
    public void deleteNoTransaksi() {
//        PreparedStatement st = null;
//        String sql = "DELETE FROM nomor_pembelian WHERE id = (SELECT id FROM (SELECT MAX(id) as id FROM nomor_pembelian) as subquery";  
//        try {
//            st = conn.prepareStatement(sql);
//            st.executeUpdate();
//            noTransaksi = null;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        PreparedStatement st = null;
        String sql = "DELETE FROM nomor_pembelian WHERE id = (SELECT MAX(id) FROM nomor_pembelian)";
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
            noTransaksi = null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }    
        }
    }
    
    @Override
    public void resetNoTransaksi() {
        PreparedStatement st = null;
        String sql = "ALTER TABLE nomor_pembelian AUTO_INCREMENT = 1";
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getTotalData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) AS total FROM pembelian";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()){
                return rs.getInt("total");
            }else{
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void updateStok(ModelPembelian model) {
        PreparedStatement st = null;
        String sql = "UPDATE produk pd "
                + "INNER JOIN detail_pembelian det ON pd.id_produk = det.id_produk "
                + "SET pd.stok = pd.stok + det.jumlah "
                + "WHERE det.id_pembelian = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, model.getIdPembelian());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStokDelete(ModelPembelian model) {
        PreparedStatement st = null;
        String sql = "UPDATE produk pd "
                + "INNER JOIN detail_pembelian det ON pd.id_produk = det.id_produk "
                + "SET pd.stok = pd.stok - det.jumlah "
                + "WHERE det.id_pembelian = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, model.getIdPembelian());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

    @Override
    public List<ModelPembelian> rangeTanggal(Date startDate, Date endDate) {
        List<ModelPembelian> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        String sql = "SELECT pb.id_pembelian, sup.id_Supplier, sup.nama_Supplier, pb.tanggal, pb.total_harga,"
                     + " ky.id_karyawan, ky.nama_karyawan\n"+
                     "FROM pembelian pb\n"+
                     "INNER JOIN Supplier sup ON sup.id_Supplier = pb.id_Supplier\n"+
                     "INNER JOIN karyawan ky ON ky.id_karyawan = pb.id_karyawan " +
                     "WHERE pb.tanggal BETWEEN ? AND ? ORDER BY pb.tanggal ASC";
        try {
            st = conn.prepareStatement(sql);
            st.setDate(1, new java.sql.Date(startDate.getTime()));
            st.setDate(2, new java.sql.Date(endDate.getTime()));
            rs = st.executeQuery();
            
            while(rs.next()){
                ModelPembelian pb = new ModelPembelian();
                ModelSupplier sup = new ModelSupplier();
                ModelKaryawan ky = new ModelKaryawan();
                
                pb.setIdPembelian       (rs.getString("id_pembelian"));
                sup.setIdSupplier       (rs.getInt("id_supplier"));
                sup.setNamaSupplier     (rs.getString("nama_supplier"));
                pb.setTanggal           (rs.getString("tanggal"));
                pb.setTotalHarga        (rs.getDouble("total_harga"));
                ky.setIdKaryawan        (rs.getInt("id_karyawan"));
                ky.setNamaKaryawan      (rs.getString("nama_karyawan"));

                pb.setModelSupplier(sup);
                pb.setModelKaryawan(ky);
                
                list.add(pb);
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}   