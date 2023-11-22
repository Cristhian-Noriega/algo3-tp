package tp1.clases;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import tp1.clases.controlador.ControladorJugar;
import tp1.clases.controlador.ControladorMenuItems;
import tp1.clases.modelo.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainJavaFX extends Application {

    private static final String RUTA_SONIDO_MENU = "file: /home/cristhian/algoritmos3/algo3-tp/Pokemon/src/main/resources/musica-inicio.mp3";

    @Override
    public void start(Stage stage) throws IOException {

        Proveedor proveedor = new Proveedor();
        List<ArrayList<Pokemon>> pokemones = proveedor.getPokemones();
        List<List<Item>> items = proveedor.getItems();
        Jugador jugador1 = new Jugador("jugador1", pokemones.get(0), items.get(0));
        Jugador jugador2 = new Jugador("jugador2", pokemones.get(1), items.get(1));
        List<Item> itemsJugadorActual = jugador1.getListaItems();

        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        listaJugadores.add(jugador1);
        listaJugadores.add(jugador2);
        Batalla batalla = new Batalla(listaJugadores);


        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("inicio-view.fxml"));
        StackPane root = loader.load();
        // Pasar el Stage al controlador ControladorJugar
        ControladorJugar controladorJugar = loader.getController();
        controladorJugar.setStage(stage);
        controladorJugar.setBatalla(batalla);

        Scene scene = new Scene(root, 640, 500);
        stage.setTitle("Pokemon");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String []args) {
        launch();
    }
}
