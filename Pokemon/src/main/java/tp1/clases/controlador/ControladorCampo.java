package tp1.clases.controlador;

import javafx.animation.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.JugadorEnum;
import tp1.clases.modelo.Pokemon;
import tp1.clases.modelo.SubscriptorTurno;

import java.util.List;
import java.util.Objects;

public class ControladorCampo implements SubscriptorTurno {
    private final ObjectProperty<Image> imagenRivalProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> imagenActualProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> pokebolasRivalProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> pokebolasActualProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Image> fondoClimaProperty = new SimpleObjectProperty<>();
    @FXML protected ControladorCentroDeEstadisticas cartelPokemonRivalController;
    @FXML protected ControladorCentroDeEstadisticas cartelPokemonActualController;
    @FXML protected ControladorClima cartelClimaController;
    @FXML private ImageView imagenRival;
    @FXML private ImageView imagenActual;
    @FXML private ImageView fondoClima;
    @FXML private ImageView pokebolasJugadorRival;
    @FXML private ImageView efectoRival;
    @FXML private ImageView efectoActual;
    @FXML private ImageView pokebolasJugadorActual;
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

    public void aplicarParpadeo(JugadorEnum jugador, String tipo) {
        if (jugador == JugadorEnum.NINGUNO) {
            return;
        }

        if (jugador == JugadorEnum.ACTUAL) {
            this.efectoActual.setImage(new Image(Archivos.getRutaAbsolutaImagenes("brillitos.gif")));
            this.efecto(this.efectoActual);
            return;
        }

        this.parpadeo(this.imagenRival);
        this.efectoRival.setImage(new Image(Archivos.getRutaAbsolutaImagenes("default.png")));
    }

    public void efecto(ImageView imagen) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(imagen.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(imagen.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(imagen.opacityProperty(), 0.0))
        );

        timeline.setCycleCount(3);
        timeline.play();
    }

    public void parpadeo(ImageView imagen) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(imagen.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(imagen.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(imagen.opacityProperty(), 1.0))
        );

        timeline.setCycleCount(3);
        timeline.play();
    }


    public void animarVida(Pokemon pokemon, JugadorEnum jugador) {
        ControladorCentroDeEstadisticas controlador = this.cartelPokemonRivalController;
        if (jugador == JugadorEnum.ACTUAL) {
            controlador = this.cartelPokemonActualController;
        }
        controlador.animarBarraDeVida(controlador.getPorcentajeBarraDeVida(), (double) pokemon.getVida() / pokemon.getVidaMax());
        controlador.setVida(pokemon, jugador);
    }


    public void aplicarIntercambioDePokemon(){
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

    public void aplicarEfectoItem(){
        Image gifImagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/spark.gif")));

        ImageView imagen = this.efectoActual;

        Image imagenActual = this.efectoActual.getImage();

        imagen.setImage(gifImagen);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));

        pause.setOnFinished(event -> {
            imagen.setImage(imagenActual);
        });
        pause.play();
    }

    public void aplicarDesaparicionPokemonMuerto(JugadorEnum jugador){
        ImageView imagen = this.imagenActual;
        if (jugador == JugadorEnum.RIVAL) {
            imagen = this.imagenRival;
        }

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(imagen.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(2), new KeyValue(imagen.opacityProperty(), 0.0))
        );

        timeline.setOnFinished(event -> {
            this.aplicarAparicionPokemonNuevo(jugador);
        });

        timeline.play();
    }

    public void aplicarAparicionPokemonNuevo(JugadorEnum jugador){
        this.batalla.cambiarPokemonMuertoJugadorSiguiente();
        this.actualizar();
        ImageView imagen = this.imagenActual;
        if (jugador == JugadorEnum.RIVAL) {
            imagen = this.imagenRival;
        }

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(imagen.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(2), new KeyValue(imagen.opacityProperty(), 1.0))
        );

        timeline.play();
    }

    @Override
    public void Update() {
        this.actualizar();
    }
}
