package com.service;

import com.model.ModelSupplier;
import java.util.List;

public interface ServiceSupplier {
    void tambahData (ModelSupplier model);
    void perbaruiData (ModelSupplier model);
    void hapusData (ModelSupplier model);
    
    List<ModelSupplier> tampilData(int posisiAwal, int dataPerHalaman);
    List<ModelSupplier> pencarianData(String id, int posisiAwal, int dataPerHalaman);
    
    boolean validasiNamaSupplier (ModelSupplier model);
    List <ModelSupplier> ambilSupplier();
    String ambilSupplierID(int id); 
    int getTotalData();
}
