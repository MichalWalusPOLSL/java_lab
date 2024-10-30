
package controller;

import com.mycompany.noticemenugui.App;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Notice;
import model.NoticeList;
import model.User;
/**
 * Controller for the "Table Screen".
 * Manages user interactions with the notice table, including viewing, adding, deleting, 
 * and checking the details of each notice. Displays notice information and allows navigation
 * between different screens.
 * 
 * @author Michal Walus
 * @version 1.0
 */
public class TableScreenController {

    /** TableView to display the list of notices. */
    @FXML
    private TableView<Notice> noticesTable;
    /** Button to navigate to the "Add Notice" screen. */
    @FXML
    private Button addNoticeButton;
    /** Button to delete the selected notice. */
    @FXML
    private Button deleteButton;
    /** Button to check the details of the selected notice. */
    @FXML
    private Button checkNoticeButton;
    /** Button to leave the current screen and log out. */
    @FXML
    private Button leaveButton;
    /** Label to display error messages. */
    @FXML
    private Label errorLabel;
    /** TextArea to show the description of a selected notice. */
    @FXML
    private TextArea descriptionTextArea;
    /** Column in the table for displaying the title of each notice. */
    @FXML
    private TableColumn<Notice, String> titleColumn;
    /** Column in the table for displaying the author of each notice. */
    @FXML
    private TableColumn<Notice, String> authorColumn;
    /** The current user viewing or managing the notices. */
    private User user;
    /** The main list of notices available in the application. */
    private NoticeList notices;
    
    
    /**
     * Constructs a new TableScreenController with the provided user and notice list.
     *
     * @param user the current user interacting with the notice table.
     * @param notices the list of notices displayed in the table.
     */
    public TableScreenController(User user, NoticeList notices){
        this.user = user;
        this.notices = notices;
    }
    
    /**
     * Initializes the TableScreen by setting up the table columns, populating 
     * the list of notices, and configuring button actions and row selection listeners.
     * 
     * This method:
     * - Sets tooltips for each main UI component to guide the user.
     * - Configures table columns to display notice titles and authors.
     * - Populates the table with an observable list of notices.
     * - Sets actions for buttons, such as deleting a notice and checking details.
     * - Disables buttons and hides the description area by default.
     * - Adds a listener to handle changes in the selected table row.
     */
    @FXML
    private void initialize() {
        Tooltip tableTooltip = new Tooltip("Displays the list of notices");
        noticesTable.setTooltip(tableTooltip);

        Tooltip addNoticeTooltip = new Tooltip("Navigate to the screen to add a new notice");
        addNoticeButton.setTooltip(addNoticeTooltip);

        Tooltip deleteTooltip = new Tooltip("Delete the selected notice from the list");
        deleteButton.setTooltip(deleteTooltip);

        Tooltip checkNoticeTooltip = new Tooltip("View details of the selected notice");
        checkNoticeButton.setTooltip(checkNoticeTooltip);

        Tooltip leaveTooltip = new Tooltip("Log out and leave the current screen");
        leaveButton.setTooltip(leaveTooltip);

        Tooltip descriptionTooltip = new Tooltip("Description of the selected notice");
        descriptionTextArea.setTooltip(descriptionTooltip);
        
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        noticesTable.setItems(notices.getAllObservable());
        deleteButton.setOnAction(event -> deleteButtonClicked(event));
        deleteButton.setDisable(true);
        checkNoticeButton.setDisable(true);
        this.descriptionTextArea.setVisible(false);
        
        noticesTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> handleRowSelectionChange(newValue)
        );

    }
    
    /**
     * Handles the action when the "Add Notice" button is clicked.
     * Navigates to the "Add Notice" screen to allow the user to add a new notice.
     *
     * @param event the event triggered by clicking the "Add Notice" button.
     */
    @FXML
    private void addNoticeButtonClicked(ActionEvent event) {
        try {
                App.setRoot("AddNoticeScreen");
            } catch (IOException e) {
                this.errorLabel.setText("Failed to load the next screen. Please try again.");
                e.printStackTrace();
            }
    }
    
    /**
     * Handles the action when the "Delete" button is clicked.
     * Deletes the selected notice from the list and updates the table view.
     *
     * @param event the event triggered by clicking the "Delete" button.
     */
    @FXML
    private void deleteButtonClicked(ActionEvent event) {
        int index = noticesTable.getSelectionModel().getSelectedIndex();
        
        if(index >= 0){
            this.errorLabel.setText("");
            this.notices.deleteOne(index);
            noticesTable.setItems(notices.getAllObservable());
        }
        else {
            this.errorLabel.setText("Please select the notice that you want to delete");
        }
        
    }
    
    /**
     * Handles the action when the "Check Notice" button is clicked.
     * Displays the details of the selected notice or returns to the notice list view.
     *
     * @param event the event triggered by clicking the "Check Notice" button.
     */
    @FXML
    private void checkNoticeButtonClicked(ActionEvent event) {
        if(this.noticesTable.isVisible() == true){
            if(noticesTable.getSelectionModel().getSelectedItem() == null){
                
            }
            else{
                this.noticesTable.setVisible(false);
                this.descriptionTextArea.setText(noticesTable.getSelectionModel().getSelectedItem().getText());
                this.descriptionTextArea.setVisible(true);
                this.deleteButton.setVisible(false);
                this.addNoticeButton.setVisible(false);
                this.errorLabel.setVisible(false);
                this.leaveButton.setVisible(false);
                this.checkNoticeButton.setText("Back");
            }
        }
        else {
                this.noticesTable.setVisible(true);
                this.descriptionTextArea.setVisible(false);
                this.deleteButton.setVisible(true);
                this.addNoticeButton.setVisible(true);
                this.errorLabel.setVisible(true);
                this.errorLabel.setText("");
                this.leaveButton.setVisible(true);
                this.checkNoticeButton.setText("Check Notice");
        }
    }
    
    /**
     * Handles the action when the "Leave" button is clicked.
     * Logs out the current user and navigates back to the login screen.
     *
     * @param event the event triggered by clicking the "Leave" button.
     */
    @FXML
    private void leaveButtonClicked(ActionEvent event) {
        try {
                App.setRoot("LoginScreen");
            } catch (IOException e) {
                this.errorLabel.setText("Failed to load the next screen. Please try again.");
                e.printStackTrace();
            }
        
    }
    
    /**
     * Handles changes in the selection of rows in the notice table.
     * Enables or disables buttons based on the selected notice and user permissions.
     *
     * @param selectedNotice the notice selected in the table, or null if no selection.
     */
    private void handleRowSelectionChange(Notice selectedNotice) {
    if (this.checkNoticeButton.isVisible() && this.deleteButton.isVisible()) {
        if (selectedNotice == null) {
            deleteButton.setDisable(true);
            checkNoticeButton.setDisable(true);
        } else {
            checkNoticeButton.setDisable(false);
            if ("admin".equals(user.getName()) || selectedNotice.getAuthor().equals(user.getName())) {
                deleteButton.setDisable(false);
            } else {
                deleteButton.setDisable(true);
            }
        }
    }
}

    
}
