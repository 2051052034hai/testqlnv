/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvn.service;
import java.awt.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 *
 * @author DELL
 */
public class registersevice {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;

    @FXML
    private Button buttonField;
    
    private String username;
    private String password;
    private String email;
   
//    @FXML
//    public void handelClick(ActionEvent event){
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Information Dialog");
//    }  
    
//        username = usernameField.getText();
//        password = passwordField.getText();
//        email = emailField.getText(); 
//        
//        System.out.println("username: " + username);
//        System.out.println("password: " + password);
//        System.out.println("email: " + email);
    
 
    public boolean vadidateHoTen(){
        return true;
    }
    
}
