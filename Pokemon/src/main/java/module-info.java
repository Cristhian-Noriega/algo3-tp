module Pokemon {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    opens tp1.clases to javafx.fxml;
    exports tp1.clases;
    exports tp1.clases.controlador;
    opens tp1.clases.controlador to javafx.fxml;
}