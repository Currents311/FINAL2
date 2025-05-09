package com.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.dao.SupplierDAO;
import com.dao.BarangRusakDAO;
import com.dao.DetailBarangRusakDAO;
import com.dao.BarangRusakSmtDAO;
import com.dao.ProdukDAO;
import com.dao.ReportDAO;
import com.jdialog.DataPembelian;
import com.jdialog.DataDetailBarangRusak;
import com.model.ModelBarangRusak;
import com.model.ModelKaryawan;
import com.model.ModelSupplier;
import com.model.ModelPembelian;
import com.model.ModelDetailBarangRusak;
import com.model.ModelBarangRusakSmt;
import com.model.ModelProduk;
import com.model.Pagination;
import com.service.ServiceSupplier;
import com.service.ServiceBarangRusak;
import com.service.ServiceDetailBarangRusak;
import com.service.ServiceBarangRusakSmt;
import com.service.ServiceProduk;
import com.service.ServiceReport;
import com.tablemodel.TableModelBarangRusak;
import com.tablemodel.TableModelBarangRusakSmt;
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

public class FormBarangRusak extends javax.swing.JPanel {

    private TableModelBarangRusak tblModelBr = new TableModelBarangRusak();
    private TableModelBarangRusakSmt tblModelSmt = new TableModelBarangRusakSmt();
    
    private ServiceBarangRusak servis = new BarangRusakDAO();
    private ServiceDetailBarangRusak servisDet = new DetailBarangRusakDAO();
    private ServiceBarangRusakSmt servisSmt = new BarangRusakSmtDAO();
    private ServiceSupplier servisSup = new SupplierDAO();
    private ServiceProduk servisProd = new ProdukDAO();
    private ServiceReport servisReport = new ReportDAO();
    private Pagination pagination;

    private Integer idProduk;
    private Integer idSupplier;
    private Integer idKaryawan;
    private String role;
    
    private final Map<String, Integer> hashMap;
    private Timer timer;
    
    public FormBarangRusak(ModelKaryawan modelKar, String role) {
        initComponents();
        
        this.idKaryawan = modelKar.getIdKaryawan();
        this.role = role;
        tblData.setModel(tblModelBr);
        tblDataSmt.setModel(tblModelSmt);
        txtNamaKasir.setText(modelKar.getNamaKaryawan());
        pagination = new Pagination(10);
        
        hashMap = new HashMap<>();
        loadData();
        loadDataSementara();
        
        setLebarKolom();
        setLayoutForm();
        setTanggal();
        viewPanel.setVisible(true);
        addPanel.setVisible(false);
    }
    
    private void setLebarKolom(){
        TableColumnModel kolom = tblData.getColumnModel();
        kolom.getColumn(0).setPreferredWidth(50);
        kolom.getColumn(0).setMaxWidth(50);
        kolom.getColumn(0).setMinWidth(50);
        
        TableColumnModel kolom1 = tblData.getColumnModel();
        kolom1.getColumn(2).setPreferredWidth(50);
        kolom1.getColumn(2).setMaxWidth(50);
        kolom1.getColumn(2).setMinWidth(50);
        
        TableColumnModel kolom2 = tblData.getColumnModel();
        kolom2.getColumn(6).setPreferredWidth(50);
        kolom2.getColumn(6).setMaxWidth(50);
        kolom2.getColumn(6).setMinWidth(50);
        
        TableColumnModel kolom3 = tblDataSmt.getColumnModel();
        kolom3.getColumn(0).setPreferredWidth(50);
        kolom3.getColumn(0).setMaxWidth(50);
        kolom3.getColumn(0).setMinWidth(50);
    }
    
