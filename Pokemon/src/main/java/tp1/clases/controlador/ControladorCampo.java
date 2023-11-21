package tp1.clases.controlador;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
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
    @FXML public Pane campoPane;

    public ControladorCampo() {}

    public void inicializar(Pokemon pokemonActual, Pokemon pokemonRival, String clima) {
        this.cartelPokemonRivalController.inicializar(pokemonRival, JugadorEnum.RIVAL);
        this.cartelPokemonActualController.inicializar(pokemonActual, JugadorEnum.ACTUAL);
        this.cartelClimaController.inicializar(clima);
        this.imagenActual.imageProperty().bind(this.imagenActualProperty);
        this.imagenRival.imageProperty().bind(this.imagenRivalProperty);
        this.fondoClima.imageProperty().bind(this.fondoClimaProperty);
        this.setImagenActualProperty(pokemonActual.getNombre());
        this.setImagenRivalProperty(pokemonRival.getNombre());
        this.setFondoClimaProperty(clima);
    }

    public void actualizarCampo(Pokemon pokemonActual, Pokemon pokemonRival, String clima) {
        this.cartelClimaController.actualizar(clima);
        this.cartelPokemonActualController.actualizar(pokemonActual);
        this.cartelPokemonRivalController.actualizar(pokemonRival);
        this.setFondoClimaProperty(clima);
    }

    public void setFondoClimaProperty(String clima) {
        Image imagen = new Image("file:/home/melina/Escritorio/algo3/TP/algo3-tp/Pokemon/src/main/resources/Imagenes/" + clima + ".png");
        this.fondoClimaProperty.set(imagen);
    }

    public void setImagenRivalProperty(String pokemon) {
        Image imagen = new Image("file:/home/melina/Escritorio/algo3/TP/algo3-tp/Pokemon/src/main/resources/Imagenes/pokemon/" + pokemon + ".gif");
        this.imagenRivalProperty.set(imagen);
    }

    public void setImagenActualProperty(String pokemon) {
        Image imagen = new Image("file:/home/melina/Escritorio/algo3/TP/algo3-tp/Pokemon/src/main/resources/Imagenes/pokemon/" + pokemon + "_espalda.gif");
        this.imagenActualProperty.set(imagen);
    }

    public void aplicarParpadeo(JugadorEnum jugador) {
        ImageView imagen = this.imagenRival;
        if (jugador == JugadorEnum.ACTUAL) {
            imagen = this.imagenActual;
        }

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(imagen.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(imagen.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(imagen.opacityProperty(), 1.0))
        );

        timeline.setCycleCount(3);
        timeline.play();
    }

    public Pane getCampoPane() {
        return campoPane;
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
