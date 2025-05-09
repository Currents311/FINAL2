package com.model;

public class ModelDetailBarangRusak {
    private ModelBarangRusak modelBarangRusak;
    private ModelPembelian modelPembelian;    
    private ModelDetailPembelian modelDetailPembelian;    
    private ModelProduk modelProduk;
    private int jumlahRetur;
    private double subtotalRetur;
    private String alasan;
    
    public ModelBarangRusak getModelBarangRusak() {
        return modelBarangRusak;
    }

    public void setModelBarangRusak(ModelBarangRusak modelBarangRusak) {
        this.modelBarangRusak = modelBarangRusak;
    }

    public ModelPembelian getModelPembelian() {
        return modelPembelian;
    }

    public void setModelPembelian(ModelPembelian modelPembelian) {
        this.modelPembelian = modelPembelian;
    }

    public ModelDetailPembelian getModelDetailPembelian() {
        return modelDetailPembelian;
    }

    public void setModelDetailPembelian(ModelDetailPembelian modelDetailPembelian) {
        this.modelDetailPembelian = modelDetailPembelian;
    }

    public ModelProduk getModelProduk() {
        return modelProduk;
    }

    public void setModelProduk(ModelProduk modelProduk) {
        this.modelProduk = modelProduk;
    }

    public int getJumlahRetur() {
        return jumlahRetur;
    }

    public void setJumlahRetur(int jumlahRetur) {
        this.jumlahRetur = jumlahRetur;
    }

    public double getSubtotalRetur() {
        return subtotalRetur;
    }

    public void setSubtotalRetur(double subtotalRetur) {
        this.subtotalRetur = subtotalRetur;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }
}
