/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.pojo.UserTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class userTestDao {
    
    public UserTest getUserById(int id) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    UserTest user = null;

    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/qlnv","root","12345678");
        stmt = conn.prepareStatement("SELECT * FROM usertest WHERE id = ?");
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {
            user = new UserTest(rs.getInt("id"), rs.getString("username"), rs.getString("password"), 
            rs.getString("phone"), rs.getString("email"), rs.getString("cofirmpass"));
        }
    } catch (SQLException e) {
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
        }
    }

    return user;
}
}
