package tp1.clases.controlador;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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

import java.util.List;

public class ControladorCartelPokemon {
    @FXML private Label labelNombre;
    @FXML private Label labelNivel;
    @FXML private ProgressBar barraVida;
    @FXML private Label labelVida;
    @FXML private ImageView imagenPokemon;
    @FXML private HBox contenedorCajaPokemon;
    @FXML private Pane imagenesEstados;
    @FXML private HBox circulosEstados;

    private Pokemon pokemon;
    private final List<ObjectProperty<Image>> imagenesEstadosProperty = List.of(new SimpleObjectProperty<>(), new SimpleObjectProperty<>(), new SimpleObjectProperty<>(), new SimpleObjectProperty<>());
    
    public void inicializar(Pokemon pokemon){
        this.pokemon = pokemon;

        contenedorCajaPokemon.setOnMouseEntered(this::handleMouseEntered);
        contenedorCajaPokemon.setOnMouseExited(this::handleMouseExited);
        contenedorCajaPokemon.setOnMouseClicked(this::handleMouseOnClick);
        //contenedorCajaPokemon.setOnMouseClicked(this::handleOnDragDetected);

        int i = 0;
        for (ObjectProperty<Image> objectProperty: imagenesEstadosProperty) {
            ImageView imagen = (ImageView) this.imagenesEstados.getChildren().get(i);
            imagen.imageProperty().bind(objectProperty);
            i++;
        }
        this.setEstados();
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

    public void handleMouseEntered(MouseEvent event) {
        contenedorCajaPokemon.setStyle("-fx-border-color:#e77a00; -fx-border-radius: 3%; -fx-border-width: 5;");
    }

    public void handleMouseExited(MouseEvent event) {
        contenedorCajaPokemon.setStyle("-fx-border-color:black; -fx-border-radius: 3%; -fx-border-width: 3;");
    }

    //public void handleOnDragDetected(MouseEvent event) {
    //    contenedorCajaPokemon.setStyle("-fx-border-color:#e77a00;-fx-background-color: #bb50bb; -fx-border-radius: 3%; -fx-border-width: 5;");
   // }

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