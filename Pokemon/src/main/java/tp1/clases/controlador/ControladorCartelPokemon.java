package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import tp1.clases.eventos.AplicarItemEvent;
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
    @FXML
    public Pane contenedorPokemon;
    private Batalla batalla;
    private Boolean esPokemonActual;
    private Pokemon pokemon;
    private int escenaAnterior;
    private Item item;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contenedorCajaPokemon.setOnMouseEntered(this::handleMouseEntered);
        contenedorCajaPokemon.setOnMouseExited(this::handleMouseExited);
        contenedorCajaPokemon.setOnMouseClicked(this::handleMouseOnClick);
    }

    public void inicializar(Batalla batalla, Boolean esPokemonActual, Pokemon pokemon, int escenaAnterior){
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

        Image imagen = new Image(Archivos.getRutaAbsolutaImagenes("pokemon/" + pokemon + ".gif"));

        this.imagenPokemon.setImage(imagen);
    }

    void handleMouseEntered(MouseEvent event) {
        if (!esPokemonActual){
            contenedorCajaPokemon.setStyle("-fx-border-color:#e77a00; -fx-border-radius: 3%; -fx-border-width: 5;");
        }
    }

    private void handleMouseExited(MouseEvent event) {
        if (!esPokemonActual) {
            contenedorCajaPokemon.setStyle("-fx-border-color:black; -fx-border-radius: 3%; -fx-border-width: 3;");
        }
    }

    @FXML
    private void handleMouseOnClick(MouseEvent event){
        if (!esPokemonActual) {
            if (this.escenaAnterior == Escena.MENU_ITEMS.ordinal() - 1) {
                this.contenedorPokemon.fireEvent(new AplicarItemEvent(this.pokemon, this.item, batalla));
                // no seria otro evento?
                // meli: los eventos son inmutables, no se puede hacer set

                CambioDeEscenaEvent evento = new CambioDeEscenaEvent(Escena.PANTALLA_SELECCION_POKEMON.ordinal());
                evento.setItem(this.item);
                evento.setPokemon(this.pokemon);
                this.contenedorPokemon.fireEvent(evento);

            } else if (!esPokemonActual) {
                this.contenedorPokemon.fireEvent(new PokemonSeleccionadoEvent(this.pokemon));
                CambioDeEscenaEvent evento = new CambioDeEscenaEvent(Escena.PANTALLA_SELECCION_POKEMON.ordinal());
                evento.setPokemon(this.pokemon);
                this.contenedorPokemon.fireEvent(evento);
            }
        }
    }

    public ControladorCartelPokemon getControlador() {
        return this;
    }
}