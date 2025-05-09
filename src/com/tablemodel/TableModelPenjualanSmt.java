package com.tablemodel;

import com.model.ModelPenjualanSmt;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelPenjualanSmt extends AbstractTableModel{
    
    private List<ModelPenjualanSmt> list = new ArrayList<>();
    
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    
    public ModelPenjualanSmt getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelPenjualanSmt>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData( ModelPenjualanSmt penjualan){
        list.add(penjualan);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    
    public void updateData(int row, ModelPenjualanSmt penjualan){
        list.set(row, penjualan);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    private final String[] columnNames = {"No", "ID Produk", "Barcode" ,"Nama Produk", "Harga", "Stok", "Jumlah", "Subtotal"};
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ModelPenjualanSmt penjualan = list.get(rowIndex);
        if(columnIndex == 0) {
            return "   " + (rowIndex + 1);
        }else{
            switch (columnIndex){
            case 1:
                return penjualan.getModelProduk().getIdProduk();
            case 2:
                return penjualan.getModelProduk().getBarcode();
            case 3:
                return penjualan.getModelProduk().getNamaProduk();
            case 4:
                return decimalFormat.format(penjualan.getModelProduk().getHarga());
            case 5:
                return penjualan.getModelProduk().getStok();
            case 6:
                return penjualan.getModelDetPen().getJumlah();
            case 7:
                return decimalFormat.format(penjualan.getModelDetPen().getSubTotal());  
            default:
                return null;
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }    
}
