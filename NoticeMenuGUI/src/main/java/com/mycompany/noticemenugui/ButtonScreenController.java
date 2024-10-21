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
/**
 * FXML Controller class
 *
 * @author micha
 */
public class ButtonScreenController {


    @FXML
    private Button addButton;
    @FXML
    private Button checkButton;
    @FXML
    private Button leaveButton;
    
    @FXML
    private void addButtonClicked(ActionEvent event) throws IOException {
        App.setRoot("AddNoticeScreen");
    }

    @FXML
    private void checkButtonClicked(ActionEvent event) {
    }

    @FXML
    private void leaveButtonClicked(ActionEvent event) throws IOException {
        App.setRoot("LoginScreen");
    }

}
