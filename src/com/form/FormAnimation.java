package com.form;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class FormAnimation extends JPanel {
    
    private ImageIcon gifIcon; 

    public FormAnimation() {
        initComponents();
        setLayoutForm(); 
    }

    private void setLayoutForm() {
        gifIcon = new ImageIcon(getClass().getResource("/com/icon/logo_Animation.gif"));

        if (gifIcon.getIconWidth() == -1) {
            System.out.println("Error: GIF not found! Check file path.");
        }

        videoLabel.setIcon(gifIcon);
        videoLabel.setHorizontalAlignment(JLabel.CENTER);
        videoLabel.setVerticalAlignment(JLabel.CENTER);

        setLayout(new BorderLayout());
        add(videoLabel, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                stretchGif();
            }
        });

        Timer timer = new Timer(11000, (ActionEvent e) -> openNextForm());
        timer.setRepeats(false);
        timer.start();
    }

    private void stretchGif() {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (panelWidth > 0 && panelHeight > 0) {
            ImageIcon stretchedIcon = new ImageIcon(
                gifIcon.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_DEFAULT)
            );
            videoLabel.setIcon(stretchedIcon);
        }
    }

    private void openNextForm() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            parentFrame.setContentPane(new FormWelcome()); 
            parentFrame.revalidate();
            parentFrame.repaint();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        videoLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(videoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(videoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel videoLabel;
    // End of variables declaration//GEN-END:variables
}
