package com.model;

import java.sql.Timestamp;

public class ModelKaryawan {
    private int idKaryawan;
    private String namaKaryawan;
    private String teleponKaryawan;
    private String passwordKaryawan;
    private String role;
    private Timestamp lastSeen;

    public int getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(int idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public String getTeleponKaryawan() {
        return teleponKaryawan;
    }

    public void setTeleponKaryawan(String teleponKaryawan) {
        this.teleponKaryawan = teleponKaryawan;
    }

    public String getPasswordKaryawan() {
        return passwordKaryawan;
    }

    public void setPasswordKaryawan(String passwordKaryawan) {
        this.passwordKaryawan = passwordKaryawan;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public Timestamp getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Timestamp lastSeen) {
        this.lastSeen = lastSeen;
    }

}