
package controller;

import com.mycompany.noticemenugui.App;
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
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import model.NoticeList;
import model.User;

/**
 * Controller for the "Add Notice" screen.
 * Handles user interactions for adding a new notice and manages the form inputs.
 * 
 * @author Michal Walus
 * @version 1.1
 */

public class AddNoticeScreenController {

    /** Text field for entering the notice title. */
    @FXML
    private TextField titleField;
    /** Text area for entering the notice description. */
    @FXML
    private TextArea descriptionField;
    /** Button to submit the notice details. */
    @FXML
    private Button submitButton;
    /** Button to leave the "Add Notice" screen without saving. */
    @FXML
    private Button leaveButton;
    /** Label to display error messages to the user. */
    @FXML
    private Label errorField;
    /** The current user adding the notice. */
    private User user;
    /** The main list of notices where the new notice will be added. */
    private NoticeList notices;
    
    
    /**
     * Constructs a new {@code AddNoticeScreenController} with the given user and notices list.
     *
     * @param user the current user adding the notice.
     * @param notices the list of notices to which the new notice will be added.
     */
    public AddNoticeScreenController(User user, NoticeList notices){
        this.user = user;
        this.notices = notices;
    }
    
    /**
     * Initializes the controller by setting tooltips and keyboard navigation for the UI components.
     * This method is automatically called after the FXML view is loaded.
     * 
     * Tooltips:
     * - Assigns a tooltip to each UI element to guide the user:
     *   - `titleField`: "Put title of your notice here"
     *   - `descriptionField`: "Put description of your notice here"
     *   - `submitButton`: "Submit your notice"
     *   - `leaveButton`: "Back to all notices"
     * 
     * Keyboard Navigation:
     * - Customizes `Tab` and `Enter` key behavior for seamless keyboard navigation:
     *   - Pressing `Tab` in `titleField` moves focus to `descriptionField`.
     *   - Pressing `Enter` in `titleField` or `descriptionField` activates `submitButton`.
     *   - Pressing `Tab` in `descriptionField` moves focus to `submitButton`.
     *   - Pressing `Tab` in `submitButton` moves focus to `leaveButton`.
     *   - Pressing `Tab` in `leaveButton` cycles focus back to `titleField`.
     */
    @FXML
    private void initialize() {
        Tooltip titleTooltip = new Tooltip("Put title of your notice here");
        titleField.setTooltip(titleTooltip);
        
        Tooltip descTooltip = new Tooltip("Put description of your notice here");
        descriptionField.setTooltip(descTooltip);
        
        Tooltip subtooltip = new Tooltip("Submit your notice");
        submitButton.setTooltip(subtooltip);
        
        Tooltip leavetooltip = new Tooltip("Back to all notices");
        leaveButton.setTooltip(leavetooltip);
        
        
        titleField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                event.consume(); 
                descriptionField.requestFocus(); 
            }
            else if (event.getCode() == KeyCode.ENTER){
                submitButton.fire();
            }
        });

        descriptionField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                event.consume();
                submitButton.requestFocus(); 
            }
            else if (event.getCode() == KeyCode.ENTER){
                submitButton.fire();
            }
        });

        submitButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                event.consume();
                leaveButton.requestFocus(); 
            }
        });

        leaveButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                event.consume();
                titleField.requestFocus(); 
            }
        });
    }


    /**
     * Handles the action when the submit button is clicked.
     * Validates the form inputs and adds a new notice to the list if valid.
     * Displays an error message if inputs are missing.
     * 
     * @param event the event triggered by clicking the submit button.
     */
    @FXML
    private void submitButtonClicked(ActionEvent event) {
        if(titleField.getText().isEmpty() || descriptionField.getText().isEmpty()){
            this.errorField.setText("Please provide all information above");
        }
        else {
            this.errorField.setText("");
            this.notices.addNotice(this.titleField.getText(), this.user.getName(), this.descriptionField.getText());
            try {
                App.setRoot("TableScreen");
            } catch (IOException e) {
                this.errorField.setText("Failed to load the next screen. Please try again.");
                e.printStackTrace();
            }
        }
    }
    
    
    /**
     * Handles the action when the leave button is clicked.
     * Navigates back to the main "TableScreen" without saving any data.
     *
     * @param event the event triggered by clicking the leave button.
     */
    @FXML
    private void leaveButtonClicked(ActionEvent event) {
        try {
                App.setRoot("TableScreen");
            } catch (IOException e) {
                this.errorField.setText("Failed to load the next screen. Please try again.");
                e.printStackTrace();
            }
    }

}
