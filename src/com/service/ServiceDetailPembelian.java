package com.service;

import com.model.ModelDetailPembelian;
import java.util.List;

public interface ServiceDetailPembelian {
    void tambahData (ModelDetailPembelian model);
    void sumTotal   (ModelDetailPembelian model);
    void sumJumlah   (ModelDetailPembelian model);
    void hapusDataSementara(); 
   
    List<ModelDetailPembelian> tampilData();
    List<ModelDetailPembelian> tampilData(String id);
    List<ModelDetailPembelian> pencarianData(String kataKunci);
    List<ModelDetailPembelian> pencarianData(String id, String kataKunci);
    
}
