package com.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import com.util.Session;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Menu extends JPanel {
            
    private final String menuItemsSuperAdmin[][] = {
        {"~MAIN~"},
        {"Dashboard"},
        {"~MASTER~"},
        {"Data Produk"},
        {"Data Kategori"},
        {"Data Supplier"},
        {"Data Pelanggan"},
        {"Manajemen User", "Data Karyawan", "Pergantian Password"},
        {"~TRANSAKSI~"},
        {"Penjualan"},
        {"Pembelian"},
        {"Barang rusak"},
        {"~LAPORAN~"},
        {"Laporan Produk"},
        {"Laporan Penjualan"},
        {"Laporan Pembelian"},
        {"Laporan Barang Rusak"},
        {"~KELUAR~"},
        {"Log Out"},
    };
    
    private final String menuItemsAdmin[][] = {
        {"~MAIN~"},
        {"Dashboard"},
        {"~MASTER~"},
        {"Data Produk"},
        {"Data Kategori"},
        {"Data Supplier"},
        {"Data Pelanggan"},
        {"~TRANSAKSI~"},
        {"Penjualan"},
        {"Pembelian"},
        {"Barang rusak"},
        {"~LAPORAN~"},
        {"Laporan Produk"},
        {"Laporan Penjualan"},
        {"Laporan Pembelian"},
        {"Laporan Barang Rusak"},
        {"~KELUAR~"},
        {"Log Out"},
    };
    
        private final String menuItemsPegawai[][] = {
        {"~MAIN~"},
        {"Dashboard"},
        {"~TRANSAKSI~"},
        {"Penjualan"},
        {"Pembelian"},
        {"Barang rusak"},
        {"~KELUAR~"},
        {"Log Out"},
    };


    public boolean isMenuFull() {
        return menuFull;
    }

    public void setMenuFull(boolean menuFull) {
        this.menuFull = menuFull;
        if (menuFull) {
            header.setText(headerText);
            header.setHorizontalAlignment(getComponentOrientation().isLeftToRight() ? JLabel.LEFT : JLabel.RIGHT);
        } else {
            header.setText("");
            header.setHorizontalAlignment(JLabel.CENTER);
        }
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                ((MenuItem) com).setFull(menuFull);
            }
        }
    }

    private final List<MenuEvent> events = new ArrayList<>();
    private boolean menuFull = true;
    private final String headerText = "<html><center>Z A A R A  M E D I A<br><small>AL-QUR'AN DAN BUKU ISLAM</small></center></html>" ;

    protected final boolean hideMenuTitleOnMinimum = true;
    protected final int menuTitleLeftInset = 5;
    protected final int menuTitleVgap = 5;
    protected final int menuMaxWidth = 250;
    protected final int menuMinWidth = 60;
    protected final int headerFullHgap = 5;

    private String role;
    String[][] menuItems = null;
    
    public Menu(String role) {
        this.role = role;
        init();
        setDoubleBuffered(true);
    }
    
    private void init() {
    setLayout(new MenuLayout());
    putClientProperty(FlatClientProperties.STYLE, ""
            + "border:20,2,2,2;"
            + "background:$Menu.background;"
            + "arc:10");

    header = new JLabel(headerText);
    header.setIcon(new ImageIcon(getClass().getResource("/com/icon/logo_ZaaraMedia.png")));
    header.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:$Menu.header.font;"
            + "foreground:$Menu.foreground");
    header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));

    scroll = new JScrollPane();
    panelMenu = new JPanel(new MenuItemLayout(this));
    panelMenu.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5;"
            + "background:$Menu.background");

    scroll.setViewportView(panelMenu);
    scroll.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:null");

    JScrollBar vscroll = scroll.getVerticalScrollBar();
    vscroll.setUnitIncrement(10);
    vscroll.putClientProperty(FlatClientProperties.STYLE, ""
            + "width:$Menu.scroll.width;"
            + "trackInsets:$Menu.scroll.trackInsets;"
            + "thumbInsets:$Menu.scroll.thumbInsets;"
            + "background:$Menu.ScrollBar.background;"
            + "thumb:$Menu.ScrollBar.thumb");

    createMenu();
    add(header);
    add(scroll);
}
    
    private void createMenu() {
        role = Session.getRole();
        if ("super admin".equalsIgnoreCase(role) || "super_admin".equalsIgnoreCase(role)) {
            menuItems = menuItemsSuperAdmin;
        } else if ("admin".equals(role)) {
            menuItems = menuItemsAdmin;
        } else if ("pegawai".equals(role)) {
            menuItems = menuItemsPegawai;
        } else {
            menuItems = new String[][]{{"Log Out"}}; 
        }
        panelMenu.removeAll();
        
        int index = 0;
        for (int i = 0; i < menuItems.length; i++) {
            String menuName = menuItems[i][0];
            if (menuName.startsWith("~") && menuName.endsWith("~")) {
                panelMenu.add(createTitle(menuName));
                } else {
                MenuItem menuItem = new MenuItem(this, menuItems[i], index++, events);
                panelMenu.add(menuItem);
            }
        }
        
        hideMenuItem();
        
        revalidate();
        repaint();
    }
    
    private JLabel createTitle(String title) {
        String menuName = title.substring(1, title.length() - 1);
        JLabel lbTitle = new JLabel(menuName);
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$Menu.label.font;"
                + "foreground:$Menu.title.foreground");
        return lbTitle;
    }

    public void setSelectedMenu(int index, int subIndex) {
        runEvent(index, subIndex);
    }

    protected void setSelected(int index, int subIndex) {
        int size = panelMenu.getComponentCount();
        for (int i = 0; i < size; i++) {
            Component com = panelMenu.getComponent(i);
            if (com instanceof MenuItem) {
                MenuItem item = (MenuItem) com;
                if (item.getMenuIndex() == index) {
                    item.setSelectedIndex(subIndex);
                } else {
                    item.setSelectedIndex(-1);
                }
            }
        }
    }

    protected void runEvent(int index, int subIndex) {
        MenuAction menuAction = new MenuAction();
        for (MenuEvent event : events) {
            event.menuSelected(index, subIndex, menuAction);
        }
        if (!menuAction.isCancel()) {
            setSelected(index, subIndex);
        }
    }

    public void addMenuEvent(MenuEvent event) {
        events.add(event);
    }

    public void hideMenuItem() {
        if ("super_admin".equalsIgnoreCase(role) || "super admin".equalsIgnoreCase(role)) {
            for (Component com : panelMenu.getComponents()) {
                if (com instanceof MenuItem) {
                    com.setVisible(true);
                }
            }
        }  
        revalidate();
        repaint();
    }


    public boolean isHideMenuTitleOnMinimum() {
        return hideMenuTitleOnMinimum;
    }

    public int getMenuTitleLeftInset() {
        return menuTitleLeftInset;
    }

    public int getMenuTitleVgap() {
        return menuTitleVgap;
    }

    public int getMenuMaxWidth() {
        return menuMaxWidth;
    }

    public int getMenuMinWidth() {
        return menuMinWidth;
    }

    private JLabel header;
    private JScrollPane scroll;
    private JPanel panelMenu;
    private LightDarkMode lightDarkMode;

    private class MenuLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int x = insets.left;
            int y = insets.top;
            int gap = UIScale.scale(5);
            int sheaderFullHgap = UIScale.scale(headerFullHgap);
            int width = parent.getWidth() - (insets.left + insets.right);
            int height = parent.getHeight() - (insets.top + insets.bottom);
            int iconWidth = width;
            int iconHeight = header.getPreferredSize().height;
            int hgap = menuFull ? sheaderFullHgap : 0;

            header.setBounds(x + hgap, y, iconWidth - (hgap * 2), iconHeight);

            int menux = x;
            int menuy = y + iconHeight; 
            int menuWidth = width;
            int menuHeight = height - iconHeight - UIScale.scale(10); 

            scroll.setBounds(menux, menuy, menuWidth, menuHeight);
            }
        }
    }    
}



