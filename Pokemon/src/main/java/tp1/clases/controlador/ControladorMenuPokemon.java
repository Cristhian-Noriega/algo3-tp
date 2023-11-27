package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Jugador;
import tp1.clases.modelo.Pokemon;
import tp1.clases.modelo.Subscriptor;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class ControladorMenuPokemon implements Initializable, Controlador, Subscriptor {
    @FXML
    public VBox contenedorPokemonActual;
    @FXML
    public HBox contenedorBotonVolver;
    @FXML
    public StackPane contenedorPrincipal;
    @FXML
    public VBox contenedorPokemon;
    private Batalla batalla;
    private Boolean focusAplicado;
    private int escenaAnterior; // HABRIA Q VER DONDE LA SETTEO

    @Override
    public void inicializar(Batalla batalla) {
        this.batalla = batalla;
        this.batalla.getAdministradorTurnos().agregarSubscriptor(this);
        List<Pokemon> pokemones = this.batalla.getJugadorActual().getListaPokemones();
        this.setPokemones(pokemones);

        if (escenaAnterior == Escena.POKEMON_MUERTO.ordinal()){ // FUNCIONA??
            contenedorBotonVolver.setStyle("-fx-border-color: black; -fx-background-color: grey; -fx-border-radius: 3%; -fx-border-width: 5;");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        contenedorBotonVolver.setOnMouseEntered(this::handleMouseEntered);
        contenedorBotonVolver.setOnMouseExited(this::handleMouseExited);
        contenedorBotonVolver.setOnMouseClicked(this::handleMouseOnClick);
    }

    public void setEscenaAnterior(int escenaAnterior){
        this.escenaAnterior = escenaAnterior;
    }

    public void actualizar() {
        this.setPokemones(this.batalla.getPokemonesJugadorActual());
    }

    public void setPokemones(List<Pokemon> pokemones){
        this.focusAplicado = false;
        for (Pokemon pokemon : pokemones) {
            int i = 0;
            try {
                if (pokemon == this.batalla.getJugadorActual().getPokemonActual()){
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
                    if (!focusAplicado) { // FALTA Q SE SQUE APENAS SE PARA EN OTRO
                        controladorCartel.handleMouseEntered(null);
                        focusAplicado = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ControladorMenuPokemon getControlador() {
        return this;
    }

    public void handleMouseEntered(MouseEvent event) {
        if (escenaAnterior == Escena.POKEMON_MUERTO.ordinal()){
            contenedorBotonVolver.setStyle("-fx-border-color:#e77a00;-fx-background-color: #bb50bb; -fx-border-radius: 3%; -fx-border-width: 5;");
        }
    }

    public void handleMouseExited(MouseEvent event) {
        if (escenaAnterior == Escena.POKEMON_MUERTO.ordinal()){
            contenedorBotonVolver.setStyle("-fx-border-color: #721572; -fx-background-color: #bb50bb; -fx-border-radius: 3%; -fx-border-width: 5;");
        }
    }

    @FXML
    private void handleMouseOnClick(MouseEvent event){
       if (escenaAnterior != Escena.POKEMON_MUERTO.ordinal()){
           this.contenedorPrincipal.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
       }
    }

    @Override
    public void Update() {
        contenedorPokemon.getChildren().clear();
        contenedorPokemonActual.getChildren().clear();
        this.actualizar();
    }
}