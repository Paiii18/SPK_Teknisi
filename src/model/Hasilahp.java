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
public class Hasilahp {
    
    private int idHasilAHP;
    private int idKriteria;
    private double bobotPrioritas;
    private double lambdaMaks;
    private double consistencyIndex;
    private double consistencyRatio;
    private String keteranganCR;
    
    private Date tanggalHitung;
    
    private String namaKriteria;
    private String kodeKriteria;
    
    public Hasilahp() {}
    
    public Hasilahp(int idHasilAHP, int idKriteria, double bobotPrioritas,
                    double lambdaMaks, double consistencyIndex,
                    double consistencyRatio, String keteranganCR,
                    Date tanggalHitung) {
        this.idHasilAHP       = idHasilAHP;
        this.idKriteria       = idKriteria;
        this.bobotPrioritas   = bobotPrioritas;
        this.lambdaMaks       = lambdaMaks;
        this.consistencyIndex = consistencyIndex;
        this.consistencyRatio = consistencyRatio;
        this.keteranganCR     = keteranganCR;
        this.tanggalHitung    = tanggalHitung;
    }
    
    public Hasilahp(int idKriteria, double bobotPrioritas,
                    double lambdaMaks, double consistencyIndex,
                    double consistencyRatio, String keteranganCR) {
        this.idKriteria       = idKriteria;
        this.bobotPrioritas   = bobotPrioritas;
        this.lambdaMaks       = lambdaMaks;
        this.consistencyIndex = consistencyIndex;
        this.consistencyRatio = consistencyRatio;
        this.keteranganCR     = keteranganCR;
    }
    
    public int getIdHasilAHP() { return idHasilAHP; }
    public void setIdHasilAHP(int idHasilAHP) { this.idHasilAHP = idHasilAHP; }
 
    public int getIdKriteria() { return idKriteria; }
    public void setIdKriteria(int idKriteria) { this.idKriteria = idKriteria; }
 
    public double getBobotPrioritas() { return bobotPrioritas; }
    public void setBobotPrioritas(double bobotPrioritas) { this.bobotPrioritas = bobotPrioritas; }
 
    public double getLambdaMaks() { return lambdaMaks; }
    public void setLambdaMaks(double lambdaMaks) { this.lambdaMaks = lambdaMaks; }
 
    public double getConsistencyIndex() { return consistencyIndex; }
    public void setConsistencyIndex(double consistencyIndex) { this.consistencyIndex = consistencyIndex; }
 
    public double getConsistencyRatio() { return consistencyRatio; }
    public void setConsistencyRatio(double consistencyRatio) { this.consistencyRatio = consistencyRatio; }
 
    public String getKeteranganCR() { return keteranganCR; }
    public void setKeteranganCR(String keteranganCR) { this.keteranganCR = keteranganCR; }
 
    public Date getTanggalHitung() { return tanggalHitung; }
    public void setTanggalHitung(Date tanggalHitung) { this.tanggalHitung = tanggalHitung; }
 
    public String getNamaKriteria() { return namaKriteria; }
    public void setNamaKriteria(String namaKriteria) { this.namaKriteria = namaKriteria; }
 
    public String getKodeKriteria() { return kodeKriteria; }
    public void setKodeKriteria(String kodeKriteria) { this.kodeKriteria = kodeKriteria; }
    
     @Override
    public String toString() {
        return "HasilAHP{" +
                "idHasilAHP="       + idHasilAHP +
                ", idKriteria="     + idKriteria +
                ", bobotPrioritas=" + bobotPrioritas +
                ", lambdaMaks="     + lambdaMaks +
                ", CI="             + consistencyIndex +
                ", CR="             + consistencyRatio +
                ", keteranganCR='"  + keteranganCR + '\'' +
                '}';
    }
}
