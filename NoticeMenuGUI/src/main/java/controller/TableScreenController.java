
package controller;

import com.mycompany.noticemenugui.App;
import java.io.IOException;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
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
    /** CheckBox to filter and display only the user's own notices. */
    @FXML
    private CheckBox myNoticesCheckBox;
    /** The current user viewing or managing the notices. */
    private User user;
    /** The main list of notices available in the application. */
    private NoticeList notices;
    /** 
     * Reference to the main application instance, used for managing scene transitions.
     * This allows the controller to request navigation to different screens within the application.
     */
    private App app;
    
    
    /**
     * Constructs a new TableScreenController with the provided user and notice list.
     *
     * @param user the current user interacting with the notice table.
     * @param notices the list of notices displayed in the table.
     */
    public TableScreenController(User user, NoticeList notices, App app){
        this.user = user;
        this.notices = notices;
        this.app = app;
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
        Map<Control, String> tooltips = Map.of(
            noticesTable, "Displays the list of notices",
            addNoticeButton, "Navigate to the screen to add a new notice",
            deleteButton, "Delete the selected notice from the list",
            checkNoticeButton, "View details of the selected notice",
            leaveButton, "Log out and leave the current screen",
            descriptionTextArea, "Description of the selected notice",
            myNoticesCheckBox, "Show only your notices"
        );

        tooltips.forEach((control, tip) -> control.setTooltip(new Tooltip(tip)));
        
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        populateNoticesTable();
        noticesTable.setItems(notices.getAllObservable());
        deleteButton.setOnAction(event -> deleteButtonClicked(event));
        deleteButton.setDisable(true);
        checkNoticeButton.setDisable(true);
        this.descriptionTextArea.setVisible(false);
        
        noticesTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> handleRowSelectionChange(newValue)
        );
        myNoticesCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> populateNoticesTable());
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
                app.setRoot("AddNoticeScreen");
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
    Notice selectedNotice = noticesTable.getSelectionModel().getSelectedItem();
    
    if (selectedNotice != null) {
        this.errorLabel.setText("");

        this.notices.deleteOne(selectedNotice);

        populateNoticesTable();
    } else {
        this.errorLabel.setText("Please select the notice that you want to delete");
    }
}
    
    /**
     * Handles the action when the "Check Notice" button is clicked.
     * Toggles between the table view and the detailed view of a selected notice.
     * If switching to the detailed view, it displays the content of the selected notice.
     *
     * @param event the event triggered by clicking the "Check Notice" button.
     */
    @FXML
    private void checkNoticeButtonClicked(ActionEvent event) {
        boolean isTableVisible = noticesTable.isVisible();
        setVisibility(!isTableVisible);
        checkNoticeButton.setText(isTableVisible ? "Back" : "Check Notice");

        if (isTableVisible && noticesTable.getSelectionModel().getSelectedItem() != null) {
            descriptionTextArea.setText(noticesTable.getSelectionModel().getSelectedItem().getText());
        }
    }

    /**
     * Sets the visibility of various UI elements based on whether the table or
     * the detailed notice view should be displayed. When showing the table, all
     * buttons and options related to the main notice list are visible. When showing
     * the detailed view, only the notice content and the "Back" button are visible.
     *
     * @param showTable true if the table should be visible, false to show the detailed view.
     */
    private void setVisibility(boolean showTable) {
        noticesTable.setVisible(showTable);
        descriptionTextArea.setVisible(!showTable);
        deleteButton.setVisible(showTable);
        addNoticeButton.setVisible(showTable);
        errorLabel.setVisible(showTable);
        leaveButton.setVisible(showTable);
        myNoticesCheckBox.setVisible(showTable);

        if (showTable) {
            errorLabel.setText("");
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
                app.setRoot("LoginScreen");
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
    /**
     * If myNoticeCheckbox is selected, it will show only notices of the current user.
     */
    private void populateNoticesTable() {
    if (myNoticesCheckBox.isSelected()) {
        noticesTable.setItems(FXCollections.observableArrayList(notices.getNoticesByUser(user.getName())));
    } else {
        noticesTable.setItems(notices.getAllObservable());
    }
}

    
}
