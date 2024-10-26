module com.mycompany.noticemenugui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.noticemenugui to javafx.fxml;
    opens controller to javafx.fxml;
    opens model to javafx.base;
    exports com.mycompany.noticemenugui;
}
