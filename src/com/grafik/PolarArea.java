package com.grafik;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class PolarArea extends javax.swing.JComponent {

    private final List<ModelPolarAreaChart> list=new ArrayList<>();
    private double maxValues;
    private double totalValues;
    private final int PADDING_BOTTOM = 50;
    private final Animator animator;
    private float animate;
    
    public PolarArea() {
        initComponents();
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                animate = fraction;
                repaint();
            }
            
        };
        animator = new Animator(800, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        
    }

    @Override
    public void paint(Graphics grphcs) {
        if(isOpaque()){
            grphcs.setColor(getBackground());
            grphcs.fillRect(0, 0, getWidth(), getHeight());
        }
        createChart(grphcs);
        super.paint(grphcs); 
    }
    
    private void createChart(Graphics grphcs){
        int width = getWidth();
        int height = getHeight()-PADDING_BOTTOM;
        int space = 5;
        int size = Math.min(width, height)-space;
        int x=(width-size)/2;
        int y=(height-size)/2;
        if(width<=0){
            width = 1;
        }
        if(height<=0){
            height = 1;
        }
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (!list.isEmpty()){
            DecimalFormat df = new DecimalFormat("#,##0.##");
            double startAngle = 90;
            for(ModelPolarAreaChart data:list){
                g2.setColor(data.getColor());
                double angle = valuesToAngle(data.getValues());
                double rs = valuesToRealSize(data.getValues(), size)*animate;
                
                Shape s = createShape(startAngle, angle, rs);
                g2.fill(s);
                g2.setComposite(AlphaComposite.Clear);
                g2.setStroke(new BasicStroke(3f));
                g2.draw(s);
                g2.setComposite(AlphaComposite.SrcOver);                
                startAngle += angle;
//                drawValues(g2, df.format(data.getValues()), startAngle + angle / 2, rs / 2);
            }
        }else{
            g2.setColor(new Color(200,200,200));
            g2.drawOval(x, y, size, size);
        }
        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
    }



    private void drawValues(Graphics2D g2, String values, double angle, double rs){
        int centerX=getWidth()/2;
        int centerY=(getHeight()-PADDING_BOTTOM)/2;
        Point p = getLocation(angle, rs);  // Already halved before being passed
        g2.setColor(getForeground());
        g2.setFont(getFont());
        g2.setFont(getFont().deriveFont(Font.BOLD, 16f));  // 16-point bold font
        FontMetrics ft = g2.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(values, g2);
        double x = (centerX+p.x)-(r2.getWidth()/2);
        double y = (centerY+p.y)+(ft.getAscent()/2);
        g2.drawString(values, (int)x, (int)y);
    }
    
    private Shape createShape(double start, double end, double values){
        int width=getWidth();
        int height=getHeight()-PADDING_BOTTOM;
        double x=(width-values)/2;
        double y=(height-values)/2;
        Shape shape=new Arc2D.Double(x, y, values, values, start, end, Arc2D.PIE);
        return shape;
    }
    
    private double valuesToRealSize(double values, int size){
        double n = values*100/maxValues;
        return n * size/100;
    }
    
    private double valuesToAngle(double values) {
        double n = values * 100 / totalValues;
        return n * 360 / 100;        
    }
    
    private Point getLocation(double angle, double rs){
        double x=Math.cos(Math.toRadians(angle))*rs;  
        double y=Math.sin(Math.toRadians(angle))*rs;
        return new Point((int)x, (int)y);
    }
    
private void calculateValues(ModelPolarAreaChart data) {
    maxValues = Math.max(maxValues, data.getValues());
    totalValues = 0;
    for (ModelPolarAreaChart l : list) {
        totalValues += l.getValues();
    }
}

public void addItem(ModelPolarAreaChart data){
    list.add(data);
    calculateValues(data);
    repaint();
    
    DecimalFormat df = new DecimalFormat("#,##0.##");
    PolarAreaLabel label = new PolarAreaLabel();
    label.setText(data.getName() + " (" + df.format(data.getValues()) + ")");
    label.setBackground(data.getColor());
    panel.setLayout(new GridLayout(2, 2, 10,10 )); 
    panel.add(label);
    panel.repaint();
    panel.revalidate();
}

    public void start(){
        if(!animator.isRunning()){
            animator.start();
        }
    }
    
    public void clear(){
        animate = 0;
        list.clear();
        panel.removeAll();
        panel.repaint();
        panel.revalidate();
        repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();

        panel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        panel.setOpaque(false);
        panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 300, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
