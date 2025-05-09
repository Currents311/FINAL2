package com.form;

import com.dao.ProdukDAO;
import com.jdialog.FormGenerateBarcode;
import com.jdialog.FormInputProduk;
import com.model.ModelProduk;
import com.model.Pagination;
import com.service.ServiceProduk;
import com.tablemodel.TableModelProduk;
import java.awt.Dimension;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class FormProduk extends javax.swing.JPanel {

    private final TableModelProduk tblModel = new TableModelProduk();
    private final ServiceProduk servis = new ProdukDAO();
    private Pagination pagination;
    
    public FormProduk() {
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
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        pnTbl = new javax.swing.JPanel();
        txtPencarian = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        txtGenerate = new javax.swing.JButton();
        lbInfoPage = new javax.swing.JLabel();
        btnHalamanSaatIni = new javax.swing.JButton();
        btnLastPage = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lbOfTotalHalaman = new javax.swing.JLabel();
        btnBefore = new javax.swing.JButton();
        btnFirstPage = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1000, 500));

        viewPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setText("MASTER");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("/ Data Produk");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Data produk");

        pnBtn.setBackground(new java.awt.Color(230, 209, 242));

        btnTambah.setBackground(new java.awt.Color(195, 238, 240));
        btnTambah.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(0, 0, 0));
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(250, 235, 178));
        btnEdit.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(0, 0, 0));
        btnEdit.setText("Perbarui");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(214, 135, 135));
        btnHapus.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(0, 0, 0));
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnBtnLayout = new javax.swing.GroupLayout(pnBtn);
        pnBtn.setLayout(pnBtnLayout);
        pnBtnLayout.setHorizontalGroup(
            pnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBtnLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnTambah)
                .addGap(18, 18, 18)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(btnHapus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnBtnLayout.setVerticalGroup(
            pnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBtnLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pnTbl.setBackground(new java.awt.Color(242, 234, 246));

        txtPencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPencarianKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Search :");

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

        txtGenerate.setBackground(new java.awt.Color(230, 209, 242));
        txtGenerate.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txtGenerate.setForeground(new java.awt.Color(0, 0, 0));
        txtGenerate.setText("Cetak Barcode");
        txtGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGenerateActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout pnTblLayout = new javax.swing.GroupLayout(pnTbl);
        pnTbl.setLayout(pnTblLayout);
        pnTblLayout.setHorizontalGroup(
            pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTblLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTblLayout.createSequentialGroup()
                        .addComponent(txtGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(468, 468, 468)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPencarian))
                    .addComponent(jScrollPane2)
                    .addGroup(pnTblLayout.createSequentialGroup()
                        .addComponent(lbInfoPage, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(285, 285, 285)
                        .addComponent(btnFirstPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBefore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHalamanSaatIni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbOfTotalHalaman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)
                        .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLastPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
        );
        pnTblLayout.setVerticalGroup(
            pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTblLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(txtGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(viewPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
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
                .addContainerGap(14, Short.MAX_VALUE))
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

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        tambahData();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        perbaruiData();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        hapusData();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txtPencarianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPencarianKeyReleased
        pencarianData();
    }//GEN-LAST:event_txtPencarianKeyReleased

    private void txtGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGenerateActionPerformed
        bikinBarcode();
    }//GEN-LAST:event_txtGenerateActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBefore;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFirstPage;
    private javax.swing.JButton btnHalamanSaatIni;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnLastPage;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnTambah;
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
    private javax.swing.JButton txtGenerate;
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
        FormInputProduk formInput = new FormInputProduk(null, true, 1, null);
        formInput.setVisible(true);
        loadData();
    }

    private void perbaruiData() {
        int row = tblData.getSelectedRow();
        if(row != -1){
            ModelProduk produk = tblModel.getData(row);
            FormInputProduk formInput = new FormInputProduk(null, true, row, produk);
            formInput.setVisible(true);
            loadData();
        }else{
            JOptionPane.showMessageDialog(null, "Pilih dahulu data yang akan diperbarui");
        }
    }

    private void hapusData() {
        int row = tblData.getSelectedRow();
        if(row != -1){
            ModelProduk produk = tblModel.getData(row);
            if(JOptionPane.showConfirmDialog(null, "Yakin data akan dihapus?", 
                    "Konfirmasi", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
            {
                servis.hapusData(produk);
                tblModel.deleteData(row);
                loadData();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Pilih dahulu data yang akan dihapus");
        }
    }

    private void bikinBarcode() {
        int row = tblData.getSelectedRow();
        if(row != -1){
            ModelProduk produk = tblModel.getData(row);
            FormGenerateBarcode formGenerate = new FormGenerateBarcode(null, true, row, produk);
            formGenerate.setVisible(true);
            loadData();
        }else{
            JOptionPane.showMessageDialog(null, "Pilih dahulu data yang akan dicetak barcode");
        }
    }
}
