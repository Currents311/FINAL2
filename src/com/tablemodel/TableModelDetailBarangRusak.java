package com.tablemodel;

import com.model.ModelDetailBarangRusak;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelDetailBarangRusak extends AbstractTableModel{
    
    private List<ModelDetailBarangRusak> list = new ArrayList<>();
    
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public ModelDetailBarangRusak getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelDetailBarangRusak>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData( ModelDetailBarangRusak detailBarangRusak){
        list.add(detailBarangRusak);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    
    public void updateData(int row, ModelDetailBarangRusak detailBarangRusak){
        list.set(row, detailBarangRusak);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    private final String[] columnNames = {"No", "ID Barang Rusak", "ID Pembelian", "ID Produk", "Barcode", "Nama Produk", "Kategori","Harga","Jumlah", "Subtotal", "Alasan"};
    
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
        ModelDetailBarangRusak detailBarangRusak = list.get(rowIndex);
        if(columnIndex == 0) {
            return (rowIndex + 1);
        }else{
            switch (columnIndex){
            case 1:
                return detailBarangRusak.getModelBarangRusak().getIdBarangRusak();
            case 2:
                return detailBarangRusak.getModelPembelian().getIdPembelian();
            case 3:
                return detailBarangRusak.getModelProduk().getIdProduk();
            case 4:
                return detailBarangRusak.getModelProduk().getBarcode();
            case 5:
                return detailBarangRusak.getModelProduk().getNamaProduk();
            case 6:
                return detailBarangRusak.getModelProduk().getModelKategori().getNamaKategori();
            case 7:
                return decimalFormat.format(detailBarangRusak.getModelProduk().getHarga());
            case 8:
                return detailBarangRusak.getJumlahRetur();
            case 9:
                return decimalFormat.format(detailBarangRusak.getSubtotalRetur());
            case 10:
                return detailBarangRusak.getAlasan();
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
