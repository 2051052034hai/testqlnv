/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nvn.fxquanlinhanvien;

import com.nvn.service.AccountService;
import com.pojo.UserTest;
import java.beans.EventHandler;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class registerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML TextField txtUsername;
    @FXML TextField txtEmail;
    @FXML TextField txtPhone;
    @FXML PasswordField txtPassword;
    @FXML PasswordField txtCofirmpass;
    
    private String username;
    private String password;
    private String phone;
    private String email;
    private String cofirmpass;
    @FXML
    private AnchorPane txtName;
    @FXML
    private Button txtbutton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   
    
    @FXML
    public void handelClick(ActionEvent event) throws SQLException{
        
        username = txtUsername.getText();
        phone = txtPhone.getText();
        password = txtPassword.getText();
        email = txtEmail.getText();
        cofirmpass = txtCofirmpass.getText();
        
        UserTest u = new UserTest(username,password,phone,email, cofirmpass);
        AccountService s = new AccountService();
        s.addUserTest(u);
        
    
    }
    
    
}
