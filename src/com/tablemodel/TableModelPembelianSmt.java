package com.tablemodel;

import com.model.ModelPembelianSmt;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelPembelianSmt extends AbstractTableModel{
    
    private List<ModelPembelianSmt> list = new ArrayList<>();
    
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    
    public ModelPembelianSmt getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelPembelianSmt>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData( ModelPembelianSmt pembelian){
        list.add(pembelian);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    
    public void updateData(int row, ModelPembelianSmt pembelian){
        list.set(row, pembelian);
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
        ModelPembelianSmt pembelian = list.get(rowIndex);
        if(columnIndex == 0) {
            return "   " + (rowIndex + 1);
        }else{
            switch (columnIndex){
            case 1:
                return pembelian.getModelProduk().getIdProduk();
            case 2:
                return pembelian.getModelProduk().getBarcode();
            case 3:
                return pembelian.getModelProduk().getNamaProduk();
            case 4:
                return decimalFormat.format(pembelian.getModelProduk().getHarga());
            case 5:
                return pembelian.getModelProduk().getStok();
            case 6:
                return pembelian.getModelDetPem().getJumlah();
            case 7:
                return decimalFormat.format(pembelian.getModelDetPem().getSubTotal());  
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
