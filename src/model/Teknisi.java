/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author ryumaaa
 */
public class Teknisi {

    private int idTeknisi;
    private String kodeTeknisi;
    private String namaTeknisi;
    private String jenisKelamin;
    private String tempatLahir;
    private Date tanggalLahir;
    private String alamat;
    private String noTelepon;
    private String email;
    private String divisi;
    private String jabatan;
    private Date tanggalMasuk;
    private String status;
    private Date createdAt;

    public Teknisi() {
    }

    public Teknisi(int idTeknisi, String kodeTeknisi, String namaTeknisi,
            String jenisKelamin, String tempatLahir, Date tanggalLahir,
            String alamat, String noTelepon, String email,
            String divisi, String jabatan, Date tanggalMasuk,
            String status, Date createdAt) {
        this.idTeknisi = idTeknisi;
        this.kodeTeknisi = kodeTeknisi;
        this.namaTeknisi = namaTeknisi;
        this.jenisKelamin = jenisKelamin;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.email = email;
        this.divisi = divisi;
        this.jabatan = jabatan;
        this.tanggalMasuk = tanggalMasuk;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Teknisi(String kodeTeknisi, String namaTeknisi,
            String jenisKelamin, String tempatLahir, Date tanggalLahir,
            String alamat, String noTelepon, String email,
            String divisi, String jabatan, Date tanggalMasuk, String status) {
        this.kodeTeknisi = kodeTeknisi;
        this.namaTeknisi = namaTeknisi;
        this.jenisKelamin = jenisKelamin;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.email = email;
        this.divisi = divisi;
        this.jabatan = jabatan;
        this.tanggalMasuk = tanggalMasuk;
        this.status = status;
    }

    public int getIdTeknisi() {
        return idTeknisi;
    }

    public void setIdTeknisi(int idTeknisi) {
        this.idTeknisi = idTeknisi;
    }

    public String getKodeTeknisi() {
        return kodeTeknisi;
    }

    public void setKodeTeknisi(String kodeTeknisi) {
        this.kodeTeknisi = kodeTeknisi;
    }

    public String getNamaTeknisi() {
        return namaTeknisi;
    }

    public void setNamaTeknisi(String namaTeknisi) {
        this.namaTeknisi = namaTeknisi;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Teknisi{"
                + "idTeknisi=" + idTeknisi
                + ", kodeTeknisi='" + kodeTeknisi + '\''
                + ", namaTeknisi='" + namaTeknisi + '\''
                + ", jenisKelamin='" + jenisKelamin + '\''
                + ", divisi='" + divisi + '\''
                + ", jabatan='" + jabatan + '\''
                + ", status='" + status + '\''
                + '}';
    }
}
