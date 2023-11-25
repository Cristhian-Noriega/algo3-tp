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
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException, CloneNotSupportedException {
        try {
            ArrayList<Jugador> jugadores = Inicializador.iniciarJugadores();
            this.batalla = new Batalla(jugadores);
        } catch (IOException e){
            System.out.println("HUBO UN ERROR");
            throw e;
        }

        ControladorEscenas controladorEscenas = new ControladorEscenas(stage, this.batalla);
    }
}