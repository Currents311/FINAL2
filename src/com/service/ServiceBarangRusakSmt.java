package com.service;

import com.model.ModelBarangRusakSmt;
import java.util.List;

public interface ServiceBarangRusakSmt {
    void tambahData (ModelBarangRusakSmt model);
    void perbaruiData (ModelBarangRusakSmt model);
    void hapusData (ModelBarangRusakSmt model);
   
    List<ModelBarangRusakSmt> tampilData();
}
