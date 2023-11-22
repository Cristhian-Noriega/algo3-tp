package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControladorPantallaInicial {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
