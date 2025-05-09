package com.dao;

import com.config.Koneks;
import com.model.ModelDetailPenjualan;
import com.model.ModelKategori;
import com.model.ModelPenjualan;
import com.model.ModelProduk;
import com.service.ServiceDetailPenjualan;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class DetailPenjualanDAO implements ServiceDetailPenjualan{

    private final Connection conn;
    
    public DetailPenjualanDAO(){
        conn = Koneks.getConnection();
    }
    
    @Override
    public void tambahData(ModelDetailPenjualan model) {
        PreparedStatement st = null;
        try{
            String sql = "INSERT INTO detail_penjualan(id_penjualan, id_produk, jumlah, subtotal) "
                         + "SELECT '"+model.getModelPenjualan().getIdPenjualan()+"',"
                         + "id_produk, jumlah , subtotal FROM smt_penjualan";
            st = conn.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sumTotal(ModelDetailPenjualan model) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT SUM(subtotal) FROM smt_penjualan";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                model.setSubTotal(rs.getLong(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            if (st!=null) {
                try{
                    st.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    
    @Override
    public void sumJumlah(ModelDetailPenjualan model) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT SUM(jumlah) FROM smt_penjualan";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                model.setJumlah(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            if (st!=null) {
                try{
                    st.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void hapusDataSementara() {
        PreparedStatement st = null;
        String sql = "DELETE FROM smt_penjualan";
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelDetailPenjualan> tampilData(String id) {
        List list = new ArrayList();
        String sql = "SELECT pj.id_penjualan, pd.id_produk, pd.barcode, pd.nama_produk, ktg.nama_kategori, pd.harga, det.jumlah, det.subtotal\n"+
                     "FROM detail_penjualan det\n"+
                     "INNER JOIN penjualan pj ON pj.id_penjualan = det.id_penjualan\n"+
                     "INNER JOIN produk pd ON pd.id_produk = det.id_produk\n"+
                     "INNER JOIN kategori ktg ON ktg.id_kategori = pd.id_kategori\n"+
                     "WHERE pj.id_penjualan = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, id);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ModelDetailPenjualan det = new ModelDetailPenjualan();
                    ModelPenjualan pj = new ModelPenjualan();
                    ModelProduk pd = new ModelProduk();
                    ModelKategori ktg = new ModelKategori();

                    pj.setIdPenjualan    (rs.getString("id_penjualan"));
                    pd.setIdProduk       (rs.getInt("id_produk"));
                    pd.setBarcode        (rs.getString("barcode"));
                    pd.setNamaProduk     (rs.getString("nama_produk"));
                    ktg.setNamaKategori  (rs.getString("nama_kategori"));
                    pd.setHarga          (rs.getLong("harga"));
                    det.setJumlah        (rs.getInt("jumlah"));
                    det.setSubTotal      (rs.getLong("subtotal"));

                    pd.setModelKategori(ktg);
                    det.setModelPenjualan(pj);
                    det.setModelProduk(pd);

                    list.add(det);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelDetailPenjualan> pencarianData(String id, String kataKunci) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT pj.id_penjualan, pd.id_produk, pd.barcode, pd.nama_produk, ktg.nama_kategori, pd.harga, det.jumlah, det.subtotal\n"+
                     "FROM detail_penjualan det\n"+
                     "INNER JOIN penjualan pj ON pj.id_penjualan = det.id_penjualan\n"+
                     "INNER JOIN produk pd ON pd.id_produk = det.id_produk\n"+
                     "INNER JOIN kategori ktg ON ktg.id_kategori = pd.id_kategori\n"+
                     "WHERE pj.id_penjualan = '"+id+"' "
                     + "AND (pj.id_penjualan LIKE '%"+kataKunci+"%' "
                     + "OR pd.id_produk LIKE '%"+kataKunci+"%'"
                     + "OR pd.barcode LIKE '%"+kataKunci+"%'"
                     + "OR ktg.nama_kategori LIKE '%"+kataKunci+"%'"
                     + "OR pd.nama_produk LIKE '%"+kataKunci+"%')";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                    ModelDetailPenjualan det = new ModelDetailPenjualan();
                    ModelPenjualan pj = new ModelPenjualan();
                    ModelProduk pd = new ModelProduk();
                    ModelKategori ktg = new ModelKategori();

                    pj.setIdPenjualan    (rs.getString("id_penjualan"));
                    pd.setIdProduk       (rs.getInt("id_produk"));
                    pd.setBarcode        (rs.getString("barcode"));
                    pd.setNamaProduk     (rs.getString("nama_produk"));
                    ktg.setNamaKategori  (rs.getString("nama_kategori"));
                    pd.setHarga          (rs.getLong("harga"));
                    det.setJumlah        (rs.getInt("jumlah"));
                    det.setSubTotal      (rs.getLong("subtotal"));

                    pd.setModelKategori(ktg);
                    det.setModelPenjualan(pj);
                    det.setModelProduk(pd);

                    list.add(det);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}