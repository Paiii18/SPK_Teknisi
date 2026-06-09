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
public class NilaiTeknisi {

    private int idNilai;
    private int idTeknisi;
    private int idKriteria;
    private double nilai;
    private String keterangan;
    private Date createdAt;

    private String namaTeknisi;
    private String kodeTeknisi;
    private String namaKriteria;
    private String kodeKriteria;

    public NilaiTeknisi() {
    }

    public NilaiTeknisi(int idNilai, int idTeknisi, int idKriteria,
            double nilai, String keterangan, Date createdAt) {
        this.idNilai = idNilai;
        this.idTeknisi = idTeknisi;
        this.idKriteria = idKriteria;
        this.nilai = nilai;
        this.keterangan = keterangan;
        this.createdAt = createdAt;
    }

    public NilaiTeknisi(int idTeknisi, int idKriteria,
            double nilai, String keterangan) {
        this.idTeknisi = idTeknisi;
        this.idKriteria = idKriteria;
        this.nilai = nilai;
        this.keterangan = keterangan;
    }

    public int getIdNilai() {
        return idNilai;
    }

    public void setIdNilai(int idNilai) {
        this.idNilai = idNilai;
    }

    public int getIdTeknisi() {
        return idTeknisi;
    }

    public void setIdTeknisi(int idTeknisi) {
        this.idTeknisi = idTeknisi;
    }

    public int getIdKriteria() {
        return idKriteria;
    }

    public void setIdKriteria(int idKriteria) {
        this.idKriteria = idKriteria;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public String getNamaKriteria() {
        return namaKriteria;
    }

    public void setNamaKriteria(String namaKriteria) {
        this.namaKriteria = namaKriteria;
    }

    public String getKodeKriteria() {
        return kodeKriteria;
    }

    public void setKodeKriteria(String kodeKriteria) {
        this.kodeKriteria = kodeKriteria;
    }

    @Override
    public String toString() {
        return "NilaiTeknisi{"
                + "idNilai=" + idNilai
                + ", idTeknisi=" + idTeknisi
                + ", idKriteria=" + idKriteria
                + ", nilai=" + nilai
                + ", keterangan='" + keterangan + '\''
                + '}';
    }

}
