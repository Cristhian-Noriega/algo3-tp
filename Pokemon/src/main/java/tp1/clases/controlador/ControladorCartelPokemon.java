package tp1.clases.controlador;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import tp1.clases.modelo.Estado;
import tp1.clases.modelo.Pokemon;
import java.util.List;

public class ControladorCartelPokemon {
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

    public ControladorCartelPokemon() {}

    public void inicializar(Pokemon pokemon, JugadorEnum jugador) {
        this.labelNombre.textProperty().bind(this.nombreProperty);
        this.labelNivel.textProperty().bind(this.nivelProperty);
        if (jugador == JugadorEnum.ACTUAL){
            this.labelCantVida.textProperty().bind(this.cantVidaProperty);
        } else {
            this.barraVida.setScaleX(1.5);
            this.barraVida.setTranslateX(32);
        }
        this.setVida(pokemon);
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
        int i = 0;
        for (Estado estado: pokemon.getEstados()) {
            if (estado == Estado.NORMAL) {
                break;
            }
            this.circulos.getChildren().get(i).setOpacity(100);
            this.imagenesEstadosProperty.get(i).set(new Image("file:/home/melina/Escritorio/algo3/TP/algo3-tp/Pokemon/src/main/resources/Imagenes/" + estado + ".png"));
            i++;
        }
    }

    public void setVida(Pokemon pokemon) {
        double porcentaje = (double) pokemon.getVida() / pokemon.getVidaMax();
        this.setCantVidaProperty(pokemon.getVida() + "/" + pokemon.getVidaMax());
        this.barraVida.setProgress(porcentaje);
        if (porcentaje < 0.3) {
            this.barraVida.setStyle("-fx-accent: #c22f2f");
        } else if (porcentaje < 0.6) {
            this.barraVida.setStyle("-fx-accent: #c5c742");
        }
    }
}
