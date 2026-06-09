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
public class MatriksPerbandingan {

    private int idMatriks;
    private int idKriteriaBaris;
    private int idKriteriaKolom;
    private double nilaiPerbandingan;
    private Date createdAt;

    private String namaKriteriaBaris;
    private String namaKriteriaKolom;

    public MatriksPerbandingan() {
    }

    public MatriksPerbandingan(int idMatriks, int idKriteriaBaris,
            int idKriteriaKolom, double nilaiPerbandingan,
            Date createdAt) {
        this.idMatriks = idMatriks;
        this.idKriteriaBaris = idKriteriaBaris;
        this.idKriteriaKolom = idKriteriaKolom;
        this.nilaiPerbandingan = nilaiPerbandingan;
        this.createdAt = createdAt;
    }

    public MatriksPerbandingan(int idKriteriaBaris, int idKriteriaKolom,
            double nilaiPerbandingan) {
        this.idKriteriaBaris = idKriteriaBaris;
        this.idKriteriaKolom = idKriteriaKolom;
        this.nilaiPerbandingan = nilaiPerbandingan;
    }

    public int getIdMatriks() {
        return idMatriks;
    }

    public void setIdMatriks(int idMatriks) {
        this.idMatriks = idMatriks;
    }

    public int getIdKriteriaBaris() {
        return idKriteriaBaris;
    }

    public void setIdKriteriaBaris(int idKriteriaBaris) {
        this.idKriteriaBaris = idKriteriaBaris;
    }

    public int getIdKriteriaKolom() {
        return idKriteriaKolom;
    }

    public void setIdKriteriaKolom(int idKriteriaKolom) {
        this.idKriteriaKolom = idKriteriaKolom;
    }

    public double getNilaiPerbandingan() {
        return nilaiPerbandingan;
    }

    public void setNilaiPerbandingan(double nilaiPerbandingan) {
        this.nilaiPerbandingan = nilaiPerbandingan;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getNamaKriteriaBaris() {
        return namaKriteriaBaris;
    }

    public void setNamaKriteriaBaris(String namaKriteriaBaris) {
        this.namaKriteriaBaris = namaKriteriaBaris;
    }

    public String getNamaKriteriaKolom() {
        return namaKriteriaKolom;
    }

    public void setNamaKriteriaKolom(String namaKriteriaKolom) {
        this.namaKriteriaKolom = namaKriteriaKolom;
    }

    @Override
    public String toString() {
        return "MatriksPerbandingan{"
                + "idMatriks=" + idMatriks
                + ", idKriteriaBaris=" + idKriteriaBaris
                + ", idKriteriaKolom=" + idKriteriaKolom
                + ", nilaiPerbandingan=" + nilaiPerbandingan
                + '}';
    }
}
