package com.main;

import com.dao.KaryawanDAO;
import com.form.FormLogin;
import com.form.FormWelcome;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.model.ModelKaryawan;
import com.util.Session;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class FormMenuUtama extends javax.swing.JFrame {
    private final FormWelcome formWelcome;
    private final FormLogin formLogin;
    private static FormMenuUtama app;
    private Main mainForm;
    private String role;


    public FormMenuUtama(String role) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        formWelcome = new FormWelcome();
        formLogin = new FormLogin();
        Session.setRole(role);
        setContentPane(formWelcome);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        FlatLaf.registerCustomDefaultsSource("com.theme");
        FlatLightLaf.setup();
        
        java.awt.EventQueue.invokeLater(() ->{
            app = new FormMenuUtama(null);
            app.setVisible(true);
        });
    }
    
    public static void showForm(Component component) {
        component.applyComponentOrientation(app.getComponentOrientation());
        app.mainForm.showForm(component);
    }
    
    public static void login(String user, String pass){
        FlatAnimatedLafChange.showSnapshot();
        KaryawanDAO dao = new KaryawanDAO();
        ModelKaryawan modelKar = new ModelKaryawan();
        modelKar.setNamaKaryawan(user);
        modelKar.setPasswordKaryawan(pass);
        ModelKaryawan loggedInUser = dao.prosesLogin(modelKar);
        if (loggedInUser != null) {
            Session.setRole(loggedInUser.getRole());
            app.mainForm = new Main();
            app.setContentPane(app.mainForm);
            app.mainForm.applyComponentOrientation(app.getComponentOrientation());
            app.mainForm.revalidate();
            app.mainForm.repaint();
            setSelectedMenu(0,0);
            app.mainForm.hideMenu();
            SwingUtilities.updateComponentTreeUI(app.mainForm);
        }
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }
        
    public static void logout(){ 
        FlatAnimatedLafChange.showSnapshot();
        Session.setRole(null);
        app.setContentPane(app.formLogin);
        app.formLogin.revalidate();
        app.formLogin.repaint();
        app.formLogin.applyComponentOrientation(app.getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(app.formLogin);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }
    
    public static void setSelectedMenu(int index, int subMenu){
        app.mainForm.setSelectedMenu(index, subMenu);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
