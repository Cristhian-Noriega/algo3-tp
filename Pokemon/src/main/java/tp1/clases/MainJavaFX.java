package tp1.clases;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp1.clases.controlador.ControladorEscenas;
import tp1.clases.controlador.ControladorPantallaInicial;

import java.io.IOException;


public class MainJavaFX extends Application{
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        ControladorEscenas controlador = new ControladorEscenas();
        controlador.inicializar(stage);
    }

}