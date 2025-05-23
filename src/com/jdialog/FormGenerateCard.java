package com.jdialog;

import com.form.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import com.dao.PelangganDAO;
import com.model.ModelPelanggan;
import com.service.ServicePelanggan;
import com.tablemodel.TableModelPelanggan;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;

public class FormGenerateCard extends javax.swing.JDialog {

    private boolean isFront = true;
    private TableModelPelanggan tblModel = new TableModelPelanggan();
    private ServicePelanggan servis = new PelangganDAO();
    private ModelPelanggan pelanggan;
    private int idPelanggan;
    private int row;
    private BufferedImage image = null;
    
    public FormGenerateCard(java.awt.Frame parent, boolean modal, int row, ModelPelanggan pelanggan) {
        super(parent, modal);
        this.pelanggan= pelanggan;
        this.row = row;
        initComponents();
        if (pelanggan != null && pelanggan.getIdPelanggan() > 0) {
            dataTable();
        } else{
            JOptionPane.showMessageDialog(this, "Data Pelanggan tidak valid", "Peringatan",JOptionPane.WARNING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnGenerate = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lbCard = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        btnPrint1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(230, 209, 242));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Master > Data pelanggan > Cetak Card Member");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cetak Card Member");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jSeparator1.setBackground(new java.awt.Color(128, 128, 128));
        jSeparator1.setForeground(new java.awt.Color(128, 128, 128));

        btnGenerate.setBackground(new java.awt.Color(230, 209, 242));
        btnGenerate.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnGenerate.setForeground(new java.awt.Color(0, 0, 0));
        btnGenerate.setText("GENERATE");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("ID Pelanggan");

        jPanel3.setBackground(new java.awt.Color(230, 209, 242));

        lbCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(lbCard, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(lbCard, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        btnPrint.setBackground(new java.awt.Color(230, 209, 242));
        btnPrint.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 0, 0));
        btnPrint.setText("PRINT");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnPrint1.setBackground(new java.awt.Color(230, 209, 242));
        btnPrint1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnPrint1.setForeground(new java.awt.Color(0, 0, 0));
        btnPrint1.setText("FLIP");
        btnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrint1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnPrint1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtID)
                                    .addComponent(btnGenerate, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                    .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 8, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
    if (!validasiInput()) {
        return; 
    }
    image = generateCardFront();
    ImageIcon icon = new ImageIcon(image);
    lbCard.setIcon(icon);
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
    if (image == null) {
        JOptionPane.showMessageDialog(this, "Belum ada Card Member yang dibuat!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
        printCard(image);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrint1ActionPerformed
        if (isFront) {
            image = generateCardBack();  
        } else {
            image = generateCardFront(); 
        }

        lbCard.setIcon(new ImageIcon(image));
        isFront = !isFront; 
    }//GEN-LAST:event_btnPrint1ActionPerformed
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormGenerateCard dialog = new FormGenerateCard(new javax.swing.JFrame(), true, 1, null);
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
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnPrint1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbCard;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
    
    private BufferedImage generateCardFront() {
        int width = 400;
        int height = 250;
        BufferedImage cardImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = cardImage.createGraphics();

        Color startColor = new Color(173, 216, 230);
        Color endColor = new Color(100, 200, 255);
        GradientPaint gp = new GradientPaint(0, 0, startColor, width, height, endColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

        try {
            BufferedImage logo = ImageIO.read(getClass().getResource("/com/icon/logo_ZaaraMedia.png")); 
            if (logo != null) {
                int logoX = (width - logo.getWidth()) / 2;
                int logoY = (height - logo.getHeight()) / 2 - 30; 
                g2d.drawImage(logo, logoX, logoY, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));
        FontMetrics fm1 = g2d.getFontMetrics();
        String line1 = "AL-QUR'AN DAN BUKU ISLAM";
        int line1Width = fm1.stringWidth(line1);
        g2d.drawString(line1, (width - line1Width) / 2, height / 2 + 30);

        g2d.setFont(new Font("SansSerif", Font.BOLD, 30));
        FontMetrics fm2 = g2d.getFontMetrics();
        String line2 = "MEMBERSHIP CARD";
        int line2Width = fm2.stringWidth(line2);
        g2d.drawString(line2, (width - line2Width) / 2, height / 2 + 70);

        g2d.dispose();
        return cardImage;
    }
    
        private BufferedImage generateCardBack() {
            int width = 400;
            int height = 250;
            BufferedImage cardBack = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = cardBack.createGraphics();
        
            Color startColor = new Color(173, 216, 230);
            Color endColor = new Color(100, 200, 255);
            GradientPaint gp = new GradientPaint(0, 0, startColor, width, height, endColor);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
            
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 14));
            g2d.drawString("Syarat dan Ketentuan Berlaku:", 20, 40);
            g2d.drawString("- Kartu ini tidak dapat dipindahtangankan", 20, 60);
            g2d.drawString("- Hubungi CS jika kartu hilang", 20, 80);

            try {
                Barcode barcode = BarcodeFactory.createCode128(String.valueOf(pelanggan.getKodeRfid()));
                BufferedImage barcodeImage = BarcodeImageHandler.getImage(barcode);

                if (barcodeImage != null) {
                    int barcodeX = 20;
                    int barcodeY = height - barcodeImage.getHeight() - 50;
                    g2d.drawImage(barcodeImage, barcodeX, barcodeY, null);

                    int textY = barcodeY - 10;
                    g2d.setColor(Color.BLACK);
                    g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));

                    FontMetrics fm = g2d.getFontMetrics();
                    String line1 = "\u260E 0851 9728 9505";
                    String line2 = "\u0040 zaaramedia";
                    String line3 = "\u2302 Jl. Pattimura Karanganyar, Ambulu, Jember";

                    int textX1 = (width - fm.stringWidth(line1)) / 2;
                    int textX2 = (width - fm.stringWidth(line2)) / 2;
                    int textX3 = (width - fm.stringWidth(line3)) / 2;

                    g2d.drawString(line1, textX1, textY - 30);
                    g2d.drawString(line2, textX2, textY - 15);
                    g2d.drawString(line3, textX3, textY);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            g2d.dispose();
            return cardBack;
        }


    private void printCard(BufferedImage image) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                int x = (int) ((pageFormat.getImageableWidth() - image.getWidth()) / 2);
                int y = (int) ((pageFormat.getImageableHeight() - image.getHeight()) / 2);
                g2d.drawImage(image, x, y, null);

                return Printable.PAGE_EXISTS;
            }
        });

        if (printerJob.printDialog()) {
            try {
                printerJob.print();
                JOptionPane.showMessageDialog(this, "Card Member berhasil dicetak!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (PrinterException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Gagal mencetak Card Member!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private boolean validasiInput() {
        if (txtID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
        return true;
    }

    private void dataTable() {
        txtID.setText(String.valueOf(pelanggan.getIdPelanggan()));
    }
}


