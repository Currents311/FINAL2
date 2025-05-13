package com.form;

import com.dao.DetailPenjualanDAO;
import com.dao.PelangganDAO;
import com.dao.PenjualanDAO;
import com.dao.PenjualanSmtDAO;
import com.dao.ProdukDAO;
import com.dao.ReportDAO;
import com.formdev.flatlaf.FlatClientProperties;
import com.jdialog.DataDetailPenjualan;
import com.jdialog.DataProduk;
import com.model.ModelDetailPenjualan;
import com.model.ModelKaryawan;
import com.model.ModelPelanggan;
import com.model.ModelPenjualan;
import com.model.ModelPenjualanSmt;
import com.model.ModelProduk;
import com.model.Pagination;
import com.service.ServiceDetailPenjualan;
import com.service.ServicePelanggan;
import com.service.ServicePenjualan;
import com.service.ServicePenjualanSmt;
import com.service.ServiceProduk;
import com.service.ServiceReport;
import com.tablemodel.TableModelPenjualan;
import com.tablemodel.TableModelPenjualanSmt;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.table.TableColumnModel;

public class FormPenjualan extends javax.swing.JPanel {

    private TableModelPenjualan tblModelPen = new TableModelPenjualan();
    private TableModelPenjualanSmt tblModelSmt = new TableModelPenjualanSmt();
    
    private ServicePenjualan servis = new PenjualanDAO();
    private ServiceDetailPenjualan servisDet = new DetailPenjualanDAO();
    private ServicePenjualanSmt servisSmt = new PenjualanSmtDAO();
    private ServicePelanggan servisPel = new PelangganDAO();
    private ServiceProduk servisProd = new ProdukDAO();
    private ServiceReport servisReport = new ReportDAO();
    private Pagination pagination;

    private Integer idProduk;
    private Integer idPelanggan;
    private Integer idKaryawan;
    private String role;
    private int totalJumlah;

    
    private final Map<String, Integer> hashMap;
    private Timer timer;
    
    public FormPenjualan(ModelKaryawan modelKar, String role) {
        initComponents();
        
        this.idKaryawan = modelKar.getIdKaryawan();
        this.role = role;
        tblData.setModel(tblModelPen);
        tblDataSmt.setModel(tblModelSmt);
        txtNamaKasir.setText(modelKar.getNamaKaryawan());
        pagination = new Pagination(10);
        
        hashMap = new HashMap<>();
        loadData();
        loadDataSementara();
        
        setLebarKolom();
        setLayoutForm();
        init();
        setTanggal();
        viewPanel.setVisible(true);
        addPanel.setVisible(false);
    }
    
    private void setLebarKolom(){
        TableColumnModel kolom = tblData.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(50);
        kolom.getColumn(0).setMaxWidth(50);
        kolom.getColumn(0).setMinWidth(50);
        
        TableColumnModel kolom4 = tblData.getColumnModel();
        kolom4.getColumn(1).setPreferredWidth(130);
        kolom4.getColumn(1).setMaxWidth(130);
        kolom4.getColumn(1).setMinWidth(130);
        
        TableColumnModel kolom1 = tblData.getColumnModel();
        kolom1.getColumn(2).setPreferredWidth(50);
        kolom1.getColumn(2).setMaxWidth(50);
        kolom1.getColumn(2).setMinWidth(50);
        
        TableColumnModel kolom2 = tblData.getColumnModel();
        kolom2.getColumn(10).setPreferredWidth(50);
        kolom2.getColumn(10).setMaxWidth(50);
        kolom2.getColumn(10).setMinWidth(50);
        
        TableColumnModel kolom3 = tblDataSmt.getColumnModel();
        kolom3.getColumn(0).setPreferredWidth(50);
        kolom3.getColumn(0).setMaxWidth(50);
        kolom3.getColumn(0).setMinWidth(50);
    }
    
