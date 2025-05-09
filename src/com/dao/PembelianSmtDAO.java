package com.dao;

import com.config.Koneks;
import com.model.ModelDetailPembelian;
import com.model.ModelProduk;
import com.model.ModelPembelianSmt;
import com.service.ServicePembelianSmt;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class PembelianSmtDAO implements ServicePembelianSmt{

    private final Connection conn;
    
    public PembelianSmtDAO(){
        conn = Koneks.getConnection();
    }

    @Override
    public void tambahData(ModelPembelianSmt model) {
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO smt_pembelian(id_produk, barcode, nama_produk, harga, stok, jumlah, subtotal) VALUES (?,?,?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            
            st.setInt       (1, model.getModelProduk().getIdProduk());
            st.setString    (2, model.getModelProduk().getBarcode());
            st.setString    (3, model.getModelProduk().getNamaProduk());
            st.setDouble    (4, model.getModelProduk().getHarga());
            st.setInt       (5, model.getModelProduk().getStok());
            st.setInt       (6, model.getModelDetPem().getJumlah());
            st.setDouble    (7, model.getModelDetPem().getSubTotal());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void perbaruiData(ModelPembelianSmt model) {
        PreparedStatement st = null;
        try {
            String sql = "UPDATE smt_pembelian SET barcode=?, nama_produk =?, harga=?, stok=?, jumlah=?, subtotal=? WHERE id_produk=?";
            
            st = conn.prepareStatement(sql);
            
            st.setString(1, model.getModelProduk().getBarcode());
            st.setString(2, model.getModelProduk().getNamaProduk());
            st.setDouble(3, model.getModelProduk().getHarga());
            st.setInt(4, model.getModelProduk().getStok());
            st.setInt(5, model.getModelDetPem().getJumlah());
            st.setDouble(6, model.getModelDetPem().getSubTotal());
            st.setInt(7, model.getModelProduk().getIdProduk());
            
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(ModelPembelianSmt model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM smt_pembelian WHERE id_produk=? AND barcode=? AND nama_produk=? AND harga=? AND stok=? AND jumlah=? AND subtotal=?";
        try {
            st = conn.prepareStatement(sql);
            
            st.setInt       (1, model.getModelProduk().getIdProduk());
            st.setString    (2, model.getModelProduk().getBarcode());
            st.setString    (3, model.getModelProduk().getNamaProduk());
            st.setDouble    (4, model.getModelProduk().getHarga());
            st.setInt       (5, model.getModelProduk().getStok());
            st.setInt       (6, model.getModelDetPem().getJumlah());
            st.setDouble    (7, model.getModelDetPem().getSubTotal());
            
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelPembelianSmt> tampilData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT * FROM smt_pembelian";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelPembelianSmt smt = new ModelPembelianSmt();
                ModelProduk pd = new ModelProduk();
                ModelDetailPembelian det = new ModelDetailPembelian();
                
                pd.setIdProduk(rs.getInt("id_produk"));
                pd.setBarcode(rs.getString("barcode"));
                pd.setNamaProduk(rs.getString("nama_produk"));
                pd.setHarga(rs.getLong("harga"));
                pd.setStok(rs.getInt("stok"));
                
                det.setJumlah(rs.getInt("jumlah"));
                det.setSubTotal(rs.getLong("subtotal"));
                
                smt.setModelProduk(pd);
                smt.setModelDetPem(det);
                
                list.add(smt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
