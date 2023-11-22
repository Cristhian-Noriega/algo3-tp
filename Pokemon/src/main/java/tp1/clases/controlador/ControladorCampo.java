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
import tp1.clases.modelo.AdministradorDeClima;
import tp1.clases.modelo.Batalla;
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
    private Batalla batalla;

    public ControladorCampo() {}

    public void inicializar(Batalla batalla) {
        this.batalla = batalla;

        this.cartelPokemonActualController.inicializar(this.batalla.getJugadorActual().getPokemonActual(), JugadorEnum.ACTUAL);
        this.cartelPokemonRivalController.inicializar(this.batalla.getJugadorSiguiente().getPokemonActual(), JugadorEnum.RIVAL);
        this.cartelClimaController.inicializar(this.batalla.getClima().name());

        this.imagenActual.imageProperty().bind(this.imagenActualProperty);
        this.imagenRival.imageProperty().bind(this.imagenRivalProperty);
        this.fondoClima.imageProperty().bind(this.fondoClimaProperty);

        this.setImagenActualProperty(this.batalla.getJugadorActual().getPokemonActual().getNombre());
        this.setImagenRivalProperty(this.batalla.getJugadorSiguiente().getPokemonActual().getNombre());
        this.setFondoClimaProperty(this.batalla.getClima().name());
    }

    public void actualizar(Batalla batalla) {
        this.batalla = batalla;
        this.cartelClimaController.actualizar(batalla.getClima().name());
        this.cartelPokemonActualController.actualizar(batalla.getJugadorActual().getPokemonActual(), JugadorEnum.ACTUAL);
        this.cartelPokemonRivalController.actualizar(batalla.getJugadorSiguiente().getPokemonActual(), JugadorEnum.RIVAL);
        this.setImagenActualProperty(this.batalla.getJugadorActual().getPokemonActual().getNombre());
        this.setImagenRivalProperty(this.batalla.getJugadorSiguiente().getPokemonActual().getNombre());
        this.setFondoClimaProperty(this.batalla.getClima().name());
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

    public void animarVida(double cantidad) {
        this.cartelPokemonRivalController.bajarVida(cantidad);
    }
}
