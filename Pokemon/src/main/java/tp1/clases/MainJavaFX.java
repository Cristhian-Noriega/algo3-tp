package tp1.clases;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp1.clases.controlador.ControladorMenuPrincipal;
import tp1.clases.modelo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainJavaFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Proveedor proveedor = new Proveedor();
        List<ArrayList<Pokemon>> pokemones = proveedor.getPokemones();
        List<List<Item>> items = proveedor.getItems();
        Jugador jugador1 = new Jugador("jugador1", pokemones.get(0), items.get(0));
        Jugador jugador2 = new Jugador("jugador2", pokemones.get(1), items.get(1));

        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        listaJugadores.add(jugador1);
        listaJugadores.add(jugador2);
        Batalla batalla = new Batalla(listaJugadores);

        jugador2.getPokemonActual().setEstado(Estado.CONFUNDIDO);
        jugador2.getPokemonActual().setEstado(Estado.ENVENENADO);
        jugador2.getPokemonActual().setEstado(Estado.DORMIDO);
        jugador2.getPokemonActual().setEstado(Estado.PARALIZADO);
        jugador2.getPokemonActual().modificarVida(-50);
        jugador1.getPokemonActual().modificarVida(-90);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("menu-principal.fxml"));
        Parent root = loader.load();
        ControladorMenuPrincipal controladorMenuPrincipal = loader.getController();
        if (controladorMenuPrincipal != null) {
            controladorMenuPrincipal.inicializar(jugador2.getPokemonActual(), jugador1.getPokemonActual(), "SOLEADO");
        } else {
            System.out.println("el controlador de menu principal es null");
        }

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Pokemon");
        stage.setScene(scene);
        stage.show();
    }
}
