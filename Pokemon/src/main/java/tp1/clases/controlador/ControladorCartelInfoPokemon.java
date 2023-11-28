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
    @FXML public HBox circulos;
    @FXML public Pane imagenesEstados;
    @FXML public Rectangle barraVida;
    @FXML public Rectangle barraFondo;

    public ControladorCartelInfoPokemon() {}

    public void inicializar(Pokemon pokemon, JugadorEnum jugador) {
        this.labelNombre.textProperty().bind(this.nombreProperty);
        this.labelNivel.textProperty().bind(this.nivelProperty);

        if (jugador == JugadorEnum.ACTUAL){
            this.barraFondo.setWidth(Constantes.tamanioBarraVidaJugadorActual + Constantes.margen);
            this.barraVida.setWidth(Constantes.tamanioBarraVidaJugadorActual);
            this.labelCantVida.textProperty().bind(this.cantVidaProperty);
            this.setCantVidaProperty(pokemon.getVida() + "/" + pokemon.getVidaMax());
        }

        this.setNombreProperty(pokemon.getNombre());
        this.setNivelProperty(("Nvl." + pokemon.getNivel()));
        this.setPorcentajeVida((double) pokemon.getVida() / pokemon.getVidaMax());
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

    public void setPorcentajeVida(double porcentajeNuevo) {
        double tamanioNuevo = ((this.barraFondo.getWidth() - Constantes.margen) * porcentajeNuevo);
        this.barraVida.setWidth(tamanioNuevo);
    }

    public double getPorcentajeBarraDeVida() {
        return this.barraVida.getWidth() / (this.barraFondo.getWidth() - Constantes.margen);
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
            String ruta = Archivos.getRutaAbsolutaImagenes("estados/" + estado.name() + ".png");
            this.imagenesEstadosProperty.get(i).set(new Image(ruta));
            i++;
        }
    }

    public void setVida(Pokemon pokemon, JugadorEnum jugador) {
        if (jugador == JugadorEnum.ACTUAL) {
            this.setCantVidaProperty(pokemon.getVida() + "/" + pokemon.getVidaMax());
        }

        this.setPorcentajeVida((double) pokemon.getVida() / pokemon.getVidaMax());

        if (this.getPorcentajeBarraDeVida() < 0.3) {
            this.barraVida.setStyle("-fx-fill: #c22f2f");
        } else if (this.getPorcentajeBarraDeVida() < 0.6) {
            this.barraVida.setStyle("-fx-fillt: #c5c742");
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
    }
}