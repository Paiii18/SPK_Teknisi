/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.KoneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.NilaiTeknisi;

/**
 *
 * @author ryumaaa
 */
public class NilaiTeknisiDAO {

    private Connection conn;

    public NilaiTeknisiDAO() {
        this.conn = KoneksiDB.getConnection();
    }

    //mendapatkan semua data
    public List<NilaiTeknisi> getAll() {
        List<NilaiTeknisi> list = new ArrayList<>();
        String sql = "SELECT n.*, t.nama_teknisi, t.kode_teknisi, "
                + "k.nama_kriteria, k.kode_kriteria "
                + "FROM nilai_teknisi n "
                + "JOIN teknisi t ON n.id_teknisi = t.id_teknisi "
                + "JOIN kriteria k ON n.id_kriteria = k.id_kriteria "
                + "ORDER BY t.kode_teknisi, k.kode_kriteria";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                NilaiTeknisi nv = mapResultSet(rs);
                nv.setNamaTeknisi(rs.getString("nama_teknisi"));
                nv.setKodeTeknisi(rs.getString("kode_teknisi"));
                nv.setNamaKriteria(rs.getString("nama_kriteria"));
                nv.setKodeKriteria(rs.getString("kode_kriteria"));
                list.add(nv);
            }
        } catch (SQLException e) {
            System.err.println("Error getAll nilaiTeknisi: " + e.getMessage());
        }
        return list;
    }

    //mendapatkan data berdasarkan id teknisi
    public List<NilaiTeknisi> getByTeknisi(int idTeknisi) {
        List<NilaiTeknisi> list = new ArrayList<>();
        String sql = "SELECT n.*, t.nama_teknisi, t.kode_teknisi, "
                + "k.nama_kriteria, k.kode_kriteria "
                + "FROM nilai_teknisi n "
                + "JOIN teknisi t ON n.id_teknisi = t.id_teknisi "
                + "JOIN kriteria k ON n.id_kriteria = k.id_kriteria "
                + "WHERE n.id_teknisi = ? ORDER BY k.kode_kriteria";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTeknisi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NilaiTeknisi nv = mapResultSet(rs);
                nv.setNamaTeknisi(rs.getString("nama_teknisi"));
                nv.setKodeTeknisi(rs.getString("kode_teknisi"));
                nv.setNamaKriteria(rs.getString("nama_kriteria"));
                nv.setKodeKriteria(rs.getString("kode_kriteria"));
                list.add(nv);
            }
        } catch (SQLException e) {
            System.err.println("Error getByTeknisi: " + e.getMessage());
        }
        return list;
    }

    //mendapatkan nilai by id
    public double getNilai(int idTeknisi, int idKriteria) {
        String sql = "SELECT nilai FROM nilai_teknisi WHERE id_teknisi = ? AND id_kriteria = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTeknisi);
            ps.setInt(2, idKriteria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("nilai");
            }
        } catch (SQLException e) {
            System.err.println("Error getNilai: " + e.getMessage());
        }
        return 0.0;
    }

    //kode untuk menambahkan data
    public boolean insert(NilaiTeknisi nv) {
        String sql = "INSERT INTO nilai_teknisi (id_teknisi, id_kriteria, nilai, keterangan) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, nv.getIdTeknisi());
            ps.setInt(2, nv.getIdKriteria());
            ps.setDouble(3, nv.getNilai());
            ps.setString(4, nv.getKeterangan());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error insert nilaiTeknisi: " + e.getMessage());
            return false;
        }
    }

    //kode untuk update data
    public boolean update(NilaiTeknisi nv) {
        String sql = "UPDATE nilai_teknisi SET nilai = ?, keterangan = ? "
                + "WHERE id_teknisi = ? AND id_kriteria = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, nv.getNilai());
            ps.setString(2, nv.getKeterangan());
            ps.setInt(3, nv.getIdTeknisi());
            ps.setInt(4, nv.getIdKriteria());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error update nilaiTeknisi: " + e.getMessage());
            return false;
        }
    }

    //kode untuk update atau menambahkan data
    public boolean upsert(int idTeknisi, int idKriteria, double nilai, String keterangan) {
        String sql = "INSERT INTO nilai_teknisi (id_teknisi, id_kriteria, nilai, keterangan) "
                + "VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE nilai = ?, keterangan = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTeknisi);
            ps.setInt(2, idKriteria);
            ps.setDouble(3, nilai);
            ps.setString(4, keterangan);
            ps.setDouble(5, nilai);
            ps.setString(6, keterangan);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error upsert nilaiTeknisi: " + e.getMessage());
            return false;
        }
    }

    // kode untuk menghapus 
    public boolean deleteByTeknisi(int idTeknisi) {
        String sql = "DELETE FROM nilai_teknisi WHERE id_teknisi = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTeknisi);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleteByTeknisi: " + e.getMessage());
            return false;
        }
    }

    public boolean isSudahDinilai(int idTeknisi, int jumlahKriteria) {
        String sql = "SELECT COUNT(*) FROM nilai_teknisi WHERE id_teknisi = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTeknisi);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) >= jumlahKriteria;
            }
        } catch (SQLException e) {
            System.err.println("Error isSudahDinilai: " + e.getMessage());
        }
        return false;
    }

    private NilaiTeknisi mapResultSet(ResultSet rs) throws SQLException {
        NilaiTeknisi nv = new NilaiTeknisi();
        nv.setIdNilai(rs.getInt("id_nilai"));
        nv.setIdTeknisi(rs.getInt("id_teknisi"));
        nv.setIdKriteria(rs.getInt("id_kriteria"));
        nv.setNilai(rs.getDouble("nilai"));
        nv.setKeterangan(rs.getString("keterangan"));
        nv.setCreatedAt(rs.getDate("created_at"));
        return nv;
    }
}
