package com.model;

public class ModelBarangRusakSmt {
    private ModelPembelian modelPembelian;    
    private ModelDetailBarangRusak modelDetailBarangRusak;    
    private ModelProduk modelProduk;

    public ModelPembelian getModelPembelian() {
        return modelPembelian;
    }

    public void setModelPembelian(ModelPembelian modelPembelian) {
        this.modelPembelian = modelPembelian;
    }

    public ModelDetailBarangRusak getModelDetailBarangRusak() {
        return modelDetailBarangRusak;
    }

    public void setModelDetailBarangRusak(ModelDetailBarangRusak modelDetailBarangRusak) {
        this.modelDetailBarangRusak = modelDetailBarangRusak;
    }

    public ModelProduk getModelProduk() {
        return modelProduk;
    }

    public void setModelProduk(ModelProduk modelProduk) {
        this.modelProduk = modelProduk;
    }
}
