package com.dao;

import com.config.Koneks;
import com.model.ModelKaryawan;
import com.model.ModelPelanggan;
import com.model.ModelPenjualan;
import com.service.ServicePenjualan;
import java.util.List;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PenjualanDAO implements ServicePenjualan{

    private final Connection conn;
    
    public PenjualanDAO(){
        conn = Koneks.getConnection();
    }
    
    @Override
    public void tambahData(ModelPenjualan model) {
        PreparedStatement st = null;
        try{
            String sql = "INSERT INTO penjualan(id_penjualan, id_pelanggan, tanggal, "
                    + "total_jumlah, total_harga, bayar, diskon, kembali, id_karyawan) VALUES (?,?,?,?,?,?,?,?,?)";

            st = conn.prepareStatement(sql);
            st.setString    (1, model.getIdPenjualan());
            st.setInt       (2, model.getModelPelanggan().getIdPelanggan());
            st.setString    (3, model.getTanggal());
            st.setInt       (4, model.getTotalJumlah());
            st.setDouble    (5, model.getTotalHarga());
            st.setDouble    (6, model.getBayar());
            st.setDouble    (7, model.getDiskon());
            st.setDouble    (8, model.getKembalian());
            st.setInt       (9, model.getModelKaryawan().getIdKaryawan());

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelPenjualan> tampilData(int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT pj.id_penjualan, pl.id_pelanggan, pl.nama_pelanggan, pj.tanggal, pj.total_jumlah, pj.total_harga,"
                     + " pj.bayar, pj.diskon, pj.kembali, ky.id_karyawan, ky.nama_karyawan\n"+
                     "FROM penjualan pj\n"+
                     "INNER JOIN pelanggan pl ON pl.id_pelanggan = pj.id_pelanggan\n"+
                     "INNER JOIN karyawan ky ON ky.id_karyawan = pj.id_karyawan "
                    + "ORDER BY pj.id_penjualan ASC LIMIT ? OFFSET ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelPenjualan pj = new ModelPenjualan();
                ModelPelanggan pl = new ModelPelanggan();
                ModelKaryawan ky = new ModelKaryawan();
                
                pj.setIdPenjualan       (rs.getString("id_penjualan"));
                pl.setIdPelanggan       (rs.getInt("id_pelanggan"));
                pl.setNamaPelanggan     (rs.getString("nama_pelanggan"));
                pj.setTanggal           (rs.getString("tanggal"));
                pj.setTotalJumlah       (rs.getInt("total_jumlah"));
                pj.setTotalHarga        (rs.getDouble("total_harga"));
                pj.setBayar             (rs.getDouble("bayar"));
                pj.setDiskon            (rs.getDouble("diskon"));
                pj.setKembalian         (rs.getDouble("kembali"));
                ky.setIdKaryawan        (rs.getInt("id_karyawan"));
                ky.setNamaKaryawan      (rs.getString("nama_karyawan"));

                pj.setModelPelanggan(pl);
                pj.setModelKaryawan(ky);
                
                list.add(pj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelPenjualan> pencarianData(String id, int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT pj.id_penjualan, pl.id_pelanggan, pl.nama_pelanggan, pj.tanggal, pj.total_harga, pj.total_jumlah,"
                     + " pj.bayar, pj.diskon, pj.kembali, ky.id_karyawan, ky.nama_karyawan\n"+
                     "FROM penjualan pj\n"+
                     "INNER JOIN pelanggan pl ON pl.id_pelanggan = pj.id_pelanggan\n"+
                     "INNER JOIN karyawan ky ON ky.id_karyawan = pj.id_karyawan "
                + "WHERE pj.id_penjualan LIKE '%"+id+"%' "
                + "OR pl.id_pelanggan LIKE '%"+id+"%' "
                + "OR pl.nama_pelanggan LIKE '%"+id+"%' "
                + "OR pj.tanggal LIKE '%"+id+"%' "
                + "OR ky.id_karyawan LIKE '%"+id+"%' "
                + "OR ky.nama_karyawan LIKE '%"+id+"%' "
                + "ORDER BY pj.id_penjualan ASC LIMIT ? OFFSET ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelPenjualan pj = new ModelPenjualan();
                ModelPelanggan pl = new ModelPelanggan();
                ModelKaryawan ky = new ModelKaryawan();
                
                pj.setIdPenjualan       (rs.getString("id_penjualan"));
                pl.setIdPelanggan       (rs.getInt("id_pelanggan"));
                pl.setNamaPelanggan     (rs.getString("nama_pelanggan"));
                pj.setTanggal           (rs.getString("tanggal"));
                pj.setTotalJumlah       (rs.getInt("total_jumlah"));
                pj.setTotalHarga        (rs.getDouble("total_harga"));
                pj.setBayar             (rs.getDouble("bayar"));
                pj.setDiskon            (rs.getDouble("diskon"));
                pj.setKembalian         (rs.getDouble("kembali"));
                ky.setIdKaryawan        (rs.getInt("id_karyawan"));
                ky.setNamaKaryawan      (rs.getString("nama_karyawan"));

                pj.setModelPelanggan(pl);
                pj.setModelKaryawan(ky);
                
                list.add(pj);
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
        
        String sql = "SELECT nomor FROM nomor_penjualan ORDER BY id DESC " +
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
            
            urutan = "TRX" + no + String.format("%04d", nomor);
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
    public void hapusData(ModelPenjualan model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM penjualan WHERE id_penjualan=?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, model.getIdPenjualan());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void simpanNoTransaksi() {
        if(noTransaksi != null){
            PreparedStatement st = null;
            String sql = "INSERT INTO nomor_penjualan(tanggal, nomor) VALUES(?,?)";
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
    public void updateStok(ModelPenjualan model) {
        PreparedStatement st = null;
        String sql = "UPDATE produk pd "
                + "INNER JOIN detail_penjualan det ON pd.id_produk = det.id_produk "
                + "SET pd.stok = pd.stok - det.jumlah "
                + "WHERE det.id_penjualan = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, model.getIdPenjualan());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStokDelete(ModelPenjualan model) {
        PreparedStatement st = null;
        String sql = "UPDATE produk pd "
                + "INNER JOIN detail_penjualan det ON pd.id_produk = det.id_produk "
                + "SET pd.stok = pd.stok + det.jumlah "
                + "WHERE det.id_penjualan = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, model.getIdPenjualan());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getTotalData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) AS total FROM penjualan";
        
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
    public void deleteNoTransaksi() {
        PreparedStatement st = null;
        String sql = "DELETE FROM nomor_penjualan WHERE id = (SELECT MAX(id) FROM nomor_penjualan)";
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
            String sql = "ALTER TABLE nomor_penjualan AUTO_INCREMENT = 1";
            try {
                st = conn.prepareStatement(sql);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }    

    @Override
    public List<ModelPenjualan> rangeTanggal(Date startDate, Date endDate) {
        List<ModelPenjualan> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT pj.id_penjualan, pl.id_pelanggan, pl.nama_pelanggan, pj.tanggal, pj.total_jumlah, pj.total_harga, " +
                     "pj.bayar, pj.diskon, pj.kembali, ky.id_karyawan, ky.nama_karyawan " +
                     "FROM penjualan pj " +
                     "INNER JOIN pelanggan pl ON pl.id_pelanggan = pj.id_pelanggan " +
                     "INNER JOIN karyawan ky ON ky.id_karyawan = pj.id_karyawan " +
                     "WHERE pj.tanggal BETWEEN ? AND ? ORDER BY pj.tanggal ASC";
        try {
            st = conn.prepareStatement(sql);
            st.setDate(1, new java.sql.Date(startDate.getTime()));
            st.setDate(2, new java.sql.Date(endDate.getTime()));
            rs = st.executeQuery();

            while (rs.next()) {
                ModelPenjualan pj = new ModelPenjualan();
                ModelPelanggan pl = new ModelPelanggan();
                ModelKaryawan ky = new ModelKaryawan();

                pj.setIdPenjualan(rs.getString("id_penjualan"));
                pj.setTanggal(rs.getString("tanggal"));
                pj.setTotalJumlah(rs.getInt("total_jumlah"));
                pj.setTotalHarga(rs.getDouble("total_harga"));
                pj.setBayar(rs.getDouble("bayar"));
                pj.setDiskon(rs.getDouble("diskon"));
                pj.setKembalian(rs.getDouble("kembali"));

                pl.setIdPelanggan(rs.getInt("id_pelanggan"));
                pl.setNamaPelanggan(rs.getString("nama_pelanggan"));
                pj.setModelPelanggan(pl);

                ky.setIdKaryawan(rs.getInt("id_karyawan"));
                ky.setNamaKaryawan(rs.getString("nama_karyawan"));
                pj.setModelKaryawan(ky);

                list.add(pj);
            }

        } catch (SQLException e) {
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