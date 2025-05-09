package com.model;

public class ModelDetailPembelian {
    private ModelPembelian modelPembelian;
    private ModelProduk modelProduk;
    private int jumlah;
    private double subTotal;

    public ModelPembelian getModelPembelian() {
        return modelPembelian;
    }

    public void setModelPembelian(ModelPembelian modelPembelian) {
        this.modelPembelian = modelPembelian;
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
