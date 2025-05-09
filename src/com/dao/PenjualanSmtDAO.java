package com.dao;

import com.config.Koneks;
import com.model.ModelDetailPenjualan;
import com.model.ModelProduk;
import com.model.ModelPenjualanSmt;
import com.service.ServicePenjualanSmt;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class PenjualanSmtDAO implements ServicePenjualanSmt{

    private final Connection conn;
    
    public PenjualanSmtDAO(){
        conn = Koneks.getConnection();
    }

    @Override
    public void tambahData(ModelPenjualanSmt model) {
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO smt_penjualan(id_produk, barcode, nama_produk, harga, stok, jumlah, subtotal) VALUES (?,?,?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            
            st.setInt       (1, model.getModelProduk().getIdProduk());
            st.setString    (2, model.getModelProduk().getBarcode());
            st.setString    (3, model.getModelProduk().getNamaProduk());
            st.setDouble    (4, model.getModelProduk().getHarga());
            st.setInt       (5, model.getModelProduk().getStok());
            st.setInt       (6, model.getModelDetPen().getJumlah());
            st.setDouble    (7, model.getModelDetPen().getSubTotal());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void perbaruiData(ModelPenjualanSmt model) {
        PreparedStatement st = null;
        try {
            String sql = "UPDATE smt_penjualan SET barcode=?, nama_produk =?, harga=?, stok=?, jumlah=?, subtotal=? WHERE id_produk=?";
            
            st = conn.prepareStatement(sql);
            
            st.setString(1, model.getModelProduk().getBarcode());
            st.setString(2, model.getModelProduk().getNamaProduk());
            st.setDouble(3, model.getModelProduk().getHarga());
            st.setInt(4, model.getModelProduk().getStok());
            st.setInt(5, model.getModelDetPen().getJumlah());
            st.setDouble(6, model.getModelDetPen().getSubTotal());
            st.setInt(7, model.getModelProduk().getIdProduk());
            
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(ModelPenjualanSmt model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM smt_penjualan WHERE id_produk=? AND barcode=? AND nama_produk=? AND harga=? AND stok=? AND jumlah=? AND subtotal=?";
        try {
            st = conn.prepareStatement(sql);
            
            st.setInt       (1, model.getModelProduk().getIdProduk());
            st.setString    (2, model.getModelProduk().getBarcode());
            st.setString    (3, model.getModelProduk().getNamaProduk());
            st.setDouble    (4, model.getModelProduk().getHarga());
            st.setInt       (5, model.getModelProduk().getStok());
            st.setInt       (6, model.getModelDetPen().getJumlah());
            st.setDouble    (7, model.getModelDetPen().getSubTotal());
            
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelPenjualanSmt> tampilData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT * FROM smt_penjualan";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelPenjualanSmt smt = new ModelPenjualanSmt();
                ModelProduk pd = new ModelProduk();
                ModelDetailPenjualan det = new ModelDetailPenjualan();
                
                pd.setIdProduk(rs.getInt("id_produk"));
                pd.setBarcode(rs.getString("barcode"));
                pd.setNamaProduk(rs.getString("nama_produk"));
                pd.setHarga(rs.getLong("harga"));
                pd.setStok(rs.getInt("stok"));
                
                det.setJumlah(rs.getInt("jumlah"));
                det.setSubTotal(rs.getLong("subtotal"));
                
                smt.setModelProduk(pd);
                smt.setModelDetPen(det);
                
                list.add(smt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
