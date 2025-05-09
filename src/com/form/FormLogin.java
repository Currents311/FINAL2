package com.form;

import com.dao.KaryawanDAO;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import com.main.FormMenuUtama;
import com.model.ModelKaryawan;
import com.service.ServiceKaryawan;
import com.util.Session;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FormLogin extends javax.swing.JPanel {

    private ServiceKaryawan servis = new KaryawanDAO();
    private Image backgroundImage;
    
    public FormLogin() {
        initComponents();
        setLayoutForm();
        add(pnGambar);
        setGifToPanel(); 
        backgroundImage = new ImageIcon(getClass().getResource("/com/icon/BackgroundForm.png")).getImage();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void setGifToPanel() {
        ImageIcon gifIcon = new ImageIcon(getClass().getResource("/com/icon/LoginPnGambar.jpg")); 
        JLabel label = new JLabel(gifIcon);
        label.setSize(pnGambar.getWidth(), pnGambar.getHeight()); 
        pnGambar.removeAll();  
        pnGambar.setLayout(new java.awt.BorderLayout());  
        pnGambar.add(label, java.awt.BorderLayout.CENTER);

        pnGambar.revalidate();
        pnGambar.repaint();
    }
    
    private void resetForm(){
        txtUser.setText("");
        txtPass.setText("");
    }
    
    private boolean validasiInput(){
        boolean valid = false;
        if(txtUser.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Username tidak boleh kosong");
        }else if(txtPass.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Password tidak boleh kosong");
        }else{
            valid = true;
        }
        return valid;
    }
    
    private void prosesLogin(){
        if(validasiInput()== true){
            String user = txtUser.getText();
            String pass = txtPass.getText();
            
            ModelKaryawan model = new ModelKaryawan();
            model.setNamaKaryawan(user);
            model.setPasswordKaryawan(pass);
            
            ModelKaryawan modelKar = servis.prosesLogin(model);
            if(modelKar != null){
            JOptionPane.showMessageDialog(null, 
                        "Login Berhasil!", "Pesan", 
                        JOptionPane.INFORMATION_MESSAGE);
            Session.setLoggedInKaryawan(modelKar);
            Session.setRole(modelKar.getRole());
            FormMenuUtama.login(user, pass);
            resetForm();
            }else{
                JOptionPane.showMessageDialog(null, 
                        "Username dan Password salah", "Pesan", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnLogin = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        lbTitle2 = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        lbPass = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        txtAsk = new javax.swing.JLabel();
        pnGambar = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        lbLogo = new javax.swing.JLabel();
        txtSignUp = new javax.swing.JButton();

        lbTitle.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(0, 0, 0));
        lbTitle.setText("Login to");

        lbTitle2.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        lbTitle2.setForeground(new java.awt.Color(0, 0, 0));
        lbTitle2.setText("Z A A R A  M E D I A");

        lbUser.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 14)); // NOI18N
        lbUser.setForeground(new java.awt.Color(0, 0, 0));
        lbUser.setText("Username");

        lbPass.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 14)); // NOI18N
        lbPass.setForeground(new java.awt.Color(0, 0, 0));
        lbPass.setText("Password");

        txtUser.setBackground(new java.awt.Color(195, 238, 240));
        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        txtPass.setBackground(new java.awt.Color(195, 238, 240));

        txtAsk.setBackground(new java.awt.Color(255, 255, 255));
        txtAsk.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        txtAsk.setForeground(new java.awt.Color(0, 74, 173));
        txtAsk.setText("Doesn't have an account?");

        pnGambar.setBackground(new java.awt.Color(195, 238, 240));

        javax.swing.GroupLayout pnGambarLayout = new javax.swing.GroupLayout(pnGambar);
        pnGambar.setLayout(pnGambarLayout);
        pnGambarLayout.setHorizontalGroup(
            pnGambarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        pnGambarLayout.setVerticalGroup(
            pnGambarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
        );

        btnLogin.setBackground(new java.awt.Color(255, 176, 181));
        btnLogin.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(0, 0, 0));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lbLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/logo_ZaaraMedia.png"))); // NOI18N

        txtSignUp.setBackground(new java.awt.Color(255, 255, 255));
        txtSignUp.setForeground(new java.awt.Color(255, 176, 181));
        txtSignUp.setText("Sign Up");
        txtSignUp.setBorder(null);
        txtSignUp.setBorderPainted(false);
        txtSignUp.setContentAreaFilled(false);
        txtSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSignUpMouseClicked(evt);
            }
        });
        txtSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSignUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnLoginLayout = new javax.swing.GroupLayout(pnLogin);
        pnLogin.setLayout(pnLoginLayout);
        pnLoginLayout.setHorizontalGroup(
            pnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLoginLayout.createSequentialGroup()
                .addGroup(pnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnLoginLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txtAsk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSignUp))
                    .addGroup(pnLoginLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnLoginLayout.createSequentialGroup()
                                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lbPass, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUser)
                                .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLoginLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(pnGambar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnLoginLayout.setVerticalGroup(
            pnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLoginLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(lbUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSignUp)
                    .addComponent(txtAsk, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnGambar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        prosesLogin();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    private void txtSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSignUpActionPerformed
    }//GEN-LAST:event_txtSignUpActionPerformed

    private void txtSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSignUpMouseClicked
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(FormLogin.this);
        parent.setContentPane(new FormSignUp()); 
        parent.revalidate();
        parent.repaint();
    }//GEN-LAST:event_txtSignUpMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbTitle2;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPanel pnGambar;
    private javax.swing.JPanel pnLogin;
    private javax.swing.JLabel txtAsk;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JButton txtSignUp;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

    private void setLayoutForm() {
        setLayout(new FormLoginLayout ());
        txtUser.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " Masukkan nama lengkap");
        txtPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, " Masukkan password");
        pnLogin.setLayout(new LoginLayout());
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:h1.font");
        pnLogin.putClientProperty(FlatClientProperties.STYLE, "" + "background:$Logn.background;" + "arc:20;" 
                + "border:30,40,50,30;");
        txtPass.putClientProperty(FlatClientProperties.STYLE, "" + "showRevealButton:true;" + "showCapsLock:true");
        btnLogin.putClientProperty(FlatClientProperties.STYLE, "" + "borderWidth:0;" + "focusWidth:0;");
    }
    
    private class FormLoginLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0,0);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0,0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            txtSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
            txtSignUp.setOpaque(true);

            synchronized (parent.getTreeLock()) {
                int width = parent.getWidth();
                int height = parent.getHeight();
                int loginWidth = UIScale.scale(400);
                int gambarWidth = UIScale.scale(300); 
            int gap = UIScale.scale(1); 

            int totalWidth = loginWidth + gambarWidth + gap;
            int startX = (width - totalWidth) / 2; 
            
            int y = (height - pnLogin.getPreferredSize().height) / 2;
            pnLogin.setBounds(startX, y, loginWidth, pnLogin.getPreferredSize().height);
            pnGambar.setBounds(startX + loginWidth + gap, y, gambarWidth, pnLogin.getPreferredSize().height);
            }
        }
        
    }

    private class LoginLayout implements LayoutManager {
    private final int titleGap = 15;
    private final int textGap = 10;
    private final int labelGap = 5;
    private final int buttonGap = 20;

    @Override
    public void addLayoutComponent(String name, Component comp) {}

    @Override
    public void removeLayoutComponent(Component comp) {}

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int height = insets.top + insets.bottom;
            
            height += lbLogo.getPreferredSize().height;
            height += UIScale.scale(titleGap);
            
            height += lbTitle.getPreferredSize().height;
            height += UIScale.scale(titleGap * 2);
            
            height += lbUser.getPreferredSize().height;
            height += UIScale.scale(labelGap);
            height += txtUser.getPreferredSize().height;
            height += UIScale.scale(textGap * 2);
            
            height += lbPass.getPreferredSize().height;
            height += UIScale.scale(labelGap);
            height += txtPass.getPreferredSize().height;
            height += UIScale.scale(buttonGap);
            
            height += btnLogin.getPreferredSize().height;
            height += UIScale.scale(textGap * 2);
            
            height += txtSignUp.getPreferredSize().height;
            
            return new Dimension(UIScale.scale(300), height);
        }
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(0, 0);
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int x = insets.left;
            int y = insets.top;
            int width = parent.getWidth() - (insets.left + insets.right);

            lbLogo.setBounds(x, y, width, lbLogo.getPreferredSize().height);
            y += lbLogo.getPreferredSize().height + UIScale.scale(labelGap);

            lbTitle.setBounds(x, y, lbTitle.getPreferredSize().width, lbTitle.getPreferredSize().height);
            lbTitle2.setBounds(
                x + lbTitle.getPreferredSize().width + UIScale.scale(titleGap), 
                y,
                lbTitle2.getPreferredSize().width,
                lbTitle2.getPreferredSize().height
            );
            y += Math.max(lbTitle.getPreferredSize().height, lbTitle2.getPreferredSize().height) + UIScale.scale(titleGap);

            lbUser.setBounds(x, y, width, lbUser.getPreferredSize().height);
            y += lbUser.getPreferredSize().height + UIScale.scale(labelGap);
            txtUser.setBounds(x, y, width, txtUser.getPreferredSize().height);
            y += txtUser.getPreferredSize().height + UIScale.scale(textGap * 2);
            
            lbPass.setBounds(x, y, width, lbPass.getPreferredSize().height);
            y += lbPass.getPreferredSize().height + UIScale.scale(labelGap);
            txtPass.setBounds(x, y, width, txtPass.getPreferredSize().height);
            y += txtPass.getPreferredSize().height + UIScale.scale(labelGap);

            txtAsk.setBounds(x, y, txtAsk.getPreferredSize().width, txtAsk.getPreferredSize().height);
            txtSignUp.setBounds(
                x + txtAsk.getPreferredSize().width + UIScale.scale(titleGap), 
                y,
                txtSignUp.getPreferredSize().width,
                txtSignUp.getPreferredSize().height
            );
            
            y += Math.max(txtAsk.getPreferredSize().height, txtSignUp.getPreferredSize().height) + UIScale.scale(buttonGap);
            btnLogin.setBounds(x, y, width, btnLogin.getPreferredSize().height);
            }
        }
    }
}
