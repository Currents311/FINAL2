package com.jdialog;

import com.form.*;
import com.dao.KategoriDAO;
import com.formdev.flatlaf.FlatClientProperties;
import com.model.ModelKategori;
import com.model.Pagination;
import com.service.ServiceKategori;
import com.tablemodel.TableModelKategori;
import java.util.List;
import javax.swing.JOptionPane;

public class FormInputKategori extends javax.swing.JDialog {

    private TableModelKategori tblModel = new TableModelKategori();
    private ServiceKategori servis = new KategoriDAO();
    private ModelKategori kategori;
    private Pagination pagination;
    private int idKategori;
    private int row;
    private FormKategori formKategori;
    
    public FormInputKategori(java.awt.Frame parent, boolean modal, int row, ModelKategori kategori, FormKategori formKategori) {
        super(parent, modal);
        this.kategori = kategori;
        this.row = row;
        this.formKategori=formKategori;
        initComponents();
        pagination = new Pagination(10);
        
        if(kategori != null){
            dataTable();
        }
        loadData();
        setLayoutForm();
    }

    private void setLayoutForm(){
        txtNama.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " Masukkan nama kategori");
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
        jLabel3 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(230, 209, 242));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Master > Data kategori > Tambah");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tambah Data kategori");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel1)
                .addContainerGap(58, Short.MAX_VALUE))
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

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nama kategori");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(btnSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatal)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(jLabel3))
                .addGap(18, 18, Short.MAX_VALUE)
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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormKategori formKategori = new FormKategori();
                FormInputKategori dialog = new FormInputKategori(new javax.swing.JFrame(), true, 1, null, formKategori);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables

    private boolean validasiInput(){
        boolean valid = false;
        String namaKategori = txtNama.getText();   
        ModelKategori kategori = new ModelKategori();
        kategori.setNamaKategori(namaKategori);
        if (txtNama.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nama Kategori tidak boleh kosong");
        }else{
            if(servis.validasiNamaKategori(kategori)){
                valid = true;
            }else{
                JOptionPane.showMessageDialog(null, "Nama Kategori sudah ada\n Silahkan masukkan nama kategori yang berbeda","Peringatan",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        return valid;
    }
    
    private void simpanData(){
        if(validasiInput()== true){
            String namaKategori = txtNama.getText();
            
            ModelKategori kategori = new ModelKategori();
            kategori.setNamaKategori(namaKategori);
            
            servis.tambahData(kategori);
            tblModel.insertData(kategori);
            formKategori.refreshTable();
            resetForm();
            
        }
    }

    private void resetForm() {
        txtNama.setText("");
    }

    private void dataTable() {
        btnSimpan.setText("PERBARUI");
        
        idKategori = kategori.getIdKategori();
        
        txtNama.setText(kategori.getNamaKategori());
        
        jLabel1.setText("Master > Data kategori > Perbarui");
        jLabel2.setText("Perbarui Data Kategori");
    }

    private void loadData() {
        List<ModelKategori> list =servis.tampilData(pagination.getPosisiAwal(), pagination.getDataPerHalaman());
        tblModel.setData(list);
    }
    
    private void perbaruiData(){
        if(validasiInput()== true){
            String namaKategori = txtNama.getText();
            
            ModelKategori kategori = new ModelKategori();
            kategori.setIdKategori(idKategori);
            kategori.setNamaKategori(namaKategori);
            
            servis.perbaruiData(kategori);
            tblModel.updateData(row,kategori);
            resetForm();
            dispose();
        }
    }
    
}

