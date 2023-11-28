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
import tp1.clases.modelo.JugadorEnum;
import tp1.clases.modelo.Pokemon;
import tp1.clases.modelo.Subscriptor;

import java.util.ArrayList;
import java.util.List;

public class ControladorCampo implements Subscriptor {
    private final ObjectProperty<Image> imagenRivalProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> imagenActualProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> pokebolasRivalProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> pokebolasActualProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> fondoClimaProperty = new SimpleObjectProperty<>();
    @FXML public ControladorCartelInfoPokemon cartelPokemonRivalController;
    @FXML public ControladorCartelInfoPokemon cartelPokemonActualController;
    @FXML public ControladorClima cartelClimaController;
    @FXML public ImageView imagenRival;
    @FXML public ImageView imagenActual;
    @FXML public ImageView fondoClima;
    @FXML public ImageView pokebolasJugadorRival;
    @FXML public ImageView efectoRival;
    @FXML public ImageView efectoActual;
    @FXML public ImageView pokebolasJugadorActual;
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
        this.pokebolasJugadorActual.imageProperty().bind(this.pokebolasActualProperty);
        this.pokebolasJugadorRival.imageProperty().bind(this.pokebolasRivalProperty);
        this.fondoClima.imageProperty().bind(this.fondoClimaProperty);

        this.actualizar();
    }

    public void actualizar() {
        this.cartelClimaController.actualizar(this.batalla.getClima().name());
        this.cartelPokemonActualController.actualizar(this.batalla.getJugadorActual().getPokemonActual(), JugadorEnum.ACTUAL);
        this.cartelPokemonRivalController.actualizar(this.batalla.getJugadorSiguiente().getPokemonActual(), JugadorEnum.RIVAL);
        this.setImagenActualProperty(this.batalla.getJugadorActual().getPokemonActual().getNombre());
        this.setImagenRivalProperty(this.batalla.getJugadorSiguiente().getPokemonActual().getNombre());
        this.setPokebolas(JugadorEnum.ACTUAL, this.batalla.getJugadorActual().getListaPokemones());
        this.setPokebolas(JugadorEnum.RIVAL, this.batalla.getJugadorSiguiente().getListaPokemones());
        this.setFondoClimaProperty(this.batalla.getClima().name());
    }

    public void setPokebolas(JugadorEnum jugador, List<Pokemon> pokemones) {
        int cant = 0;
        for (Pokemon pokemon: pokemones) {
            if (!pokemon.estaMuerto()) {
                cant++;
            }
        }

        String ruta = "pokebolas/pokebolas-" + jugador.name().toLowerCase() + "-" + cant + ".png";
        if (jugador == JugadorEnum.ACTUAL) {
            this.pokebolasActualProperty.set(new Image(Archivos.getRutaAbsolutaImagenes(ruta)));
        } else {
            this.pokebolasRivalProperty.set(new Image(Archivos.getRutaAbsolutaImagenes(ruta)));
        }
    }


    public void setFondoClimaProperty(String clima) {
        Image imagen = new Image(Archivos.getRutaAbsolutaImagenes("clima/" + clima + ".png"));
        this.fondoClimaProperty.set(imagen);
    }

    public void setImagenRivalProperty(String pokemon) {
        Image imagen = new Image(Archivos.getRutaAbsolutaImagenes("pokemon/" + pokemon + ".gif"));
        this.imagenRivalProperty.set(imagen);
    }

    public void setImagenActualProperty(String pokemon) {
        Image imagen = new Image(Archivos.getRutaAbsolutaImagenes("pokemon/" + pokemon + "_espalda.gif"));
        this.imagenActualProperty.set(imagen);
    }

    public void aplicarParpadeo(JugadorEnum jugador) {
        if (jugador == JugadorEnum.NINGUNO) {
            return;
        }
        ImageView imagen = this.imagenRival;
        if (jugador == JugadorEnum.ACTUAL) {
            imagen = this.imagenActual;
            this.efectoActual.setImage(new Image(Archivos.getRutaAbsolutaImagenes("brillos.gif")));

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(this.efectoActual.opacityProperty(), 0.0)),
                    new KeyFrame(Duration.seconds(0.2), new KeyValue(this.efectoActual.opacityProperty(), 1.0)),
                    new KeyFrame(Duration.seconds(0.5), new KeyValue(this.efectoActual.opacityProperty(), 0.0))
            );

            timeline.setCycleCount(3);
            timeline.play();
            return;
        }

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(imagen.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(imagen.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(imagen.opacityProperty(), 1.0))
        );

        timeline.setCycleCount(3);
        timeline.play();
    }

    public void animarVida(Pokemon pokemon) {
        this.cartelPokemonRivalController.animarBarraDeVida(this.cartelPokemonRivalController.getPorcentajeBarraDeVida(), (double) pokemon.getVida() / pokemon.getVidaMax());
        this.cartelPokemonRivalController.setVida(pokemon, JugadorEnum.RIVAL);
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
        //System.out.println(Archivos.getRutaAbsolutaImagenes("pokemon/" + pokemon.getNombre() + ".gif"));
        ImageView imagen = new ImageView(Archivos.getRutaAbsolutaImagenes("pokemon/" + pokemon.getNombre() + ".gif"));

        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);
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
