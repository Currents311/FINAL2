package com.service;

import com.model.ModelPembelian;
import java.util.Date;
import java.util.List;

public interface ServicePembelian {
    void tambahData (ModelPembelian model);
    void hapusData  (ModelPembelian model);
   
    List<ModelPembelian> tampilData(int posisiAwal, int dataPerHalaman);
    List<ModelPembelian> pencarianData(String id, int posisiAwal, int dataPerHalaman);
    List<ModelPembelian> rangeTanggal(Date startDate, Date endDate);
    
    String generateNoTransaksi(); 
    void simpanNoTransaksi();
    void deleteNoTransaksi();
    void resetNoTransaksi();

    int getTotalData();
    void updateStok (ModelPembelian model);
    void updateStokDelete (ModelPembelian model);
}
