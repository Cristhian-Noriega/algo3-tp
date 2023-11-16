module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
}