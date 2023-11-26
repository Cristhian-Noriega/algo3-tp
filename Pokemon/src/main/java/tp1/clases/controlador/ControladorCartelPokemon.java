package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.eventos.HabilidadSeleccionadaEvent;
import tp1.clases.eventos.PokemonSeleccionadoEvent;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Item;
import tp1.clases.modelo.Pokemon;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorCartelPokemon implements Initializable {
    @FXML
    public Label labelNombre;
    @FXML
    public Label labelNivel;
    @FXML
    public javafx.scene.control.ProgressBar barraVida;
    @FXML
    public Label labelVida;
    @FXML
    public ImageView imagenPokemon;
    @FXML
    public HBox contenedorCajaPokemon;
    private Batalla batalla;
    private Boolean esPokemonActual;
    private Pokemon pokemon;
    private Escena escenaAnterior;
    private Item item;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contenedorCajaPokemon.setOnMouseEntered(this::handleMouseEntered);
        contenedorCajaPokemon.setOnMouseExited(this::handleMouseExited);
        contenedorCajaPokemon.setOnMouseClicked(this::handleMouseOnClick);
    }

    public void inicializar(Batalla batalla, Boolean esPokemonActual, Pokemon pokemon, Escena escenaAnterior){
        this.batalla = batalla;
        this.esPokemonActual = esPokemonActual;
        this.pokemon = pokemon;
        this.escenaAnterior = escenaAnterior;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public void setDatosPokemon() {
        if (this.pokemon!=null){
            this.labelNombre.setText(this.pokemon.getNombre());
            this.labelNivel.setText("Nvl." + this.pokemon.getNivel());
            this.labelVida.setText(this.pokemon.getVida() + "/" + this.pokemon.getVidaMax());
            this.barraVida.setProgress((double) this.pokemon.getVida() / this.pokemon.getVidaMax());
            this.setImagenPokemonOpcion(this.pokemon.getNombre());
        }
    }

    private void setImagenPokemonOpcion(String pokemon){
        Image imagen = new Image(Archivos.getRutaAbsoluta("pokemon/" + pokemon + ".gif"));
        this.imagenPokemon.setImage(imagen);
    }

    private void handleMouseEntered(MouseEvent event) {
        contenedorCajaPokemon.setStyle("-fx-border-color:#e77a00; -fx-border-radius: 3%; -fx-border-width: 5;");
    }

    private void handleMouseExited(MouseEvent event) {
        contenedorCajaPokemon.setStyle("-fx-border-color:black; -fx-border-radius: 3%; -fx-border-width: 3;");
    }

    @FXML
    private void handleMouseOnClick(MouseEvent event){
        if (this.escenaAnterior == Escena.MENU_ITEMS) {
            this.batalla.usarItem(this.item, this.pokemon);
            System.out.println("estoy aca");
        }else if (!esPokemonActual){
            this.batalla.cambiarPokemon(this.pokemon);
            System.out.println("o en esta");
        }
        this.contenedorCajaPokemon.fireEvent(new PokemonSeleccionadoEvent(this.pokemon));
        CambioDeEscenaEvent evento = new CambioDeEscenaEvent(Escena.PANTALLA_SELECCION_POKEMON.ordinal());
        evento.setPokemon(this.pokemon);
        this.contenedorCajaPokemon.fireEvent(evento);
    }

    public ControladorCartelPokemon getControlador() {
        return this;
    }
}