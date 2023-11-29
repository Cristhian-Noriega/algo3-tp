module Pokemon {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires javafx.media;

    requires org.jline;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    opens tp1.clases to javafx.fxml;
    opens tp1.clases.controlador to javafx.fxml;
    opens tp1.clases.eventos to javafx.fxml;
    opens tp1.clases.modelo to javafx.fxml;

    exports tp1.clases.modelo;
    exports tp1.clases;

}