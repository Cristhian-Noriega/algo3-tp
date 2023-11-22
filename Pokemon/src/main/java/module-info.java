module Pokemon {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires org.jline;

    opens tp1.clases to javafx.fxml;
    exports tp1.clases;

    opens tp1.clases.controlador to javafx.fxml;
    exports tp1.clases.controlador;

}