module com.example.javafx1 {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires com.zaxxer.hikari;

    // 🔴 THIS IS THE IMPORTANT PART
    opens com.example.javafx1.model to javafx.base;
    opens com.example.javafx1.controller to javafx.fxml;

    exports com.example.javafx1;
}