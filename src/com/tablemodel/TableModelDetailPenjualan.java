package com.tablemodel;

import com.model.ModelDetailPenjualan;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelDetailPenjualan extends AbstractTableModel{
    
    private List<ModelDetailPenjualan> list = new ArrayList<>();
    
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public ModelDetailPenjualan getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelDetailPenjualan>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData( ModelDetailPenjualan detailPenjualan){
        list.add(detailPenjualan);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    
    public void updateData(int row, ModelDetailPenjualan detailPenjualan){
        list.set(row, detailPenjualan);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    private final String[] columnNames = {"No", "ID Transaksi", "ID Produk","Barcode", "Nama Produk", "Kategori","Harga","Jumlah", "Subtotal"};
    
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
        ModelDetailPenjualan detailPenjualan = list.get(rowIndex);
        if(columnIndex == 0) {
            return "   " + (rowIndex + 1);
        }else{
            switch (columnIndex){
            case 1:
                return detailPenjualan.getModelPenjualan().getIdPenjualan();
            case 2:
                return detailPenjualan.getModelProduk().getIdProduk();
            case 3:
                return detailPenjualan.getModelProduk().getBarcode();
            case 4:
                return detailPenjualan.getModelProduk().getNamaProduk();
            case 5:
                return detailPenjualan.getModelProduk().getModelKategori().getNamaKategori();
            case 6:
                return decimalFormat.format(detailPenjualan.getModelProduk().getHarga());
            case 7:
                return detailPenjualan.getJumlah();
            case 8:
                return decimalFormat.format(detailPenjualan.getSubTotal());
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
