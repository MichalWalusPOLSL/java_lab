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
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static NoticeList notices;
    private static User user;
    

    @Override
    public void start(Stage stage) throws IOException {
        this.notices = new NoticeList();
        this.user = new User();
        scene = new Scene(loadFXML("LoginScreen"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> createControllerInstance(controllerClass));

        Parent root = fxmlLoader.load();
        scene.setRoot(root);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> createControllerInstance(controllerClass));
        return fxmlLoader.load();
    }
    
    private static Object createControllerInstance(Class<?> controllerClass) {
        try {
            Constructor<?> constructor = controllerClass.getConstructor(User.class, NoticeList.class);
            return constructor.newInstance(user, notices);
        } catch (Exception e) {
            System.out.println(e.toString());
        } 
        return null;
    }

    public static void main(String[] args) {
        launch();
    }

}