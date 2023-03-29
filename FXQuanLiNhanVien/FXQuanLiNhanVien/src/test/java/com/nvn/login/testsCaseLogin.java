/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvn.login;

import com.nvn.service.loginservice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class testsCaseLogin {
    //Kiểm tra đăng nhập với tên người dùng và mật khẩu hợp lệ.
    @Test
    void testloginwithvalidUsernammandPassword(){
        loginservice login = new loginservice();
        boolean result = login.validateLogin("hai", "123456");
        Assertions.assertTrue(result);
        }
    //Kiểm tra đăng nhập với tên người dùng null.
    @Test
    void testloginwithNullUsername(){
         loginservice login = new loginservice();
         boolean result = login.validateLogin(null, "123456");
         Assertions.assertFalse(result);
    }
    //Kiểm tra đăng nhập với tên người dùng trống.
    @Test
    void testLoginwithEmptyUsername(){
        loginservice login = new loginservice();
        boolean result = login.validateLogin("", "password");
        Assertions.assertFalse(result);
    }
    //Kiểm tra đăng nhập với mật khẩu null.
    @Test
    void testLoginwithNullPassword(){
        loginservice login = new loginservice();
        boolean result = login.validateLogin("hai", null);
        Assertions.assertFalse(result);
    }
    //Kiểm tra đăng nhập với mật khẩu trống.
    @Test
    void testLoginwithEmptyPassword(){
        loginservice login = new loginservice();
        boolean result = login.validateLogin("hai", "");
        Assertions.assertFalse(result);
    }
    
    @Test
    void testLoginwithUsernameout50char(){
         loginservice login = new loginservice();
         boolean result = login.isValidUsername("hai");
         Assertions.assertTrue(result);
    }
}
