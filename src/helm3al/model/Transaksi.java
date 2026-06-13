/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helm3al.model;

import java.sql.Date;

public class Transaksi {

    private int idTransaksi;
    private String kodeTransaksi;
    private Date tanggalTransaksi;
    private int idProduk;
    private String namaProduk;
    private int jumlah;
    private int harga;
    private int total;
    private String namaPelanggan;
    private int idUser;

    public Transaksi() {
    }

    public Transaksi(int idTransaksi, String kodeTransaksi, Date tanggalTransaksi,
                     int idProduk, String namaProduk, int jumlah, int harga,
                     int total, String namaPelanggan, int idUser) {
        this.idTransaksi = idTransaksi;
        this.kodeTransaksi = kodeTransaksi;
        this.tanggalTransaksi = tanggalTransaksi;
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.jumlah = jumlah;
        this.harga = harga;
        this.total = total;
        this.namaPelanggan = namaPelanggan;
        this.idUser = idUser;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public int getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
