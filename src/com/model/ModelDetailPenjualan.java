package com.model;

public class ModelDetailPenjualan {
    private ModelPenjualan modelPenjualan;
    private ModelProduk modelProduk;
    private int jumlah;
    private double subTotal;

    public ModelPenjualan getModelPenjualan() {
        return modelPenjualan;
    }

    public void setModelPenjualan(ModelPenjualan modelPenjualan) {
        this.modelPenjualan = modelPenjualan;
    }

    public ModelProduk getModelProduk() {
        return modelProduk;
    }

    public void setModelProduk(ModelProduk modelProduk) {
        this.modelProduk = modelProduk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

   }
