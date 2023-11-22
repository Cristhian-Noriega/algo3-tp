package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tp1.clases.modelo.Jugador;
import tp1.clases.modelo.Pokemon;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class ControladorMenuPokemon implements Initializable {

    public VBox contenedorPokemonActual;
    private  Jugador jugadorActual;
    @FXML
    public VBox contenedorPokemon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Puedes inicializar algo aquí si es necesario
    }

    public void setJugadorActual(Jugador jugadorActual){
        this.jugadorActual = jugadorActual;
    }

    public void setPokemones(){
        List<Pokemon> pokemones = jugadorActual.getListaPokemones();
        for (Pokemon pokemon : pokemones) {
            try {
                if (pokemon == jugadorActual.getPokemonActual()){
                    // Cargar el FXML para el Pokemon actual
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("cartelPokemonActual.fxml"));
                    Pane cartelPokemonActual = loader.load();

                    // Obtener el controlador del FXML para establecer la información del Pokemon ACT
                    ControladorCartelPokemonActual controladorCartelActual = loader.getController();
                    controladorCartelActual.setDatosPokemonActual(pokemon);

                    // Agregar el HBox al VBox
                    contenedorPokemonActual.getChildren().add(cartelPokemonActual);

                }else{
                    // Cargar el FXML para cada Pokémon
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("cartelPokemon.fxml"));
                    Pane cartelPokemon = loader.load();

                    // Obtener el controlador del FXML para establecer la información del Pokémon
                    ControladorCartelPokemon controladorCartel = loader.getController();
                    controladorCartel.setDatosPokemon(pokemon);

                    // Agregar el HBox al VBox
                    contenedorPokemon.getChildren().add(cartelPokemon);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ControladorMenuPokemon getControlador() {
        return this;
    }
}

