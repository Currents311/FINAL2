package com.dao;

import com.config.Koneks;
import com.model.ModelPelanggan;
import com.service.ServicePelanggan;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class PelangganDAO implements ServicePelanggan{

    private final Connection conn;
    
    public PelangganDAO(){
        conn = Koneks.getConnection();
    }

    @Override
    public void tambahData(ModelPelanggan model) {
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO pelanggan (nama_pelanggan, telepon, alamat)VALUES (?,?,?)";
            st = conn.prepareStatement(sql);
            st.setString(1, model.getNamaPelanggan());
            st.setString(2, model.getTeleponPelanggan());
            st.setString(3, model.getAlamatPelanggan());

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void perbaruiData(ModelPelanggan model) {
        PreparedStatement st = null;
        try {
            String sql = "UPDATE pelanggan SET nama_pelanggan=?, telepon=?, alamat=? WHERE id_pelanggan=?";
            
            st = conn.prepareStatement(sql);
            st.setString(1, model.getNamaPelanggan());
            st.setString(2, model.getTeleponPelanggan());
            st.setString(3, model.getAlamatPelanggan());
            st.setInt(4, model.getIdPelanggan());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void hapusData(ModelPelanggan model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM pelanggan WHERE id_pelanggan=?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, model.getIdPelanggan());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelPelanggan> tampilData(int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ModelPelanggan> list = new ArrayList<>();

        String sql = 
            "SELECT p.*, " +
            "       CASE " +
            "           WHEN p.nama_pelanggan = 'non-member' THEN NULL " +            
            "           WHEN COALESCE(SUM(pj.total_harga), 0) > 500000 THEN 'Gold' " +
            "           WHEN COALESCE(SUM(pj.total_harga), 0) >= 100000 THEN 'Silver' " +
            "           ELSE 'Bronze' " +
            "       END AS level_member " +
            "FROM pelanggan p " +
            "LEFT JOIN penjualan pj ON p.id_pelanggan = pj.id_pelanggan " +
            "GROUP BY p.id_pelanggan " +
            "ORDER BY p.id_pelanggan ASC " +
            "LIMIT ? OFFSET ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();

            while (rs.next()) {
                ModelPelanggan pelanggan = new ModelPelanggan();
                pelanggan.setIdPelanggan(rs.getInt("id_pelanggan"));
                pelanggan.setNamaPelanggan(rs.getString("nama_pelanggan"));
                pelanggan.setTeleponPelanggan(rs.getString("telepon"));
                pelanggan.setAlamatPelanggan(rs.getString("alamat"));
                pelanggan.setLevelMember(rs.getString("level_member"));

                list.add(pelanggan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    

    @Override
    public List<ModelPelanggan> pencarianData(String id, int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT * FROM pelanggan WHERE nama_pelanggan LIKE '%"+id+"%' "
                + "OR telepon LIKE '%"+id+"%' "
                + "OR alamat LIKE '%"+id+"%' "
                + "ORDER BY id_pelanggan ASC LIMIT ? OFFSET ?";
        
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelPelanggan pelanggan = new ModelPelanggan();
                
                pelanggan.setIdPelanggan(rs.getInt("id_pelanggan"));
                pelanggan.setNamaPelanggan (rs.getString("nama_pelanggan"));
                pelanggan.setTeleponPelanggan(rs.getString("telepon"));
                pelanggan.setAlamatPelanggan(rs.getString("alamat"));
                
                list.add(pelanggan);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelPelanggan> ambilPelanggan() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT id_pelanggan, nama_pelanggan FROM pelanggan";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelPelanggan model = new ModelPelanggan();
                model.setIdPelanggan(rs.getInt("id_pelanggan"));
                model.setNamaPelanggan(rs.getString("nama_pelanggan"));
                
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getTotalData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) AS total FROM pelanggan";
        
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
    public void updateLevelMember() {
        PreparedStatement st = null;
        String sql = 
            "UPDATE pelanggan SET level_member = " +
            "CASE " +
            "    WHEN ( " +
            "        SELECT COALESCE(SUM(total_harga), 0) " +
            "        FROM penjualan " +
            "        WHERE penjualan.id_pelanggan = pelanggan.id_pelanggan " +
            "    ) > 500000 THEN 'Gold' " +
            "    WHEN ( " +
            "        SELECT COALESCE(SUM(total_harga), 0) " +
            "        FROM penjualan " +
            "        WHERE penjualan.id_pelanggan = pelanggan.id_pelanggan " +
            "    ) >= 100000 THEN 'Silver' " +
            "    ELSE 'Bronze' " +
            "END " +
            "WHERE nama_pelanggan <> 'non-member'"; 
        try {
            st = conn.prepareStatement(sql);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLevelMember(String idPelanggan) {
        String level = null;
        try {
            String sql = "SELECT level_member FROM pelanggan WHERE id_pelanggan = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idPelanggan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                level = rs.getString("level_member");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
    return level;
    }
} 

