package tp1.clases.controlador;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.util.Duration;
import tp1.clases.modelo.Estado;
import tp1.clases.modelo.Pokemon;

import java.util.List;

public class ControladorCartelInfoPokemon {
    private String nombre = "Pokemon";
    private String nivel = "Nvl.0";
    private String cantVida = "00/00";

    private StringProperty nombreProperty = new SimpleStringProperty(nombre);
    private StringProperty nivelProperty = new SimpleStringProperty(nivel);
    private StringProperty cantVidaProperty = new SimpleStringProperty(cantVida);
    private final List<ObjectProperty<Image>> imagenesEstadosProperty = List.of(new SimpleObjectProperty<>(), new SimpleObjectProperty<>(), new SimpleObjectProperty<>(), new SimpleObjectProperty<>());

    @FXML public Label labelNombre;
    @FXML public Label labelNivel;
    @FXML public Label labelCantVida;
    @FXML public ProgressBar barraVida;
    @FXML public HBox circulos;
    @FXML public Pane imagenesEstados;

    public ControladorCartelInfoPokemon() {}

    public void inicializar(Pokemon pokemon, JugadorEnum jugador) {
        this.labelNombre.textProperty().bind(this.nombreProperty);
        this.labelNivel.textProperty().bind(this.nivelProperty);

        if (jugador == JugadorEnum.ACTUAL){
            this.labelCantVida.textProperty().bind(this.cantVidaProperty);
            this.setCantVidaProperty(pokemon.getVida() + "/" + pokemon.getVidaMax());
        } else {
            this.barraVida.setScaleX(1.5);
            this.barraVida.setTranslateX(32);
        }

        this.barraVida.setProgress((double) pokemon.getVida() / pokemon.getVidaMax());
        this.setNombreProperty(pokemon.getNombre());
        this.setNivelProperty(("Nvl." + pokemon.getNivel()));

        int i = 0;
        for (ObjectProperty<Image> objectProperty: imagenesEstadosProperty) {
            ImageView imagen = (ImageView) this.imagenesEstados.getChildren().get(i);
            imagen.imageProperty().bind(objectProperty);
            i++;
        }

        this.setEstados(pokemon);
    }
    public void setNombreProperty(String nombreProperty) {
        this.nombreProperty.set(nombreProperty);
    }
    public void setNivelProperty(String nivelProperty) {
        this.nivelProperty.set(nivelProperty);
    }
    public void setCantVidaProperty(String cantVidaProperty) {
        this.cantVidaProperty.set(cantVidaProperty);
    }

    public void setEstados(Pokemon pokemon){
        int j = 0;
        for (Node circle: this.circulos.getChildren()) {
            circle.setOpacity(0);
            this.imagenesEstadosProperty.get(j).set(new Image(Archivos.getRutaAbsoluta("default.png")));
        }

        int i = 0;
        for (Estado estado: pokemon.getEstados()) {
            if (estado == Estado.NORMAL) {
                break;
            }
            this.circulos.getChildren().get(i).setOpacity(100);
            this.imagenesEstadosProperty.get(i).set(new Image(Archivos.getRutaAbsoluta(estado.name() + ".png")));
            i++;
        }
    }

    public void setVida(Pokemon pokemon, JugadorEnum jugador) {
        if (jugador == JugadorEnum.ACTUAL) {
            this.setCantVidaProperty(pokemon.getVida() + "/" + pokemon.getVidaMax());
        }
        System.out.println("entro a setVida con barra de vida en " + barraVida.getProgress());
        this.barraVida.setProgress((double) pokemon.getVida() / pokemon.getVidaMax());
        System.out.println("salgo con barra de vida en " + barraVida.getProgress());
        if (this.barraVida.getProgress() < 0.3) {
            this.barraVida.setStyle("-fx-accent: #c22f2f");
        } else if (this.barraVida.getProgress() < 0.6) {
            this.barraVida.setStyle("-fx-accent: #c5c742");
        }
    }

    public void animarBarraDeVida(double cantidad) {
        Timeline animacion = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> modificarBarraDeVida(cantidad))
        );

        animacion.setCycleCount((int) Math.abs(cantidad) / 100);
        animacion.play();
    }


    private void modificarBarraDeVida(double cantidad) {
        double porcentaje = this.barraVida.getProgress();
        if ((porcentaje <= 0.1) | (porcentaje >= 1)) {
            return;
        }
        System.out.println("entro a mod con barra de vida en " + barraVida.getProgress());
        this.barraVida.setProgress(porcentaje + cantidad);
        System.out.println("salgo con barra de vida en " + barraVida.getProgress());
        if (porcentaje < 0.3) {
            this.barraVida.setStyle("-fx-accent: #c22f2f");
        } else if (porcentaje < 0.6) {
            this.barraVida.setStyle("-fx-accent: #c5c742");
        }

    }

    public void actualizar(Pokemon pokemon, JugadorEnum jugador) {
        this.setNombreProperty(pokemon.getNombre());
        this.setNivelProperty(("Nvl." + pokemon.getNivel()));
        this.setEstados(pokemon);
        this.setVida(pokemon, jugador);
    }
}
