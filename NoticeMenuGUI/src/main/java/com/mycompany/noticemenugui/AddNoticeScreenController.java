/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.noticemenugui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import model.NoticeList;
import model.User;
/**
 * FXML Controller class
 *
 * @author micha
 */
public class AddNoticeScreenController {


    @FXML
    private TextField titleField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Button submitButton;
    @FXML
    private Button leaveButton;
    @FXML
    private Label errorField;
    
    private User user;
    private NoticeList notices;
    
    public AddNoticeScreenController(User user, NoticeList notices){
        this.user = user;
        this.notices = notices;
    }
    
    
    
    @FXML
    private void submitButtonClicked(ActionEvent event) throws IOException {
        if(titleField.getText().isEmpty() || descriptionField.getText().isEmpty()){
            this.errorField.setText("Please provide all information above");
        }
        else {
            this.errorField.setText("");
            this.notices.addNotice(this.titleField.getText(), this.user.getName(), this.descriptionField.getText());
            App.setRoot("TableScreen");
        }
    }
    
    @FXML
    private void leaveButtonClicked(ActionEvent event) throws IOException{
        App.setRoot("TableScreen");
    }

}
