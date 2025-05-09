package com.model;

public class ModelPembelian {
    private String idPembelian;
    private ModelKaryawan modelKaryawan;
    private ModelSupplier modelSupplier;
    private String tanggal;
    private double totalHarga;
    
    public String getIdPembelian() {
        return idPembelian;
    }

    public void setIdPembelian(String idPembelian) {
        this.idPembelian = idPembelian;
    }

    public ModelKaryawan getModelKaryawan() {
        return modelKaryawan;
    }

    public void setModelKaryawan(ModelKaryawan modelKaryawan) {
        this.modelKaryawan = modelKaryawan;
    }

    public ModelSupplier getModelSupplier() {
        return modelSupplier;
    }

    public void setModelSupplier(ModelSupplier modelSupplier) {
        this.modelSupplier = modelSupplier;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }
}
