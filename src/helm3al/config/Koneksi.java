/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helm3al.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static Connection koneksi;

    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/toko_helm3al";
                String user = "root";
                String password = "123456";

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);

                System.out.println("Koneksi database berhasil");

            } catch (SQLException e) {
                System.out.println("Koneksi database gagal");
                System.out.println("Error: " + e.getMessage());
            }
        }

        return koneksi;
    }
}
