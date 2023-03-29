/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvn.login;

import com.dao.userTestDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.pojo.UserTest;

public class testCaseRegister {
    
    private userTestDao user = new userTestDao();
    
    
    // Kiểm tra xem trường "Họ tên" cua user có bị để trống hay không:
    @Test
    void testUsernameEmpty() {
        
        UserTest u1 = new UserTest();
        u1 = user.getUserById(1);
        boolean result = validateUsername(u1.getUsername());
        Assertions.assertTrue(result);
    }
    
    // Kiểm tra xem trường "mật khẩu " cua user có bị để trống hay không:
    @Test
    void testPaswordEmpty() {
        
        UserTest u1 = new UserTest();
        u1 = user.getUserById(1);
        boolean result = validateUsername(u1.getPassword());
        Assertions.assertTrue(result);
    }

    // Kiểm tra tính hợp lệ của email:
    @Test
    void testEmailvalid(){
        UserTest u1 = new UserTest();
        u1 = user.getUserById(1);
        boolean result = validateEmail(u1.getEmail());
        Assertions.assertTrue(result);
    }
    
     // Kiểm tra tính hợp lệ của password:
    @Test 
    void testPasswordvalid(){
        UserTest u1 = new UserTest();
        u1 = user.getUserById(3);
        boolean result = validPassword6char(u1.getPassword());
        Assertions.assertTrue(result);
      
    }
    // Kiểm tra pass equal cofirm password
    @Test
    void testPassEqualCofirm(){
        UserTest u1 = new UserTest();
        u1 = user.getUserById(1);
        
        boolean result = validPassEqualCofirm(u1.getPassword(), u1.getCofirmpass());
        Assertions.assertTrue(result);
         
        
    }
    
    @Test
    void testphone10char(){
        
        UserTest u1 = new UserTest();
        u1 = user.getUserById(3);
        
        boolean result = validPhone10char(u1.getPhone());
        Assertions.assertTrue(result);
        
    }
    
    public boolean validateUsername(String username) {
        return !username.trim().isEmpty();
    }
    
    public boolean validateEmail(String email){
        
        return !email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
    }
    
    public boolean validPassword6char(String password){
        return !(password.length() < 6);
    }
    
    public boolean validPassEqualCofirm(String pass, String cofirm){
        
        return pass.equals(cofirm);
        
    }
     
    public boolean validPhone10char(String phone){
        
        return phone.length() == 10;
        
    }
}
