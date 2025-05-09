package com.dao;

import com.config.Koneks;
import com.service.ServiceDashboard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAO implements ServiceDashboard{

    private Connection conn;
    public DashboardDAO() {
        conn = Koneks.getConnection();
    }

    @Override
    public int tampilTotalStock() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM produk";

        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1); 
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return count;
    }
    
    @Override
    public int tampilTotalPenjualan() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM penjualan";

        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1); 
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return count;
    }

    @Override
    public int tampilTotalPembelian() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM pembelian";

        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1); 
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return count;    
    }
    
}