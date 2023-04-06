/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvn.service;
import com.nvn.conf.JdbcUtils;
import com.pojo.Account;
import com.pojo.UserTest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Joe
 */
public class AccountService {
   public List<Account> getAccounts() throws SQLException{
        List<Account> results = new ArrayList<>();
       try(Connection conn = JdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM account");
            
             while(rs.next()){
                 Account ac = new Account(rs.getInt("id"), rs.getString("accName"), rs.getString("password"));
                 results.add(ac);
              }
       }
      return results;
     
   }

//   public void addAccount(Account a) throws SQLException{
//       try(Connection conn = JdbcUtils.getConn()){
//           PreparedStatement stm1 = conn.prepareStatement("INSERT INTO account(id,accName,password) VALUES(?,?,?)");
//           stm1.setInt(1, a.getId());
//           stm1.setString(2, a.getAccName());
//           stm1.setString(3, a.getPassword());
//           stm1.executeUpdate();
//       }
//   }
    //Chinh sua code de thuc hien them id cua nhom test
   public boolean addAccount(Account a) throws SQLException{
       try(Connection conn = JdbcUtils.getConn()){
           conn.setAutoCommit(false);
           PreparedStatement stm1 = conn.prepareStatement("INSERT INTO account(id,accName,password) VALUES(?,?,?)");
           stm1.setInt(1, a.getId());
           stm1.setString(2, a.getAccName());
           stm1.setString(3, a.getPassword());
           int r = stm1.executeUpdate();
           try {
                conn.commit();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return false;
            }
            }

       }
// 
//   public void delAccount(int id) throws SQLException{
//        try(Connection conn = JdbcUtils.getConn()){
//          Statement stm1 = conn.createStatement();       
//          String sql = "DELETE FROM account WHERE id =" + id;
//          stm1.executeUpdate(sql);
//          
//       }
//        
//   }
   //Phuong thuc del account ben test
     public boolean delAccount(int id) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
          String sql = "DELETE FROM account WHERE id =?";
          PreparedStatement stm = conn.prepareCall(sql);
          stm.setInt(1, id);
          return stm.executeUpdate() > 0;
       }
        
   }
     //Phuong thuc update cua test
       public boolean updateAccount(String acc, String pw, int id) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
          String sql = "UPDATE account SET accName = ?, password = ?  WHERE id =?";
          PreparedStatement stm = conn.prepareCall(sql);
          stm.setString(1, acc);
          stm.setString(2, pw);
          stm.setInt(3, id);
         
          return stm.executeUpdate() > 0;
       }
        
   }
   
   //phần viết thêm của bên nhóm test
   public void addUserTest(UserTest u) throws SQLException{
       try(Connection conn = JdbcUtils.getConn()){
           PreparedStatement stm1 = conn.prepareStatement("INSERT INTO UserTest(username, password, phone, email, cofirmpass) VALUES(?,?,?,?,?)");
           stm1.setString(1, u.getUsername());
           stm1.setString(2, u.getPassword());
           stm1.setString(3, u.getPhone());
           stm1.setString(4, u.getEmail());
           stm1.setString(5, u.getCofirmpass());
           stm1.executeUpdate();
       }
   }
   // cau lenh tim kiem acc theo ten boi team test
   public List<Account> getAccounts (String kw) throws SQLException {
        List<Account> results = new ArrayList<>();

        try ( Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM account";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE accName like concat('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getInt("id"), rs.getString("accName"), rs.getString("password"));
                results.add(a);
            }
        }

        return results;
    }
   
}
