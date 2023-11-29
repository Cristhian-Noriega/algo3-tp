package tp1.clases.controlador;

import javafx.animation.Transition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import tp1.clases.modelo.Constantes;
import tp1.clases.modelo.Estado;
import tp1.clases.modelo.JugadorEnum;
import tp1.clases.modelo.Pokemon;

import java.util.List;

public class ControladorCentroDeEstadisticas {
    private final String nombre = "Pokemon";
    private final String nivel = "Nvl.0";
    private final String cantVida = "00/00";

    private final StringProperty nombreProperty = new SimpleStringProperty(nombre);
    private final StringProperty nivelProperty = new SimpleStringProperty(nivel);
    private final StringProperty cantVidaProperty = new SimpleStringProperty(cantVida);
    private final List<ObjectProperty<Image>> imagenesEstadosProperty = List.of(new SimpleObjectProperty<>(), new SimpleObjectProperty<>(), new SimpleObjectProperty<>(), new SimpleObjectProperty<>());

    @FXML private Label labelNombre;
    @FXML private Label labelNivel;
    @FXML private Label labelCantVida;
    @FXML private HBox circulos;
    @FXML private Pane imagenesEstados;
    @FXML private Rectangle barraVida;
    @FXML private Rectangle barraFondo;

    public ControladorCentroDeEstadisticas() {}

    public void inicializar(Pokemon pokemon, JugadorEnum jugador) {
        this.labelNombre.textProperty().bind(this.nombreProperty);
        this.labelNivel.textProperty().bind(this.nivelProperty);

        if (jugador == JugadorEnum.ACTUAL){
            this.barraFondo.setWidth(Constantes.tamanioBarraVidaJugadorActual + Constantes.margen);
            this.barraVida.setWidth(Constantes.tamanioBarraVidaJugadorActual);
            this.labelCantVida.textProperty().bind(this.cantVidaProperty);
            this.setCantVidaProperty(pokemon.getVida() + "/" + pokemon.getVidaMax());
        }

        int i = 0;
        for (ObjectProperty<Image> objectProperty: imagenesEstadosProperty) {
            ImageView imagen = (ImageView) this.imagenesEstados.getChildren().get(i);
            imagen.imageProperty().bind(objectProperty);
            i++;
        }

        this.actualizar(pokemon, jugador);
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

    public void setPorcentajeVida(double porcentajeNuevo) {
        double tamanioNuevo = ((this.barraFondo.getWidth() - Constantes.margen) * porcentajeNuevo);
        this.barraVida.setWidth(tamanioNuevo);
    }

    public void setEstados(Pokemon pokemon){
        int j = 0;
        for (Node circle: this.circulos.getChildren()) {
            circle.setOpacity(0);
            this.imagenesEstadosProperty.get(j).set(new Image(Archivos.getRutaAbsolutaImagenes("default.png")));
            j++;
        }

        int i = 0;
        for (Estado estado: pokemon.getEstados()) {
            if (estado == Estado.NORMAL) {
                break;
            }

            this.circulos.getChildren().get(i).setOpacity(100);
            this.imagenesEstadosProperty.get(i).set(new Image(Archivos.getRutaAbsolutaImagenes("estados/" + estado.name() + ".png")));
            i++;
        }
    }

    public void setVida(Pokemon pokemon, JugadorEnum jugador) {
        if (jugador == JugadorEnum.ACTUAL) {
            this.setCantVidaProperty(pokemon.getVida() + "/" + pokemon.getVidaMax());
        }

        this.setPorcentajeVida((double) pokemon.getVida() / pokemon.getVidaMax());

        this.cambiarColorBarraVida();
    }

    public double getPorcentajeBarraDeVida() {
        return this.barraVida.getWidth() / (this.barraFondo.getWidth() - Constantes.margen);
    }

    public void cambiarColorBarraVida() {
        if (this.getPorcentajeBarraDeVida() < 0.3) {
            this.barraVida.setStyle("-fx-fill: #c22f2f");
        } else if (this.getPorcentajeBarraDeVida() < 0.6) {
            this.barraVida.setStyle("-fx-fillt: #c5c742");
        } else {
            this.barraVida.setStyle("-fx-fillt: #34BC81");
        }
    }

    public void animarBarraDeVida(double porcentajeInicial, double porcentajeFinal) {
        if (porcentajeInicial == porcentajeFinal) {
            return;
        }

        Transition transicion = new Transition() {
            {
                setCycleDuration(Duration.seconds(2));
            }

            @Override
            protected void interpolate(double tiempo) {
                double nuevoPorcentaje = porcentajeInicial + tiempo * (porcentajeFinal - porcentajeInicial);
                setPorcentajeVida(nuevoPorcentaje);
            }
        };

        transicion.play();
    }

    public void actualizar(Pokemon pokemon, JugadorEnum jugador) {
        this.setNombreProperty(pokemon.getNombre());
        this.setNivelProperty(("Nvl." + pokemon.getNivel()));
        this.setEstados(pokemon);
        this.setVida(pokemon, jugador);
        this.cambiarColorBarraVida();
    }
}