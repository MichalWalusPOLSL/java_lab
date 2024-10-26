package com.mycompany.noticemenugui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import model.NoticeList;
import model.User;
import model.MyThrownException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * Main application class for the NoticeMenuGUI JavaFX application.
 * It serves as the entry point of the application, initializing the primary scene 
 * and managing transitions between different FXML views.
 * 
 * @author Michal Walus
 * @version 1.2
 */
public class App extends Application {

    /**
     * The main Scene for the application.
     * This scene is shared and reused throughout the application's lifecycle,
     * allowing different FXML views to be loaded dynamically as the root node.
     */
    private static Scene scene;
    /**
     * Stores the main list of notices (announcements) added to the program.
     * Each notice includes important information such as the title, content,
     * and the user who created it. This list is shared across the application
     * to provide a centralized source of notice data.
     */
    private static NoticeList notices;
    /**
     * Represents the current user of the application.
     * This user instance stores essential details about the user,
     * primarily their username, and is used to track user-specific actions.
     */
    private static User user;
    
    /**
     * The main entry point for all JavaFX applications. 
     * This method initializes the primary Stage and loads the initial scene.
     *
     * @param stage the primary stage for this application, onto which the application scene is set.
     * @throws IOException if loading the initial FXML file fails.
     */
    @Override
    public void start(Stage stage) throws IOException {
        this.notices = new NoticeList();
        this.user = new User();
        scene = new Scene(loadFXML("LoginScreen"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Changes the root of the current scene to the specified FXML file.
     *
     * @param fxml the name of the FXML file (without the ".fxml" extension) to load.
     * @throws IOException if the FXML file cannot be loaded.
     */
    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> createControllerInstance(controllerClass));

        Parent root = fxmlLoader.load();
        scene.setRoot(root);
    }

    /**
     * Loads an FXML file and returns its root Parent node.
     *
     * @param fxml the name of the FXML file (without the ".fxml" extension) to load.
     * @return the root Parent node of the loaded FXML file.
     * @throws IOException if the FXML file cannot be loaded.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> createControllerInstance(controllerClass));
        return fxmlLoader.load();
    }
    
    /**
     * Creates an instance of the specified controller class using reflection.
     * The controller class is expected to have a constructor that accepts 
     * a {@link User} and a {@link NoticeList} as parameters.
     *
     * @param controllerClass the class of the controller to be instantiated.
     * @return a new instance of the controller, or null if instantiation fails.
     */
    
    private static Object createControllerInstance(Class<?> controllerClass) {
        try {
            Constructor<?> constructor = controllerClass.getConstructor(User.class, NoticeList.class);
            return constructor.newInstance(user, notices);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            System.out.println(e.toString());
        } 
        return null;
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

}