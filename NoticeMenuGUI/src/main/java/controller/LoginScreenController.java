
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
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    
    /** Text field for entering the user's login. */
    @FXML
    private TextField loginField;
    /** Button to attempt logging in. */
    @FXML
    private Button loginButton;
    /** Label to display error messages related to login attempts. */
    @FXML
    private Label errorLabel;
    /** The current user attempting to log in. */
    private User user;
    /** The main list of notices for the application. */
    private NoticeList notices;
    /** 
     * Reference to the main application instance, used for managing scene transitions.
     * This allows the controller to request navigation to different screens within the application.
     */
    private App app;
    
    /**
     * Initializes the controller by setting tooltips for UI elements.
     * This method is automatically called after the FXML view is loaded.
     * 
     * The login button is assigned a tooltip that instructs the user to press it to log in.
     * The login field (text field) is assigned a tooltip that prompts the user to enter their login information.
     */
    @FXML
    private void initialize() {
        Tooltip loginTooltip = new Tooltip("Press here to login");
        loginButton.setTooltip(loginTooltip);
        Tooltip textTooltip = new Tooltip("Put your login here");
        loginField.setTooltip(textTooltip);
    }
    
    /**
     * Constructs a new LoginScreenController with the provided user and notice list.
     *
     * @param user the current user attempting to log in.
     * @param notices the list of notices available in the application.
     */
    public LoginScreenController(User user, NoticeList notices, App app){
        this.user = user;
        this.notices = notices;
        this.app = app;
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
        
        String password = this.loginField.getText();
        
        try{
            this.user.setName(password);
            app.setRoot("TableScreen");
        }
        catch (MyThrownException e){
            errorLabel.setText(e.getMessage());
        }
        catch (IOException ee){
            errorLabel.setText("Failed to load the next screen. Please try again.");
        }
        
        
    }
    
    /**
     * Handles the key press event on the login screen.
     * If the ENTER key is pressed, this method simulates a click on the login button,
     * triggering the login attempt.
     *
     * @param event the key event triggered by pressing a key.
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) { 
        this.loginButton.fire();
    }
}
    
}
