/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package helm3al.view;

import helm3al.dao.ProdukDAO;
import helm3al.dao.TransaksiDAO;
import helm3al.model.Produk;
import helm3al.model.Transaksi;
import helm3al.model.User;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class TransaksiFrame extends javax.swing.JFrame {
    
    private ProdukDAO produkDAO = new ProdukDAO();
    private TransaksiDAO transaksiDAO = new TransaksiDAO();

    private User userLogin;
    private int selectedIdProduk = 0;
    private int selectedHarga = 0;
    private int selectedStok = 0;

    /**
     * Creates new form TransaksiFrame
     */
    public TransaksiFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setupAwal();
    }

    public TransaksiFrame(User userLogin) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.userLogin = userLogin;
        setupAwal();
    }
    
    private void setupAwal() {
        isiComboBoxFilter();
        setDataAwalTransaksi();
        tampilDataProduk();
        tampilDataTransaksi();
        kosongkanFormTransaksi();
    }

    private void isiComboBoxFilter() {
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
    
    private void setDataAwalTransaksi() {
        txtKodeTransaksi.setText(transaksiDAO.generateKodeTransaksi());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txtTanggal.setText(sdf.format(new java.util.Date()));
    }
    
    private void tampilDataProduk() {
        List<Produk> listProduk = produkDAO.getAllProduk();
        tampilProdukKeTabel(listProduk);
    }

    private void tampilProdukKeTabel(List<Produk> listProduk) {
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
                produk.getStok()
            });
        }

        tblProduk.setModel(model);

        tblProduk.getColumnModel().getColumn(0).setMinWidth(0);
        tblProduk.getColumnModel().getColumn(0).setMaxWidth(0);
        tblProduk.getColumnModel().getColumn(0).setWidth(0);
    }
    
    private void tampilDataTransaksi() {
        List<Transaksi> listTransaksi = transaksiDAO.getAllTransaksi();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Kode Transaksi");
        model.addColumn("Tanggal");
        model.addColumn("Produk");
        model.addColumn("Jumlah");
        model.addColumn("Harga");
        model.addColumn("Total");
        model.addColumn("Pelanggan");

        for (Transaksi transaksi : listTransaksi) {
            model.addRow(new Object[]{
                transaksi.getKodeTransaksi(),
                transaksi.getTanggalTransaksi(),
                transaksi.getNamaProduk(),
                transaksi.getJumlah(),
                transaksi.getHarga(),
                transaksi.getTotal(),
                transaksi.getNamaPelanggan()
            });
        }

        tblTransaksi.setModel(model);
    }
    
    private void kosongkanFormTransaksi() {
        selectedIdProduk = 0;
        selectedHarga = 0;
        selectedStok = 0;

        txtNamaPelanggan.setText("");
        txtNamaProduk.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        txtJumlah.setText("");
        txtTotal.setText("");

        tblProduk.clearSelection();
    }

    private boolean validasiTransaksi() {
        if (txtNamaPelanggan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama pelanggan wajib diisi!");
            return false;
        }

        if (selectedIdProduk == 0) {
            JOptionPane.showMessageDialog(this, "Pilih produk terlebih dahulu!");
            return false;
        }

        if (txtJumlah.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jumlah beli wajib diisi!");
            return false;
        }

        int jumlah;

        try {
            jumlah = Integer.parseInt(txtJumlah.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah beli harus berupa angka!");
            return false;
        }

        if (jumlah <= 0) {
            JOptionPane.showMessageDialog(this, "Jumlah beli harus lebih dari 0!");
            return false;
        }

        if (jumlah > selectedStok) {
            JOptionPane.showMessageDialog(this, "Stok tidak mencukupi!");
            return false;
        }

        return true;
    }
    
    private void hitungTotal() {
        if (selectedIdProduk == 0) {
            JOptionPane.showMessageDialog(this, "Pilih produk terlebih dahulu!");
            return;
        }

        if (txtJumlah.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jumlah beli wajib diisi!");
            return;
        }

        try {
            int jumlah = Integer.parseInt(txtJumlah.getText());

            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Jumlah beli harus lebih dari 0!");
                return;
            }

            if (jumlah > selectedStok) {
                JOptionPane.showMessageDialog(this, "Stok tidak mencukupi!");
                return;
            }

            int total = jumlah * selectedHarga;
            txtTotal.setText(String.valueOf(total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah beli harus berupa angka!");
        }
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
        txtKodeTransaksi = new javax.swing.JTextField();
        txtNamaPelanggan = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCariProduk = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnFilterProduk = new javax.swing.JButton();
        cmbFilterMerk = new javax.swing.JComboBox<>();
        cmbFilterJenis = new javax.swing.JComboBox<>();
        cmbFilterUkuran = new javax.swing.JComboBox<>();
        cmbFilterWarna = new javax.swing.JComboBox<>();
        btnResetFilter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduk = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNamaProduk = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        txtJumlah = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnHitung = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Transaksi Penjualan");

        jLabel2.setText("Kode Tranasksi");

        jLabel3.setText("Nama Pelanggan");

        jLabel4.setText("Tanggal");

        txtNamaPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaPelangganActionPerformed(evt);
            }
        });

        jLabel5.setText("Cari Produk");

        txtCariProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariProdukActionPerformed(evt);
            }
        });

        jLabel6.setText("Merk");

        jLabel7.setText("Jenis");

        jLabel8.setText("Ukuran");

        jLabel9.setText("Warna");

        btnFilterProduk.setText("Filter Produk");
        btnFilterProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterProdukActionPerformed(evt);
            }
        });

        cmbFilterMerk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbFilterJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbFilterUkuran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbFilterWarna.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnResetFilter.setText("Reset Filter");
        btnResetFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFilterActionPerformed(evt);
            }
        });

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

        jLabel10.setText("Produk Dipilih");

        jLabel11.setText("Harga");

        jLabel12.setText("Stok");

        jLabel13.setText("Jumlah Beli");

        jLabel14.setText("Total");

        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        btnHitung.setText("Hitung Total");
        btnHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitungActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan Transaksi");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblTransaksi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNamaPelanggan))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtKodeTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTanggal))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCariProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(125, 125, 125)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbFilterJenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbFilterUkuran, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbFilterWarna, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbFilterMerk, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(btnFilterProduk)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnResetFilter))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnHitung)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSimpan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnBatal))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtTotal))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtJumlah))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtStok))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtHarga))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(txtKodeTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtCariProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbFilterMerk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbFilterJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbFilterUkuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbFilterWarna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFilterProduk)
                    .addComponent(btnResetFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHitung)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaPelangganActionPerformed

    private void txtCariProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariProdukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariProdukActionPerformed

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void btnFilterProdukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterProdukActionPerformed
        // TODO add your handling code here:
        String keyword = txtCariProduk.getText();
        String merk = cmbFilterMerk.getSelectedItem().toString();
        String jenis = cmbFilterJenis.getSelectedItem().toString();
        String ukuran = cmbFilterUkuran.getSelectedItem().toString();
        String warna = cmbFilterWarna.getSelectedItem().toString();

        List<Produk> hasilFilter = produkDAO.filterProduk(keyword, merk, jenis, ukuran, warna);
        tampilProdukKeTabel(hasilFilter);
    }//GEN-LAST:event_btnFilterProdukActionPerformed

    private void btnResetFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFilterActionPerformed
        // TODO add your handling code here:
        txtCariProduk.setText("");
        cmbFilterMerk.setSelectedIndex(0);
        cmbFilterJenis.setSelectedIndex(0);
        cmbFilterUkuran.setSelectedIndex(0);
        cmbFilterWarna.setSelectedIndex(0);

        tampilDataProduk();
    }//GEN-LAST:event_btnResetFilterActionPerformed

    private void tblProdukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdukMouseClicked
        // TODO add your handling code here:
        int row = tblProduk.getSelectedRow();

        if (row >= 0) {
            selectedIdProduk = Integer.parseInt(tblProduk.getValueAt(row, 0).toString());

            String namaProduk = tblProduk.getValueAt(row, 2).toString();
            selectedHarga = Integer.parseInt(tblProduk.getValueAt(row, 7).toString());
            selectedStok = Integer.parseInt(tblProduk.getValueAt(row, 8).toString());

            txtNamaProduk.setText(namaProduk);
            txtHarga.setText(String.valueOf(selectedHarga));
            txtStok.setText(String.valueOf(selectedStok));
            txtJumlah.setText("");
            txtTotal.setText("");
        }
    }//GEN-LAST:event_tblProdukMouseClicked

    private void btnHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitungActionPerformed
        // TODO add your handling code here:
        hitungTotal();
    }//GEN-LAST:event_btnHitungActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (!validasiTransaksi()) {
            return;
        }

        hitungTotal();

        if (txtTotal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Total belum dihitung!");
            return;
        }

        Transaksi transaksi = new Transaksi();

        transaksi.setKodeTransaksi(txtKodeTransaksi.getText());
        transaksi.setTanggalTransaksi(Date.valueOf(txtTanggal.getText()));
        transaksi.setIdProduk(selectedIdProduk);
        transaksi.setJumlah(Integer.parseInt(txtJumlah.getText()));
        transaksi.setHarga(selectedHarga);
        transaksi.setTotal(Integer.parseInt(txtTotal.getText()));
        transaksi.setNamaPelanggan(txtNamaPelanggan.getText());

        if (userLogin != null) {
            transaksi.setIdUser(userLogin.getIdUser());
        } else {
            transaksi.setIdUser(1);
        }

        boolean berhasil = transaksiDAO.simpanTransaksi(transaksi);

        if (berhasil) {
            JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan!");

            setDataAwalTransaksi();
            tampilDataProduk();
            tampilDataTransaksi();
            kosongkanFormTransaksi();

        } else {
            JOptionPane.showMessageDialog(this, "Transaksi gagal disimpan!");
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        kosongkanFormTransaksi();
    }//GEN-LAST:event_btnBatalActionPerformed

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
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnFilterProduk;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnResetFilter;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbFilterJenis;
    private javax.swing.JComboBox<String> cmbFilterMerk;
    private javax.swing.JComboBox<String> cmbFilterUkuran;
    private javax.swing.JComboBox<String> cmbFilterWarna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblProduk;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtCariProduk;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKodeTransaksi;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JTextField txtNamaProduk;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
