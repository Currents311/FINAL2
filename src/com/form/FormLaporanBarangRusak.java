package com.form;

import com.dao.BarangRusakDAO;
import com.dao.ReportDAO;
import com.formdev.flatlaf.FlatClientProperties;
import com.model.ModelKaryawan;
import com.model.ModelBarangRusak;
import com.model.Pagination;
import com.service.ServiceBarangRusak;
import com.service.ServiceReport;
import com.tablemodel.TableModelBarangRusak;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumnModel;
import raven.datetime.DatePicker;
import raven.datetime.event.DateSelectionEvent;
import raven.datetime.event.DateSelectionListener;

public class FormLaporanBarangRusak extends javax.swing.JPanel {

    private DatePicker pcDate = new DatePicker();
    private TableModelBarangRusak tblModelPen = new TableModelBarangRusak();
    private ServiceBarangRusak servis = new BarangRusakDAO();
    private ServiceReport servisReport = new ReportDAO();
    private Pagination pagination;

    private Integer idKaryawan;
    private String role;

    
    private final Map<String, Integer> hashMap;
    
    public FormLaporanBarangRusak(ModelKaryawan modelKar, String role) {
        initComponents();
        pcDate.setEditor(txtDate);
        pcDate.setDateSelectionMode(DatePicker.DateSelectionMode.BETWEEN_DATE_SELECTED);
        pcDate.setSeparator(" to date ");
        pcDate.setUsePanelOption(true);
        pcDate.setDateSelectionAble((LocalDate localDate) -> !localDate.isAfter(localDate.now()));
        pcDate.addDateSelectionListener(new DateSelectionListener() {
            @Override
            public void dateSelected(DateSelectionEvent dse) {
                LocalDate[] dates=pcDate.getSelectedDateRange();
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            }
        });
        tblModelPen = new TableModelBarangRusak(); 
        tblData.setModel(tblModelPen);
        this.idKaryawan = modelKar.getIdKaryawan();
        this.role = role;
        pagination = new Pagination(10);
        
        hashMap = new HashMap<>();
        loadData();
        
        setLebarKolom();
        setLayoutForm();
        init();
        viewPanel.setVisible(true);
    }
    
    private void setLebarKolom(){
        TableColumnModel kolom = tblData.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(50);
        kolom.getColumn(0).setMaxWidth(50);
        kolom.getColumn(0).setMinWidth(50);
        
        TableColumnModel kolom1 = tblData.getColumnModel();
        kolom1.getColumn(2).setPreferredWidth(50);
        kolom1.getColumn(2).setMaxWidth(50);
        kolom1.getColumn(2).setMinWidth(50);
        
        TableColumnModel kolom2 = tblData.getColumnModel();
        kolom2.getColumn(6).setPreferredWidth(50);
        kolom2.getColumn(6).setMaxWidth(50);
        kolom2.getColumn(6).setMinWidth(50);
    }
    
    private void setLayoutForm(){
        txtPencarian.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Pencarian");
    }
    
    private void init(){
        InputMap inputMap = viewPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = viewPanel.getActionMap();
        
        inputMap.put(KeyStroke.getKeyStroke("F1"), "clickButton");
        actionMap.put("clickButton", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnBtn = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnTampil = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPencarian = new javax.swing.JTextField();
        txtDate = new javax.swing.JFormattedTextField();
        pnTbl = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        lbInfoPage = new javax.swing.JLabel();
        btnFirstPage1 = new javax.swing.JButton();
        btnBefore = new javax.swing.JButton();
        btnHalamanSaatIni = new javax.swing.JButton();
        lbOfTotalHalaman = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLastPage = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1000, 500));

        viewPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setText("LAPORAN");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("/ Laporan Barang Rusak");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Laporan barang rusak");

        pnBtn.setBackground(new java.awt.Color(230, 209, 242));

