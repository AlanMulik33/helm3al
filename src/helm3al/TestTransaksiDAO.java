/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helm3al;

import helm3al.dao.TransaksiDAO;
import helm3al.model.Transaksi;
import java.sql.Date;

public class TestTransaksiDAO {

    public static void main(String[] args) {
        TransaksiDAO transaksiDAO = new TransaksiDAO();

        Transaksi transaksi = new Transaksi();

        transaksi.setKodeTransaksi(transaksiDAO.generateKodeTransaksi());
        transaksi.setTanggalTransaksi(new Date(System.currentTimeMillis()));
        transaksi.setIdProduk(1);
        transaksi.setJumlah(1);
        transaksi.setHarga(750000);
        transaksi.setTotal(750000);
        transaksi.setNamaPelanggan("Pelanggan Test");
        transaksi.setIdUser(1);

        boolean berhasil = transaksiDAO.simpanTransaksi(transaksi);

        if (berhasil) {
            System.out.println("Transaksi berhasil disimpan");
        } else {
            System.out.println("Transaksi gagal disimpan");
        }
    }
}
