package com.mycompany.noticemenugui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.NoticeList;
import model.User;
import java.io.IOException;
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

    /** The main Scene for the application. */
    private Scene scene;
    /** Stores the main list of notices (announcements) added to the program. */
    private NoticeList notices;
    /** Represents the current user of the application. */
    private User user;

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
        this.scene = new Scene(loadFXML("LoginScreen"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes the root of the current scene to the specified FXML file.
     *
     * @param fxml the name of the FXML file (without the ".fxml" extension) to load.
     * @throws IOException if the FXML file cannot be loaded.
     */
    public void setRoot(String fxml) throws IOException {
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
    private Parent loadFXML(String fxml) throws IOException {
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
    private Object createControllerInstance(Class<?> controllerClass) {
        try {
            Constructor<?> constructor = controllerClass.getConstructor(User.class, NoticeList.class, App.class);
            return constructor.newInstance(user, notices, this);  // Przekazujemy również instancję App
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
