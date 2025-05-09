package com.model;

public class ModelPenjualan {

    private String idPenjualan;
    private ModelKaryawan modelKaryawan;
    private ModelPelanggan modelPelanggan;
    private String tanggal;
    private int totalJumlah;
    private double totalHarga;
    private double bayar;
    private double diskon;
    private double kembalian;

    public String getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(String idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public ModelKaryawan getModelKaryawan() {
        return modelKaryawan;
    }

    public void setModelKaryawan(ModelKaryawan modelKaryawan) {
        this.modelKaryawan = modelKaryawan;
    }

    public ModelPelanggan getModelPelanggan() {
        return modelPelanggan;
    }

    public void setModelPelanggan(ModelPelanggan modelPelanggan) {
        this.modelPelanggan = modelPelanggan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getTotalJumlah() {
        return totalJumlah;
    }

    public void setTotalJumlah(int totalJumlah) {
        this.totalJumlah = totalJumlah;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public double getBayar() {
        return bayar;
    }

    public void setBayar(double bayar) {
        this.bayar = bayar;
    }

    public double getDiskon() {
        return diskon;
    }

    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }

    public double getKembalian() {
        return kembalian;
    }

    public void setKembalian(double kembalian) {
        this.kembalian = kembalian;
    }
}
