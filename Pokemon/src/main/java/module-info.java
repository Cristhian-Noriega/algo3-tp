module Pokemon {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens tp1.clases to javafx.fxml;
    exports tp1.clases;
}