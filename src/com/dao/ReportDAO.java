package com.dao;

import com.config.Koneks;
import com.service.ServiceReport;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportDAO implements ServiceReport{

    private Connection conn;
    public ReportDAO() {
        conn = Koneks.getConnection();
    }
    
    @Override
    public void printStruk(String id) {
        try {
            String reportPath = "src/com/report/StrukPenjualan.jasper";
            
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("id_penjualan", id);
                
                JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
                JasperViewer viewer = new JasperViewer(print, false);
                viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                viewer.setVisible(true);
        } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void printNota(String id) {
        try {
            String reportPath = "src/com/report/NotaPembelian.jasper";
            
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("id_pembelian", id);
                
                JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
                JasperViewer viewer = new JasperViewer(print, false);
                viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                viewer.setVisible(true);
        } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void printNotaBarangRusak(String id) {
        try {
            String reportPath = "src/com/report/NotaBarangRusak.jasper";
            
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("id_barang_rusak", id);
                
                JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
                JasperViewer viewer = new JasperViewer(print, false);
                viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                viewer.setVisible(true);
        } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e.getMessage());
        }    
    }

    @Override
    public void printLaporanProduk() {
        try {
            String reportPath = "src/com/report/LaporanProduk.jasper";
            
                HashMap<String, Object> parameters = new HashMap<>();
                
                JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
                JasperViewer viewer = new JasperViewer(print, false);
                viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                viewer.setVisible(true);
        } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void printLaporanPenjualan(Date startDate, Date endDate) {
        try {
            String reportPath = "src/com/report/LaporanPenjualan.jasper";
            
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("startDate", startDate);
                parameters.put("endDate", endDate);
                
                JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
                JasperViewer viewer = new JasperViewer(print, false);
                viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                viewer.setVisible(true);
        } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void printLaporanPembelian(Date startDate, Date endDate) {
        try {
            String reportPath = "src/com/report/LaporanPembelian.jasper";
            
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("startDate", startDate);
                parameters.put("endDate", endDate);
                
                JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
                JasperViewer viewer = new JasperViewer(print, false);
                viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                viewer.setVisible(true);
        } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    public void printLaporanBarangRusak(Date startDate, Date endDate) {
        try {
            String reportPath = "src/com/report/LaporanBarangRusak.jasper";
            
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("startDate", startDate);
                parameters.put("endDate", endDate);
                
                JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, conn);
                JasperViewer viewer = new JasperViewer(print, false);
                viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
                viewer.setVisible(true);
        } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
}
