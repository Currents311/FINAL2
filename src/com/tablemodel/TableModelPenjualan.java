package com.tablemodel;

import com.model.ModelPenjualan;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelPenjualan extends AbstractTableModel{
    
    private List<ModelPenjualan> list = new ArrayList<>();
    
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public ModelPenjualan getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelPenjualan>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData( ModelPenjualan penjualan){
        list.add(penjualan);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    
    public void updateData(int row, ModelPenjualan penjualan){
        list.set(row, penjualan);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    private final String[] columnNames = {"No", "ID Transaksi", "ID", "Pelanggan", "Tanggal", "Total Jumlah","Total Harga", "Bayar", "Diskon", "Kembali", "ID", "Karyawan"};
    
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
        ModelPenjualan penjualan = list.get(rowIndex);
        if(columnIndex == 0) {
            return "   " + (rowIndex + 1);
        }else{
            switch (columnIndex){
            case 1:
                return penjualan.getIdPenjualan();
            case 2:
                return penjualan.getModelPelanggan().getIdPelanggan();
            case 3:
                return penjualan.getModelPelanggan().getNamaPelanggan();
            case 4:
                return penjualan.getTanggal();
            case 5:
                return penjualan.getTotalJumlah();
            case 6:
                return decimalFormat.format(penjualan.getTotalHarga());
            case 7:
                return decimalFormat.format(penjualan.getBayar());
            case 8:
                return decimalFormat.format(penjualan.getDiskon());
            case 9:
                return decimalFormat.format(penjualan.getKembalian());
            case 10:
                return penjualan.getModelKaryawan().getIdKaryawan();
            case 11:
                return penjualan.getModelKaryawan().getNamaKaryawan();  
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
