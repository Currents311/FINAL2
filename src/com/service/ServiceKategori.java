package com.service;

import com.model.ModelKategori;
import java.util.List;

public interface ServiceKategori {
    void tambahData (ModelKategori model);
    void perbaruiData (ModelKategori model);
    void hapusData (ModelKategori model);
    
    public String generateBarcodeByKategori(int id);

    
    List<ModelKategori> tampilData(int posisiAwal, int dataPerHalaman);
    List<ModelKategori> pencarianData(String id, int posisiAwal, int dataPerHalaman);
    
    boolean validasiNamaKategori (ModelKategori model);
    List <ModelKategori> ambilKategori();
    String ambilKategoriID(int id); 

    int getTotalData();
}
