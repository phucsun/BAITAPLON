module com.example.libraryapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.libraryapp to javafx.fxml;
    exports com.example.libraryapp;
}