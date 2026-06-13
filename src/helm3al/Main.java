/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package helm3al;

import helm3al.dao.UserDAO;
import helm3al.model.User;
import helm3al.view.LoginFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAO();
//
//        User user = userDAO.login("Alzendi10", "Alzendi7");
//
//        if (user != null) {
//            System.out.println("Login berhasil");
//            System.out.println("Nama  : " + user.getNamaLengkap());
//            System.out.println("Role  : " + user.getRole());
//        } else {
//            System.out.println("Login gagal, username atau password salah");
//        }
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}