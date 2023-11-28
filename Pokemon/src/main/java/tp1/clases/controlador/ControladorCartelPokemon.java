package tp1.clases.controlador;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import tp1.clases.eventos.*;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Estado;
import tp1.clases.modelo.Item;
import tp1.clases.modelo.Pokemon;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladorCartelPokemon {
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
    @FXML
    public Pane imagenesEstados;
    @FXML
    public HBox circulosEstados;
    private Batalla batalla;
    private Pokemon pokemon;
    private int escenaAnterior;
    private int escenaActual;
    private Item item;
    private final List<ObjectProperty<Image>> imagenesEstadosProperty = List.of(new SimpleObjectProperty<>(), new SimpleObjectProperty<>(), new SimpleObjectProperty<>(), new SimpleObjectProperty<>());
    
    public void inicializar(Batalla batalla,  Pokemon pokemon, int escenaAnterior){
        this.batalla = batalla;
        this.pokemon = pokemon;
        this.escenaAnterior = this.escenaActual;
        this.escenaActual = escenaAnterior;

        contenedorCajaPokemon.setOnMouseEntered(this::handleMouseEntered);
        contenedorCajaPokemon.setOnMouseExited(this::handleMouseExited);
        contenedorCajaPokemon.setOnMouseClicked(this::handleMouseOnClick);

        int i = 0;
        for (ObjectProperty<Image> objectProperty: imagenesEstadosProperty) {
            ImageView imagen = (ImageView) this.imagenesEstados.getChildren().get(i);
            imagen.imageProperty().bind(objectProperty);
            i++;
        }
        this.setEstados();
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

        contenedorCajaPokemon.setStyle("-fx-border-color:#e77a00; -fx-border-radius: 3%; -fx-border-width: 5;");
    }

    private void handleMouseExited(MouseEvent event) {
        contenedorCajaPokemon.setStyle("-fx-border-color:black; -fx-border-radius: 3%; -fx-border-width: 3;");
    }

    public void setEstados(){
        int j = 0;
        for (Node circle: this.circulosEstados.getChildren()) {
            circle.setOpacity(0);
            this.imagenesEstadosProperty.get(j).set(new Image(Archivos.getRutaAbsolutaImagenes("default.png")));
            j++;
        }

        int i = 0;
        for (Estado estado: pokemon.getEstados()) {
            if (estado == Estado.NORMAL) {
                break;
            }
            //System.out.println("Agrego estado " + estado.name());
            this.circulosEstados.getChildren().get(i).setOpacity(100);
            this.imagenesEstadosProperty.get(i).set(new Image(Archivos.getRutaAbsolutaImagenes("estados/" + estado.name() + ".png")));
            i++;
        }
    }

    @FXML
    private void handleMouseOnClick(MouseEvent event){
        this.contenedorCajaPokemon.fireEvent(new PokemonSeleccionadoEvent(this.pokemon));
        this.contenedorCajaPokemon.fireEvent(new CambioDeEscenaEvent(Escena.PANTALLA_EFECTO.ordinal()));
    }

    public ControladorCartelPokemon getControlador() {
        return this;
    }
}