package com.jdialog;

import com.form.*;
import com.dao.KategoriDAO;
import com.dao.ProdukDAO;
import com.dao.SupplierDAO;
import com.formdev.flatlaf.FlatClientProperties;
import com.model.ModelKategori;
import com.model.ModelProduk;
import com.model.ModelSupplier;
import com.model.Pagination;
import com.service.ServiceKategori;
import com.service.ServiceProduk;
import com.service.ServiceSupplier;
import com.tablemodel.TableModelProduk;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FormInputProduk extends javax.swing.JDialog {

    private TableModelProduk tblModel = new TableModelProduk();
    private ServiceProduk servis = new ProdukDAO();
    private ModelProduk produk;
    private Pagination pagination;
    private int idProduk;
    private int row;
    
    private ServiceKategori servis_ktg = new KategoriDAO();
    private ServiceSupplier servis_sup = new SupplierDAO();
    private int idKategori;
    private int idSupplier;
    private final Map<String, Integer> kategoriMap = new HashMap<>(); 


    public FormInputProduk(java.awt.Frame parent, boolean modal, int row, ModelProduk produk) {
        super(parent, modal);
        this.produk = produk;
        this.row = row;
        initComponents();
        txtBarcode.requestFocus();
        txtHarga.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != ',') {
                    e.consume(); 
                }
            }
        });
        
        txtStok.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); 
                }
            }
        });

        pagination = new Pagination(10);
        if(produk != null){
            dataTable();
        }
        loadData();
        setLayoutForm();
    }
    
    private void setLayoutForm(){
        txtNama.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " Masukkan nama produk");
        txtHarga.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " Masukkan harga satuan");
        txtStok.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " Masukkan jumlah stok");
        txtBarcode.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " Masukkan nomor barcode");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbxKategori = new javax.swing.JComboBox<>();
        txtHarga = new javax.swing.JTextField();
        cbxSupplier = new javax.swing.JComboBox<>();
        txtBarcode = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(230, 209, 242));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Master > Data produk > Tambah");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tambah Data Produk");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel1)
                .addContainerGap(67, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jSeparator1.setBackground(new java.awt.Color(128, 128, 128));
        jSeparator1.setForeground(new java.awt.Color(128, 128, 128));

        btnSimpan.setBackground(new java.awt.Color(195, 238, 240));
        btnSimpan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setBackground(new java.awt.Color(214, 135, 135));
        btnBatal.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(0, 0, 0));
        btnBatal.setText("BATAL");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Nama produk");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Kategori");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Harga");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Stok");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Supplier");

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Barcode");

        cbxKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxKategoriActionPerformed(evt);
            }
        });

        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });
        txtHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHargaKeyReleased(evt);
            }
        });

        cbxSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Supplier", "1", "2", "3", "4", "5", "6" }));
        cbxSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSupplierActionPerformed(evt);
            }
        });

        txtStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxKategori, 0, 249, Short.MAX_VALUE)
                            .addComponent(txtNama)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(btnSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatal)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtHarga))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtBarcode))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtStok, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbxSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtStok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if(btnSimpan.getText().equals("SIMPAN")){
            simpanData();
        } else if (btnSimpan.getText().equals("PERBARUI")){
            perbaruiData();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        if(btnSimpan.getText().equals("SIMPAN")){
            resetForm();
        } else if (btnSimpan.getText().equals("PERBARUI")){
            dispose();
        }
    }//GEN-LAST:event_btnBatalActionPerformed

    private void cbxKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxKategoriActionPerformed

    private void cbxSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSupplierActionPerformed

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void txtStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStokActionPerformed

    private void txtHargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaKeyReleased
        try {
            String hargaBeliRp = txtHarga.getText().replaceAll("[^\\d]", "");
            if(!hargaBeliRp.isEmpty()){
                double hargaBeli = Double.parseDouble(hargaBeliRp);
                DecimalFormat df = new DecimalFormat("#,###,###");
                txtHarga.setText(df.format(hargaBeli));
            }
        } catch (NumberFormatException e) {
            txtHarga.setText("");
        }
    }//GEN-LAST:event_txtHargaKeyReleased

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormInputProduk dialog = new FormInputProduk(new javax.swing.JFrame(), true, 1, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cbxKategori;
    private javax.swing.JComboBox<String> cbxSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables

    private void resetForm() {
        txtNama.setText("");
        cbxKategori.setSelectedIndex(0);
        txtHarga.setText("");
        txtStok.setText("");
        cbxSupplier.setSelectedIndex(0);
        txtBarcode.setText("");

    }
    
    private void loadData() {
        ambilKategori();
        ambilSupplier();
        List<ModelProduk> list =servis.tampilData(pagination.getPosisiAwal(), pagination.getDataPerHalaman());
        tblModel.setData(list);
    }
    
    private void ambilKategori(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Pilih Kategori");
        
        List<ModelKategori> list = servis_ktg.ambilKategori();
        for(ModelKategori kategori : list){
            model.addElement(kategori.getNamaKategori());
            kategoriMap.put(kategori.getNamaKategori(), kategori.getIdKategori());
            
        }
            cbxKategori.setModel(model);
            cbxKategori.addActionListener(e-> {
                String namaKategori = cbxKategori.getSelectedItem().toString();
                if(!"Pilih Kategori".equals(namaKategori)){
                    idKategori = kategoriMap.get(namaKategori);
                    String generatedBarcode = servis_ktg.generateBarcodeByKategori(idKategori);
                    txtBarcode.setText(generatedBarcode);
                } else {
                    txtBarcode.setText(""); 
                    idKategori = 0;
                }             
            });
            
            
    }

    private void ambilSupplier(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Pilih Supplier");
        
        List<ModelSupplier> list = servis_sup.ambilSupplier();
        for(ModelSupplier supplier : list){
            model.addElement(supplier.getNamaSupplier());
            kategoriMap.put(supplier.getNamaSupplier(), supplier.getIdSupplier());
            
        }
            cbxSupplier.setModel(model);
            cbxSupplier.addActionListener(e-> {
                String namaSupplier = cbxSupplier.getSelectedItem().toString();
                if(!"Pilih Supplier".equals(namaSupplier)){
                    idSupplier = kategoriMap.get(namaSupplier);
                }               
            });
    }    
    
    private boolean barcodeExists(String barcode) {
        List<ModelProduk> list = servis.tampilData(pagination.getPosisiAwal(), pagination.getDataPerHalaman());
        for (ModelProduk produk : list) {
            if (produk.getBarcode().equals(barcode)) {
                return true;
            }
        }
        return false;
    }

    private boolean validasiInput(){
        boolean valid = false;
        if (txtNama.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nama Produk tidak boleh kosong");
        }else if (cbxKategori.getSelectedItem().equals("Pilih Kategori")){
            JOptionPane.showMessageDialog(null, "Harap pilih kategori");
        }else if (txtHarga.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Harga tidak boleh kosong");
        }else if (txtStok.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Stok tidak boleh kosong");    
        }else if (cbxSupplier.getSelectedItem().equals("Pilih Supplier")){
            JOptionPane.showMessageDialog(null, "Harap pilih supplier");
        }else if (txtBarcode.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Barcode tidak boleh kosong");
            } else {
                valid = true;
            }
            return valid;
        }
    
    private void simpanData(){
        if(validasiInput()== true){
            String namaProduk       = txtNama.getText();
            Long harga              = Long.parseLong(txtHarga.getText().replace(",", ""));
            int stok                = Integer.parseInt(txtStok.getText());
            String barcode          = txtBarcode.getText();
        
            ModelProduk produk = new ModelProduk();
            ModelKategori kategori = new ModelKategori();
            ModelSupplier supplier = new ModelSupplier();

            produk.setNamaProduk(namaProduk);
            kategori.setIdKategori(idKategori);
            produk.setHarga(harga);
            produk.setStok(stok);
            supplier.setIdSupplier(idSupplier);
            produk.setBarcode(barcode);

            produk.setModelKategori(kategori);
            produk.setModelSupplier(supplier);
            
            servis.tambahData(produk);
            tblModel.insertData(produk);
            resetForm();
            
        }
    }
    
    private void dataTable() {
        btnSimpan.setText("PERBARUI");
        
        idProduk = produk.getIdProduk();
        
        txtNama.setText(produk.getNamaProduk());
        
        DecimalFormat df = new DecimalFormat("#,##0.##");
        String hargaFormat = df.format(produk.getHarga());
        txtHarga.setText(hargaFormat.replace("", ""));
        
        txtStok.setText(String.valueOf(produk.getStok()));
        txtBarcode.setText(produk.getBarcode());
        
        idKategori = produk.getModelKategori().getIdKategori();
        ambilKategoriID(idKategori);
        idSupplier = produk.getModelSupplier().getIdSupplier();
        ambilSupplierID(idSupplier);

        jLabel1.setText("Master > Data produk > Perbarui");
        jLabel2.setText("Perbarui Data Produk");
    }
    
    private void ambilKategoriID(int id){
        String namaKategori = servis_ktg.ambilKategoriID(id);
        SwingUtilities.invokeLater(()->{
            cbxKategori.setSelectedItem(namaKategori);
        });
    }
    
    private void ambilSupplierID(int id){
        String namaSupplier = servis_sup.ambilSupplierID(id);
        SwingUtilities.invokeLater(()->{
            cbxSupplier.setSelectedItem(namaSupplier);
        });
    }
    
    private void perbaruiData(){
        if(validasiInput()== true){
            String namaProduk = txtNama.getText();
            Long harga = Long.parseLong(txtHarga.getText().replace(",", ""));
            int stok = Integer.parseInt(txtStok.getText());
            String barcode = txtBarcode.getText();

            ModelProduk produk = new ModelProduk();
            ModelKategori kategori = new ModelKategori();
            ModelSupplier supplier = new ModelSupplier();

            produk.setIdProduk(idProduk);
            produk.setNamaProduk(namaProduk);
            kategori.setIdKategori(idKategori);
            produk.setHarga(harga);
            produk.setStok(stok);
            supplier.setIdSupplier(idSupplier);
            produk.setBarcode(barcode);
            
            produk.setModelKategori(kategori);
            produk.setModelSupplier(supplier);
            
            servis.perbaruiData(produk);
            tblModel.updateData(row,produk);
            loadData();
            resetForm();
            dispose();
        }
    }    
}

