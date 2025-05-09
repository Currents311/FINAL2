package com.dao;

import com.config.Koneks;
import com.model.ModelKaryawan;
import com.service.ServiceKaryawan;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

public class KaryawanDAO implements ServiceKaryawan{

    private final Connection conn;
    private static final int SALT_ROUNDS = 12;
    
    public KaryawanDAO(){
        conn = Koneks.getConnection();
    }

    @Override
    public void tambahData(ModelKaryawan model) {
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO karyawan (nama_karyawan, telepon, password, role, last_seen)VALUES (?,?,?,?,?)";
            st = conn.prepareStatement(sql);
            st.setString(1, model.getNamaKaryawan());
            st.setString(2, model.getTeleponKaryawan());
            String hashPassword = BCrypt.hashpw(model.getPasswordKaryawan(), BCrypt.gensalt(12));
            st.setString(3, hashPassword);
            st.setString(4, model.getRole());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis())); 

            

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (st != null) st.close(); } catch (SQLException ignored) {}
        }
    }

    @Override
    public void perbaruiData(ModelKaryawan model) {
        PreparedStatement st = null;
        try {
            String sql = "UPDATE karyawan SET nama_karyawan=?, telepon=?, role=?  WHERE id_karyawan=?";
            
            st = conn.prepareStatement(sql);
            st.setString(1, model.getNamaKaryawan());
            st.setString(2, model.getTeleponKaryawan());
            st.setString(3, model.getRole());
            st.setInt(4, model.getIdKaryawan());


            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void hapusData(ModelKaryawan model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM karyawan WHERE id_karyawan=?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, model.getIdKaryawan());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelKaryawan> tampilData(int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT * FROM karyawan ORDER BY id_karyawan ASC LIMIT ? OFFSET ?";
        
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelKaryawan karyawan = new ModelKaryawan();
                
                karyawan.setIdKaryawan(rs.getInt("id_karyawan"));
                karyawan.setNamaKaryawan(rs.getString("nama_karyawan"));
                karyawan.setTeleponKaryawan(rs.getString("telepon"));
                karyawan.setRole(rs.getString("role"));
                karyawan.setLastSeen(rs.getTimestamp("last_seen"));

                list.add(karyawan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelKaryawan> pencarianData(String id, int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT * FROM karyawan WHERE nama_karyawan LIKE '%"+id+"%' "
                + "OR telepon LIKE '%"+id+"%' "        
                + "OR role LIKE '%"+id+"%' "
                + "ORDER BY id_karyawan ASC LIMIT ? OFFSET ?";        
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelKaryawan karyawan = new ModelKaryawan();
                
                karyawan.setIdKaryawan(rs.getInt("id_karyawan"));
                karyawan.setNamaKaryawan(rs.getString("nama_karyawan"));
                karyawan.setTeleponKaryawan(rs.getString("telepon"));
                karyawan.setRole(rs.getString("role"));
                karyawan.setLastSeen(rs.getTimestamp("last_seen"));

                list.add(karyawan);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ModelKaryawan prosesLogin(ModelKaryawan model) {
        PreparedStatement st = null;
        ResultSet rs = null;
        ModelKaryawan modelKar = null;
        String sql = "SELECT * FROM karyawan WHERE nama_karyawan = ?";
        String sqlUpdateLastSeen = "UPDATE karyawan SET last_seen = CURRENT_TIMESTAMP WHERE id_karyawan = ?";
        
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, model.getNamaKaryawan());
            rs = st.executeQuery();
            
            if(rs.next()){
                String hashPassword = rs.getString("password");
                if (BCrypt.checkpw(model.getPasswordKaryawan(), hashPassword)) {
                    modelKar = new ModelKaryawan();
                    modelKar.setIdKaryawan(rs.getInt("id_karyawan"));
                    modelKar.setNamaKaryawan(rs.getString("nama_karyawan"));
                    modelKar.setRole(rs.getString("role"));
                    modelKar.setLastSeen(rs.getTimestamp("last_seen"));
                    
                try (PreparedStatement stUpdate = conn.prepareStatement(sqlUpdateLastSeen)) {
                    stUpdate.setInt(1, modelKar.getIdKaryawan());
                    stUpdate.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (st != null) st.close(); } catch (SQLException ignored) {}
        }
        return modelKar;
    }

    @Override
    public boolean prosesSignUp(ModelKaryawan model) {
        if ("super_admin".equals(model.getRole())) {
            String checkSuperAdminSql = "SELECT COUNT(*) FROM karyawan WHERE role = 'super_admin'";

            try (PreparedStatement checkSuperAdminSt = conn.prepareStatement(checkSuperAdminSql);
                 ResultSet rs = checkSuperAdminSt.executeQuery()) {

                if (rs.next() && rs.getInt(1) > 0) {
                    return false; 
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        String checkUserSql = "SELECT COUNT(*) FROM karyawan WHERE nama_karyawan = ?";
        String insertSql = "INSERT INTO karyawan (nama_karyawan, telepon, password, role, last_seen) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (PreparedStatement checkSt = conn.prepareStatement(checkUserSql)) {
            checkSt.setString(1, model.getNamaKaryawan());
            ResultSet rs = checkSt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return false; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        try (PreparedStatement insertSt = conn.prepareStatement(insertSql)) {
            insertSt.setString(1, model.getNamaKaryawan());
            insertSt.setString(2, model.getTeleponKaryawan());
            String hashPassword = BCrypt.hashpw(model.getPasswordKaryawan(), BCrypt.gensalt(SALT_ROUNDS));
            insertSt.setString(3, hashPassword);
            insertSt.setString(4, model.getRole());

            return insertSt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validasiPasswordLama(String namaKaryawan, String passwordLama) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT password FROM karyawan WHERE nama_karyawan = ?";
        
        try {
            st = conn.prepareStatement(sql);
            st.setString(1,namaKaryawan);
            rs = st.executeQuery();
            if (rs.next()) {
                String storedHashedPassword = rs.getString("password"); 
                return BCrypt.checkpw(passwordLama, storedHashedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false; 
    }

    @Override
    public boolean pergantianPassword(String namaKaryawan, String passwordLama, String passwordBaru) {
        String sqlSelect = "SELECT password FROM karyawan WHERE nama_karyawan = ?";
        String sqlUpdate = "UPDATE karyawan SET password = ? WHERE nama_karyawan = ?";
        
        try (PreparedStatement st = conn.prepareStatement(sqlSelect)) {
            st.setString(1, namaKaryawan);
            try (ResultSet rs = st.executeQuery()) {
                if(rs.next()){ 
                    String hashPassword = rs.getString("password");

                    if (!BCrypt.checkpw(passwordLama, hashPassword)) {
                        return false;
                    }
                    String newHashPassword = BCrypt.hashpw(passwordBaru, BCrypt.gensalt(12));

                    try (PreparedStatement stUpdate = conn.prepareStatement(sqlUpdate)) {
                        stUpdate.setString(1, newHashPassword);
                        stUpdate.setString(2, namaKaryawan);
                        return stUpdate.executeUpdate() > 0;
                    }
                }
            }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    @Override
    public String hashPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return BCrypt.hashpw(password, BCrypt.gensalt(SALT_ROUNDS));    
    }

    @Override
    public boolean checkPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false; 
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    @Override
    public int getSuperAdminCount() {
    String sql = "SELECT COUNT(*) FROM karyawan WHERE role = 'super_admin'";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; 
        }

    @Override
    public int getTotalData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) AS total FROM karyawan";
        
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
}


