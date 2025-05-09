package com.service;

import com.model.ModelProduk;
import java.util.List;

public interface ServiceProduk {
    void tambahData (ModelProduk model);
    void perbaruiData (ModelProduk model);
    void hapusData (ModelProduk model);
    
    List<ModelProduk> tampilData(int posisiAwal, int dataPerHalaman);
    List<ModelProduk> pencarianData(String id, int posisiAwal, int dataPerHalaman);
    List<ModelProduk> pencarianDataByBarcode(String id, int posisiAwal, int dataPerHalaman);
    
    boolean isBarcodeExists(String barcode);
    int getTotalData();
}
