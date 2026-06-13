/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helm3al.dao;

import helm3al.config.Koneksi;
import helm3al.model.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    private Connection conn;

    public SupplierDAO() {
        conn = Koneksi.getKoneksi();
    }

    public List<Supplier> getAllSupplier() {
        List<Supplier> listSupplier = new ArrayList<>();

        String sql = "SELECT * FROM supplier ORDER BY id_supplier DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Supplier supplier = new Supplier();

                supplier.setIdSupplier(rs.getInt("id_supplier"));
                supplier.setKodeSupplier(rs.getString("kode_supplier"));
                supplier.setNamaSupplier(rs.getString("nama_supplier"));
                supplier.setNoTelp(rs.getString("no_telp"));
                supplier.setAlamat(rs.getString("alamat"));

                listSupplier.add(supplier);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mengambil data supplier");
            System.out.println("Error: " + e.getMessage());
        }

        return listSupplier;
    }

    public boolean tambahSupplier(Supplier supplier) {
        String sql = "INSERT INTO supplier "
                + "(kode_supplier, nama_supplier, no_telp, alamat) "
                + "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, supplier.getKodeSupplier());
            ps.setString(2, supplier.getNamaSupplier());
            ps.setString(3, supplier.getNoTelp());
            ps.setString(4, supplier.getAlamat());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Gagal menambah supplier");
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean ubahSupplier(Supplier supplier) {
        String sql = "UPDATE supplier SET "
                + "kode_supplier = ?, "
                + "nama_supplier = ?, "
                + "no_telp = ?, "
                + "alamat = ? "
                + "WHERE id_supplier = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, supplier.getKodeSupplier());
            ps.setString(2, supplier.getNamaSupplier());
            ps.setString(3, supplier.getNoTelp());
            ps.setString(4, supplier.getAlamat());
            ps.setInt(5, supplier.getIdSupplier());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Gagal mengubah supplier");
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean hapusSupplier(int idSupplier) {
        String sql = "DELETE FROM supplier WHERE id_supplier = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idSupplier);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Gagal menghapus supplier");
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public List<Supplier> cariSupplier(String keyword) {
        List<Supplier> listSupplier = new ArrayList<>();

        String sql = "SELECT * FROM supplier "
                + "WHERE kode_supplier LIKE ? "
                + "OR nama_supplier LIKE ? "
                + "OR no_telp LIKE ? "
                + "OR alamat LIKE ? "
                + "ORDER BY id_supplier DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            String cari = "%" + keyword + "%";

            ps.setString(1, cari);
            ps.setString(2, cari);
            ps.setString(3, cari);
            ps.setString(4, cari);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Supplier supplier = new Supplier();

                supplier.setIdSupplier(rs.getInt("id_supplier"));
                supplier.setKodeSupplier(rs.getString("kode_supplier"));
                supplier.setNamaSupplier(rs.getString("nama_supplier"));
                supplier.setNoTelp(rs.getString("no_telp"));
                supplier.setAlamat(rs.getString("alamat"));

                listSupplier.add(supplier);
            }

        } catch (SQLException e) {
            System.out.println("Gagal mencari supplier");
            System.out.println("Error: " + e.getMessage());
        }

        return listSupplier;
    }
}