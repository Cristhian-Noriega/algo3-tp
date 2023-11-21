package tp1.clases;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tp1.clases.controlador.ControladorMenuPokemon;
import tp1.clases.modelo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainJavaFX extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Proveedor proveedor = new Proveedor();
        List<ArrayList<Pokemon>> pokemones = proveedor.getPokemones();
        List<List<Item>> items = proveedor.getItems();
        Jugador jugador1 = new Jugador("jugador1", pokemones.get(0), items.get(0));
        Jugador jugador2 = new Jugador("jugador2", pokemones.get(1), items.get(1));
        List<Pokemon> currentPlayerPokemones = jugador1.getListaPokemones();

        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        listaJugadores.add(jugador1);
        listaJugadores.add(jugador2);
        Batalla batalla = new Batalla(listaJugadores);

//        ControladorJuego controlador = new ControladorJuego(batalla);

//        while (!controlador.getJuegoTerminado()) {
//            controlador.jugarTurno();
//        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("items-view.fxml"));
        BorderPane existingBorderPane = fxmlLoader.load();
//        Scene scene = new Scene(fxmlLoader.load(), 600, 440);

        ControladorMenuPokemon controller = fxmlLoader.getController();

        controller.setPokemones(FXCollections.observableList(currentPlayerPokemones));

        StackPane stackPane = new StackPane();

        Scene scene = new Scene(stackPane, 600, 440);
        stage.setTitle("Pokemon");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String []args) {
        launch();
    }
}