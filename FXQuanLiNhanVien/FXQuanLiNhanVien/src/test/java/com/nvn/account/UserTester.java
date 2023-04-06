/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvn.account;

import com.nvn.conf.JdbcUtils;
import com.nvn.service.UserService;
import com.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author quynh
 */
public class UserTester {
    private static Connection conn;
    private static UserService us;
    @BeforeAll
    public static void beforeAll() throws SQLException {
        conn = JdbcUtils.getConn();
        us = new UserService();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
    
    @Test
    public void testUniqueID_Account() {

        
        try {
            List<User> user = us.getUser();

            List<Integer> ids= user.stream().flatMap(c -> Stream.of(c.getId_account())).collect(Collectors.toList());
            Set<Integer> testIDs = new HashSet<>(ids);
            Assertions.assertEquals(ids.size(), testIDs.size());
        } catch (SQLException ex) {
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testAddSuccessful() throws SQLException {
       
        User b = new User(4,"lam","thanh", 21, 1231242, 4);
        try {
        boolean actual = us.addUser(b);
        Assertions.assertTrue(actual);
        String sql = "SELECT * FROM user WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, b.getId());
            
            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals("lam", rs.getString("lastname"));
            Assertions.assertEquals("thanh", rs.getString("firstname"));
            Assertions.assertEquals("21", rs.getString("age"));
            Assertions.assertEquals("123456", rs.getString("phone"));
            Assertions.assertEquals("4", rs.getString("id_account"));
            } catch (SQLException ex) {
            Logger.getLogger(UserTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Test
    public void testSearch() {
        try {
            List<User> uc = us.getUser("thanh");
            
            Assertions.assertEquals(1, uc.size());
            for (User a: uc)
                Assertions.assertTrue(a.getFirstname().contains("khanh"));
        } catch (SQLException ex) {
            Logger.getLogger(UserTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testDelete() {
        int id = 3;
        boolean actual;
        try {
            actual = us.delUser(id);
            Assertions.assertTrue(actual);
            
            String sql = "SELECT * FROM user WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Assertions.assertFalse(rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(UserTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUpdate() {
        int id = 1;
        boolean actual;
        try {
            actual = us.updateUser("lam","thanh", 1, 21, 123,1);
            Assertions.assertTrue(actual);
            
            String sql = "SELECT * FROM user WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            
            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals("lam", rs.getString("lastname"));
            Assertions.assertEquals("thanh", rs.getString("firstname"));
            Assertions.assertEquals("21", rs.getString("age"));
            Assertions.assertEquals("123456", rs.getString("phone"));
            Assertions.assertEquals("4", rs.getString("id_account"));
        } catch (SQLException ex) {
            Logger.getLogger(UserTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
