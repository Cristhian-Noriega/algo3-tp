package tp1.clases.controlador;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Pokemon;
import tp1.clases.modelo.Subscriptor;

public class ControladorCampo implements Subscriptor {
    private final ObjectProperty<Image> imagenRivalProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> imagenActualProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> fondoClimaProperty = new SimpleObjectProperty<>();
    @FXML public ControladorCartelInfoPokemon cartelPokemonRivalController;
    @FXML public ControladorCartelInfoPokemon cartelPokemonActualController;
    @FXML public ControladorClima cartelClimaController;
    @FXML public ImageView imagenRival;
    @FXML public ImageView imagenActual;
    @FXML public ImageView fondoClima;
    @FXML public Pane campoPane;
    private Batalla batalla;

    public ControladorCampo() {}

    public void inicializar(Batalla batalla) {
        this.batalla = batalla;
        this.batalla.getAdministradorTurnos().agregarSubscriptor(this);
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

    public void actualizar() {
        this.cartelClimaController.actualizar(this.batalla.getClima().name());
        this.cartelPokemonActualController.actualizar(this.batalla.getJugadorActual().getPokemonActual(), JugadorEnum.ACTUAL);
        this.cartelPokemonRivalController.actualizar(this.batalla.getJugadorSiguiente().getPokemonActual(), JugadorEnum.RIVAL);
        this.setImagenActualProperty(this.batalla.getJugadorActual().getPokemonActual().getNombre());
        this.setImagenRivalProperty(this.batalla.getJugadorSiguiente().getPokemonActual().getNombre());
        this.setFondoClimaProperty(this.batalla.getClima().name());
    }

    public void setFondoClimaProperty(String clima) {
        Image imagen = new Image(Archivos.getRutaAbsoluta(clima + ".png"));
        this.fondoClimaProperty.set(imagen);
    }

    public void setImagenRivalProperty(String pokemon) {
        Image imagen = new Image(Archivos.getRutaAbsoluta("pokemon/" + pokemon + ".gif"));
        this.imagenRivalProperty.set(imagen);
    }

    public void setImagenActualProperty(String pokemon) {
        Image imagen = new Image(Archivos.getRutaAbsoluta("pokemon/" + pokemon + "_espalda.gif"));
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

    public void animarVida(double cantidad) {
        this.cartelPokemonRivalController.animarBarraDeVida(cantidad);
    }

    public void aplicarCambioPokemon(){
        ImageView imagen = this.imagenActual;

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), imagen);
        rotateTransition.setByAngle(360);

        rotateTransition.setOnFinished(event -> {
                    this.actualizar();

            imagenActual.setRotate(0);
        });

        rotateTransition.play();
    }

    public void aplicarItem(Pokemon pokemon) {
        ImageView imagen = new ImageView(Archivos.getRutaAbsoluta("pokemon/" + pokemon + ".gif"));
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);

        // Aplicar el efecto de luz al nodo ImageView
        Lighting lighting = new Lighting();
        lighting.setLight(light);

        Blend blend = new Blend();
        blend.setMode(BlendMode.ADD);
        blend.setTopInput(lighting);

        imagen.setEffect(blend);
    }

    @Override
    public void Update() {
        this.actualizar();
    }
}
