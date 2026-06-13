/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helm3al.model;

public class Produk {

    private int idProduk;
    private String kodeProduk;
    private String namaProduk;
    private String merk;
    private String jenis;
    private String ukuran;
    private String warna;
    private int harga;
    private int stok;
    private int idSupplier;

    public Produk() {
    }

    public Produk(int idProduk, String kodeProduk, String namaProduk, String merk, String jenis,
                  String ukuran, String warna, int harga, int stok, int idSupplier) {
        this.idProduk = idProduk;
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.merk = merk;
        this.jenis = jenis;
        this.ukuran = ukuran;
        this.warna = warna;
        this.harga = harga;
        this.stok = stok;
        this.idSupplier = idSupplier;
    }

    public int getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public void setKodeProduk(String kodeProduk) {
        this.kodeProduk = kodeProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }
}