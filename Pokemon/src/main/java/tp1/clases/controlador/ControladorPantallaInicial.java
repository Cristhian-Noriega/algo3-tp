package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControladorPantallaInicial {

    @FXML
    private ImageView boton;


    @FXML
    protected void inicializar(Stage stage) {

        this.boton.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/imagenes/play.png")))));

        this.boton.setOnMouseEntered(event -> handleMouseDragEntered());
        this.boton.setOnMouseExited(event -> handleMouseDragExited());

        this.boton.setOnMouseClicked(event -> {
            handleOnMouseClicked();
            try {
                crearEfectoInicio(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    private void handleMouseDragEntered() {
        boton.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/imagenes/playPressed.png")))));
    }

    @FXML
    private void handleMouseDragExited() {
        boton.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/imagenes/play.png")))));
    }

    @FXML
    private void handleOnMouseClicked(){
        this.boton.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/imagenes/playRojo.png")))));
    }

    public void crearEfectoInicio(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("pantallaEfectoInicio.fxml"));

        Scene scene = new Scene(loader.load());
        ControladorEfectoInicio controlador = loader.getController();
        controlador.inicializar();

        stage.setScene(scene);
        stage.show();

    }

}
