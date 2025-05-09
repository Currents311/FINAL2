package com.dao;

import com.config.Koneks;
import com.model.ModelKategori;
import com.service.ServiceKategori;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KategoriDAO implements ServiceKategori{

    private final Connection conn;
    
    public KategoriDAO(){
        conn = Koneks.getConnection();
    }

    @Override
    public void tambahData(ModelKategori model) {
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO kategori(nama_kategori)VALUES (?)";
            st = conn.prepareStatement(sql);
            st.setString(1, model.getNamaKategori());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void perbaruiData(ModelKategori model) {
        PreparedStatement st = null;
        try {
            String sql = "UPDATE kategori SET nama_kategori=? WHERE id_kategori=?";
            
            st = conn.prepareStatement(sql);
            st.setString(1, model.getNamaKategori());
            st.setInt(2, model.getIdKategori());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void hapusData(ModelKategori model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM kategori WHERE id_kategori=?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, model.getIdKategori());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelKategori> tampilData(int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT * FROM kategori ORDER BY id_kategori ASC LIMIT ? OFFSET ?";
        
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelKategori kategori = new ModelKategori();
                
                kategori.setIdKategori(rs.getInt("id_kategori"));
                kategori.setNamaKategori(rs.getString("nama_kategori"));
                list.add(kategori);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelKategori> pencarianData(String id, int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT * FROM kategori WHERE nama_kategori LIKE '%"+id+"%' "
                + "ORDER BY id_kategori ASC LIMIT ? OFFSET ?";
        
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelKategori kategori = new ModelKategori();
                
                kategori.setIdKategori(rs.getInt("id_kategori"));
                kategori.setNamaKategori (rs.getString("nama_kategori"));
                
                list.add(kategori);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean validasiNamaKategori(ModelKategori model) {
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean valid = false;
        
        String sql = "SELECT nama_kategori FROM kategori WHERE nama_kategori LIKE BINARY ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, model.getNamaKategori());
            rs = st.executeQuery();
            
            if(rs.next()){
                valid= false;
            }else {
                valid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (rs != null){
                    rs.close();
                }
                if (st != null){
                    st.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return valid;
    }

    @Override
    public List<ModelKategori> ambilKategori() {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT id_kategori, nama_kategori FROM kategori";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelKategori kategori = new ModelKategori();
                kategori.setIdKategori(rs.getInt("id_kategori"));
                kategori.setNamaKategori(rs.getString("nama_kategori"));
                list.add(kategori);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String ambilKategoriID(int id) {
        String namaKategori = null;
        PreparedStatement st = null;
        ResultSet rs= null;
        String sql = "SELECT nama_kategori FROM kategori WHERE id_kategori=? ";
        
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                namaKategori = rs.getString("nama_kategori");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return namaKategori;
    }

    @Override
    public int getTotalData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) AS total FROM kategori";
        
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
        public String generateBarcodeByKategori(int id) {
            Map<Integer, String> prefixMap = new HashMap<>();
            prefixMap.put(1, "ALQ");  
            prefixMap.put(2, "KTB"); 
            prefixMap.put(3, "KRM"); 
            prefixMap.put(4, "SWK"); 

            String prefix = prefixMap.getOrDefault(id, "XX");

            String today = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.BASIC_ISO_DATE);

            PreparedStatement st = null;
            ResultSet rs = null;
            String sql = "SELECT COUNT(*) FROM produk WHERE id_kategori = ? AND DATE(update_at) = CURDATE()";

            try {
                st = conn.prepareStatement(sql);
                st.setInt(1, id); 
                rs = st.executeQuery();

                if (rs.next()) {
                    int count = rs.getInt(1);
                    return prefix + today + String.format("%03d", count + 1); 
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
            return prefix + today + "001"; 
        }
}
