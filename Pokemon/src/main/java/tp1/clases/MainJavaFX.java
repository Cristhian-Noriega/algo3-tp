package tp1.clases;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp1.clases.controlador.ControladorMenuPokemon;
import tp1.clases.modelo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Proveedor proveedor = new Proveedor();
            List<ArrayList<Pokemon>> pokemones = proveedor.getPokemones();
            List<List<Item>> items = proveedor.getItems();
            Jugador jugador1 = new Jugador("jugador1", pokemones.get(0), items.get(0));
            Jugador jugador2 = new Jugador("jugador2", pokemones.get(1), items.get(1));
            List<Item> currentPlayerItems = jugador1.getListaItems();

            ArrayList<Jugador> listaJugadores = new ArrayList<>();
            listaJugadores.add(jugador1);
            listaJugadores.add(jugador2);
            Batalla batalla = new Batalla(listaJugadores);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("pokemones-view.fxml"));
            Parent root = fxmlLoader.load();

            ControladorMenuPokemon controladorMenuPokemon = fxmlLoader.getController();
            if (controladorMenuPokemon != null) {
                controladorMenuPokemon.setJugadorActual(batalla.getJugadorActual());
                controladorMenuPokemon.setPokemones();
            } else {
                System.out.println("El controlador de menu poke es null");
            }

            Scene scene = new Scene(root, 709.0, 707.0);
            stage.setTitle("Pokemon");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
