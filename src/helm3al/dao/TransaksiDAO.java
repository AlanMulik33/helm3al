/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helm3al.dao;

import helm3al.config.Koneksi;
import helm3al.model.Transaksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {

    private Connection conn;

    public TransaksiDAO() {
        conn = Koneksi.getKoneksi();
    }

    public boolean simpanTransaksi(Transaksi transaksi) {
        String sqlTransaksi = "INSERT INTO transaksi "
                + "(kode_transaksi, tanggal_transaksi, id_produk, jumlah, harga, total, nama_pelanggan, id_user) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlUpdateStok = "UPDATE produk SET stok = stok - ? WHERE id_produk = ?";

        try {
            conn.setAutoCommit(false);

            PreparedStatement psTransaksi = conn.prepareStatement(sqlTransaksi);
            psTransaksi.setString(1, transaksi.getKodeTransaksi());
            psTransaksi.setDate(2, transaksi.getTanggalTransaksi());
            psTransaksi.setInt(3, transaksi.getIdProduk());
            psTransaksi.setInt(4, transaksi.getJumlah());
            psTransaksi.setInt(5, transaksi.getHarga());
            psTransaksi.setInt(6, transaksi.getTotal());
            psTransaksi.setString(7, transaksi.getNamaPelanggan());
            psTransaksi.setInt(8, transaksi.getIdUser());
            psTransaksi.executeUpdate();

            PreparedStatement psStok = conn.prepareStatement(sqlUpdateStok);
            psStok.setInt(1, transaksi.getJumlah());
            psStok.setInt(2, transaksi.getIdProduk());
            psStok.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);

            return true;

        } catch (SQLException e) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Rollback gagal");
                System.out.println("Error: " + ex.getMessage());
            }

            System.out.println("Gagal menyimpan transaksi");
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public List<Transaksi> getAllTransaksi() {
        List<Transaksi> listTransaksi = new ArrayList<>();

        String sql = "SELECT t.*, p.nama_produk "
                + "FROM transaksi t "
                + "JOIN produk p ON t.id_produk = p.id_produk "
                + "ORDER BY t.id_transaksi DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaksi transaksi = new Transaksi();

                transaksi.setIdTransaksi(rs.getInt("id_transaksi"));
                transaksi.setKodeTransaksi(rs.getString("kode_transaksi"));
                transaksi.setTanggalTransaksi(rs.getDate("tanggal_transaksi"));
                transaksi.setIdProduk(rs.getInt("id_produk"));
                transaksi.setNamaProduk(rs.getString("nama_produk"));
                transaksi.setJumlah(rs.getInt("jumlah"));
                transaksi.setHarga(rs.getInt("harga"));
                transaksi.setTotal(rs.getInt("total"));
                transaksi.setNamaPelanggan(rs.getString("nama_pelanggan"));
                transaksi.setIdUser(rs.getInt("id_user"));

                listTransaksi.add(transaksi);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data transaksi");
            System.out.println("Error: " + e.getMessage());
        }

        return listTransaksi;
    }

    public String generateKodeTransaksi() {
        String kode = "TRX001";

        String sql = "SELECT MAX(id_transaksi) AS id_terakhir FROM transaksi";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idTerakhir = rs.getInt("id_terakhir");
                int nomorBaru = idTerakhir + 1;

                kode = String.format("TRX%03d", nomorBaru);
            }

        } catch (SQLException e) {
            System.out.println("Gagal membuat kode transaksi");
            System.out.println("Error: " + e.getMessage());
        }

        return kode;
    }
}