    private void setLayoutForm(){
        txtPencarian.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Pencarian");
        txtPelanggan.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Scan card member");
        txtTransaksi.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "No. Transaksi");
        txtNamaKasir.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nama Kasir");
        txtBarcode.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Scan Barcode disini");
        txtNamaProduk.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nama Produk");
        txtHarga.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Harga");
        txtStok.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Stok");
        txtJumlah.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Jumlah");
        txtSubtotal.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Subtotal");
        txtPersen.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "0");        
        txtBayar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Bayar");
        txtPotongan.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Diskon");
        txtTotalHarga.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Total Harga");
        txtKembalian.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Kembalian");
    }
    
    private void init(){
        InputMap inputMap = viewPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = viewPanel.getActionMap();
        
        inputMap.put(KeyStroke.getKeyStroke("F1"), "clickButton");
        actionMap.put("clickButton", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnTambah.doClick();
            }
        });
    }
    
    private void setTanggal(){
        timer = new Timer (1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar calendar = Calendar.getInstance();
                Date now = new Date();
                SimpleDateFormat formatHari = new SimpleDateFormat("EEEE", new Locale("in", "ID"));
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String hari = formatHari.format(calendar.getTime());
                String dateTime = dateFormat.format(now);
                lbHari.setText(hari+",");
                lbTanggal.setText(dateTime);
            }
        });
        timer.start();
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
        btnDetail = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        pnTbl = new javax.swing.JPanel();
        txtPencarian = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lbInfoPage = new javax.swing.JLabel();
        btnFirstPage1 = new javax.swing.JButton();
        btnBefore = new javax.swing.JButton();
        btnHalamanSaatIni = new javax.swing.JButton();
        lbOfTotalHalaman = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLastPage = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        addPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbTanggal = new javax.swing.JLabel();
        lbHari = new javax.swing.JLabel();
        pnBtn1 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtTransaksi = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtNamaKasir = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        cbxPelanggan = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        lbTotalHarga = new javax.swing.JLabel();
        pnTbl1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDataSmt = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNamaProduk = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        btnProduk = new javax.swing.JButton();
        btnTambahProduk = new javax.swing.JButton();
        btnHapusProduk = new javax.swing.JButton();
        btnBatalProduk = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        txtKembalian = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtPotongan = new javax.swing.JTextField();
        txtPersen = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtTotalHarga = new javax.swing.JTextField();
        txtPelanggan = new javax.swing.JTextField();
        btnScan = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1000, 500));

        viewPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setText("TRANSAKSI");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("/ Penjualan");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Data Penjualan");

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

        btnDetail.setBackground(new java.awt.Color(250, 235, 178));
        btnDetail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnDetail.setForeground(new java.awt.Color(0, 0, 0));
        btnDetail.setText("Detail");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
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

        btnPrint.setBackground(new java.awt.Color(195, 238, 240));
        btnPrint.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 0, 0));
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
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
                .addComponent(btnHapus)
                .addGap(18, 18, 18)
                .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnBtnLayout.setVerticalGroup(
            pnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBtnLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnDetail)
                    .addComponent(btnHapus)
                    .addComponent(btnPrint))
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

        lbInfoPage.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbInfoPage.setForeground(new java.awt.Color(0, 0, 0));
        lbInfoPage.setText("Data 1 - 30 dari Total Data 500");

        btnFirstPage1.setText("<<<");
        btnFirstPage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstPage1ActionPerformed(evt);
            }
        });

        btnBefore.setText("<");
        btnBefore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeforeActionPerformed(evt);
            }
        });

        btnHalamanSaatIni.setText("1");

        lbOfTotalHalaman.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lbOfTotalHalaman.setForeground(new java.awt.Color(0, 0, 0));
        lbOfTotalHalaman.setText("of 999");

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLastPage.setText(">>>");
        btnLastPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastPageActionPerformed(evt);
            }
        });

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
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblData);

        javax.swing.GroupLayout pnTblLayout = new javax.swing.GroupLayout(pnTbl);
        pnTbl.setLayout(pnTblLayout);
        pnTblLayout.setHorizontalGroup(
            pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTblLayout.createSequentialGroup()
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnTblLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(pnTblLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnTblLayout.createSequentialGroup()
                                .addGap(395, 395, 395)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnTblLayout.createSequentialGroup()
                                .addComponent(lbInfoPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(507, 507, 507)
                                .addComponent(btnFirstPage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBefore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHalamanSaatIni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbOfTotalHalaman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(4, 4, 4)
                                .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLastPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(50, 50, 50))
        );
        pnTblLayout.setVerticalGroup(
            pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTblLayout.createSequentialGroup()
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTblLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(437, 437, 437))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTblLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbInfoPage, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(btnHalamanSaatIni)
                    .addComponent(btnLastPage)
                    .addComponent(btnNext)
                    .addComponent(lbOfTotalHalaman, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBefore)
                    .addComponent(btnFirstPage1))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnTbl, javax.swing.GroupLayout.DEFAULT_SIZE, 1039, Short.MAX_VALUE))
                .addContainerGap())
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
                .addContainerGap(89, Short.MAX_VALUE))
        );

        addPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setText("Transaksi > Penjualan > Tambah");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tambah Data Penjualan");

        lbTanggal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbTanggal.setForeground(new java.awt.Color(0, 0, 0));
        lbTanggal.setText("Tanggal");
        lbTanggal.setPreferredSize(new java.awt.Dimension(30, 16));
        lbTanggal.setRequestFocusEnabled(false);

        lbHari.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbHari.setForeground(new java.awt.Color(0, 0, 0));
        lbHari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHari.setText("Hari");
        lbHari.setPreferredSize(new java.awt.Dimension(30, 16));

        pnBtn1.setBackground(new java.awt.Color(230, 209, 242));

        btnSimpan.setBackground(new java.awt.Color(195, 238, 240));
        btnSimpan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnKembali.setBackground(new java.awt.Color(214, 135, 135));
        btnKembali.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnKembali.setForeground(new java.awt.Color(0, 0, 0));
        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(230, 209, 242));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        txtTransaksi.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(230, 209, 242));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kasir", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        txtNamaKasir.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNamaKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtNamaKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(230, 209, 242));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pelanggan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));

        cbxPelanggan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxPelanggan, 0, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(cbxPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(230, 209, 242));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(0, 0, 0));

        lbTotalHarga.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbTotalHarga.setForeground(new java.awt.Color(0, 0, 0));
        lbTotalHarga.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTotalHarga.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTotalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lbTotalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnBtn1Layout = new javax.swing.GroupLayout(pnBtn1);
        pnBtn1.setLayout(pnBtn1Layout);
        pnBtn1Layout.setHorizontalGroup(
            pnBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBtn1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnBtn1Layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnKembali)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        pnBtn1Layout.setVerticalGroup(
            pnBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBtn1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnBtn1Layout.createSequentialGroup()
                        .addGroup(pnBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnKembali)
                            .addComponent(btnSimpan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pnTbl1.setBackground(new java.awt.Color(242, 234, 246));
        pnTbl1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produk", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        tblDataSmt.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDataSmt.setRowHeight(30);
        tblDataSmt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataSmtMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDataSmt);

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Barcode");

        txtBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBarcodeKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Nama Produk");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Harga");

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Stok");

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jumlah");

        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });

        btnProduk.setText("...");
        btnProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdukActionPerformed(evt);
            }
        });

        btnTambahProduk.setBackground(new java.awt.Color(195, 238, 240));
        btnTambahProduk.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnTambahProduk.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahProduk.setText("Tambah");
        btnTambahProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahProdukActionPerformed(evt);
            }
        });

        btnHapusProduk.setBackground(new java.awt.Color(214, 135, 135));
        btnHapusProduk.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnHapusProduk.setForeground(new java.awt.Color(0, 0, 0));
        btnHapusProduk.setText("Hapus");
        btnHapusProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusProdukActionPerformed(evt);
            }
        });

        btnBatalProduk.setBackground(new java.awt.Color(250, 235, 178));
        btnBatalProduk.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnBatalProduk.setForeground(new java.awt.Color(0, 0, 0));
        btnBatalProduk.setText("Batal");
        btnBatalProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalProdukActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTbl1Layout = new javax.swing.GroupLayout(pnTbl1);
        pnTbl1.setLayout(pnTbl1Layout);
        pnTbl1Layout.setHorizontalGroup(
            pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTbl1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTbl1Layout.createSequentialGroup()
                        .addComponent(btnTambahProduk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHapusProduk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBatalProduk)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)
                    .addGroup(pnTbl1Layout.createSequentialGroup()
                        .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnTbl1Layout.createSequentialGroup()
                                .addComponent(txtBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(pnTbl1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaProduk, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStok, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnTbl1Layout.setVerticalGroup(
            pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTbl1Layout.createSequentialGroup()
                .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapusProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBatalProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Sub Total");

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Bayar");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Kembalian");

        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });
        txtBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBayarKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Potongan");

        txtPersen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPersenActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("%");

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Total Harga");

        txtPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPelangganActionPerformed(evt);
            }
        });

        btnScan.setBackground(new java.awt.Color(204, 255, 204));
        btnScan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnScan.setForeground(new java.awt.Color(0, 0, 0));
        btnScan.setText("Scan Card");
        btnScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addPanelLayout = new javax.swing.GroupLayout(addPanel);
        addPanel.setLayout(addPanelLayout);
        addPanelLayout.setHorizontalGroup(
            addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(addPanelLayout.createSequentialGroup()
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnTbl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPersen, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPotongan, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(71, 71, 71)
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnScan))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(addPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addPanelLayout.setVerticalGroup(
            addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnTbl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnScan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPersen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPotongan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(viewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(addPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(12, 12, 12)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 771, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(viewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(27, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        tambahData();
        loadDataSementara();
        btnSimpan.setEnabled(false);
        txtBarcode.requestFocus();
        btnTambahProduk.setText("Tambah");
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        detailPenjualan();
    }//GEN-LAST:event_btnDetailActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        hapusData();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        int row = tblData.getSelectedRow();
        String id = tblData.getValueAt(row, 1).toString();
        servisReport.printStruk(id);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtPencarianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPencarianKeyReleased
        pencarianData();
    }//GEN-LAST:event_txtPencarianKeyReleased

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpanData();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        kembali();
        servisDet.hapusDataSementara();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void tblDataSmtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataSmtMouseClicked
        dataTabelSementara();
    }//GEN-LAST:event_tblDataSmtMouseClicked

    private void txtBarcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeKeyReleased
        pencarianProduk();
    }//GEN-LAST:event_txtBarcodeKeyReleased

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        if(btnTambahProduk.getText().equals("Tambah")){
            txtBarcode.requestFocus();
        }else{
            perbaruiDataSementara(); 
            btnTambahProduk.setText("Tambah");
        }
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void btnProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdukActionPerformed
        pencarianProdukFromDialog();
    }//GEN-LAST:event_btnProdukActionPerformed

    private void btnTambahProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahProdukActionPerformed
        if(btnTambahProduk.getText().equals("Tambah")){
            insertDataSementara();
        }else{
            perbaruiDataSementara(); 
            btnTambahProduk.setText("Tambah");
            txtJumlah.requestFocus();
            txtBarcode.setEnabled(true);
            btnProduk.setEnabled(true);
        }
    }//GEN-LAST:event_btnTambahProdukActionPerformed

    private void btnHapusProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusProdukActionPerformed
        hapusDataSementara();
    }//GEN-LAST:event_btnHapusProdukActionPerformed

    private void btnBatalProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalProdukActionPerformed
        loadDataSementara();
        resetProduk();
        btnTambahProduk.setText("Tambah");
        txtJumlah.requestFocus();
    }//GEN-LAST:event_btnBatalProdukActionPerformed

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed
        pembayaran();
    }//GEN-LAST:event_txtBayarActionPerformed

    private void txtBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBayarKeyReleased
        try {
            String hargaBeliRp = txtBayar.getText().replaceAll("[^\\d]", "");
            if(!hargaBeliRp.isEmpty()){
                double hargaBeli = Double.parseDouble(hargaBeliRp);
                DecimalFormat df = new DecimalFormat("#,###,###");
                txtBayar.setText(df.format(hargaBeli));
            }
        } catch (NumberFormatException e) {
            txtBayar.setText("");
        }
    }//GEN-LAST:event_txtBayarKeyReleased

    private void btnFirstPage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstPage1ActionPerformed
        pagination.halamanPertama();
        loadData();
    }//GEN-LAST:event_btnFirstPage1ActionPerformed

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

    private void txtPersenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersenActionPerformed
        hitungPotongan();
    }//GEN-LAST:event_txtPersenActionPerformed

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        btnDetail.setEnabled(true);
        btnPrint.setEnabled(true);
    }//GEN-LAST:event_tblDataMouseClicked

    private void txtPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPelangganActionPerformed
        hitungPotongan();
    }//GEN-LAST:event_txtPelangganActionPerformed

    private void btnScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanActionPerformed
        txtPelanggan.requestFocus();
    }//GEN-LAST:event_btnScanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addPanel;
    private javax.swing.JButton btnBatalProduk;
    private javax.swing.JButton btnBefore;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnFirstPage1;
    private javax.swing.JButton btnHalamanSaatIni;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHapusProduk;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnLastPage;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnProduk;
    private javax.swing.JButton btnScan;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTambahProduk;
    private javax.swing.JComboBox<String> cbxPelanggan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbHari;
    private javax.swing.JLabel lbInfoPage;
    private javax.swing.JLabel lbOfTotalHalaman;
    private javax.swing.JLabel lbTanggal;
    private javax.swing.JLabel lbTotalHarga;
    private javax.swing.JPanel pnBtn;
    private javax.swing.JPanel pnBtn1;
    private javax.swing.JPanel pnTbl;
    private javax.swing.JPanel pnTbl1;
    private javax.swing.JTable tblData;
    private javax.swing.JTable tblDataSmt;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKembalian;
    private javax.swing.JTextField txtNamaKasir;
    private javax.swing.JTextField txtNamaProduk;
    private javax.swing.JTextField txtPelanggan;
    private javax.swing.JTextField txtPencarian;
    private javax.swing.JTextField txtPersen;
    private javax.swing.JTextField txtPotongan;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotalHarga;
    private javax.swing.JTextField txtTransaksi;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
    
    private void showPanel(){
        viewPanel.setVisible(true);
        addPanel.setVisible(false);
//        mainPanel.removeAll();
//        mainPanel.add(viewPanel);
//        mainPanel.repaint();
//        mainPanel.revalidate();
    }
    
    private void loadData() {  
        txtTransaksi.setText(servis.generateNoTransaksi());
        getPelanggan();
        btnDetail.setEnabled(false);
        btnPrint.setEnabled(false);
        int totalData = servis.getTotalData();
        pagination.setTotalData(totalData);
        
        int posisiAwal = pagination.getPosisiAwal();
        int dataPerHalaman = pagination.getDataPerHalaman();
        List<ModelPenjualan> list = servis.tampilData(posisiAwal, dataPerHalaman);
        tblModelPen.setData(list);
        
        if (totalData > 0){
            lbInfoPage.setText("Data " + (posisiAwal + 1) + " - " + Math.min(posisiAwal + dataPerHalaman, totalData) + " of Total Data " + totalData);
        } else {
            lbInfoPage.setText("Data 0 of Total Data 0");
        }
        
        btnHalamanSaatIni.setText(String.valueOf(pagination.getHalamanSaatIni()));
        lbOfTotalHalaman.setText("of " + pagination.getTotalHalaman());
    }
        
    private void tambahData() {
        viewPanel.setVisible(false);
        addPanel.setVisible(true);
//        mainPanel.removeAll();
//        mainPanel.add(addPanel);
//        mainPanel.repaint();
//        mainPanel.revalidate();
    }
    
    private void kembali(){
        viewPanel.setVisible(true);
        addPanel.setVisible(false);
    }

    private void loadDataSementara() {        
        List<ModelPenjualanSmt> list = servisSmt.tampilData();
        tblModelSmt.setData(list);

        nonAktif();
        txtBarcode.setEnabled(true);
        btnProduk.setEnabled(true);
        btnTambahProduk.setEnabled(false);
    }
    
    private void hapusData() {
        int row = tblData.getSelectedRow();
        if(row != -1){
            ModelPenjualan modelPen = tblModelPen.getData(row);
            if(JOptionPane.showConfirmDialog(null, "Yakin data akan dihapus?", 
                    "Konfirmasi", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
            {
                servis.updateStokDelete(modelPen);
                servis.hapusData(modelPen);
                servis.deleteNoTransaksi();
                servis.resetNoTransaksi();
                tblModelPen.deleteData(row);
                loadData();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Pilih dahulu data yang akan dihapus");
        }
    }    

    private void pencarianData(){
        String keyword = txtPencarian.getText();
        int posisiAwal = pagination.getPosisiAwal();
        int dataPerHalaman = pagination.getDataPerHalaman();
        
        List<ModelPenjualan> list =servis.pencarianData(keyword, posisiAwal, dataPerHalaman);
        tblModelPen.setData(list);
    }

    private void detailPenjualan(){
        int row = tblData.getSelectedRow();
        String id = tblData.getValueAt(row, 1).toString();
        DataDetailPenjualan dataDetail = new DataDetailPenjualan(null, true, id);
        dataDetail.setVisible(true);
        loadData();
    }

    private void aktif(){
        txtBarcode.setEnabled(true);
        txtNamaProduk.setEnabled(true);
        txtHarga.setEnabled(true);
        txtStok.setEnabled(true);
        txtJumlah.setEnabled(true);
        
        btnProduk.setEnabled(true);
        btnTambahProduk.setEnabled(true);
        btnHapusProduk.setEnabled(true);
    }
    
    private void nonAktif(){
        txtTransaksi.setEditable(false);
        txtNamaKasir.setEditable(false);
        txtNamaProduk.setEditable(false);
        txtHarga.setEditable(false);
        txtStok.setEditable(false);
        
        btnProduk.setEnabled(false);
        btnTambahProduk.setEnabled(false);
        btnHapusProduk.setEnabled(false);
        btnBatalProduk.setEnabled(false);
    }
    
    private void resetProduk(){
        txtBarcode.setText("");
        txtNamaProduk.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        txtJumlah.setText("");
    }
    
    private void resetBayarSementara(){
        txtPersen.setText("");
        txtPotongan.setText("0");
        txtBayar.setText("");
        txtKembalian.setText("");
    }
    
    private void resetPembayaran() {
        txtPersen.setText("");
        txtPotongan.setText("");
        txtSubtotal.setText("");
        txtTotalHarga.setText("");
        txtBayar.setText("");
        txtKembalian.setText("");
        lbTotalHarga.setText("0");
    }
    
    private void getPelanggan(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Pilih Pelanggan");
        
        List<ModelPelanggan> list = servisPel.ambilPelanggan();
        for(ModelPelanggan modelPel : list){
            model.addElement(modelPel.getNamaPelanggan());
            hashMap.put(modelPel.getNamaPelanggan(), modelPel.getIdPelanggan());
        }
        
        cbxPelanggan.setModel(model);
        cbxPelanggan.addActionListener(e -> {
            String namaPelanggan = cbxPelanggan.getSelectedItem().toString();
            if(!"Pilih Pelanggan".equals(namaPelanggan)){
                idPelanggan = hashMap.get(namaPelanggan);
            }
        });
    }
    
    private void pencarianProduk(){
        String keyword = txtBarcode.getText();
        int posisiAwal = pagination.getPosisiAwal();
        int dataPerHalaman = pagination.getDataPerHalaman();
        
        List<ModelProduk> list = servisProd.pencarianDataByBarcode(keyword, posisiAwal, dataPerHalaman);
        
        if(!list.isEmpty()) {
            ModelProduk produk = list.get(0);
            
            boolean produkSudahAda = false;
            
        int rowIndex = -1;

        for (int i = 0; i < tblModelSmt.getRowCount(); i++) {
            if (tblModelSmt.getData(i).getModelProduk().getBarcode().equals(produk.getBarcode())) {
                produkSudahAda = true;
                rowIndex = i;
                break;
            }
        }

        if (produkSudahAda) {
            int jumlahTambahan = 1; 
            if (!txtJumlah.getText().isEmpty()) {
                try {
                    jumlahTambahan = Integer.parseInt(txtJumlah.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Jumlah tidak valid");
                    return;
                }
            }
            updateJumlah(rowIndex, jumlahTambahan, produk.getHarga());
        } else {
            idProduk = produk.getIdProduk();
            txtBarcode.setText(produk.getBarcode());
            txtNamaProduk.setText(produk.getNamaProduk());
            txtHarga.setText(Double.toString(produk.getHarga()));
            txtStok.setText(Integer.toString(produk.getStok()));

            insertDataSementara();
            }
        }
    }
    
    private void pencarianProdukFromDialog(){
        boolean closable = true;
        DataProduk modelForm = new DataProduk(null, closable);
        modelForm.setVisible(true);
        
        if(modelForm.modelDialog.getBarcode() !=null){
            idProduk = modelForm.modelDialog.getIdProduk();
            txtBarcode.setText(modelForm.modelDialog.getBarcode());
            txtNamaProduk.setText(modelForm.modelDialog.getNamaProduk());
            txtHarga.setText(Double.toString(modelForm.modelDialog.getHarga()));
            txtStok.setText(Integer.toString(modelForm.modelDialog.getStok()));
            
            insertDataSementara();
        }
    }

    private void insertDataSementara(){
        String barcode = txtBarcode.getText();
        String namaProduk = txtNamaProduk.getText();
        double harga = Double.parseDouble(txtHarga.getText());
        int stok = Integer.parseInt(txtStok.getText());
        int jumlah = 1;
        double subTotal = harga * jumlah;
        
        if(!txtJumlah.getText().isEmpty()){
            try {
                jumlah = Integer.parseInt(txtJumlah.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Jumlah tidak valid");
                return;
            }
        }

        boolean produkSudahAda = false;
        for(int i = 0; i < tblModelSmt.getRowCount(); i++) {
            if (tblModelSmt.getData(i).getModelProduk().getBarcode().equals(barcode)) {
                produkSudahAda = true;
                updateJumlah(i, jumlah, harga);
                break;
            }
        }

        if(!produkSudahAda){
            ModelPenjualanSmt smt = new ModelPenjualanSmt();
            ModelProduk pd = new ModelProduk();
            ModelDetailPenjualan det = new ModelDetailPenjualan();

            pd.setIdProduk(idProduk);
            pd.setBarcode(barcode);
            pd.setNamaProduk(namaProduk);
            pd.setHarga(harga);
            pd.setStok(stok);


            det.setJumlah(jumlah);
            det.setSubTotal(subTotal);

            smt.setModelProduk(pd);
            smt.setModelDetPen(det);

            servisSmt.tambahData(smt);
            servisDet.sumTotal(det);
            servisDet.sumJumlah(det);
            totalJumlah = det.getJumlah();
            
            DecimalFormat df1 = new DecimalFormat("#,##0");
            DecimalFormat df2 = new DecimalFormat("#,##0.00");
            double jumlahSubtotal = det.getSubTotal();
            
            String totalNoDecimal = df1.format(jumlahSubtotal);
            String totalDecimal = df2.format(jumlahSubtotal);
             
            txtSubtotal.setText(String.valueOf(det.getSubTotal()));
            String total = txtSubtotal.getText();
            txtSubtotal.setText(totalNoDecimal);
            txtTotalHarga.setText(totalNoDecimal);
            lbTotalHarga.setText("Rp. " + totalDecimal);

            loadDataSementara();
            resetProduk();
            resetBayarSementara();
        } 
    }
    
    private void updateJumlah(int rowIndex, int jumlah, double harga){
        ModelDetailPenjualan det = tblModelSmt.getData(rowIndex).getModelDetPen();
        int jumlahLama = det.getJumlah();
        int jumlahBaru = jumlahLama + jumlah;
        double subTotalBaru = jumlahBaru * harga;
        
        det.setJumlah(jumlahBaru);
        det.setSubTotal(subTotalBaru);
        servisSmt.perbaruiData(tblModelSmt.getData(rowIndex));
        servisDet.sumTotal(det);
        
        DecimalFormat df1 = new DecimalFormat("#,##0");
        DecimalFormat df2 = new DecimalFormat("#,##0.00");
        double jumlahSubtotal = det.getSubTotal();

        String totalNoDecimal = df1.format(jumlahSubtotal);
        String totalDecimal = df2.format(jumlahSubtotal);

        txtSubtotal.setText(totalNoDecimal);
        txtTotalHarga.setText(totalNoDecimal);
        lbTotalHarga.setText("Rp. " + totalDecimal);

        loadDataSementara();
        resetProduk();
        resetBayarSementara();
        
        tblModelSmt.fireTableCellUpdated(rowIndex, rowIndex );
    }

    private void dataTabelSementara(){
        btnTambahProduk.setText("Perbarui");
        int row = tblDataSmt.getSelectedRow();
        
        idProduk = Integer.valueOf(tblDataSmt.getValueAt(row, 1).toString().trim());
        txtBarcode.setText(tblDataSmt.getValueAt(row, 2).toString());
        txtNamaProduk.setText(tblDataSmt.getValueAt(row, 3).toString());
        
        DecimalFormat df = new DecimalFormat("#,##0.##");
        String hargaStr = tblDataSmt.getValueAt(row, 4).toString().trim();
        double harga = Double.parseDouble(hargaStr.replaceAll("[^\\d.]", ""));
        String hargaFormat = df.format(harga);
        txtHarga.setText(hargaFormat.replace(".00", ""));
        
        txtStok.setText(tblDataSmt.getValueAt(row, 5).toString());
        txtJumlah.setText(tblDataSmt.getValueAt(row, 6).toString());
        nonAktif();
        txtJumlah.setEditable(true);
        btnTambahProduk.setEnabled(true);
        btnHapusProduk.setEnabled(true);
        btnBatalProduk.setEnabled(true);
        txtBarcode.requestFocus();
    }
    
    private void perbaruiDataSementara(){
        if(!txtJumlah.getText().equals("")){
            String barcode = txtBarcode.getText();
            String namaProduk = txtNamaProduk.getText();
            double harga = Double.valueOf(txtHarga.getText().replace(",", ""));
            int stok = Integer.valueOf(txtStok.getText());
            int jumlah = Integer.valueOf(txtJumlah.getText());
            
            if (jumlah > stok) {
                JOptionPane.showMessageDialog(null, "Jumlah melebihi stok tersedia!");
                return;
            }
            
            double subTotal = harga * jumlah;
            
            ModelPenjualanSmt smt = new ModelPenjualanSmt();
            ModelProduk pd = new ModelProduk();
            ModelDetailPenjualan det = new ModelDetailPenjualan();
                
            pd.setIdProduk(idProduk);
            pd.setBarcode(barcode);
            pd.setNamaProduk(namaProduk);
            pd.setHarga(harga);
            pd.setStok(stok);
                   
            det.setJumlah(jumlah);
            det.setSubTotal(subTotal);
            
            smt.setModelProduk(pd);
            smt.setModelDetPen(det);
            
            servisSmt.perbaruiData(smt);
            servisDet.sumTotal(det);
            servisDet.sumJumlah(det);
            totalJumlah = det.getJumlah();
            
            DecimalFormat df1 = new DecimalFormat("#,##0");
            DecimalFormat df2 = new DecimalFormat("#,##0.00");
            double jumlahSubtotal = det.getSubTotal();
            
            String totalNoDecimal = df1.format(jumlahSubtotal);
            String totalDecimal = df2.format(jumlahSubtotal);
            
            txtSubtotal.setText(totalNoDecimal);
            txtTotalHarga.setText(totalNoDecimal);
            lbTotalHarga.setText("Rp. " + totalDecimal);
    
            loadDataSementara();
            resetProduk();
        } else{
            JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong !");
        }    
    }
    
    private void hapusDataSementara(){
        int row = tblDataSmt.getSelectedRow();
        if(row != -1){
            ModelPenjualanSmt model = tblModelSmt.getData(row);
            if(JOptionPane.showConfirmDialog(null, "Yakin data akan dihapus?",
                    "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)== JOptionPane.OK_OPTION)
            {
                servisSmt.hapusData(model);
                tblModelSmt.deleteData(row);
                loadDataSementara();
                resetProduk();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Pilih dahulu data yang akan dihapus");
        }
    }
    
    private void pembayaran(){
        try {
            String totalStr = txtTotalHarga.getText().replace(",", "");
            double total = Double.parseDouble(totalStr);
            double bayar = Double.parseDouble(txtBayar.getText().replace(",", ""));
            double kembalian = bayar - total;
            
            DecimalFormat df1 = new DecimalFormat("#,##0");
            String totalKembalian = df1.format(kembalian);
            
            txtKembalian.setText(totalKembalian);
            btnSimpan.setEnabled(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan nominal pembayaran yang valid");
        }
    }
    
    private boolean validasiSimpan(){
        boolean valid = false;
        if(idPelanggan == null){
            JOptionPane.showMessageDialog(null, "Silahkan pilih jenis pelanggan");
        }else if(txtTotalHarga.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Total harga tidak boleh kosong");
        }else if(idKaryawan == null){
            JOptionPane.showMessageDialog(null, "ID Karyawan tidak boleh kosong");
        }else{
            valid = true;
        }
        return valid;
    }
    
    private void simpanData(){
        if(validasiSimpan()== true){
            String idPenjualan = txtTransaksi.getText();
            String tanggal = lbTanggal.getText();
            double total = Double.parseDouble(txtTotalHarga.getText().replace(",", ""));
            double bayar = Double.parseDouble(txtBayar.getText().replace(",", ""));
            double diskon = Double.parseDouble(txtPotongan.getText().replace(",", ""));
            double kembalian = Double.parseDouble(txtKembalian.getText().replace(",", ""));
            
            ModelPenjualan modelPen = new ModelPenjualan();
            ModelProduk modelProd = new ModelProduk();
            ModelPelanggan modelPel = new ModelPelanggan();
            ModelKaryawan modelKar = new ModelKaryawan();
            ModelDetailPenjualan modelDet = new ModelDetailPenjualan();
            
            modelPen.setIdPenjualan(idPenjualan);
            modelPen.setTanggal(tanggal);
            modelPen.setTotalJumlah(totalJumlah);
            modelPen.setTotalHarga(total);
            modelPen.setBayar(bayar);
            modelPen.setDiskon(diskon);
            modelPen.setKembalian(kembalian);
            modelPel.setIdPelanggan(idPelanggan);
            modelKar.setIdKaryawan(idKaryawan);
            
            modelPen.setModelPelanggan(modelPel);
            modelPen.setModelKaryawan(modelKar);
            
            modelDet.setModelPenjualan(modelPen);
            modelDet.setModelProduk(modelProd);
            
            servis.tambahData(modelPen);
            servisDet.tambahData(modelDet);
            servis.updateStok(modelPen);
            servisDet.hapusDataSementara();
            tblModelPen.insertData(modelPen);
            servisPel.updateLevelMember();
            servisReport.printStruk(idPenjualan);
            servis.simpanNoTransaksi();
            showPanel();
            loadData();
            loadDataSementara();
            resetProduk();
            resetPembayaran();     
        }
    }    

    private void hitungPotongan() {
            try {
                DecimalFormat df1 = new DecimalFormat("#,##0");
                DecimalFormat df2 = new DecimalFormat("#,##0.00");

                double subtotal = Double.parseDouble(txtSubtotal.getText().replace(",", ""));
                int diskon = 0;
                String kodeRfid = txtPelanggan.getText().trim();
            if (!kodeRfid.isEmpty()) {
                String level = servisPel.getLevelMember(kodeRfid);
                if (level != null && !level.equalsIgnoreCase("-")) {
                    switch (level) {
                        case "Bronze":
                            diskon = 5;
                            break;
                        case "Silver":
                            diskon = 20;
                            break;
                        case "Gold":
                            diskon = 40;
                            break;
                    }
                }
            }
            txtPersen.setText(String.valueOf(diskon)); 

            double hasilDiskon = subtotal * (diskon / 100.0);
            double total = subtotal - hasilDiskon;
            
            String totalDiskon = df1.format(hasilDiskon);
            String totalHarga = df1.format(total);
            String totalHargaDecimal = df2.format(total);
            
            txtPotongan.setText(String.valueOf(totalDiskon));
            txtTotalHarga.setText(String.valueOf(totalHarga));
            lbTotalHarga.setText("Rp. " + totalHargaDecimal);
            
            txtBayar.requestFocus();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid untuk diskon");
        }
    }
}
