package com.model;

public class ModelBarangRusak {
    private String idBarangRusak;
    private ModelKaryawan modelKaryawan;    
    private ModelSupplier modelSupplier;
    private String tanggalRetur;
    private double totalRetur;

    public String getIdBarangRusak() {
        return idBarangRusak;
    }

    public void setIdBarangRusak(String idBarangRusak) {
        this.idBarangRusak = idBarangRusak;
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

    public String getTanggalRetur() {
        return tanggalRetur;
    }

    public void setTanggalRetur(String tanggalRetur) {
        this.tanggalRetur = tanggalRetur;
    }

    public double getTotalRetur() {
        return totalRetur;
    }

    public void setTotalRetur(double totalRetur) {
        this.totalRetur = totalRetur;
    }
}
