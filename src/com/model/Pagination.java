package com.model;

public class Pagination {
    private int halamanSaatIni;
    private int totalData;
    private int dataPerHalaman;

    public Pagination(int dataPerHalaman){
        this.dataPerHalaman = dataPerHalaman;
        this.halamanSaatIni = 1;
    }
    
    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public int getTotalHalaman(){
        return (int) Math.ceil((double) totalData/dataPerHalaman);
    }
    
    public int getPosisiAwal(){
        return (halamanSaatIni -1) * dataPerHalaman;
    }
        
    public int getDataPerHalaman() {
        return dataPerHalaman;
    }

    public void halamanPertama(){
        halamanSaatIni = 1;
    }
    
    public void halamanBerikutnya(){
        if (halamanSaatIni < getTotalHalaman()){
            halamanSaatIni++;
        }
    }
    
    public void halamanSebelumnya(){
        if(halamanSaatIni > 1){
            halamanSaatIni--;
        }
    }
    
    public void halamanTerakhir(){
        halamanSaatIni = getTotalHalaman();
    }
    
    public int getHalamanSaatIni() {
        return halamanSaatIni;
    }

}
