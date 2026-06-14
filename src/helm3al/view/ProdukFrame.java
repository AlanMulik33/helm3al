/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package helm3al.view;

import helm3al.dao.ProdukDAO;
import helm3al.model.Produk;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class ProdukFrame extends javax.swing.JFrame {
    
    private ProdukDAO produkDAO = new ProdukDAO();
    private int selectedIdProduk = 0;
    /**
     * Creates new form ProdukFrame
     */
    public ProdukFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        isiComboBox();
        tampilData();
        kosongkanForm();
        
    }
    private void isiComboBox() {
    cmbMerk.removeAllItems();
    cmbMerk.addItem("Pilih Merk");
    cmbMerk.addItem("KYT");
    cmbMerk.addItem("INK");
    cmbMerk.addItem("NHK");
    cmbMerk.addItem("Cargloss");
    cmbMerk.addItem("AGV");
    cmbMerk.addItem("Arai");
    cmbMerk.addItem("Shoei");
    cmbMerk.addItem("NJS");

    cmbJenis.removeAllItems();
    cmbJenis.addItem("Pilih Jenis");
    cmbJenis.addItem("Full Face");
    cmbJenis.addItem("Half Face");
    cmbJenis.addItem("Modular");
    cmbJenis.addItem("Retro");
    cmbJenis.addItem("Off Road");
    cmbJenis.addItem("Open Face");

    cmbUkuran.removeAllItems();
    cmbUkuran.addItem("Pilih Ukuran");
    cmbUkuran.addItem("S");
    cmbUkuran.addItem("M");
    cmbUkuran.addItem("L");
    cmbUkuran.addItem("XL");
    cmbUkuran.addItem("XXL");

    cmbWarna.removeAllItems();
    cmbWarna.addItem("Pilih Warna");
    cmbWarna.addItem("Hitam");
    cmbWarna.addItem("Putih");
    cmbWarna.addItem("Merah");
    cmbWarna.addItem("Biru");
    cmbWarna.addItem("Abu-abu");
    cmbWarna.addItem("Coklat");
    cmbWarna.addItem("Hijau");
    cmbWarna.addItem("Kuning");

    cmbFilterMerk.removeAllItems();
    cmbFilterMerk.addItem("Semua");
    cmbFilterMerk.addItem("KYT");
    cmbFilterMerk.addItem("INK");
    cmbFilterMerk.addItem("NHK");
    cmbFilterMerk.addItem("Cargloss");
    cmbFilterMerk.addItem("AGV");
    cmbFilterMerk.addItem("Arai");
    cmbFilterMerk.addItem("Shoei");
    cmbFilterMerk.addItem("NJS");

    cmbFilterJenis.removeAllItems();
    cmbFilterJenis.addItem("Semua");
    cmbFilterJenis.addItem("Full Face");
    cmbFilterJenis.addItem("Half Face");
    cmbFilterJenis.addItem("Modular");
    cmbFilterJenis.addItem("Retro");
    cmbFilterJenis.addItem("Off Road");
    cmbFilterJenis.addItem("Open Face");

    cmbFilterUkuran.removeAllItems();
    cmbFilterUkuran.addItem("Semua");
    cmbFilterUkuran.addItem("S");
    cmbFilterUkuran.addItem("M");
    cmbFilterUkuran.addItem("L");
    cmbFilterUkuran.addItem("XL");
    cmbFilterUkuran.addItem("XXL");

    cmbFilterWarna.removeAllItems();
    cmbFilterWarna.addItem("Semua");
    cmbFilterWarna.addItem("Hitam");
    cmbFilterWarna.addItem("Putih");
    cmbFilterWarna.addItem("Merah");
    cmbFilterWarna.addItem("Biru");
    cmbFilterWarna.addItem("Abu-abu");
    cmbFilterWarna.addItem("Coklat");
    cmbFilterWarna.addItem("Hijau");
    cmbFilterWarna.addItem("Kuning");
}
    
    private void tampilData() {
        List<Produk> listProduk = produkDAO.getAllProduk();
        tampilDataKeTabel(listProduk);
    }

    private void tampilDataKeTabel(List<Produk> listProduk) {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Kode");
        model.addColumn("Nama Produk");
        model.addColumn("Merk");
        model.addColumn("Jenis");
        model.addColumn("Ukuran");
        model.addColumn("Warna");
        model.addColumn("Harga");
        model.addColumn("Stok");
        model.addColumn("ID Supplier");

        for (Produk produk : listProduk) {
            model.addRow(new Object[]{
                produk.getIdProduk(),
                produk.getKodeProduk(),
                produk.getNamaProduk(),
                produk.getMerk(),
                produk.getJenis(),
                produk.getUkuran(),
                produk.getWarna(),
                produk.getHarga(),
                produk.getStok(),
                produk.getIdSupplier()
            });
        }

        tblProduk.setModel(model);

        tblProduk.getColumnModel().getColumn(0).setMinWidth(0);
        tblProduk.getColumnModel().getColumn(0).setMaxWidth(0);
        tblProduk.getColumnModel().getColumn(0).setWidth(0);
    }
    
    private void kosongkanForm() {
       selectedIdProduk = 0;

       txtKodeProduk.setText("");
       txtNamaProduk.setText("");
       txtHarga.setText("");
       txtStok.setText("");
       txtIdSupplier.setText("");

       cmbMerk.setSelectedIndex(0);
       cmbJenis.setSelectedIndex(0);
       cmbUkuran.setSelectedIndex(0);
       cmbWarna.setSelectedIndex(0);

       tblProduk.clearSelection();
   }

   private boolean validasiForm() {
       if (txtKodeProduk.getText().isEmpty()
               || txtNamaProduk.getText().isEmpty()
               || txtHarga.getText().isEmpty()
               || txtStok.getText().isEmpty()
               || txtIdSupplier.getText().isEmpty()) {

           JOptionPane.showMessageDialog(this, "Kode, nama, harga, stok, dan ID Supplier wajib diisi!");
           return false;
       }

       if (cmbMerk.getSelectedIndex() == 0
               || cmbJenis.getSelectedIndex() == 0
               || cmbUkuran.getSelectedIndex() == 0
               || cmbWarna.getSelectedIndex() == 0) {

           JOptionPane.showMessageDialog(this, "Merk, jenis, ukuran, dan warna wajib dipilih!");
           return false;
       }

       try {
           Integer.parseInt(txtHarga.getText());
           Integer.parseInt(txtStok.getText());
           Integer.parseInt(txtIdSupplier.getText());
       } catch (NumberFormatException e) {
           JOptionPane.showMessageDialog(this, "Harga, stok, dan ID Supplier harus berupa angka!");
           return false;
       }

       return true;
   }

   private Produk ambilDataForm() {
       Produk produk = new Produk();

       produk.setIdProduk(selectedIdProduk);
       produk.setKodeProduk(txtKodeProduk.getText());
       produk.setNamaProduk(txtNamaProduk.getText());
       produk.setMerk(cmbMerk.getSelectedItem().toString());
       produk.setJenis(cmbJenis.getSelectedItem().toString());
       produk.setUkuran(cmbUkuran.getSelectedItem().toString());
       produk.setWarna(cmbWarna.getSelectedItem().toString());
       produk.setHarga(Integer.parseInt(txtHarga.getText()));
       produk.setStok(Integer.parseInt(txtStok.getText()));
       produk.setIdSupplier(Integer.parseInt(txtIdSupplier.getText()));

       return produk;
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtKodeProduk = new javax.swing.JTextField();
        txtNamaProduk = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        txtIdSupplier = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduk = new javax.swing.JTable();
        cmbMerk = new javax.swing.JComboBox<>();
        cmbJenis = new javax.swing.JComboBox<>();
        cmbUkuran = new javax.swing.JComboBox<>();
        cmbWarna = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cmbFilterMerk = new javax.swing.JComboBox<>();
        cmbFilterJenis = new javax.swing.JComboBox<>();
        cmbFilterUkuran = new javax.swing.JComboBox<>();
        cmbFilterWarna = new javax.swing.JComboBox<>();
        btnFilter = new javax.swing.JButton();
        btnResetFilter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Data Produk Helm");

        jLabel2.setText("Kode Produk");

        jLabel3.setText("Nama Produk");

        jLabel4.setText("Merk");

        jLabel5.setText("Jenis");

        jLabel6.setText("Ukuran");

        jLabel7.setText("Warna");

        jLabel8.setText("Harga");

        jLabel9.setText("Stok");

        jLabel10.setText("ID Supplier");

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel11.setText("Cari Nama/Kode");

        tblProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduk);

        cmbMerk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbUkuran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbWarna.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Filter Merk");

        jLabel13.setText("Filter Jenis");

        jLabel14.setText("Filter Ukuran");

        jLabel15.setText("Filter Warna");

        cmbFilterMerk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbFilterJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbFilterUkuran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbFilterWarna.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnFilter.setText("Filter");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        btnResetFilter.setText("Reset Filter");
        btnResetFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnSimpan, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBatal))
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnResetFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cmbFilterWarna, javax.swing.GroupLayout.Alignment.LEADING, 0, 133, Short.MAX_VALUE)
                                .addComponent(cmbFilterUkuran, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbFilterJenis, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbFilterMerk, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtKodeProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtIdSupplier))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbWarna, 0, 84, Short.MAX_VALUE)
                            .addComponent(cmbUkuran, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbMerk, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtKodeProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtIdSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan)
                            .addComponent(btnEdit)
                            .addComponent(btnHapus)
                            .addComponent(btnBatal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cmbFilterMerk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cmbFilterJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cmbFilterUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cmbFilterWarna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbMerk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbWarna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFilter)
                    .addComponent(btnResetFilter))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (!validasiForm()) {
            return;
        }

        Produk produk = ambilDataForm();

        boolean berhasil = produkDAO.tambahProduk(produk);

        if (berhasil) {
            JOptionPane.showMessageDialog(this, "Data produk berhasil disimpan!");
            tampilData();
            kosongkanForm();
        } else {
            JOptionPane.showMessageDialog(this, "Data produk gagal disimpan!");
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (selectedIdProduk == 0) {
            JOptionPane.showMessageDialog(this, "Pilih data produk yang ingin diedit!");
            return;
        }

        if (!validasiForm()) {
            return;
        }

        Produk produk = ambilDataForm();

        boolean berhasil = produkDAO.ubahProduk(produk);

        if (berhasil) {
            JOptionPane.showMessageDialog(this, "Data produk berhasil diedit!");
            tampilData();
            kosongkanForm();
        } else {
            JOptionPane.showMessageDialog(this, "Data produk gagal diedit!");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        if (selectedIdProduk == 0) {
            JOptionPane.showMessageDialog(this, "Pilih data produk yang ingin dihapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Apakah Anda yakin ingin menghapus produk ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION
        );

        if (konfirmasi == JOptionPane.YES_OPTION) {
            boolean berhasil = produkDAO.hapusProduk(selectedIdProduk);

            if (berhasil) {
                JOptionPane.showMessageDialog(this, "Data produk berhasil dihapus!");
                tampilData();
                kosongkanForm();
            } else {
                JOptionPane.showMessageDialog(this, "Data produk gagal dihapus!");
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        kosongkanForm();
        tampilData();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void tblProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdukMouseClicked
        // TODO add your handling code here:
        int row = tblProduk.getSelectedRow();

        if (row >= 0) {
            selectedIdProduk = Integer.parseInt(tblProduk.getValueAt(row, 0).toString());

            txtKodeProduk.setText(tblProduk.getValueAt(row, 1).toString());
            txtNamaProduk.setText(tblProduk.getValueAt(row, 2).toString());
            cmbMerk.setSelectedItem(tblProduk.getValueAt(row, 3).toString());
            cmbJenis.setSelectedItem(tblProduk.getValueAt(row, 4).toString());
            cmbUkuran.setSelectedItem(tblProduk.getValueAt(row, 5).toString());
            cmbWarna.setSelectedItem(tblProduk.getValueAt(row, 6).toString());
            txtHarga.setText(tblProduk.getValueAt(row, 7).toString());
            txtStok.setText(tblProduk.getValueAt(row, 8).toString());
            txtIdSupplier.setText(tblProduk.getValueAt(row, 9).toString());
        }
    }//GEN-LAST:event_tblProdukMouseClicked

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        String keyword = txtCari.getText();
        String merk = cmbFilterMerk.getSelectedItem().toString();
        String jenis = cmbFilterJenis.getSelectedItem().toString();
        String ukuran = cmbFilterUkuran.getSelectedItem().toString();
        String warna = cmbFilterWarna.getSelectedItem().toString();

        List<Produk> hasilFilter = produkDAO.filterProduk(keyword, merk, jenis, ukuran, warna);
        tampilDataKeTabel(hasilFilter);
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnResetFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFilterActionPerformed
        // TODO add your handling code here:
        txtCari.setText("");
        cmbFilterMerk.setSelectedIndex(0);
        cmbFilterJenis.setSelectedIndex(0);
        cmbFilterUkuran.setSelectedIndex(0);
        cmbFilterWarna.setSelectedIndex(0);

        tampilData();
    }//GEN-LAST:event_btnResetFilterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProdukFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProdukFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProdukFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProdukFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProdukFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnResetFilter;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbFilterJenis;
    private javax.swing.JComboBox<String> cmbFilterMerk;
    private javax.swing.JComboBox<String> cmbFilterUkuran;
    private javax.swing.JComboBox<String> cmbFilterWarna;
    private javax.swing.JComboBox<String> cmbJenis;
    private javax.swing.JComboBox<String> cmbMerk;
    private javax.swing.JComboBox<String> cmbUkuran;
    private javax.swing.JComboBox<String> cmbWarna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProduk;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtIdSupplier;
    private javax.swing.JTextField txtKodeProduk;
    private javax.swing.JTextField txtNamaProduk;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
