/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helm3al.dao;

import helm3al.config.Koneksi;
import helm3al.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection conn;

    public UserDAO() {
        conn = Koneksi.getKoneksi();
    }

    public User login(String username, String password) {
        User user = null;

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();

                user.setIdUser(rs.getInt("id_user"));
                user.setNamaLengkap(rs.getString("nama_lengkap"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }

        } catch (SQLException e) {
            System.out.println("Login gagal diproses");
            System.out.println("Error: " + e.getMessage());
        }

        return user;
    }
}