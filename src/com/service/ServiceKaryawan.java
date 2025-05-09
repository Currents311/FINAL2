package com.service;

import com.model.ModelKaryawan;
import java.util.List;

public interface ServiceKaryawan {
    void tambahData (ModelKaryawan model);
    void perbaruiData (ModelKaryawan model);
    void hapusData (ModelKaryawan model);
    
    List<ModelKaryawan> tampilData(int posisiAwal, int dataPerHalaman);
    List<ModelKaryawan> pencarianData(String id, int posisiAwal, int dataPerHalaman);
    
    String hashPassword(String password);  
    boolean checkPassword(String plainPassword, String hashedPassword);

    ModelKaryawan prosesLogin(ModelKaryawan model);
    boolean prosesSignUp(ModelKaryawan model);
    
    boolean validasiPasswordLama(String namaKaryawan, String passwordLama);
    boolean pergantianPassword(String namaKaryawan, String passwordLama, String passwordBaru);
    
    int getTotalData();
    int getSuperAdminCount();
}
