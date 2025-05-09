package com.tablemodel;

import com.model.ModelDetailPembelian;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelDetailPembelian extends AbstractTableModel{
    
    private List<ModelDetailPembelian> list = new ArrayList<>();
    
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public ModelDetailPembelian getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelDetailPembelian>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData( ModelDetailPembelian detailPembelian){
        list.add(detailPembelian);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    
    public void updateData(int row, ModelDetailPembelian detailPembelian){
        list.set(row, detailPembelian);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    private final String[] columnNames = {"No", "ID Transaksi", "Tanggal", "ID Produk","Barcode", "Nama Produk", "Kategori","Harga","Jumlah", "Subtotal"};
    
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
        ModelDetailPembelian detailPembelian = list.get(rowIndex);
        if(columnIndex == 0) {
            return (rowIndex + 1);
        }else{
            switch (columnIndex){
            case 1:
                return detailPembelian.getModelPembelian().getIdPembelian();
            case 2:
                return detailPembelian.getModelPembelian().getTanggal();
            case 3:
                return detailPembelian.getModelProduk().getIdProduk();
            case 4:
                return detailPembelian.getModelProduk().getBarcode();
            case 5:
                return detailPembelian.getModelProduk().getNamaProduk();
            case 6:
                return detailPembelian.getModelProduk().getModelKategori().getNamaKategori();
            case 7:
                return decimalFormat.format(detailPembelian.getModelProduk().getHarga());
            case 8:
                return detailPembelian.getJumlah();
            case 9:
                return decimalFormat.format(detailPembelian.getSubTotal());
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
