
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
 * Controller for the "Login Screen".
 * Manages user interactions on the login screen, including login attempts
 * and error handling for incorrect login information.
 * 
 * @author Michal Walus
 * @version 1.2
 */
public class LoginScreenController {
    
    /** Text field for entering the user's password. */
    @FXML
    private TextField PasswordField;
    /** Button to attempt logging in. */
    @FXML
    private Button LoginButton;
    /** Label to display error messages related to login attempts. */
    @FXML
    private Label ErrorLabel;
    /** The current user attempting to log in. */
    private User user;
    /** The main list of notices for the application. */
    private NoticeList notices;
    
    /**
     * Constructs a new LoginScreenController with the provided user and notice list.
     *
     * @param user the current user attempting to log in.
     * @param notices the list of notices available in the application.
     */
    public LoginScreenController(User user, NoticeList notices){
        this.user = user;
        this.notices = notices;
    }
    
    
    /**
     * Attempts to log in to the system by validating the entered password.
     * If the login is successful, navigates to the "TableScreen".
     * If an exception occurs during login, displays an error message.
     *
     * @param event the event triggered by clicking the login button.
     */
    @FXML
    private void tryLoginToSystem(ActionEvent event) {
        
        String password = this.PasswordField.getText();
        
        try{
            this.user.setName(password);
            App.setRoot("TableScreen");
        }
        catch (MyThrownException e){
            ErrorLabel.setText(e.getMessage());
        }
        catch (IOException ee){
            ErrorLabel.setText("Failed to load the next screen. Please try again.");
        }
        
        
    }
    
}