    private void setLayoutForm(){
        txtPencarian.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Pencarian");
        txtTransaksi.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "No. Transaksi");
        txtNamaKasir.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nama Kasir");
        txtPembelianID.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Scan ID Pembelian disini");
        txtTanggal.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tanggal");        
        txtBarcode.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Barcode");
        txtNamaProduk.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nama Produk");
        txtHarga.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Harga");
        txtJumlah.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Jumlah");
        txtAlasan.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Alasan");
        txtTotalRetur.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Subtotal");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        lbInfoPage = new javax.swing.JLabel();
        btnFirstPage = new javax.swing.JButton();
        btnBefore = new javax.swing.JButton();
        btnHalamanSaatIni = new javax.swing.JButton();
        lbOfTotalHalaman = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLastPage = new javax.swing.JButton();
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
        cbxSupplier = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        lbTotalRetur = new javax.swing.JLabel();
        pnTbl1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDataSmt = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtPembelianID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNamaProduk = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtAlasan = new javax.swing.JTextField();
        btnPembelian = new javax.swing.JButton();
        btnTambahPembelian = new javax.swing.JButton();
        btnHapusProduk = new javax.swing.JButton();
        btnBatalProduk = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTotalRetur = new javax.swing.JTextField();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1000, 500));

        viewPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setText("TRANSAKSI");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("/ Barang Rusak");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Data Barang Rusak");

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
        jLabel4.setText("Search :");

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

        lbInfoPage.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbInfoPage.setForeground(new java.awt.Color(0, 0, 0));
        lbInfoPage.setText("Data 1 - 30 dari Total Data 500");

        btnFirstPage.setText("<<<");

        btnBefore.setText("<");

        btnHalamanSaatIni.setText("1");

        lbOfTotalHalaman.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lbOfTotalHalaman.setForeground(new java.awt.Color(0, 0, 0));
        lbOfTotalHalaman.setText("of 999");

        btnNext.setText(">");

        btnLastPage.setText(">>>");

        javax.swing.GroupLayout pnTblLayout = new javax.swing.GroupLayout(pnTbl);
        pnTbl.setLayout(pnTblLayout);
        pnTblLayout.setHorizontalGroup(
            pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTblLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTblLayout.createSequentialGroup()
                        .addComponent(lbInfoPage, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(421, 421, 421)
                        .addComponent(btnFirstPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBefore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHalamanSaatIni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbOfTotalHalaman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLastPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTblLayout.createSequentialGroup()
                        .addGap(663, 663, 663)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(21, 21, 21))
        );
        pnTblLayout.setVerticalGroup(
            pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTblLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbInfoPage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFirstPage)
                    .addComponent(btnBefore)
                    .addComponent(btnHalamanSaatIni)
                    .addComponent(lbOfTotalHalaman)
                    .addComponent(btnNext)
                    .addComponent(btnLastPage))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnTbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(viewPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(86, Short.MAX_VALUE))
        );

        addPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel5.setText("Transaksi > Barang Rusak > Tambah");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tambah Data Barang Rusak");

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
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));

        cbxSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nama Supplier" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxSupplier, 0, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(cbxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(230, 209, 242));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(0, 0, 0));

        lbTotalRetur.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbTotalRetur.setForeground(new java.awt.Color(0, 0, 0));
        lbTotalRetur.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTotalRetur.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTotalRetur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lbTotalRetur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnBtn1Layout = new javax.swing.GroupLayout(pnBtn1);
        pnBtn1.setLayout(pnBtn1Layout);
        pnBtn1Layout.setHorizontalGroup(
            pnBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBtn1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBtn1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnBtn1Layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnKembali)))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnTbl1.setBackground(new java.awt.Color(242, 234, 246));
        pnTbl1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pembelian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N

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
        jLabel6.setText("Id Pembelian");

        txtPembelianID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPembelianIDKeyReleased(evt);
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
        jLabel12.setText("Jumlah");

        txtJumlah.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtJumlahFocusLost(evt);
            }
        });
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Alasan");

        txtAlasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlasanActionPerformed(evt);
            }
        });

        btnPembelian.setText("...");
        btnPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPembelianActionPerformed(evt);
            }
        });

        btnTambahPembelian.setBackground(new java.awt.Color(195, 238, 240));
        btnTambahPembelian.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnTambahPembelian.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahPembelian.setText("Tambah");
        btnTambahPembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPembelianActionPerformed(evt);
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

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Barcode");

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Tanggal");

        javax.swing.GroupLayout pnTbl1Layout = new javax.swing.GroupLayout(pnTbl1);
        pnTbl1.setLayout(pnTbl1Layout);
        pnTbl1Layout.setHorizontalGroup(
            pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTbl1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(pnTbl1Layout.createSequentialGroup()
                        .addComponent(btnTambahPembelian)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapusProduk)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatalProduk)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnTbl1Layout.createSequentialGroup()
                        .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnTbl1Layout.createSequentialGroup()
                                .addComponent(txtPembelianID, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTanggal)
                            .addGroup(pnTbl1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 38, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnTbl1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 56, Short.MAX_VALUE))
                            .addComponent(txtHarga))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAlasan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(jLabel13)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPembelianID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlasan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTbl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahPembelian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapusProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBatalProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Total Retur");

        txtTotalRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalReturActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addPanelLayout = new javax.swing.GroupLayout(addPanel);
        addPanel.setLayout(addPanelLayout);
        addPanelLayout.setHorizontalGroup(
            addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(addPanelLayout.createSequentialGroup()
                            .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(addPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbHari, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(pnTbl1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnBtn1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap()))
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        addPanelLayout.setVerticalGroup(
            addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbHari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnTbl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1111, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(viewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(84, 84, 84)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(46, 46, 46)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(viewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(addPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        tambahData();
        loadDataSementara();
        btnSimpan.setEnabled(false);
        btnPembelian.requestFocus();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        detailBarangRusak();
    }//GEN-LAST:event_btnDetailActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        hapusData();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txtPencarianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPencarianKeyReleased
        pencarianData();
    }//GEN-LAST:event_txtPencarianKeyReleased

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        btnDetail.setEnabled(true);
        btnPrint.setEnabled(true);
    }//GEN-LAST:event_tblDataMouseClicked

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

    private void txtPembelianIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPembelianIDKeyReleased
    }//GEN-LAST:event_txtPembelianIDKeyReleased

    private void txtAlasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlasanActionPerformed
        if(btnTambahPembelian.getText().equals("Tambah")){
            insertDataSementara();
            txtPembelianID.requestFocus();
            btnSimpan.setEnabled(true);
        }else{
            perbaruiDataSementara(); 
            btnTambahPembelian.setText("Tambah");
            btnPembelian.requestFocus();
            btnSimpan.setEnabled(true);
        }
    }//GEN-LAST:event_txtAlasanActionPerformed

    private void btnPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPembelianActionPerformed
        pencarianPembelianDialog();
    }//GEN-LAST:event_btnPembelianActionPerformed

    private void btnTambahPembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPembelianActionPerformed
        if(btnTambahPembelian.getText().equals("Tambah")){
            insertDataSementara();
            btnPembelian.requestFocus();
        }else{
            perbaruiDataSementara(); 
            btnTambahPembelian.setText("Tambah");
            btnPembelian.requestFocus();
            btnPembelian.setEnabled(true);
        }
    }//GEN-LAST:event_btnTambahPembelianActionPerformed

    private void btnHapusProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusProdukActionPerformed
        hapusDataSementara();
        btnSimpan.setEnabled(true);
    }//GEN-LAST:event_btnHapusProdukActionPerformed

    private void btnBatalProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalProdukActionPerformed
        loadDataSementara();
        resetFormPembelian();
        btnTambahPembelian.setText("Tambah");
        btnPembelian.requestFocus();
        btnSimpan.setEnabled(true);
    }//GEN-LAST:event_btnBatalProdukActionPerformed

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        txtAlasan.requestFocus();
        txtAlasan.setEditable(true);
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txtJumlahFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtJumlahFocusLost
        txtAlasan.setEditable(true);
    }//GEN-LAST:event_txtJumlahFocusLost

    private void txtTotalReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalReturActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalReturActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        int row = tblData.getSelectedRow();
        String id = tblData.getValueAt(row, 1).toString();
        servisReport.printNotaBarangRusak(id);
    }//GEN-LAST:event_btnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addPanel;
    private javax.swing.JButton btnBatalProduk;
    private javax.swing.JButton btnBefore;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnFirstPage;
    private javax.swing.JButton btnHalamanSaatIni;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnHapusProduk;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnLastPage;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPembelian;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTambahPembelian;
    private javax.swing.JComboBox<String> cbxSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JLabel lbTotalRetur;
    private javax.swing.JPanel pnBtn;
    private javax.swing.JPanel pnBtn1;
    private javax.swing.JPanel pnTbl;
    private javax.swing.JPanel pnTbl1;
    private javax.swing.JTable tblData;
    private javax.swing.JTable tblDataSmt;
    private javax.swing.JTextField txtAlasan;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtNamaKasir;
    private javax.swing.JTextField txtNamaProduk;
    private javax.swing.JTextField txtPembelianID;
    private javax.swing.JTextField txtPencarian;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTotalRetur;
    private javax.swing.JTextField txtTransaksi;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
    
    private void showPanel(){
        viewPanel.setVisible(true);
        addPanel.setVisible(false);
    }
    
    private void loadData() {  
        txtTransaksi.setText(servis.generateNoTransaksi());
        getSupplier();
        btnDetail.setEnabled(false);
        btnPrint.setEnabled(false);
        int totalData = servis.getTotalData();
        pagination.setTotalData(totalData);
        
        int posisiAwal = pagination.getPosisiAwal();
        int dataPerHalaman = pagination.getDataPerHalaman();
        List<ModelBarangRusak> list = servis.tampilData(posisiAwal, dataPerHalaman);
        tblModelBr.setData(list);
        
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
    }
    
    private void kembali(){
        viewPanel.setVisible(true);
        addPanel.setVisible(false);
    }

    private void loadDataSementara() {        
        List<ModelBarangRusakSmt> list = servisSmt.tampilData();
        tblModelSmt.setData(list);
        
        ModelDetailBarangRusak det = new ModelDetailBarangRusak();
        servisDet.sumTotal(det);
        DecimalFormat df1 = new DecimalFormat("#,##0");        
        DecimalFormat df2 = new DecimalFormat("#,##0.00");
        double jumlahSubTotal = det.getSubtotalRetur();
        
        String totalNoDecimal = df1.format(jumlahSubTotal);
        String totalDecimal = df2.format(jumlahSubTotal);
        txtTotalRetur.setText(totalNoDecimal);
        lbTotalRetur.setText("Rp. " + totalDecimal);
        
        nonAktif();
        btnPembelian.setEnabled(true);
    }
    
    private void hapusData() {
        int row = tblData.getSelectedRow();
        if(row != -1){
            ModelBarangRusak model = tblModelBr.getData(row);
            if(JOptionPane.showConfirmDialog(null, "Yakin data akan dihapus?", 
                    "Konfirmasi", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
            {
                servis.hapusData(model);
                servis.deleteNoTransaksi();
                servis.resetNoTransaksi();
                tblModelBr.deleteData(row);
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
        
        List<ModelBarangRusak> list =servis.pencarianData(keyword, posisiAwal, dataPerHalaman);
        tblModelBr.setData(list);
    }

    private void detailBarangRusak(){
        int row = tblData.getSelectedRow();
        String id = tblData.getValueAt(row, 1).toString();
        DataDetailBarangRusak dataDetail = new DataDetailBarangRusak(null, true, id);
        dataDetail.setVisible(true);
        loadData();
    }

    private void aktif(){
        txtPembelianID.setEnabled(true);
        txtNamaProduk.setEnabled(true);
        txtHarga.setEnabled(true);
        txtJumlah.setEnabled(true);
        txtAlasan.setEnabled(true);
        
        btnPembelian.setEnabled(true);
        btnTambahPembelian.setEnabled(true);
        btnHapusProduk.setEnabled(true);
    }
    
    private void nonAktif(){
        txtTransaksi.setEditable(false);
        txtNamaKasir.setEditable(false);
        txtPembelianID.setEditable(false);
        txtBarcode.setEditable(false);
        txtTanggal.setEditable(false);
        txtNamaProduk.setEditable(false);
        txtHarga.setEditable(false);
        txtAlasan.setEditable(false);
        
        btnPembelian.setEnabled(false);
        btnTambahPembelian.setEnabled(false);
        btnHapusProduk.setEnabled(false);
        btnBatalProduk.setEnabled(false);
    }
    
    private void resetFormPembelian(){
        txtPembelianID.setText("");
        txtTanggal.setText("");
        txtBarcode.setText("");
        txtNamaProduk.setText("");
        txtHarga.setText("");
        txtJumlah.setText("");
        txtAlasan.setText("");
    }
    
    private void resetPembayaran(){
        txtTotalRetur.setText("");
        lbTotalRetur.setText("0");
    }
        
    private void getSupplier(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Pilih Supplier");
        
        List<ModelSupplier> list = servisSup.ambilSupplier();
        for(ModelSupplier modelSup : list){
            model.addElement(modelSup.getNamaSupplier());
            hashMap.put(modelSup.getNamaSupplier(), modelSup.getIdSupplier());
        }
        
        cbxSupplier.setModel(model);
        cbxSupplier.addActionListener(e -> {
            String namaSupplier = cbxSupplier.getSelectedItem().toString();
            if(!"Pilih Supplier".equals(namaSupplier)){
                idSupplier = hashMap.get(namaSupplier);
            }
        });
    }
    
    private void pencarianPembelianDialog(){
        boolean closable = true;
        DataPembelian modelForm = new DataPembelian(null, closable);
        modelForm.setVisible(true);
        
        if(modelForm.modelPembelian.getIdPembelian() !=null){
            txtPembelianID.setText(modelForm.modelPembelian.getIdPembelian());
            txtTanggal.setText(modelForm.modelPembelian.getTanggal());
            idProduk = modelForm.modelProduk.getIdProduk();
            txtBarcode.setText(modelForm.modelProduk.getBarcode());
            txtNamaProduk.setText(modelForm.modelProduk.getNamaProduk());
            
            double harga = modelForm.modelProduk.getHarga();
            DecimalFormat df = new DecimalFormat("#,##0");
            String hargaStr = df.format(harga);
            txtHarga.setText(hargaStr);
            txtJumlah.requestFocus();
            txtJumlah.setEditable(true);            
        }else{
            System.out.println("Anda tidak memilih data Pembelian");
            resetFormPembelian();
        }
    }
    
    private void insertDataSementara(){
        if(!txtJumlah.getText().equals("") && !txtAlasan.getText().equals("")){
            String pembelianID = txtPembelianID.getText();
            String tanggal = txtTanggal.getText();
            String barcode = txtBarcode.getText();
            String namaProduk = txtNamaProduk.getText();
            double harga = Double.parseDouble(txtHarga.getText().replace(",", ""));
            int jumlah = Integer.parseInt(txtJumlah.getText());
            double subTotal = harga * jumlah;
            String alasan = txtAlasan.getText();
            
            ModelBarangRusakSmt smt = new ModelBarangRusakSmt();
            ModelPembelian pb = new ModelPembelian();
            ModelProduk pd = new ModelProduk();
            ModelDetailBarangRusak det = new ModelDetailBarangRusak();
            
            pb.setIdPembelian(pembelianID);
            pb.setTanggal(tanggal);
            
            pd.setIdProduk(idProduk);
            pd.setBarcode(barcode);
            pd.setNamaProduk(namaProduk);
            pd.setHarga(harga);

            det.setJumlahRetur(jumlah);
            det.setSubtotalRetur(subTotal);
            det.setAlasan(alasan);

            smt.setModelPembelian(pb);
            smt.setModelProduk(pd);
            smt.setModelDetailBarangRusak(det);
            
            servisSmt.tambahData(smt);
            servisDet.sumTotal(det);
            
            DecimalFormat df1 = new DecimalFormat("#,##0");
            DecimalFormat df2 = new DecimalFormat("#,##0.00");
            double jumlahSubtotal = det.getSubtotalRetur();
            
            String totalNoDecimal = df1.format(jumlahSubtotal);
            String totalDecimal = df2.format(jumlahSubtotal);
            
            txtTotalRetur.setText(totalNoDecimal);
            lbTotalRetur.setText("Rp. " + totalDecimal);

            loadDataSementara();
            resetFormPembelian();
        }else{
            JOptionPane.showMessageDialog(null, "Jumlah dan Alasan tidak boleh kosong");
            txtJumlah.requestFocus();
        }
    }

    private void dataTabelSementara(){
        btnTambahPembelian.setText("Perbarui");
        int row = tblDataSmt.getSelectedRow();
        
        txtPembelianID.setText(tblDataSmt.getValueAt(row, 1).toString());
        txtTanggal.setText(tblDataSmt.getValueAt(row, 2).toString());
        idProduk = Integer.valueOf(tblDataSmt.getValueAt(row, 3).toString());
        txtBarcode.setText(tblDataSmt.getValueAt(row, 4).toString());
        txtNamaProduk.setText(tblDataSmt.getValueAt(row, 5).toString());
        
        DecimalFormat df = new DecimalFormat("#,###");
        String hargaStr = tblDataSmt.getValueAt(row, 6).toString().trim();
        double harga = Double.parseDouble(hargaStr.replaceAll("[^\\d.]", ""));
        String hargaFormat = df.format(harga);
        txtHarga.setText(hargaFormat);
        
        txtJumlah.setText(tblDataSmt.getValueAt(row, 7).toString());
        txtAlasan.setText(tblDataSmt.getValueAt(row, 9).toString());

        nonAktif();
        txtJumlah.setEditable(true);
        txtAlasan.setEditable(true);
        btnTambahPembelian.setEnabled(true);
        btnHapusProduk.setEnabled(true);
        btnBatalProduk.setEnabled(true);
    }
    
    private void perbaruiDataSementara(){
        if(!txtJumlah.getText().equals("") && !txtAlasan.getText().equals("")){
            String pembelianID = txtPembelianID.getText();
            String tanggal = txtTanggal.getText();
            String barcode = txtBarcode.getText();
            String namaProduk = txtNamaProduk.getText();
            double harga = Double.parseDouble(txtHarga.getText().replace(",", ""));
            int jumlah = Integer.parseInt(txtJumlah.getText());
            double subTotal = harga * jumlah;
            String alasan = txtAlasan.getText();

            ModelBarangRusakSmt smt = new ModelBarangRusakSmt();
            ModelPembelian pb = new ModelPembelian();
            ModelProduk pd = new ModelProduk();
            ModelDetailBarangRusak det = new ModelDetailBarangRusak();

            pb.setIdPembelian(pembelianID);
            pb.setTanggal(tanggal);

            pd.setIdProduk(idProduk);
            pd.setBarcode(barcode);
            pd.setNamaProduk(namaProduk);
            pd.setHarga(harga);

            det.setJumlahRetur(jumlah);
            det.setSubtotalRetur(subTotal);
            det.setAlasan(alasan);

            smt.setModelPembelian(pb);
            smt.setModelProduk(pd);
            smt.setModelDetailBarangRusak(det);

            servisSmt.tambahData(smt);
            servisDet.sumTotal(det);

            DecimalFormat df1 = new DecimalFormat("#,##0");
            DecimalFormat df2 = new DecimalFormat("#,##0.00");
            double jumlahSubtotal = det.getSubtotalRetur();

            String totalNoDecimal = df1.format(jumlahSubtotal);
            String totalDecimal = df2.format(jumlahSubtotal);

            txtTotalRetur.setText(totalNoDecimal);
            lbTotalRetur.setText("Rp. " + totalDecimal);

            loadDataSementara();
            resetFormPembelian();
        }else{
            JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong !");
        }    
    }
    
    private void hapusDataSementara(){
        int row = tblDataSmt.getSelectedRow();
        if(row != -1){
            ModelBarangRusakSmt model = tblModelSmt.getData(row);
            if(JOptionPane.showConfirmDialog(null, "Yakin data akan dihapus?",
                    "Konfirmasi", JOptionPane.OK_CANCEL_OPTION)== JOptionPane.OK_OPTION)
            {
                servisSmt.hapusData(model);
                tblModelSmt.deleteData(row);
                loadDataSementara();
                resetFormPembelian();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Pilih dahulu data yang akan dihapus");
        }
    }

    private boolean validasiSimpan(){
        boolean valid = false;
        if(idSupplier == null){
            JOptionPane.showMessageDialog(null, "Silahkan pilih jenis Supplier");
        }else if(txtTotalRetur.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Total retur tidak boleh kosong");
        }else if(idKaryawan == null){
            JOptionPane.showMessageDialog(null, "ID Karyawan tidak boleh kosong");
        }else{
            valid = true;
        }
        return valid;
    }
    
    private void simpanData(){
        if(validasiSimpan()== true){
            String idBarangRusak = txtTransaksi.getText();
            String tanggal = lbTanggal.getText();
            double total = Double.parseDouble(txtTotalRetur.getText().replace(",", ""));
            
            ModelBarangRusak model = new ModelBarangRusak();
            ModelDetailBarangRusak modelDet = new ModelDetailBarangRusak();            
            ModelSupplier modelSup = new ModelSupplier();
            ModelKaryawan modelKar = new ModelKaryawan();
            
            model.setIdBarangRusak(idBarangRusak);
            model.setTanggalRetur(tanggal);
            model.setTotalRetur(total);
            
            modelSup.setIdSupplier(idSupplier);
            modelKar.setIdKaryawan(idKaryawan);
            
            model.setModelSupplier(modelSup);
            model.setModelKaryawan(modelKar);
            
            modelDet.setModelBarangRusak(model);
            
            servis.tambahData(model);
            servisDet.tambahData(modelDet);
            servisDet.hapusDataSementara();
            tblModelBr.insertData(model);
            servisReport.printNotaBarangRusak(idBarangRusak);
            servis.simpanNoTransaksi();
            showPanel();
            loadData();
            loadDataSementara();
            resetFormPembelian();
            resetPembayaran();
        }
    }    
}
