/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helm3al.dao;

import helm3al.config.Koneksi;
import helm3al.model.Produk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdukDAO {

    private Connection conn;

    public ProdukDAO() {
        conn = Koneksi.getKoneksi();
    }

    public List<Produk> getAllProduk() {
        List<Produk> listProduk = new ArrayList<>();

        String sql = "SELECT * FROM produk ORDER BY id_produk DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produk produk = new Produk();

                produk.setIdProduk(rs.getInt("id_produk"));
                produk.setKodeProduk(rs.getString("kode_produk"));
                produk.setNamaProduk(rs.getString("nama_produk"));
                produk.setMerk(rs.getString("merk"));
                produk.setJenis(rs.getString("jenis"));
                produk.setUkuran(rs.getString("ukuran"));
                produk.setWarna(rs.getString("warna"));
                produk.setHarga(rs.getInt("harga"));
                produk.setStok(rs.getInt("stok"));
                produk.setIdSupplier(rs.getInt("id_supplier"));

                listProduk.add(produk);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data produk");
            System.out.println("Error: " + e.getMessage());
        }

        return listProduk;
    }

    public boolean tambahProduk(Produk produk) {
        String sql = "INSERT INTO produk "
                + "(kode_produk, nama_produk, merk, jenis, ukuran, warna, harga, stok, id_supplier) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, produk.getKodeProduk());
            ps.setString(2, produk.getNamaProduk());
            ps.setString(3, produk.getMerk());
            ps.setString(4, produk.getJenis());
            ps.setString(5, produk.getUkuran());
            ps.setString(6, produk.getWarna());
            ps.setInt(7, produk.getHarga());
            ps.setInt(8, produk.getStok());
            ps.setInt(9, produk.getIdSupplier());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Gagal menambah produk");
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean ubahProduk(Produk produk) {
        String sql = "UPDATE produk SET "
                + "kode_produk = ?, "
                + "nama_produk = ?, "
                + "merk = ?, "
                + "jenis = ?, "
                + "ukuran = ?, "
                + "warna = ?, "
                + "harga = ?, "
                + "stok = ?, "
                + "id_supplier = ? "
                + "WHERE id_produk = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, produk.getKodeProduk());
            ps.setString(2, produk.getNamaProduk());
            ps.setString(3, produk.getMerk());
            ps.setString(4, produk.getJenis());
            ps.setString(5, produk.getUkuran());
            ps.setString(6, produk.getWarna());
            ps.setInt(7, produk.getHarga());
            ps.setInt(8, produk.getStok());
            ps.setInt(9, produk.getIdSupplier());
            ps.setInt(10, produk.getIdProduk());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah produk");
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean hapusProduk(int idProduk) {
        String sql = "DELETE FROM produk WHERE id_produk = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idProduk);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus produk");
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public List<Produk> cariProduk(String keyword) {
        List<Produk> listProduk = new ArrayList<>();

        String sql = "SELECT * FROM produk "
                + "WHERE kode_produk LIKE ? "
                + "OR nama_produk LIKE ? "
                + "OR merk LIKE ? "
                + "ORDER BY id_produk DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            String cari = "%" + keyword + "%";
            ps.setString(1, cari);
            ps.setString(2, cari);
            ps.setString(3, cari);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produk produk = new Produk();

                produk.setIdProduk(rs.getInt("id_produk"));
                produk.setKodeProduk(rs.getString("kode_produk"));
                produk.setNamaProduk(rs.getString("nama_produk"));
                produk.setMerk(rs.getString("merk"));
                produk.setJenis(rs.getString("jenis"));
                produk.setUkuran(rs.getString("ukuran"));
                produk.setWarna(rs.getString("warna"));
                produk.setHarga(rs.getInt("harga"));
                produk.setStok(rs.getInt("stok"));
                produk.setIdSupplier(rs.getInt("id_supplier"));

                listProduk.add(produk);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mencari produk");
            System.out.println("Error: " + e.getMessage());
        }

        return listProduk;
    }
    
    public List<Produk> filterProduk(String keyword, String merk, String jenis, String ukuran, String warna) {
        List<Produk> listProduk = new ArrayList<>();

        String sql = "SELECT * FROM produk "
                + "WHERE (kode_produk LIKE ? OR nama_produk LIKE ? OR merk LIKE ?) "
                + "AND (? = 'Semua' OR merk = ?) "
                + "AND (? = 'Semua' OR jenis = ?) "
                + "AND (? = 'Semua' OR ukuran = ?) "
                + "AND (? = 'Semua' OR warna = ?) "
                + "ORDER BY id_produk DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            String cari = "%" + keyword + "%";

            ps.setString(1, cari);
            ps.setString(2, cari);
            ps.setString(3, cari);

            ps.setString(4, merk);
            ps.setString(5, merk);

            ps.setString(6, jenis);
            ps.setString(7, jenis);

            ps.setString(8, ukuran);
            ps.setString(9, ukuran);

            ps.setString(10, warna);
            ps.setString(11, warna);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produk produk = new Produk();

                produk.setIdProduk(rs.getInt("id_produk"));
                produk.setKodeProduk(rs.getString("kode_produk"));
                produk.setNamaProduk(rs.getString("nama_produk"));
                produk.setMerk(rs.getString("merk"));
                produk.setJenis(rs.getString("jenis"));
                produk.setUkuran(rs.getString("ukuran"));
                produk.setWarna(rs.getString("warna"));
                produk.setHarga(rs.getInt("harga"));
                produk.setStok(rs.getInt("stok"));
                produk.setIdSupplier(rs.getInt("id_supplier"));

                listProduk.add(produk);
            }

        } catch (SQLException e) {
            System.out.println("Gagal memfilter data produk");
            System.out.println("Error: " + e.getMessage());
        }

        return listProduk;
    }
    
    public Produk getProdukById(int idProduk) {
        Produk produk = null;

        String sql = "SELECT * FROM produk WHERE id_produk = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idProduk);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                produk = new Produk();

                produk.setIdProduk(rs.getInt("id_produk"));
                produk.setKodeProduk(rs.getString("kode_produk"));
                produk.setNamaProduk(rs.getString("nama_produk"));
                produk.setMerk(rs.getString("merk"));
                produk.setJenis(rs.getString("jenis"));
                produk.setUkuran(rs.getString("ukuran"));
                produk.setWarna(rs.getString("warna"));
                produk.setHarga(rs.getInt("harga"));
                produk.setStok(rs.getInt("stok"));
                produk.setIdSupplier(rs.getInt("id_supplier"));
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil produk berdasarkan ID");
            System.out.println("Error: " + e.getMessage());
        }

        return produk;
    }
}
