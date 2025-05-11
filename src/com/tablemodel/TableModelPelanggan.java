package com.tablemodel;

import com.model.ModelPelanggan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelPelanggan extends AbstractTableModel{

    private List<ModelPelanggan> list = new ArrayList<>();
    
    public ModelPelanggan getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelPelanggan>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData(ModelPelanggan pelanggan){
        list.add(pelanggan);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    
    public void updateData(int row, ModelPelanggan pelanggan){
        list.set(row, pelanggan);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    private final String[] columnNames = {"ID", "Kode RFID", "Nama pelanggan", "Telepon", "Alamat", "Level Member"};
    
    
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
         ModelPelanggan pelanggan = list.get(rowIndex);
        switch (columnIndex){
            case 0:
                return pelanggan.getIdPelanggan();
            case 1:
                return pelanggan.getKodeRfid();
            case 2:
                return pelanggan.getNamaPelanggan();
            case 3:
                return pelanggan.getTeleponPelanggan();
            case 4:
                return pelanggan.getAlamatPelanggan();
            case 5:
                return pelanggan.getLevelMember();
            default:
                return null;
        }
    }
       @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }    
}
