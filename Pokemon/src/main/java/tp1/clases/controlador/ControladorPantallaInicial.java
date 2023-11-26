package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControladorPantallaInicial {
    @FXML
    private ImageView boton;

    @FXML
    protected void setBoton() {
        //this.boton.setOnMouseClicked(event -> );
        this.boton.setOnMouseDragEntered(event -> handleMouseEnter());
        this.boton.setOnMouseDragExited(event -> handleMouseExit());
    }

    @FXML
    private void handleMouseEnter() {
        boton.setImage(new Image("imagenes/play.png"));
    }

    @FXML
    private void handleMouseExit() {
        boton.setImage(new Image("imagenes/playPressed.png"));
    }

}