        btnPrint.setBackground(new java.awt.Color(195, 238, 240));
        btnPrint.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 0, 0));
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnTampil.setBackground(new java.awt.Color(242, 234, 246));
        btnTampil.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnTampil.setForeground(new java.awt.Color(0, 0, 0));
        btnTampil.setText("Tampilkan");
        btnTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pilih Periode Laporan");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Search :");

        txtPencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPencarianKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnBtnLayout = new javax.swing.GroupLayout(pnBtn);
        pnBtn.setLayout(pnBtnLayout);
        pnBtnLayout.setHorizontalGroup(
            pnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBtnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBtnLayout.createSequentialGroup()
                        .addComponent(txtDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTampil))
                    .addGroup(pnBtnLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnBtnLayout.setVerticalGroup(
            pnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnBtnLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint)
                    .addComponent(btnTampil)
                    .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnTbl.setBackground(new java.awt.Color(242, 234, 246));

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblData);

        lbInfoPage.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbInfoPage.setForeground(new java.awt.Color(0, 0, 0));
        lbInfoPage.setText("Data 1 - 30 dari Total Data 500");

        btnFirstPage1.setText("<<<");
        btnFirstPage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstPage1ActionPerformed(evt);
            }
        });

        btnBefore.setText("<");
        btnBefore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeforeActionPerformed(evt);
            }
        });

        btnHalamanSaatIni.setText("1");

        lbOfTotalHalaman.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lbOfTotalHalaman.setForeground(new java.awt.Color(0, 0, 0));
        lbOfTotalHalaman.setText("of 999");

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLastPage.setText(">>>");
        btnLastPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastPageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTblLayout = new javax.swing.GroupLayout(pnTbl);
        pnTbl.setLayout(pnTblLayout);
        pnTblLayout.setHorizontalGroup(
            pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTblLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTblLayout.createSequentialGroup()
                        .addComponent(lbInfoPage, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(291, 291, 291)
                        .addComponent(btnFirstPage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBefore, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHalamanSaatIni, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbOfTotalHalaman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)
                        .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLastPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnTblLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        pnTblLayout.setVerticalGroup(
            pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTblLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbInfoPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnTblLayout.createSequentialGroup()
                        .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHalamanSaatIni)
                            .addComponent(btnLastPage)
                            .addComponent(btnNext)
                            .addComponent(lbOfTotalHalaman, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBefore)
                            .addComponent(btnFirstPage1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnTbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(viewPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        viewPanelLayout.setVerticalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnTbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1030, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(viewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 754, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(viewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
    LocalDate[] selectedRange = pcDate.getSelectedDateRange();
    if (selectedRange != null && selectedRange.length == 2) {
        LocalDate from = selectedRange[0];
        LocalDate to = selectedRange[1];

        Date startDate = java.sql.Date.valueOf(from);
        Date endDate = java.sql.Date.valueOf(to);
        
        servisReport.printLaporanBarangRusak(startDate, endDate);
    }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtPencarianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPencarianKeyReleased
        pencarianData();
    }//GEN-LAST:event_txtPencarianKeyReleased

    private void btnFirstPage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstPage1ActionPerformed
        pagination.halamanPertama();
        loadData();
    }//GEN-LAST:event_btnFirstPage1ActionPerformed

    private void btnBeforeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBeforeActionPerformed
        pagination.halamanSebelumnya();
        loadData();
    }//GEN-LAST:event_btnBeforeActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        pagination.halamanBerikutnya();
        loadData();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastPageActionPerformed
        pagination.halamanTerakhir();
        loadData();
    }//GEN-LAST:event_btnLastPageActionPerformed

    private void btnTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilActionPerformed
    LocalDate[] selectedRange = pcDate.getSelectedDateRange();
    if (selectedRange != null && selectedRange.length == 2) {
        LocalDate from = selectedRange[0];
        LocalDate to = selectedRange[1];

        Date startDate = java.sql.Date.valueOf(from);
        Date endDate = java.sql.Date.valueOf(to);

        List<ModelBarangRusak> list = servis.rangeTanggal(startDate, endDate);
        tblModelPen.setData(list);
        pagination.setTotalData(list.size());
        btnPrint.setEnabled(!list.isEmpty());
    } else {
        JOptionPane.showMessageDialog(this, "Silakan pilih periode terlebih dahulu.");
    }
    }//GEN-LAST:event_btnTampilActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBefore;
    private javax.swing.JButton btnFirstPage1;
    private javax.swing.JButton btnHalamanSaatIni;
    private javax.swing.JButton btnLastPage;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnTampil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbInfoPage;
    private javax.swing.JLabel lbOfTotalHalaman;
    private javax.swing.JPanel pnBtn;
    private javax.swing.JPanel pnTbl;
    private javax.swing.JTable tblData;
    private javax.swing.JFormattedTextField txtDate;
    private javax.swing.JTextField txtPencarian;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables

    private void loadData() {  
        btnPrint.setEnabled(false);
        int totalData = servis.getTotalData();
        pagination.setTotalData(totalData);
        
        int posisiAwal = pagination.getPosisiAwal();
        int dataPerHalaman = pagination.getDataPerHalaman();
        List<ModelBarangRusak> list = servis.tampilData(posisiAwal, dataPerHalaman);
        tblModelPen.setData(list);
        
        if (totalData > 0){
            lbInfoPage.setText("Data " + (posisiAwal + 1) + " - " + Math.min(posisiAwal + dataPerHalaman, totalData) + " of Total Data " + totalData);
        } else {
            lbInfoPage.setText("Data 0 of Total Data 0");
        }
        
        btnHalamanSaatIni.setText(String.valueOf(pagination.getHalamanSaatIni()));
        lbOfTotalHalaman.setText("of " + pagination.getTotalHalaman());
    }
        
    private void pencarianData(){
        String keyword = txtPencarian.getText();
        int posisiAwal = pagination.getPosisiAwal();
        int dataPerHalaman = pagination.getDataPerHalaman();
        
        List<ModelBarangRusak> list =servis.pencarianData(keyword, posisiAwal, dataPerHalaman);
        tblModelPen.setData(list);
    }
}
