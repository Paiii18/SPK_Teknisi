/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.KoneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Hasilahp;

/**
 *
 * @author ryumaaa
 */
public class HasilAHPDAO {

    private Connection conn;

    public HasilAHPDAO() {
        this.conn = KoneksiDB.getConnection();
    }

    //mendapatkan semua data join kriteria
    public List<Hasilahp> getAll() {
        List<Hasilahp> list = new ArrayList<>();
        String sql = "SELECT h.*, k.nama_kriteria, k.kode_kriteria "
                + "FROM hasil_ahp h "
                + "JOIN kriteria k ON h.id_kriteria = k.id_kriteria "
                + "ORDER BY h.bobot_prioritas DESC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Hasilahp h = mapResultSet(rs);
                h.setNamaKriteria(rs.getString("nama_kriteria"));
                h.setKodeKriteria(rs.getString("kode_kriteria"));
                list.add(h);
            }
        } catch (SQLException e) {
            System.err.println("Error getAll hasilAHP: " + e.getMessage());
        }
        return list;
    }

    public List<Hasilahp> getLatest() {
        List<Hasilahp> list = new ArrayList<>();
        String sql = "SELECT h.*, k.nama_kriteria, k.kode_kriteria "
                + "FROM hasil_ahp h "
                + "JOIN kriteria k ON h.id_kriteria = k.id_kriteria "
                + "WHERE h.tanggal_hitung = (SELECT MAX(tanggal_hitung) FROM hasil_ahp) "
                + "ORDER BY h.bobot_prioritas DESC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Hasilahp h = mapResultSet(rs);
                h.setNamaKriteria(rs.getString("nama_kriteria"));
                h.setKodeKriteria(rs.getString("kode_kriteria"));
                list.add(h);
            }
        } catch (SQLException e) {
            System.err.println("Error getLatest hasilAHP: " + e.getMessage());
        }
        return list;
    }

    //kode untuk menambahkan data
    public boolean insert(Hasilahp h) {
        String sql = "INSERT INTO hasil_ahp (id_kriteria, bobot_prioritas, lambda_maks, "
                + "consistency_index, consistency_ratio, keterangan_cr) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, h.getIdKriteria());
            ps.setDouble(2, h.getBobotPrioritas());
            ps.setDouble(3, h.getLambdaMaks());
            ps.setDouble(4, h.getConsistencyIndex());
            ps.setDouble(5, h.getConsistencyRatio());
            ps.setString(6, h.getKeteranganCR());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error insert hasilAHP: " + e.getMessage());
            return false;
        }
    }

    //kode untuk simpan semua hasil sekaligus
    public boolean insertBatch(List<Hasilahp> listHasil) {
        String sql = "INSERT INTO hasil_ahp (id_kriteria, bobot_prioritas, lambda_maks, "
                + "consistency_index, consistency_ratio, keterangan_cr) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (Hasilahp h : listHasil) {
                ps.setInt(1, h.getIdKriteria());
                ps.setDouble(2, h.getBobotPrioritas());
                ps.setDouble(3, h.getLambdaMaks());
                ps.setDouble(4, h.getConsistencyIndex());
                ps.setDouble(5, h.getConsistencyRatio());
                ps.setString(6, h.getKeteranganCR());
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            System.err.println("Error insertBatch hasilAHP: " + e.getMessage());
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            return false;
        }
    }

    //delete semua data
    public boolean deleteAll() {
        String sql = "DELETE FROM hasil_ahp";
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleteAll hasilAHP: " + e.getMessage());
            return false;
        }
    }

    private Hasilahp mapResultSet(ResultSet rs) throws SQLException {
        Hasilahp h = new Hasilahp();
        h.setIdHasilAHP(rs.getInt("id_hasil_ahp"));
        h.setIdKriteria(rs.getInt("id_kriteria"));
        h.setBobotPrioritas(rs.getDouble("bobot_prioritas"));
        h.setLambdaMaks(rs.getDouble("lambda_maks"));
        h.setConsistencyIndex(rs.getDouble("consistency_index"));
        h.setConsistencyRatio(rs.getDouble("consistency_ratio"));
        h.setKeteranganCR(rs.getString("keterangan_cr"));
        h.setTanggalHitung(rs.getDate("tanggal_hitung"));
        return h;
    }
}
