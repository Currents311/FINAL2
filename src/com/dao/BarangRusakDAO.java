package com.dao;

import com.config.Koneks;
import com.model.ModelKaryawan;
import com.model.ModelSupplier;
import com.model.ModelBarangRusak;
import com.service.ServiceBarangRusak;
import java.util.List;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BarangRusakDAO implements ServiceBarangRusak{

    private final Connection conn;
    
    public BarangRusakDAO(){
        conn = Koneks.getConnection();
    }
    
    @Override
    public void tambahData(ModelBarangRusak model) {
        PreparedStatement st = null;
        try{
            String sql = "INSERT INTO barang_rusak(id_barang_rusak, id_supplier, tanggal_retur, "
                    + " total_retur, id_karyawan) VALUES (?,?,?,?,?)";

            st = conn.prepareStatement(sql);
            st.setString    (1, model.getIdBarangRusak());
            st.setInt       (2, model.getModelSupplier().getIdSupplier());
            st.setString    (3, model.getTanggalRetur());
            st.setDouble    (4, model.getTotalRetur());
            st.setInt       (5, model.getModelKaryawan().getIdKaryawan());

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelBarangRusak> tampilData(int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT br.id_barang_rusak, sup.id_supplier, sup.nama_supplier, br.tanggal_retur, br.total_retur,"
                     + " ky.id_karyawan, ky.nama_karyawan\n"+
                     "FROM barang_rusak br\n"+
                     "INNER JOIN Supplier sup ON sup.id_Supplier = br.id_Supplier\n"+
                     "INNER JOIN karyawan ky ON ky.id_karyawan = br.id_karyawan "
                   + "ORDER BY br.id_barang_rusak ASC LIMIT ? OFFSET ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelBarangRusak br = new ModelBarangRusak();
                ModelSupplier sup = new ModelSupplier();
                ModelKaryawan ky = new ModelKaryawan();
                
                br.setIdBarangRusak     (rs.getString("id_barang_rusak"));
                sup.setIdSupplier       (rs.getInt("id_supplier"));
                sup.setNamaSupplier     (rs.getString("nama_supplier"));
                br.setTanggalRetur      (rs.getString("tanggal_retur"));
                br.setTotalRetur        (rs.getDouble("total_retur"));
                ky.setIdKaryawan        (rs.getInt("id_karyawan"));
                ky.setNamaKaryawan      (rs.getString("nama_karyawan"));

                br.setModelSupplier(sup);
                br.setModelKaryawan(ky);
                
                list.add(br);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelBarangRusak> pencarianData(String id, int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT br.id_barang_rusak, sup.id_supplier, sup.nama_supplier, br.tanggal_retur, br.total_retur,"
                     + " ky.id_karyawan, ky.nama_karyawan\n"+
                     "FROM barang_rusak br\n"+
                     "INNER JOIN Supplier sup ON sup.id_Supplier = br.id_Supplier\n"+
                     "INNER JOIN karyawan ky ON ky.id_karyawan = br.id_karyawan "
                + "WHERE br.id_barang_rusak LIKE '%"+id+"%' "
                + "OR sup.id_supplier LIKE '%"+id+"%' "
                + "OR sup.nama_supplier LIKE '%"+id+"%' "
                + "OR br.tanggal_retur LIKE '%"+id+"%' "
                + "OR ky.id_karyawan LIKE '%"+id+"%' "
                + "OR ky.nama_karyawan LIKE '%"+id+"%' "
                + "ORDER BY br.id_barang_rusak ASC LIMIT ? OFFSET ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelBarangRusak br = new ModelBarangRusak();
                ModelSupplier sup = new ModelSupplier();
                ModelKaryawan ky = new ModelKaryawan();
                
                br.setIdBarangRusak     (rs.getString("id_barang_rusak"));
                sup.setIdSupplier       (rs.getInt("id_supplier"));
                sup.setNamaSupplier     (rs.getString("nama_supplier"));
                br.setTanggalRetur      (rs.getString("tanggal_retur"));
                br.setTotalRetur        (rs.getDouble("total_retur"));
                ky.setIdKaryawan        (rs.getInt("id_karyawan"));
                ky.setNamaKaryawan      (rs.getString("nama_karyawan"));

                br.setModelSupplier(sup);
                br.setModelKaryawan(ky);
                
                list.add(br);
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
        
        String sql = "SELECT nomor FROM nomor_barang_rusak ORDER BY id DESC " +
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
            
            urutan = "TBR" + no + String.format("%04d", nomor);
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
    public void hapusData(ModelBarangRusak model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM barang_rusak WHERE id_barang_rusak=?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, model.getIdBarangRusak());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void simpanNoTransaksi() {
        if(noTransaksi != null){
            PreparedStatement st = null;
            String sql = "INSERT INTO nomor_barang_rusak(tanggal, nomor) VALUES(?,?)";
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
    public int getTotalData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) AS total FROM barang_rusak";
        
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
    public List<ModelBarangRusak> rangeTanggal(Date startDate, Date endDate) {
        List<ModelBarangRusak> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        String sql = "SELECT br.id_barang_rusak, sup.id_supplier, sup.nama_supplier, br.tanggal_retur, br.total_retur,"
                     + " ky.id_karyawan, ky.nama_karyawan\n"+
                     "FROM barang_rusak br\n"+
                     "INNER JOIN Supplier sup ON sup.id_Supplier = br.id_Supplier\n"+
                     "INNER JOIN karyawan ky ON ky.id_karyawan = br.id_karyawan " +
                     "WHERE br.tanggal_retur BETWEEN ? AND ? ORDER BY br.tanggal_retur ASC";
        try {
            st = conn.prepareStatement(sql);
            st.setDate(1, new java.sql.Date(startDate.getTime()));
            st.setDate(2, new java.sql.Date(endDate.getTime()));
            rs = st.executeQuery();
        while(rs.next()){
                ModelBarangRusak br = new ModelBarangRusak();
                ModelSupplier sup = new ModelSupplier();
                ModelKaryawan ky = new ModelKaryawan();
                
                br.setIdBarangRusak     (rs.getString("id_barang_rusak"));
                sup.setIdSupplier       (rs.getInt("id_supplier"));
                sup.setNamaSupplier     (rs.getString("nama_supplier"));
                br.setTanggalRetur      (rs.getString("tanggal_retur"));
                br.setTotalRetur        (rs.getDouble("total_retur"));
                ky.setIdKaryawan        (rs.getInt("id_karyawan"));
                ky.setNamaKaryawan      (rs.getString("nama_karyawan"));

                br.setModelSupplier(sup);
                br.setModelKaryawan(ky);
                
                list.add(br);
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

    @Override
    public void deleteNoTransaksi() {
        PreparedStatement st = null;
        String sql = "DELETE FROM nomor_barang_rusak WHERE id = (SELECT MAX(id) FROM nomor_barang_rusak)";
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
        String sql = "ALTER TABLE nomor_barang_rusak AUTO_INCREMENT = 1";
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}   