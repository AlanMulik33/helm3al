package helm3al;

import helm3al.dao.ProdukDAO;
import helm3al.model.Produk;
import java.util.List;

public class TestProdukDAO {

    public static void main(String[] args) {
        ProdukDAO produkDAO = new ProdukDAO();

        List<Produk> listProduk = produkDAO.getAllProduk();

        for (Produk produk : listProduk) {
            System.out.println(
                    produk.getKodeProduk() + " - "
                    + produk.getNamaProduk() + " - "
                    + produk.getMerk() + " - "
                    + produk.getHarga() + " - "
                    + produk.getStok()
            );
        }
    }
}