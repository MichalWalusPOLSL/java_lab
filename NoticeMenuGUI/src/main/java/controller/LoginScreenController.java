/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.noticemenugui.App;
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
import model.MyThrownException;
import model.NoticeList;
import model.User;

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
    
    private User user;
    private NoticeList notices;
    
    
    public LoginScreenController(User user, NoticeList notices){
        this.user = user;
        this.notices = notices;
    }

    @FXML
    private void tryLoginToSystem(ActionEvent event) throws IOException {
        
        String password = this.PasswordField.getText();
        
        try{
            this.user.setName(password);
            App.setRoot("TableScreen");
        }
        catch (MyThrownException e){
            ErrorLabel.setText(e.getMessage());
        }
        
        
    }
    
}
