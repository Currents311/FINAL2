package com.model;

public class ModelPenjualanSmt {
    private ModelProduk modelProduk = new ModelProduk();  
    private ModelDetailPenjualan modelDetPen = new ModelDetailPenjualan();

    public ModelProduk getModelProduk() {
        return modelProduk;
    }

    public void setModelProduk(ModelProduk modelProduk) {
        this.modelProduk = modelProduk;
    }

    public ModelDetailPenjualan getModelDetPen() {
        return modelDetPen;
    }

    public void setModelDetPen(ModelDetailPenjualan modelDetPen) {
        this.modelDetPen = modelDetPen;
    }
   }
