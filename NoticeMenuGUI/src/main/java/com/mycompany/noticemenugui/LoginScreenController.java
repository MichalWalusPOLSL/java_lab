/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.noticemenugui;

import com.mycompany.noticemenugui.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author micha
 */
public class LoginScreenController {

    @FXML
    private TextField PasswordField;
    @FXML
    private Button LoginButton;
    @FXML
    private Label ErrorLabel;

    @FXML
    private void tryLoginToSystem(ActionEvent event) throws IOException {
        
        String password = this.PasswordField.getText();
        
        if(password.isEmpty()){
            this.ErrorLabel.setText("Please write your login");
        }
        else if (Character.isDigit(password.charAt(0))){
            this.ErrorLabel.setText("Login has to start with a letter");
        }
        else {
            System.out.println(password);
            App.setRoot("ButtonScreen");
        }
        
        
    }
    
}
