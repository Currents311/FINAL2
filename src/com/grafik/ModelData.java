package com.grafik;

public class ModelData {
    String bulan;
    int penjualan;
    int pembelian;
    int barang_rusak;
    
    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public int getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(int penjualan) {
        this.penjualan = penjualan;
    }

    public int getPembelian() {
        return pembelian;
    }

    public void setPembelian(int pembelian) {
        this.pembelian = pembelian;
    }

    public int getBarang_rusak() {
        return barang_rusak;
    }

    public void setBarang_rusak(int barang_rusak) {
        this.barang_rusak = barang_rusak;
    }

    public ModelData(String bulan, int penjualan, int pembelian, int barang_rusak) {
        this.bulan = bulan;
        this.penjualan = penjualan;
        this.pembelian = pembelian;
        this.barang_rusak = barang_rusak;
    }    
}
