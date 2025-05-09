package com.jdialog;

import com.form.*;
import com.dao.KaryawanDAO;
import com.formdev.flatlaf.FlatClientProperties;
import com.model.ModelKaryawan;
import com.model.Pagination;
import com.service.ServiceKaryawan;
import com.tablemodel.TableModelKaryawan;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;


public class FormInputKaryawan extends javax.swing.JDialog {

    private TableModelKaryawan tblModel = new TableModelKaryawan();
    private ServiceKaryawan servis = new KaryawanDAO();
    private ModelKaryawan karyawan;
    private Pagination pagination;
    private int idKaryawan;
    private int row;
    private FormKaryawan formKaryawan;
    
    
    public FormInputKaryawan(java.awt.Frame parent, boolean modal, int row, ModelKaryawan karyawan, FormKaryawan formKaryawan) {
        super(parent, modal);
        this.karyawan = karyawan;
        this.row = row;
        this.formKaryawan = formKaryawan;
        initComponents();
        txtFillTelepon.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); 
                }
            }
        });
        
        pagination = new Pagination(10);

        if(karyawan != null){
            dataTable();
        }
        loadData();
        setLayoutForm();
    }
    
    private void setLayoutForm(){
        txtFillUser.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " Masukkan nama karyawan");
        txtFillTelepon.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " Masukkan nomor telepon");
        txtFillPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " Masukkan password");        
        txtFillPass.putClientProperty(FlatClientProperties.STYLE, "" 
                + "showRevealButton:true;" 
                + "showCapsLock:true;");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnInput = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        lbUser = new javax.swing.JLabel();
        txtFillUser = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFillTelepon = new javax.swing.JTextField();
        lbTelepon = new javax.swing.JLabel();
        txtFillPass = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        cbxRole = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnInput.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(230, 209, 242));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Master > Data karyawan > Tambah");

        lbTitle.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(0, 0, 0));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Tambah Data Karyawan");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        lbUser.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbUser.setForeground(new java.awt.Color(0, 0, 0));
        lbUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbUser.setText("Nama karyawan");

        txtFillUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFillUserActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Password");

        txtFillTelepon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFillTeleponActionPerformed(evt);
            }
        });

        lbTelepon.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbTelepon.setForeground(new java.awt.Color(0, 0, 0));
        lbTelepon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTelepon.setText("Telepon");

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Role");

        cbxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih peran", "super_admin", "admin", "pegawai" }));

        javax.swing.GroupLayout pnInputLayout = new javax.swing.GroupLayout(pnInput);
        pnInput.setLayout(pnInputLayout);
        pnInputLayout.setHorizontalGroup(
            pnInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(pnInputLayout.createSequentialGroup()
                .addGroup(pnInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnInputLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnInputLayout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(btnSimpan)
                                .addGap(18, 18, 18)
                                .addComponent(btnBatal)
                                .addGap(0, 107, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnInputLayout.createSequentialGroup()
                        .addGroup(pnInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnInputLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFillTelepon, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFillPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                            .addComponent(txtFillUser, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnInputLayout.setVerticalGroup(
            pnInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInputLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFillUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelepon)
                    .addComponent(txtFillTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFillPass, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxRole, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(pnInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void txtFillUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFillUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFillUserActionPerformed

    private void txtFillTeleponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFillTeleponActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFillTeleponActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormKaryawan formKaryawan = new FormKaryawan();
                FormInputKaryawan dialog = new FormInputKaryawan(new javax.swing.JFrame(), true, 1, null, formKaryawan);
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
    private javax.swing.JComboBox<String> cbxRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTelepon;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPanel pnInput;
    private javax.swing.JPasswordField txtFillPass;
    private javax.swing.JTextField txtFillTelepon;
    private javax.swing.JTextField txtFillUser;
    // End of variables declaration//GEN-END:variables

    private boolean validasiInput(){
        boolean valid = false;
        String namaKaryawan = txtFillUser.getText(); 
        String telepon = txtFillTelepon.getText().trim();
        ModelKaryawan karyawan = new ModelKaryawan();
        karyawan.setNamaKaryawan(namaKaryawan);
        karyawan.setIdKaryawan(idKaryawan);
        if (txtFillUser.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nama karyawan tidak boleh kosong");
        }else if (txtFillPass.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Password tidak boleh kosong");    
        }else if (telepon.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nomor Telepon tidak boleh kosong");  
        } else if(!telepon.startsWith("08")) {
            JOptionPane.showMessageDialog(null, "Nomor telepon harus dimulai dengan 08");
        } else if (telepon.length() < 10 || telepon.length() > 13) {
            JOptionPane.showMessageDialog(null, "Panjang nomor telepon tidak valid (10-13 digit)");
        }else if (cbxRole.getSelectedItem().equals("Pilih peran")){
            JOptionPane.showMessageDialog(null, "Silahkan pilih peran");    
        }else {
            valid = true;
            }
        return valid;
    }

    private boolean validasiInputPerbarui(){
        boolean valid = false;
        String namaKaryawan = txtFillUser.getText(); 
        String telepon = txtFillTelepon.getText().trim();
        ModelKaryawan karyawan = new ModelKaryawan();
        karyawan.setNamaKaryawan(namaKaryawan);
        karyawan.setIdKaryawan(idKaryawan);
        if (txtFillUser.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nama karyawan tidak boleh kosong");  
        }else if (telepon.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nomor Telepon tidak boleh kosong");  
        } else if(!telepon.startsWith("08")) {
            JOptionPane.showMessageDialog(null, "Nomor telepon harus dimulai dengan 08");
        } else if (telepon.length() < 10 || telepon.length() > 13) {
            JOptionPane.showMessageDialog(null, "Panjang nomor telepon tidak valid (10-13 digit)");
        }else if (cbxRole.getSelectedItem().equals("Pilih peran")){
            JOptionPane.showMessageDialog(null, "Silahkan pilih peran");    
        }else {
            valid = true;
            }
        return valid;
    }

    
    private void simpanData(){
        if(validasiInput()== true){
            String namaKaryawan        = txtFillUser.getText();
            String passwordKaryawan    = txtFillPass.getText();
            String teleponKaryawan     = txtFillTelepon.getText();
            String role                = cbxRole.getSelectedItem().toString();
            
            ModelKaryawan karyawan = new ModelKaryawan();
            karyawan.setNamaKaryawan(namaKaryawan);
            karyawan.setPasswordKaryawan(passwordKaryawan);
            karyawan.setTeleponKaryawan(teleponKaryawan);
            karyawan.setRole(role);
            
            servis.tambahData(karyawan);
            tblModel.insertData(karyawan);
            formKaryawan.refreshTable();
            resetForm();
        }
    }

    private void resetForm() {
        txtFillUser.setText("");
        txtFillPass.setText("");
        txtFillTelepon.setText("");
        cbxRole.setSelectedIndex(0);
    }

    private void dataTable() {
        btnSimpan.setText("PERBARUI");
        
        idKaryawan = karyawan.getIdKaryawan();
        txtFillPass.setPreferredSize(new Dimension(0, 0));
        jLabel5.setEnabled(false);
        txtFillPass.setEnabled(false);
            
        txtFillUser.setText(karyawan.getNamaKaryawan());
        txtFillTelepon.setText(karyawan.getTeleponKaryawan());
        cbxRole.setSelectedItem(karyawan.getRole());
        
        jLabel1.setText("Master > Data karyawan > Perbarui");
        lbTitle.setText("Perbarui Data Karyawan");
    }

    private void loadData() {
        List<ModelKaryawan> list =servis.tampilData(pagination.getPosisiAwal(), pagination.getDataPerHalaman());
        tblModel.setData(list);
    }
    
    private void perbaruiData(){
        if(validasiInputPerbarui()== true){
            String namaKaryawan     = txtFillUser.getText();
            String teleponKaryawan  = txtFillTelepon.getText();
            String role             = cbxRole.getSelectedItem().toString();

            
            ModelKaryawan karyawan = new ModelKaryawan();
            karyawan.setIdKaryawan(idKaryawan);
            karyawan.setNamaKaryawan(namaKaryawan);
            karyawan.setTeleponKaryawan(teleponKaryawan);
            karyawan.setRole(role);

            
            servis.perbaruiData(karyawan);
            tblModel.updateData(row,karyawan);
            resetForm();
            dispose();
        }
    }

}


