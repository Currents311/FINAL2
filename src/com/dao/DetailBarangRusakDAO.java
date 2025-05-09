package com.dao;

import com.config.Koneks;
import com.model.ModelBarangRusak;
import com.model.ModelDetailBarangRusak;
import com.model.ModelKategori;
import com.model.ModelPembelian;
import com.model.ModelProduk;
import com.service.ServiceDetailBarangRusak;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class DetailBarangRusakDAO implements ServiceDetailBarangRusak{

    private final Connection conn;
    
    public DetailBarangRusakDAO(){
        conn = Koneks.getConnection();
    }
    
    @Override
    public void tambahData(ModelDetailBarangRusak model) {
        PreparedStatement st = null;
        try{
            String sql = "INSERT INTO detail_barang_rusak(id_barang_rusak, id_pembelian, id_produk, jumlah_retur, subtotal_retur, alasan) "
             + "SELECT '"+model.getModelBarangRusak().getIdBarangRusak()+"', "
             + "id_pembelian, id_produk, jumlah_retur, subtotal_retur, alasan FROM smt_barang_rusak";
            st = conn.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sumTotal(ModelDetailBarangRusak model) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT SUM(subtotal_retur) FROM smt_barang_rusak";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if(rs.next()){
                model.setSubtotalRetur(rs.getDouble(1));
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
        String sql = "DELETE FROM smt_barang_rusak";
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelDetailBarangRusak> tampilData(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT det.id_barang_rusak, pb.id_pembelian, pd.id_produk, pd.barcode, pd.nama_produk, ktg.nama_kategori, pd.harga, det.jumlah_retur, det.subtotal_retur, det.alasan\n"+
                     "FROM detail_barang_rusak det\n"+
                     "INNER JOIN pembelian pb ON pb.id_pembelian = det.id_pembelian\n"+
                     "INNER JOIN produk pd ON pd.id_produk = det.id_produk\n"+
                     "INNER JOIN kategori ktg ON ktg.id_kategori = pd.id_kategori\n"+
                     "WHERE det.id_barang_rusak = '"+id+"'";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                ModelDetailBarangRusak det = new ModelDetailBarangRusak();
                ModelBarangRusak br = new ModelBarangRusak();
                ModelPembelian pb = new ModelPembelian();
                ModelProduk pd = new ModelProduk();
                ModelKategori ktg = new ModelKategori();

                br.setIdBarangRusak  (rs.getString("id_barang_rusak"));
                pb.setIdPembelian    (rs.getString("id_pembelian"));
                pd.setIdProduk       (rs.getInt("id_produk"));
                pd.setBarcode        (rs.getString("barcode"));
                pd.setNamaProduk     (rs.getString("nama_produk"));
                ktg.setNamaKategori  (rs.getString("nama_kategori"));
                pd.setHarga          (rs.getLong("harga"));
                det.setJumlahRetur   (rs.getInt("jumlah_retur"));
                det.setSubtotalRetur (rs.getLong("subtotal_retur"));
                det.setAlasan        (rs.getString("alasan"));

                pd.setModelKategori(ktg);
                det.setModelBarangRusak(br);
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
    public List<ModelDetailBarangRusak> pencarianData(String id, String kataKunci) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT det.id_barang_rusak, pb.id_pembelian, pd.id_produk, pd.barcode, pd.nama_produk, ktg.nama_kategori, pd.harga, det.jumlah_retur, det.subtotal_retur, det.alasan\n"+
                     "FROM detail_barang_rusak det\n"+
                     "INNER JOIN pembelian pb ON pb.id_pembelian = det.id_pembelian\n"+
                     "INNER JOIN produk pd ON pd.id_produk = det.id_produk\n"+
                     "INNER JOIN kategori ktg ON ktg.id_kategori = pd.id_kategori\n"+
                     "WHERE det.id_barang_rusak = '"+id+"' "
                     + "AND (det.id_barang_rusak LIKE '%"+kataKunci+"%' "
                     + "OR pb.id_pembelian LIKE '%"+kataKunci+"%'"
                     + "OR pd.id_produk LIKE '%"+kataKunci+"%'"
                     + "OR pd.barcode LIKE '%"+kataKunci+"%'"
                     + "OR ktg.nama_kategori LIKE '%"+kataKunci+"%'"
                     + "OR pd.nama_produk LIKE '%"+kataKunci+"%')";
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                    ModelDetailBarangRusak det = new ModelDetailBarangRusak();
                    ModelBarangRusak br = new ModelBarangRusak();
                    ModelPembelian pb = new ModelPembelian();
                    ModelProduk pd = new ModelProduk();
                    ModelKategori ktg = new ModelKategori();

                    br.setIdBarangRusak  (rs.getString("id_barang_rusak"));
                    pb.setIdPembelian    (rs.getString("id_pembelian"));
                    pd.setIdProduk       (rs.getInt("id_produk"));
                    pd.setBarcode        (rs.getString("barcode"));
                    pd.setNamaProduk     (rs.getString("nama_produk"));
                    ktg.setNamaKategori  (rs.getString("nama_kategori"));
                    pd.setHarga          (rs.getLong("harga"));
                    det.setJumlahRetur   (rs.getInt("jumlah_retur"));
                    det.setSubtotalRetur (rs.getLong("subtotal_retur"));
                    det.setAlasan        (rs.getString("alasan"));

                    pd.setModelKategori(ktg);
                    det.setModelBarangRusak(br);
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