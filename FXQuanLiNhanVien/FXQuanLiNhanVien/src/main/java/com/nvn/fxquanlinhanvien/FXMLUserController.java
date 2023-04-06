/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nvn.fxquanlinhanvien;



import com.nvn.conf.JdbcUtils;
import com.nvn.service.UserService;

import com.pojo.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author quynh  ttttttt
 */
public class FXMLUserController implements Initializable{

    static UserService us = new  UserService();
    @FXML private TableView<User> tbvUser;
    @FXML private TextField txtLastName;
    @FXML private TextField txtFirstName;
    @FXML private TextField txtId;
    @FXML private TextField txtSearch;
    @FXML private TextField txtAge;
    @FXML private TextField txtPhone;
    @FXML private TextField txtId_Account;
    
        ObservableList<User> userList = FXCollections.observableArrayList();

    /** 
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
             this.loadTableView();
        UserService usa = new UserService();
        this.tbvUser.setItems(FXCollections.observableList(usa.getUser()));
        //Utils.getBox("Thêm tài khoản mới thành công", Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLUserController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        this.txtSearch.textProperty().addListener(e -> {
            try {
                this.loadTableData(this.txtSearch.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
       
    private void loadTableData(String kw) throws SQLException {
        
        List<User> ques = us.getUser(kw);
        
        this.tbvUser.getItems().clear();
        this.tbvUser.setItems(FXCollections.observableList(ques));
    }
    public void loadTableView(){
        TableColumn colId = new TableColumn("Mã User");
        colId.setCellValueFactory(new PropertyValueFactory("id")); 
        colId.setPrefWidth(100);
        
        TableColumn colLastName = new TableColumn("Họ");
        colLastName.setCellValueFactory(new PropertyValueFactory("lastname")); 
        colLastName.setPrefWidth(300);
        
        TableColumn colFirstName = new TableColumn("Tên");
        colFirstName.setCellValueFactory(new PropertyValueFactory("firstname")); 
        colFirstName.setPrefWidth(300);
        TableColumn colAge = new TableColumn("Tuổi");
        colAge.setCellValueFactory(new PropertyValueFactory("age")); 
        colAge.setPrefWidth(300);
        TableColumn colPhone = new TableColumn("SĐT");
        colPhone.setCellValueFactory(new PropertyValueFactory("phone")); 
        colPhone.setPrefWidth(300);
        TableColumn colId_account = new TableColumn("ID_account");
        colId_account.setCellValueFactory(new PropertyValueFactory("id_account")); 
        colId_account.setPrefWidth(300);
        
        this.tbvUser.getColumns().addAll(colId,colLastName,colFirstName,colAge,colPhone,colId_account);
    }
    
    @FXML
    public void addUserHandler(ActionEvent event) throws SQLException{
        User u = new User(txtLastName.getText(),txtFirstName.getText(), Integer.parseInt(txtAge.getText()), Integer.parseInt(txtPhone.getText()),Integer.parseInt(txtId_Account.getText()) );
        UserService usb = new UserService();
        usb.addUser(u);
    }
    
    @FXML
    public void delUserHandler(ActionEvent event) throws SQLException{
        UserService s = new UserService();
        s.delUser(Integer.parseInt(this.txtId.getText()));   
    }

    @FXML
    public void refreshTable(ActionEvent event) throws SQLException{
       
       userList.clear();
       
       String sql = "SELECT * FROM user";
        try(Connection conn = JdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM user");
            
            while(rs.next()){
                userList.add(new User(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"),rs.getInt("age"),rs.getInt("phone"),rs.getInt("id_account"))) ;
            }
            this.tbvUser.setItems(userList);
            this.txtLastName.setText("");
            this.txtFirstName.setText("");
            this.txtAge.setText("");
            this.txtPhone.setText("");
            this.txtId_Account.setText("");
            
            
       }
    }
    
    @FXML
    public void updateUserHandler(ActionEvent event) throws SQLException {
        UserService s = new UserService();
        s.updateUser(this.txtLastName.getText(), this.txtFirstName.getText(), Integer.parseInt(this.txtId.getText()), Integer.parseInt(this.txtAge.getText()), Integer.parseInt(this.txtPhone.getText()), Integer.parseInt(this.txtId_Account.getText()));
        
    }
}
