package com.model;

public class ModelPelanggan {
    private int idPelanggan;
    private String kodeRfid;
    private String namaPelanggan;
    private String teleponPelanggan;
    private String alamatPelanggan;
    private String levelMember;

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }
    
    public String getKodeRfid() {
        return kodeRfid;
    }

    public void setKodeRfid(String kodeRfid) {
        this.kodeRfid = kodeRfid;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getTeleponPelanggan() {
        return teleponPelanggan;
    }

    public void setTeleponPelanggan(String teleponPelanggan) {
        this.teleponPelanggan = teleponPelanggan;
    }

    public String getAlamatPelanggan() {
        return alamatPelanggan;
    }

    public void setAlamatPelanggan(String alamatPelanggan) {
        this.alamatPelanggan = alamatPelanggan;
    }

    public String getLevelMember() {
        return levelMember;
    }

    public void setLevelMember(String levelMember) {
        this.levelMember = levelMember;
    }
}
