package com.tablemodel;

import com.model.ModelKaryawan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelKaryawan extends AbstractTableModel{

    private List<ModelKaryawan> list = new ArrayList<>();
    
    public ModelKaryawan getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelKaryawan>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData(ModelKaryawan karyawan){
        list.add(karyawan);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    
    public void updateData(int row, ModelKaryawan karyawan){
        list.set(row, karyawan);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    private final String[] columnNames = {"ID", "Nama karyawan","Telepon", "Peran", "Riwayat login"};
    
    
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
         ModelKaryawan karyawan = list.get(rowIndex);
        switch (columnIndex){
            case 0:
                return karyawan.getIdKaryawan();
            case 1:
                return karyawan.getNamaKaryawan();
            case 2:
                return karyawan.getTeleponKaryawan();
            case 3:
                return karyawan.getRole();
            case 4:
                return karyawan.getLastSeen();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }    
}
