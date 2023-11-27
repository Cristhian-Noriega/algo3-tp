package tp1.clases.controlador;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorEscenas {

    public void inicializar(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("pantallaInicial.fxml"));

        Scene scene = new Scene(loader.load());
        ControladorPantallaInicial controlador = loader.getController();
        controlador.inicializar(stage);

        stage.setScene(scene);
        stage.show();
    }
}
