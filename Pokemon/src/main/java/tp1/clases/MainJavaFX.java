package tp1.clases;

import javafx.application.Application;
import javafx.stage.Stage;
import tp1.clases.controlador.ControladorEscenas;
import tp1.clases.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class MainJavaFX extends Application {
    private Batalla batalla;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Proveedor proveedor = new Proveedor();
        List<ArrayList<Pokemon>> pokemones = proveedor.getPokemones();
        List<List<Item>> items = proveedor.getItems();
        Jugador jugador1 = new Jugador("jugador1", pokemones.get(0), items.get(0));
        Jugador jugador2 = new Jugador("jugador2", pokemones.get(1), items.get(1));

        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        listaJugadores.add(jugador1);
        listaJugadores.add(jugador2);
        this.batalla = new Batalla(listaJugadores);

        ControladorEscenas controladorEscenas = new ControladorEscenas(stage, this.batalla);
    }
}
