package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Jugador;
import tp1.clases.modelo.Pokemon;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class ControladorMenuPokemon implements Initializable, Controlador {
    @FXML
    public VBox contenedorPokemonActual;
    @FXML
    public HBox contenedorBoton;
    private  Jugador jugadorActual;
    @FXML
    public VBox contenedorPokemon;
    private Batalla batalla;

    private Escena escenaAnterior;

    @Override
    public void inicializar(Batalla batalla) { // PODRIAMOS MANDARLE A TODOS LOS INICIALIZAR LA ESCENA ANTERIOR?
        this.batalla = batalla;
        this.jugadorActual = batalla.getJugadorActual();
        this.setPokemones();

        if (escenaAnterior == Escena.POKEMON_MUERTO){
            contenedorBoton.setStyle("-fx-border-color: black; -fx-background-color: grey; -fx-border-radius: 3%; -fx-border-width: 5;");
        }
    }

    public void setEscenaAnterior(Escena escenaAnterior){
        this.escenaAnterior = escenaAnterior;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        contenedorBoton.setOnMouseEntered(this::handleMouseEntered);
        contenedorBoton.setOnMouseExited(this::handleMouseExited);
    }

    public void setPokemones(){
        List<Pokemon> pokemones = jugadorActual.getListaPokemones();
        for (Pokemon pokemon : pokemones) {
            try {
                if (pokemon == jugadorActual.getPokemonActual()){
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("cartel-opcion-pokemon-actual.fxml"));
                    Pane cartelPokemonActual = loader.load();

                    ControladorCartelPokemon controladorCartelActual = loader.getController();
                    controladorCartelActual.inicializar(batalla, true, pokemon, escenaAnterior);
                    controladorCartelActual.setDatosPokemon();

                    contenedorPokemonActual.getChildren().add(cartelPokemonActual);

                }else{
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("cartel-opcion-pokemon.fxml"));
                    Pane cartelPokemon = loader.load();

                    ControladorCartelPokemon controladorCartel = loader.getController();
                    controladorCartel.inicializar(batalla, false, pokemon, escenaAnterior);
                    controladorCartel.setDatosPokemon();

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

    private void handleMouseEntered(MouseEvent event) {
        contenedorBoton.setStyle("-fx-border-color:#e77a00;-fx-background-color: #bb50bb; -fx-border-radius: 3%; -fx-border-width: 5;");
    }

    private void handleMouseExited(MouseEvent event) {
        contenedorBoton.setStyle("-fx-border-color: #721572; -fx-background-color: #bb50bb; -fx-border-radius: 3%; -fx-border-width: 5;");
    }

    @FXML
    private void handleMouseOnClick(MouseEvent event){
       if (escenaAnterior != Escena.POKEMON_MUERTO){

       }
    }
}