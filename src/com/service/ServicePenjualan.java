package com.service;

import com.model.ModelPenjualan;
import java.util.Date;
import java.util.List;

public interface ServicePenjualan {
    void tambahData (ModelPenjualan model);
    void hapusData  (ModelPenjualan model);
   
    List<ModelPenjualan> tampilData(int posisiAwal, int dataPerHalaman);
    List<ModelPenjualan> pencarianData(String id, int posisiAwal, int dataPerHalaman);
    List<ModelPenjualan> rangeTanggal(Date startDate, Date endDate);
    
    String generateNoTransaksi();
    void simpanNoTransaksi();
    void deleteNoTransaksi();
    void resetNoTransaksi();
    
    int getTotalData();
    void updateStok (ModelPenjualan model);
    void updateStokDelete (ModelPenjualan model);
}
