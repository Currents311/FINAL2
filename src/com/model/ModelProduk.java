package com.model;

public class ModelProduk {
    private int idProduk;
    private String barcode;    
    private String namaProduk;
    private ModelKategori modelKategori;
    private double harga;
    private int stok;
    private ModelSupplier modelSupplier;

    public int getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public ModelKategori getModelKategori() {
        return modelKategori;
    }

    public void setModelKategori(ModelKategori modelKategori) {
        this.modelKategori = modelKategori;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public ModelSupplier getModelSupplier() {
        return modelSupplier;
    }

    public void setModelSupplier(ModelSupplier modelSupplier) {
        this.modelSupplier = modelSupplier;
    }
}
