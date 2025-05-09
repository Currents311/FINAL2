package com.form;

import com.config.Koneks;
import com.dao.DashboardDAO;
import com.grafik.ModelData;
import com.grafik.ModelPolarAreaChart;
import com.service.ServiceDashboard;
import com.kitfox.svg.app.beans.SVGIcon;
import com.model.ModelKaryawan;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import raven.chart.ModelChart;


public class FormDashboard extends javax.swing.JPanel {

    private Connection conn;
    private final ServiceDashboard servis = new DashboardDAO();
    private Integer idKaryawan;
    private String role;

    
    public FormDashboard(ModelKaryawan modelKar, String role) {
        initComponents();     
        this.idKaryawan = modelKar.getIdKaryawan();
        this.role = role;
        lbKar.setText("WELCOME BACK, " + modelKar.getNamaKaryawan());
        notif.setVisible(false);
        cmd.setVisible(true);
        loadSvgIcon();
        loadData();
        chart.setTitle("Transaksi 5 bulan terakhir");
        chart.addLegend("Penjualan",  Color.decode("#E6D1F2"), Color.decode("#E6D1F2"));
        chart.addLegend("Pembelian", Color.decode("#FAEBB2"), Color.decode("#F9D423"));
        chart.addLegend("Barang Rusak",Color.decode("#ffb7bc"), Color.decode("#dc2430"));
        setData();
        setDataPolar();
 
        
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                loadData(); 
            }   
        });

        viewPanel.setVisible(true);
    }
    
    private void loadSvgIcon() {
        try {
            SVGIcon svgIconPd = new SVGIcon();
            URL svgUrl = getClass().getResource("/com/icon/produk.svg"); 
            URI svgUri = svgUrl.toURI();
            svgIconPd.setSvgURI(svgUri);
            
            SVGIcon svgIconPj = new SVGIcon();
            URL svgUrl1 = getClass().getResource("/com/icon/penjualan.svg"); 
            URI svgUri1 = svgUrl1.toURI();
            svgIconPj.setSvgURI(svgUri1);
            
            SVGIcon svgIconPb = new SVGIcon();
            URL svgUrl2 = getClass().getResource("/com/icon/pembelian.svg"); 
            URI svgUri2 = svgUrl2.toURI();
            svgIconPb.setSvgURI(svgUri2);
            
            lbIconPd.setIcon(svgIconPd); 
            lbIconPj.setIcon(svgIconPj);
            lbIconPb.setIcon(svgIconPb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void setData(){
        try {
           List<ModelData> lists=new ArrayList<>();
           conn = Koneks.getConnection();
           PreparedStatement st = null;
           ResultSet rs= null;
           String sql = "WITH RECURSIVE bulan_list AS (\n" +
            "    SELECT CURDATE() - INTERVAL 4 MONTH AS full_date\n" +
            "    UNION ALL\n" +
            "    SELECT DATE_ADD(full_date, INTERVAL 1 MONTH)\n" +
            "    FROM bulan_list\n" +
            "    WHERE full_date < CURDATE()\n" +
            ")\n" +
            "\n" +
            "SELECT\n" +
            "    DATE_FORMAT(full_date, '%M %Y') AS bulan, \n" +
            "    COALESCE(p.penjualan, 0) AS penjualan,\n" +
            "    COALESCE(b.pembelian, 0) AS pembelian,\n" +
            "    COALESCE(r.barang_rusak, 0) AS barang_rusak\n" +
            "FROM bulan_list bl\n" +
            "LEFT JOIN (\n" +
            "    SELECT DATE_FORMAT(tanggal, '%Y-%m') AS bulan, COUNT(*) AS penjualan\n" +
            "    FROM penjualan\n" +
            "    GROUP BY bulan\n" +
            ") p ON DATE_FORMAT(bl.full_date, '%Y-%m') = p.bulan\n" +
            "LEFT JOIN (\n" +
            "    SELECT DATE_FORMAT(tanggal, '%Y-%m') AS bulan, COUNT(*) AS pembelian\n" +
            "    FROM pembelian\n" +
            "    GROUP BY bulan\n" +
            ") b ON DATE_FORMAT(bl.full_date, '%Y-%m') = b.bulan\n" +
            "LEFT JOIN (\n" +
            "    SELECT DATE_FORMAT(tanggal_retur, '%Y-%m') AS bulan, COUNT(*) AS barang_rusak\n" +
            "    FROM barang_rusak\n" +
            "    GROUP BY bulan\n" +
            ") r ON DATE_FORMAT(bl.full_date, '%Y-%m') = r.bulan\n" +
            "ORDER BY bl.full_date";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                String bulan = rs.getString("bulan");
                int penjualan = rs.getInt("penjualan");
                int pembelian = rs.getInt("pembelian");
                int barangRusak = rs.getInt("barang_rusak");
                
                lists.add(new ModelData(bulan, penjualan, pembelian, barangRusak));
            }
            st.close();
            rs.close();
            
            for (int i = lists.size() - 1; i >= 0; i--) {
                ModelData d = lists.get(i);
                chart.addData(new ModelChart(d.getBulan(), new double[]{d.getPenjualan(), d.getPembelian(), d.getBarang_rusak()}));
            }
            
            chart.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDataPolar() {
        try {
            List<ModelPolarAreaChart> list = new ArrayList<>();
            conn = Koneks.getConnection();
            PreparedStatement st = null;
            ResultSet rs = null;

            String sql =
                "SELECT k.nama_kategori, SUM(COALESCE(p.stok, 0)) AS jumlah " +
                "FROM kategori k " +
                "LEFT JOIN produk p ON k.id_kategori = p.id_kategori " +
                "GROUP BY k.id_kategori, k.nama_kategori";

            Color[] colors = {
                new Color(0xE6D1F2), new Color(0xFAEBB2),
                new Color(0xC3EEFA), new Color(0xFFB7BC)
            };

            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            int index = 0; 
            while (rs.next()) {
                String category = rs.getString("nama_kategori");
                double count = rs.getDouble("jumlah");

                ModelPolarAreaChart item = new ModelPolarAreaChart();
                item.setName(category);
                item.setValues(count);
                item.setColor(colors[index % colors.length]);

                polarAreaChart.addItem(item);
                index++;
            }

            rs.close();
            st.close();

            if (polarAreaChart != null) {
                polarAreaChart.start();
            }

        } catch (Exception e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void loadData() {
        notifKurang();
        tampilkanProdukStokRendah();
        totalStok();
        totalPenjualan();
        totalPembelian();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        notif = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        lbPro = new javax.swing.JLabel();
        viewPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbKar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmd = new com.grafik.BagdeButton();
        pnPd = new javax.swing.JPanel();
        lbIconPd = new javax.swing.JLabel();
        lbPd = new javax.swing.JLabel();
        lbJumlahPd = new javax.swing.JLabel();
        pnTbl = new javax.swing.JPanel();
        panelShadow1 = new raven.panel.PanelShadow();
        chart = new com.grafik.CurveLineChart();
        polarAreaChart = new com.grafik.PolarArea();
        jLabel4 = new javax.swing.JLabel();
        pnPj = new javax.swing.JPanel();
        lbIconPj = new javax.swing.JLabel();
        lbPj = new javax.swing.JLabel();
        lbJumlahPj = new javax.swing.JLabel();
        pnPb = new javax.swing.JPanel();
        lbIconPb = new javax.swing.JLabel();
        lbPb = new javax.swing.JLabel();
        lbJumlahPb = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1000, 500));

        notif.setBackground(new java.awt.Color(255, 255, 255));
        notif.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 1, true));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Notifications");

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setOpaque(false);

        lbPro.setText("jLabel5");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPro, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPro)
                .addContainerGap(523, Short.MAX_VALUE))
        );

        scroll.setViewportView(panel);

        javax.swing.GroupLayout notifLayout = new javax.swing.GroupLayout(notif);
        notif.setLayout(notifLayout);
        notifLayout.setHorizontalGroup(
            notifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notifLayout.createSequentialGroup()
                .addGroup(notifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(notifLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(notifLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scroll)))
                .addContainerGap())
        );
        notifLayout.setVerticalGroup(
            notifLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notifLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel6)
                .addGap(1, 1, 1)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        viewPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setText("MAIN");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("/ Dashboard");

        lbKar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbKar.setForeground(new java.awt.Color(153, 153, 255));
        lbKar.setText("WELCOME BACK,");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Dashboard");

        cmd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/icons8-notification-30.png"))); // NOI18N
        cmd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActionPerformed(evt);
            }
        });

        pnPd.setBackground(new java.awt.Color(230, 209, 242));
        pnPd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lbPd.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbPd.setForeground(new java.awt.Color(0, 0, 0));
        lbPd.setText("Produk");

        lbJumlahPd.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbJumlahPd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbJumlahPd.setText("jLabel6");

        javax.swing.GroupLayout pnPdLayout = new javax.swing.GroupLayout(pnPd);
        pnPd.setLayout(pnPdLayout);
        pnPdLayout.setHorizontalGroup(
            pnPdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPdLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIconPd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnPdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbJumlahPd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        pnPdLayout.setVerticalGroup(
            pnPdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnPdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbPd)
                    .addComponent(lbIconPd, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbJumlahPd, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnTbl.setBackground(new java.awt.Color(242, 234, 246));

        panelShadow1.setBackground(new java.awt.Color(195, 238, 250));
        panelShadow1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelShadow1.setColorGradient(new java.awt.Color(160, 219, 255));

        chart.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Banyak stok per kategori");

        javax.swing.GroupLayout pnTblLayout = new javax.swing.GroupLayout(pnTbl);
        pnTbl.setLayout(pnTblLayout);
        pnTblLayout.setHorizontalGroup(
            pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTblLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(polarAreaChart, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnTblLayout.setVerticalGroup(
            pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTblLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(polarAreaChart, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnPj.setBackground(new java.awt.Color(230, 209, 242));
        pnPj.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lbPj.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbPj.setForeground(new java.awt.Color(0, 0, 0));
        lbPj.setText("Penjualan");

        lbJumlahPj.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbJumlahPj.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbJumlahPj.setText("jLabel6");

        javax.swing.GroupLayout pnPjLayout = new javax.swing.GroupLayout(pnPj);
        pnPj.setLayout(pnPjLayout);
        pnPjLayout.setHorizontalGroup(
            pnPjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPjLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIconPj, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnPjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPj, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbJumlahPj, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        pnPjLayout.setVerticalGroup(
            pnPjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPjLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnPjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbPj)
                    .addComponent(lbIconPj, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbJumlahPj, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnPb.setBackground(new java.awt.Color(230, 209, 242));
        pnPb.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        lbPb.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lbPb.setForeground(new java.awt.Color(0, 0, 0));
        lbPb.setText("Pembelian");

        lbJumlahPb.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lbJumlahPb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbJumlahPb.setText("jLabel6");

        javax.swing.GroupLayout pnPbLayout = new javax.swing.GroupLayout(pnPb);
        pnPb.setLayout(pnPbLayout);
        pnPbLayout.setHorizontalGroup(
            pnPbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPbLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIconPb, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnPbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPb, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbJumlahPb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        pnPbLayout.setVerticalGroup(
            pnPbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPbLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnPbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbPb)
                    .addComponent(lbIconPb, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbJumlahPb, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnTbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(viewPanelLayout.createSequentialGroup()
                        .addGroup(viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewPanelLayout.createSequentialGroup()
                                .addComponent(pnPd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(28, 28, 28)
                                .addComponent(pnPj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(32, 32, 32)
                                .addComponent(pnPb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(viewPanelLayout.createSequentialGroup()
                                .addGroup(viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(viewPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbKar, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        viewPanelLayout.setVerticalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(viewPanelLayout.createSequentialGroup()
                        .addGroup(viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbKar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnPd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnPb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnPj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnTbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(498, 498, 498)
                .addComponent(notif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(254, 254, 254))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(viewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(notif, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(viewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActionPerformed
        notif.setVisible(!notif.isVisible());
    }//GEN-LAST:event_cmdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.grafik.CurveLineChart chart;
    private com.grafik.BagdeButton cmd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lbIconPb;
    private javax.swing.JLabel lbIconPd;
    private javax.swing.JLabel lbIconPj;
    private javax.swing.JLabel lbJumlahPb;
    private javax.swing.JLabel lbJumlahPd;
    private javax.swing.JLabel lbJumlahPj;
    private javax.swing.JLabel lbKar;
    private javax.swing.JLabel lbPb;
    private javax.swing.JLabel lbPd;
    private javax.swing.JLabel lbPj;
    private javax.swing.JLabel lbPro;
    private javax.swing.JPanel notif;
    private javax.swing.JPanel panel;
    private raven.panel.PanelShadow panelShadow1;
    private javax.swing.JPanel pnPb;
    private javax.swing.JPanel pnPd;
    private javax.swing.JPanel pnPj;
    private javax.swing.JPanel pnTbl;
    private com.grafik.PolarArea polarAreaChart;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables

    private void totalStok() {
        try {
            DashboardDAO dashboardDAO = new DashboardDAO();
            int total = dashboardDAO.tampilTotalStock();
            lbJumlahPd.setText(String.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void totalPenjualan() {
        try {
            DashboardDAO dashboardDAO = new DashboardDAO();
            int total = dashboardDAO.tampilTotalPenjualan();
            lbJumlahPj.setText(String.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void totalPembelian() {
        try {
            DashboardDAO dashboardDAO = new DashboardDAO();
            int total = dashboardDAO.tampilTotalPembelian();
            lbJumlahPb.setText(String.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notifKurang() {
        try {
            conn = Koneks.getConnection();
            String sql = "SELECT COUNT(nama_produk) AS total_stok FROM produk WHERE stok <= 10";

            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total_stok");
                cmd.setText(""+ total);
            } else {
                cmd.setText("");
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            cmd.setText("Error retrieving data.");
        }
    }

    private void tampilkanProdukStokRendah() {
        try {
            conn = Koneks.getConnection();
            String sql = "SELECT nama_produk, stok FROM produk WHERE stok <= 10";

            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            StringBuilder sb = new StringBuilder("<html>");

            while (rs.next()) {
                String nama = rs.getString("nama_produk");
                int stok = rs.getInt("stok");
                sb.append("- Produk ").append(nama).append(" berjumlah ").append(stok).append("<br>");
            }

            sb.append("</html>");
            lbPro.setText(sb.toString());

            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
