package com.tablemodel;

import com.model.ModelProduk;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelProduk extends AbstractTableModel{
    
    private List<ModelProduk> list = new ArrayList<>();
    
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    
    public ModelProduk getData(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelProduk>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData( ModelProduk produk){
        list.add(produk);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
    }
    
    public void updateData(int row, ModelProduk produk){
        list.set(row, produk);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui");
    }
    
    public void deleteData(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
    }
    private final String[] columnNames = {"ID","Barcode", "Nama Produk", "ID", "Kategori", "Harga", "Stok", "ID", "Supplier"};
    
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
        ModelProduk produk = list.get(rowIndex);
        switch (columnIndex){
            case 0:
                return "   "+produk.getIdProduk();
            case 1:
                return produk.getBarcode();  
            case 2:
                return produk.getNamaProduk();
            case 3:
                return produk.getModelKategori().getIdKategori();
            case 4:
                return produk.getModelKategori().getNamaKategori();
            case 5:
                return decimalFormat.format(produk.getHarga());
            case 6:
                return produk.getStok();
            case 7:
                return produk.getModelSupplier().getIdSupplier();
            case 8:
                return produk.getModelSupplier().getNamaSupplier();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }    
}
