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
public class HasilPerankingan {

    private int idPerankingan;
    private int idTeknisi;
    private double nilaiAkhir;
    private int ranking;
    private String keterangan;
    private Date tanggalRanking;

    private String namaTeknisi;
    private String kodeTeknisi;
    private String divisi;
    private String jabatan;

    public HasilPerankingan() {
    }

    public HasilPerankingan(int idPerankingan, int idTeknisi, double nilaiAkhir,
            int ranking, String keterangan, Date tanggalRanking) {
        this.idPerankingan = idPerankingan;
        this.idTeknisi = idTeknisi;
        this.nilaiAkhir = nilaiAkhir;
        this.ranking = ranking;
        this.keterangan = keterangan;
        this.tanggalRanking = tanggalRanking;
    }

    public HasilPerankingan(int idTeknisi, double nilaiAkhir,
            int ranking, String keterangan) {
        this.idTeknisi = idTeknisi;
        this.nilaiAkhir = nilaiAkhir;
        this.ranking = ranking;
        this.keterangan = keterangan;
    }

    public int getIdPerankingan() {
        return idPerankingan;
    }

    public void setIdPerankingan(int idPerankingan) {
        this.idPerankingan = idPerankingan;
    }

    public int getIdTeknisi() {
        return idTeknisi;
    }

    public void setIdTeknisi(int idTeknisi) {
        this.idTeknisi = idTeknisi;
    }

    public double getNilaiAkhir() {
        return nilaiAkhir;
    }

    public void setNilaiAkhir(double nilaiAkhir) {
        this.nilaiAkhir = nilaiAkhir;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getTanggalRanking() {
        return tanggalRanking;
    }

    public void setTanggalRanking(Date tanggalRanking) {
        this.tanggalRanking = tanggalRanking;
    }

    public String getNamaTeknisi() {
        return namaTeknisi;
    }

    public void setNamaTeknisi(String namaTeknisi) {
        this.namaTeknisi = namaTeknisi;
    }

    public String getKodeTeknisi() {
        return kodeTeknisi;
    }

    public void setKodeTeknisi(String kodeTeknisi) {
        this.kodeTeknisi = kodeTeknisi;
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

    @Override
    public String toString() {
        return "HasilPerankingan{"
                + "idPerankingan=" + idPerankingan
                + ", idTeknisi=" + idTeknisi
                + ", namaTeknisi='" + namaTeknisi + '\''
                + ", nilaiAkhir=" + nilaiAkhir
                + ", ranking=" + ranking
                + ", keterangan='" + keterangan + '\''
                + '}';
    }
}
