module com.mycompany.noticemenugui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.noticemenugui to javafx.fxml;
    exports com.mycompany.noticemenugui;
}
