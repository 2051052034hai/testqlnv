/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvn.service;

import com.nvn.conf.JdbcUtils;
import com.pojo.Account;
import com.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quynh
 */// code userService cua team test
public class UserService {
    public List<User> getUser() throws SQLException{
        List<User> results = new ArrayList<>();
       try(Connection conn = JdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM user");
            
             while(rs.next()){
                 User us = new User(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), rs.getInt("age"),rs.getInt("phone"),rs.getInt("id_account"));
                 results.add(us);
              }
       }
      return results;
    }     
      public boolean addUser(User u) throws SQLException{
       try(Connection conn = JdbcUtils.getConn()){
           conn.setAutoCommit(false);
           PreparedStatement stm1 = conn.prepareStatement("INSERT INTO user(id,lastname,firstname,age,phone,id_account) VALUES(?,?,?,?,?,?)");
           stm1.setInt(1, u.getId());
           stm1.setString(2, u.getLastname());
           stm1.setString(3, u.getFirstname());
           stm1.setInt(4, u.getAge());
           stm1.setInt(5, u.getPhone());
           stm1.setInt(6, u.getId_account());
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
public boolean delUser(int id) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
          String sql = "DELETE FROM user WHERE id =?";
          PreparedStatement stm = conn.prepareCall(sql);
          stm.setInt(1, id);
          return stm.executeUpdate() > 0;
       }
        
   }   
public List<User> getUser (String kw) throws SQLException {
        List<User> results = new ArrayList<>();

        try ( Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM user";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE firstname like concat('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                 User us = new User(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), rs.getInt("age"),rs.getInt("phone"),rs.getInt("id_account"));
                 results.add(us);
            }
        }

        return results;
    }

     //Phuong thuc update cua test
       public boolean updateUser(String lastname, String firstname, int id, int age, int phone, int id_account) throws SQLException{
        try(Connection conn = JdbcUtils.getConn()){
          String sql = "UPDATE user SET lastname = ?, firstname = ?, age = ?, phone=?,id_account=?  WHERE id =?";
          PreparedStatement stm = conn.prepareCall(sql);
          stm.setString(1, lastname);
          stm.setString(2, firstname);
          stm.setInt(3, age);
          stm.setInt(4, phone);
          stm.setInt(5, id_account);
          stm.setInt(6, id);
         
          return stm.executeUpdate() > 0;
       }
        
   }
}

