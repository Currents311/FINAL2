package com.dao;

import com.config.Koneks;
import com.model.ModelDetailPembelian;
import com.model.ModelKategori;
import com.model.ModelPembelian;
import com.model.ModelProduk;
import com.service.ServiceDetailPembelian;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class DetailPembelianDAO implements ServiceDetailPembelian{

    private final Connection conn;
    
    public DetailPembelianDAO(){
        conn = Koneks.getConnection();
    }
    
    @Override
    public void tambahData(ModelDetailPembelian model) {
        PreparedStatement st = null;
        try{
            String sql = "INSERT INTO detail_pembelian(id_pembelian, id_produk, jumlah, subtotal) "
                         + "SELECT '"+model.getModelPembelian().getIdPembelian()+"',"
                         + "id_produk, jumlah , subtotal FROM smt_pembelian";
            st = conn.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sumTotal(ModelDetailPembelian model) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT SUM(subtotal) FROM smt_pembelian";
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
    public void sumJumlah(ModelDetailPembelian model) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT SUM(jumlah) FROM smt_pembelian";
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
        String sql = "DELETE FROM smt_pembelian";
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelDetailPembelian> tampilData(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT pb.id_pembelian, pb.tanggal, pd.id_produk, pd.barcode, pd.nama_produk, ktg.nama_kategori, pd.harga, det.jumlah, det.subtotal\n"+
                     "FROM detail_pembelian det\n"+
                     "INNER JOIN pembelian pb ON pb.id_pembelian = det.id_pembelian\n"+
                     "INNER JOIN produk pd ON pd.id_produk = det.id_produk\n"+
                     "INNER JOIN kategori ktg ON ktg.id_kategori = pd.id_kategori\n"+
                     "WHERE pb.id_pembelian = '"+id+"'";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                ModelDetailPembelian det = new ModelDetailPembelian();
                ModelPembelian pb = new ModelPembelian();
                ModelProduk pd = new ModelProduk();
                ModelKategori ktg = new ModelKategori();

                pb.setIdPembelian    (rs.getString("id_pembelian"));
                pb.setTanggal        (rs.getString("tanggal"));
                pd.setIdProduk       (rs.getInt("id_produk"));
                pd.setBarcode        (rs.getString("barcode"));
                pd.setNamaProduk     (rs.getString("nama_produk"));
                ktg.setNamaKategori  (rs.getString("nama_kategori"));
                pd.setHarga          (rs.getLong("harga"));
                det.setJumlah        (rs.getInt("jumlah"));
                det.setSubTotal      (rs.getLong("subtotal"));

                pd.setModelKategori(ktg);
                det.setModelPembelian(pb);
                det.setModelProduk(pd);

                list.add(det);
            }            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelDetailPembelian> pencarianData(String id, String kataKunci) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT pb.id_pembelian, pb.tanggal, pd.id_produk, pd.barcode, pd.nama_produk, ktg.nama_kategori, pd.harga, det.jumlah, det.subtotal\n"+
                     "FROM detail_pembelian det\n"+
                     "INNER JOIN pembelian pb ON pb.id_pembelian = det.id_pembelian\n"+
                     "INNER JOIN produk pd ON pd.id_produk = det.id_produk\n"+
                     "INNER JOIN kategori ktg ON ktg.id_kategori = pd.id_kategori\n"+
                     "WHERE pb.id_pembelian = '"+id+"' "
                     + "AND (pb.id_pembelian LIKE '%"+kataKunci+"%' "
                     + "OR pd.id_produk LIKE '%"+kataKunci+"%'"
                     + "OR pd.barcode LIKE '%"+kataKunci+"%'"
                     + "OR ktg.nama_kategori LIKE '%"+kataKunci+"%'"
                     + "OR pd.nama_produk LIKE '%"+kataKunci+"%')";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                    ModelDetailPembelian det = new ModelDetailPembelian();
                    ModelPembelian pb = new ModelPembelian();
                    ModelProduk pd = new ModelProduk();
                    ModelKategori ktg = new ModelKategori();

                    pb.setIdPembelian    (rs.getString("id_pembelian"));
                    pb.setTanggal        (rs.getString("tanggal"));
                    pd.setIdProduk       (rs.getInt("id_produk"));
                    pd.setBarcode        (rs.getString("barcode"));
                    pd.setNamaProduk     (rs.getString("nama_produk"));
                    ktg.setNamaKategori  (rs.getString("nama_kategori"));
                    pd.setHarga          (rs.getLong("harga"));
                    det.setJumlah        (rs.getInt("jumlah"));
                    det.setSubTotal      (rs.getLong("subtotal"));

                    pd.setModelKategori(ktg);
                    det.setModelPembelian(pb);
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

    @Override
    public List<ModelDetailPembelian> tampilData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT pb.id_pembelian, pb.tanggal, pd.id_produk, pd.barcode, pd.nama_produk, ktg.nama_kategori, pd.harga, det.jumlah, det.subtotal\n"+
                     "FROM detail_pembelian det\n"+
                     "INNER JOIN pembelian pb ON pb.id_pembelian = det.id_pembelian\n"+
                     "INNER JOIN produk pd ON pd.id_produk = det.id_produk\n"+
                     "INNER JOIN kategori ktg ON ktg.id_kategori = pd.id_kategori ORDER BY pb.id_pembelian ASC";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelDetailPembelian det = new ModelDetailPembelian();
                    ModelPembelian pb = new ModelPembelian();
                    ModelProduk pd = new ModelProduk();
                    ModelKategori ktg = new ModelKategori();

                    pb.setIdPembelian    (rs.getString("id_pembelian"));
                    pb.setTanggal        (rs.getString("tanggal"));
                    pd.setIdProduk       (rs.getInt("id_produk"));
                    pd.setBarcode        (rs.getString("barcode"));
                    pd.setNamaProduk     (rs.getString("nama_produk"));
                    ktg.setNamaKategori  (rs.getString("nama_kategori"));
                    pd.setHarga          (rs.getLong("harga"));
                    det.setJumlah        (rs.getInt("jumlah"));
                    det.setSubTotal      (rs.getLong("subtotal"));

                    pd.setModelKategori(ktg);
                    det.setModelPembelian(pb);
                    det.setModelProduk(pd);

                    list.add(det);
            }            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    

    @Override
    public List<ModelDetailPembelian> pencarianData(String kataKunci) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT pb.id_pembelian, pb.tanggal, pd.id_produk, pd.barcode, pd.nama_produk, ktg.nama_kategori, pd.harga, det.jumlah, det.subtotal\n"+
                     "FROM detail_pembelian det\n"+
                     "INNER JOIN pembelian pb ON pb.id_pembelian = det.id_pembelian\n"+
                     "INNER JOIN produk pd ON pd.id_produk = det.id_produk\n"+
                     "INNER JOIN kategori ktg ON ktg.id_kategori = pd.id_kategori\n"+
                     "WHERE pb.id_pembelian = '"+kataKunci+"' "
                     + "OR pd.id_produk LIKE '%"+kataKunci+"%'"
                     + "OR pd.barcode LIKE '%"+kataKunci+"%'"
                     + "OR ktg.nama_kategori LIKE '%"+kataKunci+"%'"
                     + "OR pd.nama_produk LIKE '%"+kataKunci+"%'";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                    ModelDetailPembelian det = new ModelDetailPembelian();
                    ModelPembelian pb = new ModelPembelian();
                    ModelProduk pd = new ModelProduk();
                    ModelKategori ktg = new ModelKategori();

                    pb.setIdPembelian    (rs.getString("id_pembelian"));
                    pb.setTanggal        (rs.getString("tanggal"));
                    pd.setIdProduk       (rs.getInt("id_produk"));
                    pd.setBarcode        (rs.getString("barcode"));
                    pd.setNamaProduk     (rs.getString("nama_produk"));
                    ktg.setNamaKategori  (rs.getString("nama_kategori"));
                    pd.setHarga          (rs.getLong("harga"));
                    det.setJumlah        (rs.getInt("jumlah"));
                    det.setSubTotal      (rs.getLong("subtotal"));

                    pd.setModelKategori(ktg);
                    det.setModelPembelian(pb);
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