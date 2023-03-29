/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pojo;

/**
 *
 * @author DELL
 */

// phần của nhóm test viết
public class UserTest {

    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String cofirmpass;
    
    public UserTest(){
        
    }
    
     public UserTest(int id,String username, String password, String phone, String email, String cofirmpass) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.cofirmpass = cofirmpass;
    }
     
      public UserTest(String username, String password, String phone, String email, String cofirmpass) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.cofirmpass = cofirmpass;
    }
     
       public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCofirmpass() {
        return cofirmpass;
    }

    public void setCofirmpass(String cofirmpass) {
        this.cofirmpass = cofirmpass;
    }

  
}
