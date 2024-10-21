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
    
    
    @FXML
    private void submitButtonClicked(ActionEvent event) {
        if(titleField.getText().isEmpty() || descriptionField.getText().isEmpty()){
            this.errorField.setText("Please provide all information above");
        }
        else {
            System.out.println("correct data");
            this.errorField.setText("");
        }
    }
    
    @FXML
    private void leaveButtonClicked(ActionEvent event) throws IOException{
        App.setRoot("ButtonScreen");
    }

}
