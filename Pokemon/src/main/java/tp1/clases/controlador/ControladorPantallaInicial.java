package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.modelo.Batalla;


import java.io.IOException;
import java.util.Objects;

public class ControladorPantallaInicial implements Controlador {

    @FXML
    private ImageView boton;

    private Stage stage;

    @Override
    public void inicializar(Batalla batalla) {

        this.boton.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/Imagenes/play.png")))));


        this.boton.setOnMouseEntered(event -> handleMouseDragEntered());
        this.boton.setOnMouseExited(event -> handleMouseDragExited());

        this.boton.setOnMouseClicked(event -> {
            handleOnMouseClicked();
            try {
                crearEfectoInicio();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    private void handleMouseDragEntered() {

        boton.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/Imagenes/playPressed.png")))));

    }

    @FXML
    private void handleMouseDragExited() {

        boton.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/Imagenes/play.png")))));

    }

    @FXML
    private void handleOnMouseClicked() {
        this.boton.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("/Imagenes/playRojo.png")))));
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void crearEfectoInicio() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/pantallaEfectoInicio.fxml"));

        Scene scene = new Scene(loader.load());
        ControladorEfectoInicio controlador = loader.getController();
        controlador.inicializar();

        this.stage.setScene(scene);
        this.stage.show();

    }

}

