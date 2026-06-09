/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.KoneksiDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author ryumaaa
 */
public class UserDAO {
     private Connection conn;
 
    public UserDAO() {
        this.conn = KoneksiDB.getConnection();
    }
    
    // kode untuk mengecek username & password
    
     public User login(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM user WHERE username = ? AND password = MD5(?) AND status = 'aktif'";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = mapResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error login: " + e.getMessage());
        }
        return user;
    }
     
     public List<User> getAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user ORDER BY id_user ASC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getAll user: " + e.getMessage());
        }
        return list;
    }
}
