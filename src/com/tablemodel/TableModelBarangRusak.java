package com.tablemodel;

import com.model.ModelBarangRusak;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelBarangRusak extends AbstractTableModel{
    
    private List<ModelBarangRusak> list = new ArrayList<>();
    
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public ModelBarangRusak getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelBarangRusak>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData( ModelBarangRusak barangRusak){
        list.add(barangRusak);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    
    public void updateData(int row, ModelBarangRusak barangRusak){
        list.set(row, barangRusak);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    private final String[] columnNames = {"No", "ID Barang Rusak", "ID", "Supplier", "Tanggal", "Total Harga", "ID", "Karyawan"};
    
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
        ModelBarangRusak barangRusak = list.get(rowIndex);
        if(columnIndex == 0) {
            return (rowIndex + 1);
        }else{
            switch (columnIndex){
            case 1:
                return barangRusak.getIdBarangRusak();
            case 2:
                return barangRusak.getModelSupplier().getIdSupplier();
            case 3:
                return barangRusak.getModelSupplier().getNamaSupplier();
            case 4:
                return barangRusak.getTanggalRetur();
            case 5:
                return decimalFormat.format(barangRusak.getTotalRetur());
            case 6:
                return barangRusak.getModelKaryawan().getIdKaryawan();
            case 7:
                return barangRusak.getModelKaryawan().getNamaKaryawan();  
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
