package com.service;

import com.model.ModelPelanggan;
import java.util.List;

public interface ServicePelanggan {
    void tambahData (ModelPelanggan model);
    void perbaruiData (ModelPelanggan model);
    void hapusData (ModelPelanggan model);
    void updateLevelMember();
    String getLevelMember(String idPelanggan);
    
    List<ModelPelanggan> tampilData(int posisiAwal, int dataPerHalaman);
    List<ModelPelanggan> pencarianData(String id, int posisiAwal, int dataPerHalaman);
    List<ModelPelanggan> ambilPelanggan();

    int getTotalData();
}
