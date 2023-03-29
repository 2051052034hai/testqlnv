/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvn.service;

/**
 *
 * @author DELL
 */
public class loginservice {
    
    public boolean validateLogin(String username, String password) {
    if (username == null || username.trim().isEmpty()) {
        return false;
    }
    if (password == null || password.trim().isEmpty()) {
        return false;
    }
    // check if username and password are valid
    // and return true if they are, otherwise false
    return true;
}
    
    public boolean isValidUsername(String username)
    {
        return username.length() <= 50;
    }
    
    
}
