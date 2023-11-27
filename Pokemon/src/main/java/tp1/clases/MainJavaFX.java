package tp1.clases;

import javafx.application.Application;
import javafx.stage.Stage;
import tp1.clases.controlador.ControladorEscenas;
import tp1.clases.modelo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainJavaFX extends Application {
    private Batalla batalla;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, CloneNotSupportedException {

        ArrayList<Jugador> jugadores = Inicializador.iniciarJugadores();
        this.batalla = new Batalla(jugadores);

        ControladorEscenas controladorEscenas = new ControladorEscenas(stage, this.batalla);
    }
}