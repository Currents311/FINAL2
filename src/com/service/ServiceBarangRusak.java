package com.service;

import com.model.ModelBarangRusak;
import java.util.Date;
import java.util.List;

public interface ServiceBarangRusak {
    void tambahData (ModelBarangRusak model);
    void hapusData (ModelBarangRusak model);
    
    List<ModelBarangRusak> tampilData(int posisiAwal, int dataPerHalaman);
    List<ModelBarangRusak> pencarianData(String id, int posisiAwal, int dataPerHalaman);
    List<ModelBarangRusak> rangeTanggal(Date startDate, Date endDate);

    String generateNoTransaksi();
    void simpanNoTransaksi();
    void deleteNoTransaksi();
    void resetNoTransaksi();

    int getTotalData();
}
