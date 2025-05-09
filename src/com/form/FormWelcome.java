package com.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.formdev.flatlaf.FlatClientProperties;


public class FormWelcome extends javax.swing.JPanel {

    public FormWelcome() {
        initComponents();
        setLayoutForm();
        
        JPanel panel = new JPanel();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnWelcome = new javax.swing.JPanel();
        lbChoose = new javax.swing.JLabel();
        btnLogIn = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        Logo_img = new javax.swing.JLabel();

        pnWelcome.setBackground(new java.awt.Color(250, 250, 250));
        pnWelcome.setPreferredSize(new java.awt.Dimension(845, 677));

        lbChoose.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbChoose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbChoose.setText("Please select one of these");

        btnLogIn.setBackground(new java.awt.Color(211, 245, 255));
        btnLogIn.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnLogIn.setText("  Login  ");
        btnLogIn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 245, 255), 10));
        btnLogIn.setBorderPainted(false);
        btnLogIn.setPreferredSize(new java.awt.Dimension(75, 25));
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });

        btnSignUp.setBackground(new java.awt.Color(211, 245, 255));
        btnSignUp.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnSignUp.setText("  Sign Up  ");
        btnSignUp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(211, 245, 255), 10, true));
        btnSignUp.setBorderPainted(false);
        btnSignUp.setPreferredSize(new java.awt.Dimension(75, 25));
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        Logo_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Logo_img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/Logo_Animation.png"))); // NOI18N

        javax.swing.GroupLayout pnWelcomeLayout = new javax.swing.GroupLayout(pnWelcome);
        pnWelcome.setLayout(pnWelcomeLayout);
        pnWelcomeLayout.setHorizontalGroup(
            pnWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnWelcomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(292, 292, 292))
            .addGroup(pnWelcomeLayout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(lbChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnWelcomeLayout.createSequentialGroup()
                .addComponent(Logo_img, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnWelcomeLayout.setVerticalGroup(
            pnWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnWelcomeLayout.createSequentialGroup()
                .addComponent(Logo_img, javax.swing.GroupLayout.PREFERRED_SIZE, 508, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbChoose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(FormWelcome.this);
        parent.setContentPane(new FormLogin());
        parent.revalidate();
        parent.repaint();
    }//GEN-LAST:event_btnLogInActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(FormWelcome.this);
        parent.setContentPane(new FormSignUp());
        parent.revalidate();
        parent.repaint();
    }//GEN-LAST:event_btnSignUpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo_img;
    private javax.swing.JButton btnLogIn;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel lbChoose;
    private javax.swing.JPanel pnWelcome;
    // End of variables declaration//GEN-END:variables
    
    public void setLayoutForm() {
    setLayout(new BorderLayout());

    pnWelcome.setLayout(new BorderLayout());
    pnWelcome.setBackground(new Color(250, 250, 250));

    JPanel topPanel = new JPanel();
    topPanel.setBackground(new Color(250, 250, 250));
    topPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
    topPanel.add(Logo_img);

    JPanel centerPanel = new JPanel(new GridBagLayout());
    centerPanel.setBackground(new Color(250, 250, 250));
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(0, 0, 0, 0);
    gbc.gridx = 0;
    gbc.gridy = 0;
    
    lbChoose.setFont(new Font("Arial", Font.PLAIN, 20));
    centerPanel.add(lbChoose, gbc);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(250, 250, 250));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 45, 20));

    btnLogIn.setPreferredSize(new Dimension(120, 35)); 
    btnSignUp.setPreferredSize(new Dimension(120, 35));

    buttonPanel.add(btnLogIn);
    buttonPanel.add(btnSignUp);

    gbc.gridy++;
    centerPanel.add(buttonPanel, gbc);

    pnWelcome.add(topPanel, BorderLayout.NORTH); 
    pnWelcome.add(centerPanel, BorderLayout.CENTER); 

    add(pnWelcome, BorderLayout.CENTER);

    btnLogIn.setVisible(true);
    btnSignUp.setVisible(true);
    pnWelcome.revalidate();
    pnWelcome.repaint();
    }
}

