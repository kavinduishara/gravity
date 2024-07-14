module com.example.gravity {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gravity to javafx.fxml;
    exports com.example.gravity;
}