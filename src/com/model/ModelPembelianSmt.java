package com.model;

public class ModelPembelianSmt {
    private ModelProduk modelProduk = new ModelProduk();  
    private ModelDetailPembelian modelDetPem = new ModelDetailPembelian();

    public ModelProduk getModelProduk() {
        return modelProduk;
    }

    public void setModelProduk(ModelProduk modelProduk) {
        this.modelProduk = modelProduk;
    }

    public ModelDetailPembelian getModelDetPem() {
        return modelDetPem;
    }

    public void setModelDetPem(ModelDetailPembelian modelDetPem) {
        this.modelDetPem = modelDetPem;
    }
}
