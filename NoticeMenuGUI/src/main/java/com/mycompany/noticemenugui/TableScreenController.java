/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.noticemenugui;

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
import javafx.scene.control.cell.PropertyValueFactory;
import model.Notice;
import model.NoticeList;
import model.User;
/**
 * FXML Controller class
 *
 * @author micha
 */
public class TableScreenController {


    @FXML
    private TableView<Notice> noticesTable;
    @FXML
    private Button addNoticeButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button checkNoticeButton;
    @FXML
    private Button leaveButton;
    @FXML
    private Label errorLabel;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TableColumn<Notice, String> titleColumn;
    @FXML
    private TableColumn<Notice, String> authorColumn;
    
    private User user;
    private NoticeList notices;
    
    public TableScreenController(User user, NoticeList notices){
        this.user = user;
        this.notices = notices;
    }
    
    @FXML
    public void initialize() {
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
    
    
    @FXML
    private void addNoticeButtonClicked(ActionEvent event) throws IOException {
        App.setRoot("AddNoticeScreen");
    }

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
    
    @FXML
    private void leaveButtonClicked(ActionEvent event) throws IOException{
        App.setRoot("LoginScreen");
        
    }
    
    private void handleRowSelectionChange(Notice selectedNotice) {
        if(this.checkNoticeButton.isVisible() && this.deleteButton.isVisible()){
            if (selectedNotice == null) {
                deleteButton.setDisable(true);
                checkNoticeButton.setDisable(true);
            } else {
                checkNoticeButton.setDisable(false);
                if (selectedNotice.getAuthor().equals(user.getName())) {
                    deleteButton.setDisable(false); 
                } else {
                    deleteButton.setDisable(true);
                }
            }
        }
    }

    
    
    
}
