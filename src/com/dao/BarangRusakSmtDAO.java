package com.dao;

import com.config.Koneks;
import com.model.ModelDetailBarangRusak;
import com.model.ModelProduk;
import com.model.ModelBarangRusakSmt;
import com.model.ModelPembelian;
import com.service.ServiceBarangRusakSmt;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class BarangRusakSmtDAO implements ServiceBarangRusakSmt{

    private final Connection conn;
    
    public BarangRusakSmtDAO(){
        conn = Koneks.getConnection();
    }

    @Override
    public void tambahData(ModelBarangRusakSmt model) {
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO smt_barang_rusak(id_pembelian, tanggal, id_produk, barcode, nama_produk, harga, jumlah_retur, subtotal_retur, alasan) VALUES (?,?,?,?,?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            
            st.setString    (1, model.getModelPembelian().getIdPembelian());
            st.setString    (2, model.getModelPembelian().getTanggal());
            st.setInt       (3, model.getModelProduk().getIdProduk());
            st.setString    (4, model.getModelProduk().getBarcode());
            st.setString    (5, model.getModelProduk().getNamaProduk());
            st.setDouble    (6, model.getModelProduk().getHarga());
            st.setInt       (7, model.getModelDetailBarangRusak().getJumlahRetur());
            st.setDouble    (8, model.getModelDetailBarangRusak().getSubtotalRetur());
            st.setString    (9, model.getModelDetailBarangRusak().getAlasan());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void perbaruiData(ModelBarangRusakSmt model) {
        PreparedStatement st = null;
        try {
            String sql = "UPDATE smt_barang_rusak SET jumlah_retur=?, subtotal_retur=?, alasan=? WHERE id_pembelian=?";
            
            st = conn.prepareStatement(sql);
            
            st.setInt(1, model.getModelDetailBarangRusak().getJumlahRetur());
            st.setDouble(2, model.getModelDetailBarangRusak().getSubtotalRetur());
            st.setString(3, model.getModelDetailBarangRusak().getAlasan());
            st.setString(4, model.getModelPembelian().getIdPembelian());
            
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(ModelBarangRusakSmt model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM smt_barang_rusak "
                + "WHERE id_pembelian=? AND tanggal=? AND id_produk=? AND barcode=? AND nama_produk=? AND harga=? "
                + "AND jumlah_retur=? AND subtotal_retur=? AND alasan=?";
        try {
            st = conn.prepareStatement(sql);
            
            st.setString    (1, model.getModelPembelian().getIdPembelian());
            st.setString    (2, model.getModelPembelian().getTanggal());
            st.setInt       (3, model.getModelProduk().getIdProduk());
            st.setString    (4, model.getModelProduk().getBarcode());
            st.setString    (5, model.getModelProduk().getNamaProduk());
            st.setDouble    (6, model.getModelProduk().getHarga());
            st.setInt       (7, model.getModelDetailBarangRusak().getJumlahRetur());
            st.setDouble    (8, model.getModelDetailBarangRusak().getSubtotalRetur());
            st.setString    (9, model.getModelDetailBarangRusak().getAlasan());
            
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelBarangRusakSmt> tampilData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT * FROM smt_barang_rusak";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelBarangRusakSmt smt = new ModelBarangRusakSmt();
                ModelProduk pd = new ModelProduk();
                ModelPembelian pb = new ModelPembelian();
                ModelDetailBarangRusak det = new ModelDetailBarangRusak();
                
                pb.setIdPembelian(rs.getString("id_pembelian"));
                pb.setTanggal(rs.getString("tanggal"));
                pd.setIdProduk(rs.getInt("id_produk"));
                pd.setBarcode(rs.getString("barcode"));
                pd.setNamaProduk(rs.getString("nama_produk"));
                pd.setHarga(rs.getLong("harga"));
                
                det.setJumlahRetur(rs.getInt("jumlah_retur"));
                det.setSubtotalRetur(rs.getLong("subtotal_retur"));
                det.setAlasan(rs.getString("alasan"));
                
                smt.setModelPembelian(pb);
                smt.setModelProduk(pd);
                smt.setModelDetailBarangRusak(det);
                
                list.add(smt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
