module Pokemon {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.jline;

    opens tp1.clases to javafx.fxml;
    opens tp1.clases.controlador to javafx.fxml;
    opens tp1.clases.eventos to javafx.fxml;
    exports tp1.clases;
}