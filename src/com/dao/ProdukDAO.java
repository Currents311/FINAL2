package com.dao;

import com.config.Koneks;
import com.model.ModelKategori;
import com.model.ModelProduk;
import com.model.ModelSupplier;
import com.service.ServiceProduk;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class ProdukDAO implements ServiceProduk{

    private final Connection conn;
    
    public ProdukDAO(){
        conn = Koneks.getConnection();
    }

    @Override
    public void tambahData(ModelProduk model) {
        if (isBarcodeExists(model.getBarcode())) {
            return;
        }

        String sql = "INSERT INTO produk(barcode, nama_produk, id_kategori, harga, stok, id_supplier) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString    (1, model.getBarcode());
            st.setString    (2, model.getNamaProduk());
            st.setInt       (3, model.getModelKategori().getIdKategori());
            st.setDouble    (4, model.getHarga());
            st.setInt       (5, model.getStok());
            st.setInt       (6, model.getModelSupplier().getIdSupplier());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void perbaruiData(ModelProduk model) {
        String sql = "UPDATE produk SET barcode=?, nama_produk=?, id_kategori=?, harga=?, stok=?, id_supplier=? WHERE id_produk=?";

        String checkSql = "SELECT COUNT(*) FROM produk WHERE barcode = ? AND id_produk != ?";
        try (PreparedStatement checkSt = conn.prepareStatement(checkSql)) {
            checkSt.setString(1, model.getBarcode());
            checkSt.setInt(2, model.getIdProduk());
            ResultSet rs = checkSt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString    (1, model.getBarcode());
            st.setString    (2, model.getNamaProduk());
            st.setInt       (3, model.getModelKategori().getIdKategori());
            st.setDouble    (4, model.getHarga());
            st.setInt       (5, model.getStok());
            st.setInt       (6, model.getModelSupplier().getIdSupplier());
            st.setInt       (7, model.getIdProduk());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(ModelProduk model) {
        PreparedStatement st = null;
        String sql = "DELETE FROM produk WHERE id_produk=?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, model.getIdProduk());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelProduk> tampilData(int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT pd.id_produk, pd.barcode, pd.nama_produk, ktg.id_kategori, ktg.nama_kategori, pd.harga, "
                     + "pd.stok, sup.id_supplier, sup.nama_supplier\n"+
                     "FROM produk pd\n"+
                     "INNER JOIN kategori ktg ON ktg.id_kategori = pd.id_kategori\n"+
                     "INNER JOIN supplier sup ON sup.id_supplier = pd.id_supplier "
                     +"ORDER BY pd.id_produk ASC LIMIT ? OFFSET ?";
                     
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelProduk produk = new ModelProduk();
                ModelKategori kategori = new ModelKategori();
                ModelSupplier supplier = new ModelSupplier();
                
                produk.setIdProduk      (rs.getInt("id_produk"));
                produk.setBarcode       (rs.getString("barcode"));
                produk.setNamaProduk    (rs.getString("nama_produk"));
                kategori.setIdKategori  (rs.getInt("id_kategori"));
                kategori.setNamaKategori(rs.getString("nama_kategori"));
                produk.setHarga         (rs.getLong("harga"));
                produk.setStok          (rs.getInt("stok"));
                supplier.setIdSupplier  (rs.getInt("id_supplier"));
                supplier.setNamaSupplier(rs.getString("nama_supplier"));
                
                produk.setModelKategori(kategori);
                produk.setModelSupplier(supplier);
                
                list.add(produk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelProduk> pencarianData(String id, int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs= null;
        List list = new ArrayList();
        String sql = "SELECT pd.id_produk, pd.barcode, pd.nama_produk, ktg.id_kategori, ktg.nama_kategori, pd.harga, "
                     + "pd.stok, sup.id_supplier, sup.nama_supplier\n"+
                     "FROM produk pd\n"+
                     "INNER JOIN kategori ktg ON ktg.id_kategori = pd.id_kategori\n"+
                     "INNER JOIN supplier sup ON sup.id_supplier = pd.id_supplier "
                    + "WHERE pd.nama_produk LIKE '%"+id+"%' "
                    + "OR pd.barcode LIKE '%"+id+"%'"
                    + "OR ktg.nama_kategori LIKE '%"+id+"%' "
                    + "OR sup.nama_supplier LIKE '%"+id+"%' "   
                    + "ORDER BY pd.id_produk ASC LIMIT ? OFFSET ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, dataPerHalaman);
            st.setInt(2, posisiAwal);
            rs = st.executeQuery();
            while(rs.next()){
                ModelProduk produk = new ModelProduk();
                ModelKategori kategori = new ModelKategori();
                ModelSupplier supplier = new ModelSupplier();
                
                produk.setIdProduk      (rs.getInt("id_produk"));
                produk.setBarcode       (rs.getString("barcode"));
                produk.setNamaProduk    (rs.getString("nama_produk"));
                kategori.setIdKategori  (rs.getInt("id_kategori"));
                kategori.setNamaKategori(rs.getString("nama_kategori"));
                produk.setHarga         (rs.getLong("harga"));
                produk.setStok          (rs.getInt("stok"));
                supplier.setIdSupplier  (rs.getInt("id_supplier"));
                supplier.setNamaSupplier(rs.getString("nama_supplier"));
                
                produk.setModelKategori(kategori);
                produk.setModelSupplier(supplier);
                
                list.add(produk);           
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean isBarcodeExists(String barcode) {
        String sql = "SELECT COUNT(*) FROM produk WHERE barcode = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, barcode);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ModelProduk> pencarianDataByBarcode(String id, int posisiAwal, int dataPerHalaman) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ModelProduk>list = new ArrayList<>();
        String sql = "SELECT pd.id_produk, pd.barcode, pd.nama_produk, ktg.id_kategori, ktg.nama_kategori, pd.harga, "
                     + "pd.stok, sup.id_supplier, sup.nama_supplier\n"+
                     "FROM produk pd\n"+
                     "INNER JOIN kategori ktg ON ktg.id_kategori = pd.id_kategori\n"+
                     "INNER JOIN supplier sup ON sup.id_supplier = pd.id_supplier "
                    + "WHERE pd.barcode = ? "
                    + "ORDER BY pd.id_produk ASC LIMIT ? OFFSET ?";
        try{
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.setInt(2, dataPerHalaman);
            st.setInt(3, posisiAwal);
            rs = st.executeQuery();
            if(rs.next()){
                ModelProduk produk = new ModelProduk();
                ModelKategori kategori = new ModelKategori();
                ModelSupplier supplier = new ModelSupplier();
                
                produk.setIdProduk      (rs.getInt("id_produk"));
                produk.setBarcode       (rs.getString("barcode"));
                produk.setNamaProduk    (rs.getString("nama_produk"));
                kategori.setIdKategori  (rs.getInt("id_kategori"));
                kategori.setNamaKategori(rs.getString("nama_kategori"));
                produk.setHarga         (rs.getLong("harga"));
                produk.setStok          (rs.getInt("stok"));
                supplier.setIdSupplier  (rs.getInt("id_supplier"));
                supplier.setNamaSupplier(rs.getString("nama_supplier"));
                
                produk.setModelKategori(kategori);
                produk.setModelSupplier(supplier);
                
                list.add(produk);        
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getTotalData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) AS total FROM produk";
        
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            
            if(rs.next()){
                return rs.getInt("total");
            }else{
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
