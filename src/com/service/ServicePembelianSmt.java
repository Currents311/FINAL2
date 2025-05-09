package com.service;

import com.model.ModelPembelianSmt;
import java.util.List;

public interface ServicePembelianSmt {
    void tambahData (ModelPembelianSmt model);
    void perbaruiData (ModelPembelianSmt model);
    void hapusData (ModelPembelianSmt model);
   
    List<ModelPembelianSmt> tampilData();
}
