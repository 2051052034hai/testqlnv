/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvn.account;

import com.nvn.conf.JdbcUtils;
import com.nvn.service.AccountService;
import com.pojo.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * @author Joe
 */
public class AccountTester {

    private static Connection conn;
    private static AccountService as;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        conn = JdbcUtils.getConn();
        as = new AccountService();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    /*@Test
    public void testUnique() throws SQLException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM account");
        
         while(rs.next()){
             String accoutName = rs.getString("accName");
             System.out.println(accoutName);
         }
       
    }*/
    @Test
    public void testUniqueName() {

        
        try {
            List<Account> accs = as.getAccounts();

            List<String> names = accs.stream().flatMap(c -> Stream.of(c.getAccName())).collect(Collectors.toList());
            Set<String> testNames = new HashSet<>(names);
            Assertions.assertEquals(names.size(), testNames.size());
        } catch (SQLException ex) {
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testAddSuccessful() throws SQLException {
       
        Account b = new Account(1001,"1001","1001");
        try {
        boolean actual = as.addAccount(b);
        Assertions.assertTrue(actual);
        String sql = "SELECT * FROM account WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, b.getId());
            
            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals("1001", rs.getString("accName"));
            Assertions.assertEquals("1001", rs.getString("password"));
            } catch (SQLException ex) {
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Test
    public void testSearch() {
        try {
            List<Account> ac = as.getAccounts("khanh");
            
            Assertions.assertEquals(1, ac.size());
            for (Account a: ac)
                Assertions.assertTrue(a.getAccName().contains("khanh"));
        } catch (SQLException ex) {
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testDelete() {
        int id = 18;
        boolean actual;
        try {
            actual = as.delAccount(id);
            Assertions.assertTrue(actual);
            
            String sql = "SELECT * FROM account WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Assertions.assertFalse(rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testUpdate() {
        int id = 1;
        boolean actual;
        try {
            actual = as.updateAccount("1001","1001",1);
            Assertions.assertTrue(actual);
            
            String sql = "SELECT * FROM account WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Assertions.assertFalse(rs.next());
            Assertions.assertEquals("lam", rs.getString("accName"));
            Assertions.assertEquals("thanh", rs.getString("password"));
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
