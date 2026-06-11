/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.HasilAHPDAO;
import dao.HasilPerankinganDAO;
import dao.KriteriaDAO;
import dao.MatriksDAO;
import dao.NilaiTeknisiDAO;
import dao.TeknisiDAO;
import model.Hasilahp;
import model.HasilPerankingan;
import model.Kriteria;
import model.Teknisi;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ryumaaa
 */
public class HasilPerangkingan extends javax.swing.JPanel {

    private static final double[] RI = {0.00, 0.00, 0.58, 0.90, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49};

    /**
     * Creates new form HasilPerangkingan
     */
    public HasilPerangkingan() {
        initComponents();
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);

        jButton1.addActionListener(e -> prosesPerbandinganKriteria());
    }

    private void prosesPerbandinganKriteria() {
        KriteriaDAO kriteriaDAO = new KriteriaDAO();
        MatriksDAO matriksDAO = new MatriksDAO();

        List<Kriteria> kriteriaList = kriteriaDAO.getAll();
        int n = kriteriaList.size();

        if (n < 2) {
            JOptionPane.showMessageDialog(this, "Minimal 2 kriteria untuk melakukan perbandingan");
            return;
        }

        List<Integer> idList = new ArrayList<>();
        for (Kriteria k : kriteriaList) {
            idList.add(k.getIdKriteria());
        }

        // Ambil matriks perbandingan
        double[][] matriks = matriksDAO.getMatriksArray(idList);

        // Hitung jumlah kolom
        double[] colSum = new double[n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                colSum[j] += matriks[i][j];
            }
        }

        // Normalisasi matriks
        double[][] normalized = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                normalized[i][j] = matriks[i][j] / colSum[j];
            }
        }

        // Hitung bobot prioritas (rata-rata baris dari matriks normalisasi)
        double[] bobot = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += normalized[i][j];
            }
            bobot[i] = sum / n;
        }

        // Hitung Lambda Max
        double[] weightedSum = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                weightedSum[i] += matriks[i][j] * bobot[j];
            }
        }

        double lambdaMax = 0;
        for (int i = 0; i < n; i++) {
            lambdaMax += weightedSum[i] / bobot[i];
        }
        lambdaMax /= n;

        // Hitung CI dan CR
        double ci = (lambdaMax - n) / (n - 1);
        double ri = (n <= 10) ? RI[n - 1] : 1.49;
        double cr = (ri > 0) ? ci / ri : 0;
        String keteranganCR = (cr <= 0.1) ? "Konsisten" : "Tidak Konsisten";

        // Tampilkan di jTable1 (format matriks + kolom bobot)
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kriteria");
        for (Kriteria k : kriteriaList) {
            model.addColumn(k.getNamaKriteria());
        }
        model.addColumn("Bobot Prioritas");

        for (int i = 0; i < n; i++) {
            Object[] row = new Object[n + 2];
            row[0] = kriteriaList.get(i).getNamaKriteria();
            for (int j = 0; j < n; j++) {
                row[j + 1] = String.format("%.4f", matriks[i][j]);
            }
            row[n + 1] = String.format("%.4f", bobot[i]);
            model.addRow(row);
        }

        jTable1.setModel(model);

        // Tampilkan Lambda Max, CI, CR
        jTextField1.setText(String.format("%.4f", lambdaMax));
        jTextField2.setText(String.format("%.4f", ci));
        jTextField3.setText(String.format("%.4f", cr) + " (" + keteranganCR + ")");

        // Simpan ke hasil_ahp
        HasilAHPDAO hasilDAO = new HasilAHPDAO();
        hasilDAO.deleteAll();

        List<Hasilahp> listHasil = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Hasilahp h = new Hasilahp(
                    idList.get(i), bobot[i], lambdaMax, ci, cr, keteranganCR
            );
            listHasil.add(h);
        }
        hasilDAO.insertBatch(listHasil);

        // Update bobot_akhir di tabel kriteria
        for (int i = 0; i < n; i++) {
            kriteriaDAO.updateBobotAkhir(idList.get(i), bobot[i]);
        }

        JOptionPane.showMessageDialog(this,
                "Proses Perbandingan Kriteria Selesai\nCR = "
                + String.format("%.4f", cr) + " (" + keteranganCR + ")");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Matriks Perbandingan Kriteria");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Hasil Akhir");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton1.setText("Proses Perbandingan Kriteria");

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton2.setText("Proses Hitung Hasil Akhir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton3.setText("Reset Hasil");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Lambda MAX");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("CI");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("CR");

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1073, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Yakin ingin mereset semua hasil?",
                "Konfirmasi Reset", JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            HasilAHPDAO hasilAHPDAO = new HasilAHPDAO();
            HasilPerankinganDAO perankinganDAO = new HasilPerankinganDAO();

            hasilAHPDAO.deleteAll();
            perankinganDAO.deleteAll();

            jTable1.setModel(new DefaultTableModel());
            jTable2.setModel(new DefaultTableModel());

            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");

            JOptionPane.showMessageDialog(this, "Semua hasil berhasil direset");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        HasilAHPDAO hasilAHPDAO = new HasilAHPDAO();
        List<Hasilahp> listAHP = hasilAHPDAO.getAll();

        if (listAHP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Proses Perbandingan Kriteria terlebih dahulu");
            return;
        }

        TeknisiDAO teknisiDAO = new TeknisiDAO();
        NilaiTeknisiDAO nilaiDAO = new NilaiTeknisiDAO();
        KriteriaDAO kriteriaDAO = new KriteriaDAO();

        List<Teknisi> teknisiList = teknisiDAO.getAll();
        List<Kriteria> kriteriaList = kriteriaDAO.getAll();

        if (teknisiList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data teknisi belum tersedia");
            return;
        }

        // Map bobot prioritas per kriteria
        java.util.Map<Integer, Double> bobotMap = new java.util.HashMap<>();
        for (Hasilahp h : listAHP) {
            bobotMap.put(h.getIdKriteria(), h.getBobotPrioritas());
        }

        // Hitung nilai akhir tiap teknisi
        List<HasilPerankingan> hasilList = new ArrayList<>();
        for (Teknisi t : teknisiList) {
            double nilaiAkhir = 0;
            for (Kriteria k : kriteriaList) {
                double nilai = nilaiDAO.getNilai(t.getIdTeknisi(), k.getIdKriteria());
                double bobot = bobotMap.getOrDefault(k.getIdKriteria(), 0.0);
                nilaiAkhir += nilai * bobot;
            }

            HasilPerankingan hp = new HasilPerankingan();
            hp.setIdTeknisi(t.getIdTeknisi());
            hp.setNamaTeknisi(t.getNamaTeknisi());
            hp.setNilaiAkhir(nilaiAkhir);
            hasilList.add(hp);
        }

        // Urutkan berdasarkan nilai akhir (terbesar ke terkecil)
        hasilList.sort((a, b) -> Double.compare(b.getNilaiAkhir(), a.getNilaiAkhir()));

        // Tentukan ranking dan keterangan
        for (int i = 0; i < hasilList.size(); i++) {
            hasilList.get(i).setRanking(i + 1);
            if (i == 0) {
                hasilList.get(i).setKeterangan("Teknisi Terbaik");
            } else {
                hasilList.get(i).setKeterangan("Ranking " + (i + 1));
            }
        }

        // Tampilkan di jTable2
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Ranking");
        model.addColumn("Nama Teknisi");
        model.addColumn("Nilai Akhir");
        model.addColumn("Keterangan");

        for (HasilPerankingan hp : hasilList) {
            model.addRow(new Object[]{
                hp.getRanking(),
                hp.getNamaTeknisi(),
                String.format("%.4f", hp.getNilaiAkhir()),
                hp.getKeterangan()
            });
        }

        jTable2.setModel(model);

        // Simpan ke database
        HasilPerankinganDAO perankinganDAO = new HasilPerankinganDAO();
        perankinganDAO.deleteAll();
        perankinganDAO.insertBatch(hasilList);

        JOptionPane.showMessageDialog(this, "Proses Hitung Hasil Akhir Selesai");
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
