package com.service;

import com.model.ModelDetailBarangRusak;
import java.util.List;

public interface ServiceDetailBarangRusak {
    void tambahData (ModelDetailBarangRusak model);
    void sumTotal   (ModelDetailBarangRusak model);
    void hapusDataSementara(); 
   
    List<ModelDetailBarangRusak> tampilData(String id);
    List<ModelDetailBarangRusak> pencarianData(String id, String kataKunci);
}
