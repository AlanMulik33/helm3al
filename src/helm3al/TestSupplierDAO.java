/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helm3al;

import helm3al.dao.SupplierDAO;
import helm3al.model.Supplier;
import java.util.List;

public class TestSupplierDAO {

    public static void main(String[] args) {
        SupplierDAO supplierDAO = new SupplierDAO();

        List<Supplier> listSupplier = supplierDAO.getAllSupplier();

        for (Supplier supplier : listSupplier) {
            System.out.println(
                    supplier.getIdSupplier() + " - "
                    + supplier.getKodeSupplier() + " - "
                    + supplier.getNamaSupplier() + " - "
                    + supplier.getNoTelp() + " - "
                    + supplier.getAlamat()
            );
        }
    }
}
