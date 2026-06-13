/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helm3al.model;

public class Supplier {

    private int idSupplier;
    private String kodeSupplier;
    private String namaSupplier;
    private String noTelp;
    private String alamat;

    public Supplier() {
    }

    public Supplier(int idSupplier, String kodeSupplier, String namaSupplier, String noTelp, String alamat) {
        this.idSupplier = idSupplier;
        this.kodeSupplier = kodeSupplier;
        this.namaSupplier = namaSupplier;
        this.noTelp = noTelp;
        this.alamat = alamat;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getKodeSupplier() {
        return kodeSupplier;
    }

    public void setKodeSupplier(String kodeSupplier) {
        this.kodeSupplier = kodeSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}