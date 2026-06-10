     /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.KoneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Teknisi;

/**
 *
 * @author ryumaaa
 */
public class TeknisiDAO {

    private Connection conn;

    public TeknisiDAO() {
        this.conn = KoneksiDB.getConnection();
    }

    // kode untuk mengambil semua data
    public List<Teknisi> getAll() {
        List<Teknisi> list = new ArrayList<>();
        String sql = "SELECT * FROM teknisi ORDER BY kode_teknisi ASC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getAll teknisi: " + e.getMessage());
        }
        return list;
    }

    // kode untuk mengambil semua data aktif
    public List<Teknisi> getAllAktif() {
        List<Teknisi> list = new ArrayList<>();
        String sql = "SELECT * FROM teknisi WHERE status = 'aktif' ORDER BY kode_teknisi ASC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getAllAktif teknisi: " + e.getMessage());
        }
        return list;
    }

    // kode untuk mengambil data by id
    public Teknisi getById(int idTeknisi) {
        Teknisi teknisi = null;
        String sql = "SELECT * FROM teknisi WHERE id_teknisi = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTeknisi);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                teknisi = mapResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error getById teknisi: " + e.getMessage());
        }
        return teknisi;
    }

    // kode pencarian by nama
    public List<Teknisi> search(String keyword) {
        List<Teknisi> list = new ArrayList<>();
        String sql = "SELECT * FROM teknisi WHERE nama_teknisi LIKE ? OR kode_teknisi LIKE ? ORDER BY kode_teknisi ASC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error search teknisi: " + e.getMessage());
        }
        return list;
    }

    // kode untuk menambahkan data
    public boolean insert(Teknisi t) {
        String sql = "INSERT INTO teknisi (kode_teknisi, nama_teknisi, jenis_kelamin, "
                + "tempat_lahir, tanggal_lahir, alamat, no_telepon, email, "
                + "divisi, jabatan, tanggal_masuk, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getKodeTeknisi());
            ps.setString(2, t.getNamaTeknisi());
            ps.setString(3, t.getJenisKelamin());
            ps.setString(4, t.getTempatLahir());
            ps.setDate(5, t.getTanggalLahir() != null ? new java.sql.Date(t.getTanggalLahir().getTime()) : null);
            ps.setString(6, t.getAlamat());
            ps.setString(7, t.getNoTelepon());
            ps.setString(8, t.getEmail());
            ps.setString(9, t.getDivisi());
            ps.setString(10, t.getJabatan());
            ps.setDate(11, t.getTanggalMasuk() != null ? new java.sql.Date(t.getTanggalMasuk().getTime()) : null);
            ps.setString(12, t.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error insert teknisi: " + e.getMessage());
            return false;
        }
    }

    // kode untuk update data
    public boolean update(Teknisi t) {
        String sql = "UPDATE teknisi SET kode_teknisi = ?, nama_teknisi = ?, jenis_kelamin = ?, "
                + "tempat_lahir = ?, tanggal_lahir = ?, alamat = ?, no_telepon = ?, email = ?, "
                + "divisi = ?, jabatan = ?, tanggal_masuk = ?, status = ? "
                + "WHERE id_teknisi = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getKodeTeknisi());
            ps.setString(2, t.getNamaTeknisi());
            ps.setString(3, t.getJenisKelamin());
            ps.setString(4, t.getTempatLahir());
            ps.setDate(5, t.getTanggalLahir() != null ? new java.sql.Date(t.getTanggalLahir().getTime()) : null);
            ps.setString(6, t.getAlamat());
            ps.setString(7, t.getNoTelepon());
            ps.setString(8, t.getEmail());
            ps.setString(9, t.getDivisi());
            ps.setString(10, t.getJabatan());
            ps.setDate(11, t.getTanggalMasuk() != null ? new java.sql.Date(t.getTanggalMasuk().getTime()) : null);
            ps.setString(12, t.getStatus());
            ps.setInt(13, t.getIdTeknisi());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error update teknisi: " + e.getMessage());
            return false;
        }
    }

    // kode untuk delete data
    public boolean delete(int idTeknisi) {
        String sql = "DELETE FROM teknisi WHERE id_teknisi = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTeknisi);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error delete teknisi: " + e.getMessage());
            return false;
        }
    }

    //kode untuk generate teknisi
    public String generateKode() {
        String sql = "SELECT kode_teknisi FROM teknisi ORDER BY id_teknisi DESC LIMIT 1";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                String lastKode = rs.getString("kode_teknisi");
                int number = Integer.parseInt(lastKode.replace("TKN-", "")) + 1;
                return String.format("TKN-%03d", number);
            }
        } catch (SQLException e) {
            System.err.println("Error generateKode: " + e.getMessage());
        }
        return "TKN-001";
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM teknisi WHERE status = 'aktif'";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error count teknisi: " + e.getMessage());
        }
        return 0;
    }

    private Teknisi mapResultSet(ResultSet rs) throws SQLException {
        Teknisi t = new Teknisi();
        t.setIdTeknisi(rs.getInt("id_teknisi"));
        t.setKodeTeknisi(rs.getString("kode_teknisi"));
        t.setNamaTeknisi(rs.getString("nama_teknisi"));
        t.setJenisKelamin(rs.getString("jenis_kelamin"));
        t.setTempatLahir(rs.getString("tempat_lahir"));
        t.setTanggalLahir(rs.getDate("tanggal_lahir"));
        t.setAlamat(rs.getString("alamat"));
        t.setNoTelepon(rs.getString("no_telepon"));
        t.setEmail(rs.getString("email"));
        t.setDivisi(rs.getString("divisi"));
        t.setJabatan(rs.getString("jabatan"));
        t.setTanggalMasuk(rs.getDate("tanggal_masuk"));
        t.setStatus(rs.getString("status"));
        t.setCreatedAt(rs.getDate("created_at"));
        return t;
    }
}
