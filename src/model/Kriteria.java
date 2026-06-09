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
public class Kriteria {

    private int idKriteria;
    private String kodeKriteria;
    private String namaKriteria;
    private String deskripsi;
    private double bobotAkhir;
    private String status;
    private Date createdAt;

    public Kriteria() {
    }

    public Kriteria(int idKriteria, String kodeKriteria, String namaKriteria,
            String deskripsi, double bobotAkhir,
            String status, Date createdAt) {
        this.idKriteria = idKriteria;
        this.kodeKriteria = kodeKriteria;
        this.namaKriteria = namaKriteria;
        this.deskripsi = deskripsi;
        this.bobotAkhir = bobotAkhir;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Kriteria(String kodeKriteria, String namaKriteria,
            String deskripsi, String status) {
        this.kodeKriteria = kodeKriteria;
        this.namaKriteria = namaKriteria;
        this.deskripsi = deskripsi;
        this.status = status;
    }

    public int getIdKriteria() {
        return idKriteria;
    }

    public void setIdKriteria(int idKriteria) {
        this.idKriteria = idKriteria;
    }

    public String getKodeKriteria() {
        return kodeKriteria;
    }

    public void setKodeKriteria(String kodeKriteria) {
        this.kodeKriteria = kodeKriteria;
    }

    public String getNamaKriteria() {
        return namaKriteria;
    }

    public void setNamaKriteria(String namaKriteria) {
        this.namaKriteria = namaKriteria;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public double getBobotAkhir() {
        return bobotAkhir;
    }

    public void setBobotAkhir(double bobotAkhir) {
        this.bobotAkhir = bobotAkhir;
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
        return "Kriteria{"
                + "idKriteria=" + idKriteria
                + ", kodeKriteria='" + kodeKriteria + '\''
                + ", namaKriteria='" + namaKriteria + '\''
                + ", bobotAkhir=" + bobotAkhir
                + ", status='" + status + '\''
                + '}';
    }
}
