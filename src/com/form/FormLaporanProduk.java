package com.form;

import com.dao.ProdukDAO;
import com.dao.ReportDAO;
import com.jdialog.FormInputProduk;
import com.model.ModelProduk;
import com.model.Pagination;
import com.service.ServiceProduk;
import com.service.ServiceReport;
import com.tablemodel.TableModelProduk;
import java.awt.Dimension;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class FormLaporanProduk extends javax.swing.JPanel {

    private final TableModelProduk tblModel = new TableModelProduk();
    private final ServiceProduk servis = new ProdukDAO();
    private ServiceReport servisReport = new ReportDAO();
    private Pagination pagination;
    
    public FormLaporanProduk() {
        initComponents();
        tblData.setModel(tblModel);
        pagination = new Pagination(15);
        loadData();
        
        setTableProperties();
        viewPanel.setVisible(true);
    }
    
    private void setTableProperties(){
        setLebarKolom();
        tblData.setIntercellSpacing(new Dimension(0, 0));
        tblData.setShowGrid(false);
        
        setTableSort();
    }
    
    private void setTableSort(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblModel);
        tblData.setRowSorter(sorter);
        sorter.setComparator(3, Comparator.comparingInt(o-> (Integer) o));
        sorter.setComparator(5, Comparator.comparingDouble(o-> Double.parseDouble(o.toString().replace(",", ""))));
        sorter.setComparator(6, Comparator.comparingInt(o-> (Integer) o));
        sorter.setComparator(7, Comparator.comparingInt(o-> (Integer) o));
    }
    
    private void setLebarKolom(){
        TableColumnModel kolom = tblData.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(50);
        kolom.getColumn(0).setMaxWidth(50);
        kolom.getColumn(0).setMinWidth(50);
        
        TableColumnModel kolom1 = tblData.getColumnModel();
        kolom1.getColumn(1).setPreferredWidth(150);
        kolom1.getColumn(1).setMaxWidth(150);
        kolom1.getColumn(1).setMinWidth(150);
        
        TableColumnModel kolom2 = tblData.getColumnModel();
        kolom2.getColumn(3).setPreferredWidth(50);
        kolom2.getColumn(3).setMaxWidth(50);
        kolom2.getColumn(3).setMinWidth(50);
    
        TableColumnModel kolom3 = tblData.getColumnModel();
        kolom3.getColumn(7).setPreferredWidth(50);
        kolom3.getColumn(7).setMaxWidth(50);
        kolom3.getColumn(7).setMinWidth(50);
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
        jLabel4 = new javax.swing.JLabel();
        txtPencarian = new javax.swing.JTextField();
        pnTbl = new javax.swing.JPanel();
        lbInfoPage = new javax.swing.JLabel();
        btnHalamanSaatIni = new javax.swing.JButton();
        btnLastPage = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lbOfTotalHalaman = new javax.swing.JLabel();
        btnBefore = new javax.swing.JButton();
        btnFirstPage = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1000, 500));

        viewPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setText("LAPORAN");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("/ Laporan Produk");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Laporan produk");

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
                .addGap(17, 17, 17)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        pnBtnLayout.setVerticalGroup(
            pnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBtnLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(btnPrint))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pnTbl.setBackground(new java.awt.Color(242, 234, 246));

        lbInfoPage.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbInfoPage.setForeground(new java.awt.Color(0, 0, 0));
        lbInfoPage.setText("Data 1 - 30 dari Total Data 500");

        btnHalamanSaatIni.setText("1");

        btnLastPage.setText(">>>");
        btnLastPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastPageActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        lbOfTotalHalaman.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lbOfTotalHalaman.setForeground(new java.awt.Color(0, 0, 0));
        lbOfTotalHalaman.setText("of 999");

        btnBefore.setText("<");
        btnBefore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeforeActionPerformed(evt);
            }
        });

        btnFirstPage.setText("<<<");
        btnFirstPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstPageActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout pnTblLayout = new javax.swing.GroupLayout(pnTbl);
        pnTbl.setLayout(pnTblLayout);
        pnTblLayout.setHorizontalGroup(
            pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTblLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnTblLayout.createSequentialGroup()
                        .addComponent(lbInfoPage, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(285, 285, 285)
                        .addComponent(btnFirstPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBefore, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHalamanSaatIni, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbOfTotalHalaman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)
                        .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLastPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnTblLayout.setVerticalGroup(
            pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTblLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbInfoPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnTblLayout.createSequentialGroup()
                        .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHalamanSaatIni)
                            .addComponent(btnLastPage)
                            .addComponent(btnNext)
                            .addComponent(lbOfTotalHalaman, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBefore)
                            .addComponent(btnFirstPage))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPencarianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPencarianKeyReleased
        pencarianData();
    }//GEN-LAST:event_txtPencarianKeyReleased

    private void btnFirstPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstPageActionPerformed
        pagination.halamanPertama();
        loadData();
    }//GEN-LAST:event_btnFirstPageActionPerformed

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

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        servisReport.printLaporanProduk();
    }//GEN-LAST:event_btnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBefore;
    private javax.swing.JButton btnFirstPage;
    private javax.swing.JButton btnHalamanSaatIni;
    private javax.swing.JButton btnLastPage;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbInfoPage;
    private javax.swing.JLabel lbOfTotalHalaman;
    private javax.swing.JPanel pnBtn;
    private javax.swing.JPanel pnTbl;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtPencarian;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables

    private void loadData() { 
        int totalData = servis.getTotalData();
        pagination.setTotalData(totalData);
        int posisiAwal = pagination.getPosisiAwal();
        int dataPerHalaman = pagination.getDataPerHalaman();
        
        List<ModelProduk> list =servis.tampilData(posisiAwal, dataPerHalaman);
        tblModel.setData(list);
        
        lbInfoPage.setText("Data " + (pagination.getPosisiAwal() + 1) + " - " +
                Math.min(pagination.getPosisiAwal() + pagination.getDataPerHalaman(), totalData) 
                + " of Total Data " + totalData);
        btnHalamanSaatIni.setText(String.valueOf(pagination.getHalamanSaatIni()));
        lbOfTotalHalaman.setText("of " + pagination.getTotalHalaman());
    }
    
    private void pencarianData(){
        String keyword = txtPencarian.getText();
        int posisiAwal = pagination.getPosisiAwal();
        int dataPerHalaman = pagination.getDataPerHalaman();
        
        List<ModelProduk> list =servis.pencarianData(keyword, posisiAwal, dataPerHalaman);
        tblModel.setData(list);
    }

    private void tambahData() {
        int row = tblData.getSelectedRow();
        if(row != -1){
            ModelProduk produk = tblModel.getData(row);
            FormInputProduk formInput = new FormInputProduk(null, true, row, produk);
            formInput.setVisible(true);
            loadData();
        }else{
            JOptionPane.showMessageDialog(null, "Pilih dahulu data yang akan diprint");
        }
    }
}
