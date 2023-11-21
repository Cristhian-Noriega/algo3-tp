package tp1.clases.controlador;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import tp1.clases.modelo.Pokemon;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorCampo {
    private final ObjectProperty<Image> imagenRivalProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> imagenActualProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> fondoClimaProperty = new SimpleObjectProperty<>();
    @FXML public ControladorCartelPokemon cartelPokemonRivalController;
    @FXML public ControladorCartelPokemon cartelPokemonActualController;
    @FXML public ControladorClima cartelClimaController;
    @FXML public ImageView imagenRival;
    @FXML public ImageView imagenActual;
    @FXML public ImageView fondoClima;

    public ControladorCampo() {}

    public void inicializar(Pokemon pokemonActual, Pokemon pokemonRival, String clima) {
        this.cartelPokemonRivalController.inicializar(pokemonRival, JugadorEnum.RIVAL);
        this.cartelPokemonActualController.inicializar(pokemonActual, JugadorEnum.ACTUAL);
        this.cartelClimaController.inicializar(clima);
        this.imagenActual.imageProperty().bind(this.imagenActualProperty);
        this.imagenRival.imageProperty().bind(this.imagenRivalProperty);
        this.fondoClima.imageProperty().bind(this.fondoClimaProperty);
        this.setImagenActualProperty(new Image("file:/home/melina/Escritorio/algo3/TP/algo3-tp/Pokemon/src/main/resources/Imagenes/pokemon/" + pokemonActual.getNombre() + "_espalda.gif"));
        this.setImagenRivalProperty(new Image("file:/home/melina/Escritorio/algo3/TP/algo3-tp/Pokemon/src/main/resources/Imagenes/pokemon/" + pokemonRival.getNombre() + ".gif"));
        this.setFondoClimaProperty(new Image("file:/home/melina/Escritorio/algo3/TP/algo3-tp/Pokemon/src/main/resources/Imagenes/" + clima + ".png"));
    }

    public void setFondoClimaProperty(Image fondoClimaProperty) {
        this.fondoClimaProperty.set(fondoClimaProperty);
    }

    public void setImagenRivalProperty(Image imagenRivalProperty) {
        this.imagenRivalProperty.set(imagenRivalProperty);
    }

    public void setImagenActualProperty(Image imagenActualProperty) {
        this.imagenActualProperty.set(imagenActualProperty);
    }

    public ControladorCartelPokemon getCartelPokemonRivalController() {
        return cartelPokemonRivalController;
    }

    public ControladorCartelPokemon getCartelPokemonActualController() {
        return cartelPokemonActualController;
    }

    public ControladorClima getCartelClimaController() {
        return cartelClimaController;
    }
}
