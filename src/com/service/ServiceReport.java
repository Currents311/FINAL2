package com.service;

import java.util.Date;

public interface ServiceReport {
   void printStruk(String id);
   void printNota(String id);
   void printNotaBarangRusak(String id);
   void printLaporanProduk();
   void printLaporanPenjualan(Date startDate, Date endDate);
   void printLaporanPembelian(Date startDate, Date endDate);
   void printLaporanBarangRusak(Date startDate, Date endDate);
}
