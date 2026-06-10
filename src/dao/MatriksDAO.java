/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.KoneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.MatriksPerbandingan;

/**
 *
 * @author ryumaaa
 */
public class MatriksDAO {

    private Connection conn;

    public MatriksDAO() {
        this.conn = KoneksiDB.getConnection();
    }

    // mengambill semua data dengan join nama kriteria
    public List<MatriksPerbandingan> getAll() {
        List<MatriksPerbandingan> list = new ArrayList<>();
        String sql = "SELECT m.*, "
                + "kb.nama_kriteria AS nama_baris, kb.kode_kriteria AS kode_baris, "
                + "kk.nama_kriteria AS nama_kolom, kk.kode_kriteria AS kode_kolom "
                + "FROM matriks_perbandingan m "
                + "JOIN kriteria kb ON m.id_kriteria_baris = kb.id_kriteria "
                + "JOIN kriteria kk ON m.id_kriteria_kolom = kk.id_kriteria "
                + "ORDER BY m.id_kriteria_baris, m.id_kriteria_kolom";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                MatriksPerbandingan m = mapResultSet(rs);
                m.setNamaKriteriaBaris(rs.getString("nama_baris"));
                m.setNamaKriteriaKolom(rs.getString("nama_kolom"));
                list.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error getAll matriks: " + e.getMessage());
        }
        return list;
    }

    // mengambil nilai by baris
    public double getNilai(int idKriteriaBaris, int idKriteriaKolom) {
        String sql = "SELECT nilai_perbandingan FROM matriks_perbandingan "
                + "WHERE id_kriteria_baris = ? AND id_kriteria_kolom = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idKriteriaBaris);
            ps.setInt(2, idKriteriaKolom);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("nilai_perbandingan");
            }
        } catch (SQLException e) {
            System.err.println("Error getNilai matriks: " + e.getMessage());
        }
        return 1.0; // default diagonal = 1
    }

    //mendapatkan matriks sebagai array
    public double[][] getMatriksArray(List<Integer> idKriteriaList) {
        int n = idKriteriaList.size();
        double[][] matriks = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriks[i][j] = getNilai(idKriteriaList.get(i), idKriteriaList.get(j));
            }
        }
        return matriks;
    }

    // kode untuk menambahkan data
    public boolean insert(MatriksPerbandingan m) {
        String sql = "INSERT INTO matriks_perbandingan (id_kriteria_baris, id_kriteria_kolom, nilai_perbandingan) "
                + "VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, m.getIdKriteriaBaris());
            ps.setInt(2, m.getIdKriteriaKolom());
            ps.setDouble(3, m.getNilaiPerbandingan());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error insert matriks: " + e.getMessage());
            return false;
        }
    }

    // kode untuk update data
    public boolean update(int idKriteriaBaris, int idKriteriaKolom, double nilaiPerbandingan) {
        String sql = "UPDATE matriks_perbandingan SET nilai_perbandingan = ? "
                + "WHERE id_kriteria_baris = ? AND id_kriteria_kolom = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, nilaiPerbandingan);
            ps.setInt(2, idKriteriaBaris);
            ps.setInt(3, idKriteriaKolom);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error update matriks: " + e.getMessage());
            return false;
        }
    }

    //kode untuk menambahkan atau update
    public boolean upsert(int idKriteriaBaris, int idKriteriaKolom, double nilai) {
        String sql = "INSERT INTO matriks_perbandingan (id_kriteria_baris, id_kriteria_kolom, nilai_perbandingan) "
                + "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE nilai_perbandingan = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idKriteriaBaris);
            ps.setInt(2, idKriteriaKolom);
            ps.setDouble(3, nilai);
            ps.setDouble(4, nilai);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error upsert matriks: " + e.getMessage());
            return false;
        }
    }

    //kode untuk menghapus semua
    public boolean deleteAll() {
        String sql = "DELETE FROM matriks_perbandingan";
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleteAll matriks: " + e.getMessage());
            return false;
        }
    }

    private MatriksPerbandingan mapResultSet(ResultSet rs) throws SQLException {
        MatriksPerbandingan m = new MatriksPerbandingan();
        m.setIdMatriks(rs.getInt("id_matriks"));
        m.setIdKriteriaBaris(rs.getInt("id_kriteria_baris"));
        m.setIdKriteriaKolom(rs.getInt("id_kriteria_kolom"));
        m.setNilaiPerbandingan(rs.getDouble("nilai_perbandingan"));
        m.setCreatedAt(rs.getDate("created_at"));
        return m;
    }
}
