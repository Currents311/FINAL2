package com.main;

import com.form.FormBarangRusak;
import com.form.FormDashboard;
import com.form.FormKaryawan;
import com.form.FormKategori;
import com.form.FormLaporanBarangRusak;
import com.form.FormLaporanPembelian;
import com.form.FormLaporanPenjualan;
import com.form.FormLaporanProduk;
import com.form.FormPelanggan;
import com.form.FormPembelian;
import com.form.FormPergantianPassword;
import com.form.FormPenjualan;
import com.form.FormProduk;
import com.form.FormSupplier;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import com.menu.Menu;
import com.menu.MenuAction;
import com.model.ModelKaryawan;
import com.util.Session;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main extends JLayeredPane {
    
    private Menu menu;
    private JPanel panelBody;
    private JButton menuButton;
    private String role;
    private ModelKaryawan modelKar;    
    
    public Main() {
        role = Session.getRole();
        modelKar = Session.getLoggedInKaryawan();
        init();
    }
    
    private void init() {
        setBorder (new EmptyBorder(5,5,5,5));
        setLayout (new MainFormLayout());
        menu = new Menu(role);
        panelBody = new JPanel(new BorderLayout());
        initMenuArrowIcon();
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
        + "background:$Menu.button.background;"
        + "arc:999;"
        + "focusWidth:0;"
        + "borderWidth:0;");
        menuButton.addActionListener((ActionEvent e) -> {
            setMenuFull(!menu.isMenuFull());
        });
        initMenuEvent();
        add(menuButton);
        add(menu);
        add(panelBody);
    }
    
    private void initMenuArrowIcon(){
        if(menuButton == null){
            menuButton = new JButton();
        }
        String icon = (getComponentOrientation().isLeftToRight()?"menu_left.svg" : "menu_right.svg");
        menuButton.setIcon(new FlatSVGIcon("com/icon/" + icon, 0.5f));
    }
    
    private void initMenuEvent(){
        if ("super admin".equalsIgnoreCase(role) || "super_admin".equalsIgnoreCase(role)) {
            initSuperAdminMenuEvent();
        } else if ("admin".equalsIgnoreCase(role)) {
            initAdminMenuEvent();
        } else if ("pegawai".equalsIgnoreCase(role)) {
            initPegawaiMenuEvent();
        } else {
            System.out.println("Unknown role: No menu assigned.");
        }
    }
    
    private void initSuperAdminMenuEvent() {
        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
            if(index== 0){
                FormMenuUtama.showForm(new FormDashboard(modelKar, "super_admin"));
            }else if(index == 1){
                FormMenuUtama.showForm(new FormProduk());
            }else if(index == 2){
                FormMenuUtama.showForm(new FormKategori());
            }else if(index == 3){
                FormMenuUtama.showForm(new FormSupplier());
            }else if(index == 4){
                FormMenuUtama.showForm(new FormPelanggan());
            }else if(index == 5){
                if(subIndex == 1){
                    FormMenuUtama.showForm(new FormKaryawan());
                }else if(subIndex == 2){
                    FormMenuUtama.showForm(new FormPergantianPassword());
                }
            }else if(index == 6){
                FormMenuUtama.showForm(new FormPenjualan(modelKar, "super_admin"));
            }else if(index == 7){
                FormMenuUtama.showForm(new FormPembelian(modelKar, "super_admin"));
            }else if(index == 8){
                FormMenuUtama.showForm(new FormBarangRusak(modelKar, "super_admin"));
            }else if(index == 9){
                FormMenuUtama.showForm(new FormLaporanProduk());
            }else if(index == 10){
                FormMenuUtama.showForm(new FormLaporanPenjualan(modelKar, "super_admin"));
            }else if(index == 11){
                FormMenuUtama.showForm(new FormLaporanPembelian(modelKar, "super_admin"));
            }else if(index == 12){
                FormMenuUtama.showForm(new FormLaporanBarangRusak(modelKar, "super_admin"));
            }else if (index == 13) {
                int confirm = JOptionPane.showConfirmDialog(null, 
                    "Apakah Anda yakin ingin keluar?", "Konfirmasi Logout", 
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    FormMenuUtama.logout();   
                } 
            }else{
                action.cancel();
            }
    });
    }
    
    private void initAdminMenuEvent() {
        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
            if(index== 0){
                FormMenuUtama.showForm(new FormDashboard(modelKar, "admin"));
            }else if(index == 1){
                FormMenuUtama.showForm(new FormProduk());
            }else if(index == 2){
                FormMenuUtama.showForm(new FormKategori());
            }else if(index == 3){
                FormMenuUtama.showForm(new FormSupplier());
            }else if(index == 4){
                FormMenuUtama.showForm(new FormPelanggan());
//            }else if(index == 5){
//                FormMenuUtama.showForm(new FormPenjualan(modelKar, "admin"));            
//            }else if(index == 6){
//                FormMenuUtama.showForm(new FormPembelian(modelKar, "admin"));  
//            }else if(index == 7){
//                FormMenuUtama.showForm(new FormBarangRusak(modelKar, "admin"));  
            }else if(index == 8){
                FormMenuUtama.showForm(new FormLaporanProduk());
//            }else if(index == 9){
//                FormMenuUtama.showForm(new FormLaporanPenjualan(modelKar, "admin"));
//            }else if(index == 10){
//                FormMenuUtama.showForm(new FormLaporanPembelian(modelKar, "admin"));
//            }else if(index == 11){
//                FormMenuUtama.showForm(new FormLaporanBarangRusak(modelKar, "admin"));
            }else if (index == 12) {
                int confirm = JOptionPane.showConfirmDialog(null, 
                    "Apakah Anda yakin ingin keluar?", "Konfirmasi Logout", 
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    FormMenuUtama.logout();   
                } 
            }else{
                action.cancel();
            }
    });
    }
    
    private void initPegawaiMenuEvent() {
        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
            if(index== 0){
                FormMenuUtama.showForm(new FormDashboard(modelKar, "pegawai"));
//            }else if(index == 1){
//                FormMenuUtama.showForm(new FormPenjualan(modelKar, "pegawai"));
//            }else if(index == 2){
//                FormMenuUtama.showForm(new FormPembelian(modelKar, "pegawai"));
//            }else if(index == 3){
//                FormMenuUtama.showForm(new FormBarangRusak(modelKar, "pegawai"));
            }else if (index == 4) {
                int confirm = JOptionPane.showConfirmDialog(null, 
                    "Apakah Anda yakin ingin keluar?", "Konfirmasi Logout", 
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    FormMenuUtama.logout();   
                } 
            }else{
                action.cancel();
            }
    });
    }
    
    private void setMenuFull(boolean full){
        String icon;
        if(getComponentOrientation().isLeftToRight()){
            icon = (full) ? "menu_left.svg" : "menu_right.svg";
        } else{
            icon = (full) ? "menu_right.svg" : "menu_left.svg";
        }
        menuButton.setIcon(new FlatSVGIcon("com/icon/" + icon, 0.5f));
        menu.setMenuFull(full);
        revalidate();
    }
    
    public void hideMenu(){
        menu.hideMenuItem();
    }
    
    public void showForm(Component component){
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }
    
    public void setSelectedMenu(int index, int subIndex){
        menu.setSelectedMenu(index, subIndex);
    }
    
    private class MainFormLayout implements LayoutManager{

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5,5);
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
            synchronized (parent.getTreeLock()) {
                boolean ltr = parent.getComponentOrientation().isLeftToRight();
                Insets insets = UIScale.scale(parent.getInsets());
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int menuWidth = UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
                int menuX = ltr ? x : x + width - menuWidth;
                menu.setBounds(menuX, y, menuWidth, height);
                int menuButtonWidth = menuButton.getPreferredSize().width;
                int menuButtonHeight = menuButton.getPreferredSize().height;
                int menubX;
                if(ltr) {
                    menubX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
                } else{
                    menubX = (int) (menuX - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.7f)));
                }
                menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
                int gap = UIScale.scale(5);
                int bodyWidth = width - menuWidth - gap;
                int bodyHeight = height;
                int bodyx = ltr ? (x + menuWidth + gap) : x;
                int bodyy = y;
                panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
            }
        }
        
    }
}

