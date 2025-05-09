package com.tablemodel;

import com.model.ModelPembelian;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelPembelian extends AbstractTableModel{
    
    private List<ModelPembelian> list = new ArrayList<>();
    
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public ModelPembelian getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelPembelian>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData( ModelPembelian pembelian){
        list.add(pembelian);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    
    public void updateData(int row, ModelPembelian pembelian){
        list.set(row, pembelian);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    private final String[] columnNames = {"No", "ID Transaksi", "ID", "Supplier", "Tanggal", "Total Harga", "ID", "Karyawan"};
    
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
        ModelPembelian pembelian = list.get(rowIndex);
        if(columnIndex == 0) {
            return (rowIndex + 1);
        }else{
            switch (columnIndex){
            case 1:
                return pembelian.getIdPembelian();
            case 2:
                return pembelian.getModelSupplier().getIdSupplier();
            case 3:
                return pembelian.getModelSupplier().getNamaSupplier();
            case 4:
                return pembelian.getTanggal();
            case 5:
                return decimalFormat.format(pembelian.getTotalHarga());
            case 6:
                return pembelian.getModelKaryawan().getIdKaryawan();
            case 7:
                return pembelian.getModelKaryawan().getNamaKaryawan();  
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
