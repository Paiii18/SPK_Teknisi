/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.KoneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Kriteria;

/**
 *
 * @author ryumaaa
 */
public class KriteriaDAO {

    private Connection conn;

    public KriteriaDAO() {
        this.conn = KoneksiDB.getConnection();
    }

    //mengambil semua data
    public List<Kriteria> getAll() {
        List<Kriteria> list = new ArrayList<>();
        String sql = "SELECT * FROM kriteria ORDER BY kode_kriteria ASC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getAll kriteria: " + e.getMessage());
        }
        return list;
    }

    // mengambil data aktif
    public List<Kriteria> getAllAktif() {
        List<Kriteria> list = new ArrayList<>();
        String sql = "SELECT * FROM kriteria WHERE status = 'aktif' ORDER BY kode_kriteria ASC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getAllAktif kriteria: " + e.getMessage());
        }
        return list;
    }

    //mengambil data by id
    public Kriteria getById(int idKriteria) {
        Kriteria k = null;
        String sql = "SELECT * FROM kriteria WHERE id_kriteria = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idKriteria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                k = mapResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error getById kriteria: " + e.getMessage());
        }
        return k;
    }

    //kode untuk menambahkan data
    public boolean insert(Kriteria k) {
        String sql = "INSERT INTO kriteria (kode_kriteria, nama_kriteria, deskripsi, status) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getKodeKriteria());
            ps.setString(2, k.getNamaKriteria());
            ps.setString(3, k.getDeskripsi());
            ps.setString(4, k.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error insert kriteria: " + e.getMessage());
            return false;
        }
    }

    //kode untuk update data
    public boolean update(Kriteria k) {
        String sql = "UPDATE kriteria SET kode_kriteria = ?, nama_kriteria = ?, "
                + "deskripsi = ?, status = ? WHERE id_kriteria = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getKodeKriteria());
            ps.setString(2, k.getNamaKriteria());
            ps.setString(3, k.getDeskripsi());
            ps.setString(4, k.getStatus());
            ps.setInt(5, k.getIdKriteria());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error update kriteria: " + e.getMessage());
            return false;
        }
    }

    //UPDATE BOBOT AKHIR
    public boolean updateBobotAkhir(int idKriteria, double bobotAkhir) {
        String sql = "UPDATE kriteria SET bobot_akhir = ? WHERE id_kriteria = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, bobotAkhir);
            ps.setInt(2, idKriteria);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updateBobotAkhir: " + e.getMessage());
            return false;
        }
    }
    // kode untuk delete data

    public boolean delete(int idKriteria) {
        String sql = "DELETE FROM kriteria WHERE id_kriteria = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idKriteria);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error delete kriteria: " + e.getMessage());
            return false;
        }
    }

    //kode untuk generate kriteria
    public String generateKode() {
        String sql = "SELECT kode_kriteria FROM kriteria ORDER BY id_kriteria DESC LIMIT 1";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String lastKode = rs.getString("kode_kriteria");
                int number = Integer.parseInt(lastKode.replace("K", "")) + 1;
                return String.format("K%02d", number);
            }
        } catch (SQLException e) {
            System.err.println("Error generateKode kriteria: " + e.getMessage());
        }
        return "K01";
    }

    // menghitung total kriteria aktif
    public int count() {
        String sql = "SELECT COUNT(*) FROM kriteria WHERE status = 'aktif'";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error count kriteria: " + e.getMessage());
        }
        return 0;
    }

    private Kriteria mapResultSet(ResultSet rs) throws SQLException {
        Kriteria k = new Kriteria();
        k.setIdKriteria(rs.getInt("id_kriteria"));
        k.setKodeKriteria(rs.getString("kode_kriteria"));
        k.setNamaKriteria(rs.getString("nama_kriteria"));
        k.setDeskripsi(rs.getString("deskripsi"));
        k.setBobotAkhir(rs.getDouble("bobot_akhir"));
        k.setStatus(rs.getString("status"));
        k.setCreatedAt(rs.getDate("created_at"));
        return k;
    }
}
