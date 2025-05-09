package com.service;

import com.model.ModelDetailPenjualan;
import java.util.List;

public interface ServiceDetailPenjualan {
    void tambahData (ModelDetailPenjualan model);
    void sumTotal   (ModelDetailPenjualan model);
    void sumJumlah   (ModelDetailPenjualan model);
    void hapusDataSementara();
   
    List<ModelDetailPenjualan> tampilData(String id);
    List<ModelDetailPenjualan> pencarianData(String id, String kataKunci);
}
