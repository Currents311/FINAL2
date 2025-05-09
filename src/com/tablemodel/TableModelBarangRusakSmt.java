package com.tablemodel;

import com.model.ModelBarangRusakSmt;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelBarangRusakSmt extends AbstractTableModel{
    
    private List<ModelBarangRusakSmt> list = new ArrayList<>();
    
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    
    public ModelBarangRusakSmt getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelBarangRusakSmt>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData( ModelBarangRusakSmt BarangRusak){
        list.add(BarangRusak);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    
    public void updateData(int row, ModelBarangRusakSmt BarangRusak){
        list.set(row, BarangRusak);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    private final String[] columnNames = {"No", "ID Pembelian", "Tanggal","ID Produk", "Barcode" ,"Nama Produk", "Harga", "Jumlah", "Subtotal", "Alasan"};
    
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
        ModelBarangRusakSmt BarangRusak = list.get(rowIndex);
        if(columnIndex == 0) {
            return "   " + (rowIndex + 1);
        }else{
            switch (columnIndex){
            case 1:
                return BarangRusak.getModelPembelian().getIdPembelian();
            case 2:
                return BarangRusak.getModelPembelian().getTanggal();
            case 3:
                return BarangRusak.getModelProduk().getIdProduk();
            case 4:
                return BarangRusak.getModelProduk().getBarcode();
            case 5:
                return BarangRusak.getModelProduk().getNamaProduk();
            case 6:
                return decimalFormat.format(BarangRusak.getModelProduk().getHarga());
            case 7:
                return BarangRusak.getModelDetailBarangRusak().getJumlahRetur();
            case 8:
                return decimalFormat.format(BarangRusak.getModelDetailBarangRusak().getSubtotalRetur()); 
            case 9:
                return BarangRusak.getModelDetailBarangRusak().getAlasan();
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
