/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.KoneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HasilPerankingan;

/**
 *
 * @author ryumaaa
 */
public class HasilPerankinganDAO {

    private Connection conn;

    public HasilPerankinganDAO() {
        this.conn = KoneksiDB.getConnection();
    }

    public List<HasilPerankingan> getAll() {
        List<HasilPerankingan> list = new ArrayList<>();
        String sql = "SELECT hp.*, t.nama_teknisi, t.kode_teknisi, t.divisi, t.jabatan "
                + "FROM hasil_perankingan hp "
                + "JOIN teknisi t ON hp.id_teknisi = t.id_teknisi "
                + "ORDER BY hp.ranking ASC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                HasilPerankingan hp = mapResultSet(rs);
                hp.setNamaTeknisi(rs.getString("nama_teknisi"));
                hp.setKodeTeknisi(rs.getString("kode_teknisi"));
                hp.setDivisi(rs.getString("divisi"));
                hp.setJabatan(rs.getString("jabatan"));
                list.add(hp);
            }
        } catch (SQLException e) {
            System.err.println("Error getAll hasilPerankingan: " + e.getMessage());
        }
        return list;
    }

    public List<HasilPerankingan> getLatest() {
        List<HasilPerankingan> list = new ArrayList<>();
        String sql = "SELECT hp.*, t.nama_teknisi, t.kode_teknisi, t.divisi, t.jabatan "
                + "FROM hasil_perankingan hp "
                + "JOIN teknisi t ON hp.id_teknisi = t.id_teknisi "
                + "WHERE hp.tanggal_ranking = (SELECT MAX(tanggal_ranking) FROM hasil_perankingan) "
                + "ORDER BY hp.ranking ASC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                HasilPerankingan hp = mapResultSet(rs);
                hp.setNamaTeknisi(rs.getString("nama_teknisi"));
                hp.setKodeTeknisi(rs.getString("kode_teknisi"));
                hp.setDivisi(rs.getString("divisi"));
                hp.setJabatan(rs.getString("jabatan"));
                list.add(hp);
            }
        } catch (SQLException e) {
            System.err.println("Error getLatest hasilPerankingan: " + e.getMessage());
        }
        return list;
    }

    public HasilPerankingan getTeknisiTerbaik() {
        HasilPerankingan hp = null;
        String sql = "SELECT hp.*, t.nama_teknisi, t.kode_teknisi, t.divisi, t.jabatan "
                + "FROM hasil_perankingan hp "
                + "JOIN teknisi t ON hp.id_teknisi = t.id_teknisi "
                + "ORDER BY hp.nilai_akhir DESC LIMIT 1";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                hp = mapResultSet(rs);
                hp.setNamaTeknisi(rs.getString("nama_teknisi"));
                hp.setKodeTeknisi(rs.getString("kode_teknisi"));
                hp.setDivisi(rs.getString("divisi"));
                hp.setJabatan(rs.getString("jabatan"));
            }
        } catch (SQLException e) {
            System.err.println("Error getTeknisiTerbaik: " + e.getMessage());
        }
        return hp;
    }

    public boolean insertBatch(List<HasilPerankingan> listHasil) {
        String sql = "INSERT INTO hasil_perankingan (id_teknisi, nilai_akhir, ranking, keterangan) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (HasilPerankingan hp : listHasil) {
                ps.setInt(1, hp.getIdTeknisi());
                ps.setDouble(2, hp.getNilaiAkhir());
                ps.setInt(3, hp.getRanking());
                ps.setString(4, hp.getKeterangan());
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            System.err.println("Error insertBatch perankingan: " + e.getMessage());
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            return false;
        }
    }

    public boolean deleteAll() {
        String sql = "DELETE FROM hasil_perankingan";
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleteAll perankingan: " + e.getMessage());
            return false;
        }
    }

    private HasilPerankingan mapResultSet(ResultSet rs) throws SQLException {
        HasilPerankingan hp = new HasilPerankingan();
        hp.setIdPerankingan(rs.getInt("id_perankingan"));
        hp.setIdTeknisi(rs.getInt("id_teknisi"));
        hp.setNilaiAkhir(rs.getDouble("nilai_akhir"));
        hp.setRanking(rs.getInt("ranking"));
        hp.setKeterangan(rs.getString("keterangan"));
        hp.setTanggalRanking(rs.getDate("tanggal_ranking"));
        return hp;
    }
}
