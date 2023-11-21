package tp1.clases.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import tp1.clases.modelo.Pokemon;

public class ControladorCartelPokemon {
    private String nombre = "Pokemon";
    private String nivel = "Nvl.0";
    private String cantVida = "00/00";

    private StringProperty nombreProperty = new SimpleStringProperty(nombre);
    private StringProperty nivelProperty = new SimpleStringProperty(nivel);
    private StringProperty cantVidaProperty = new SimpleStringProperty(cantVida);

    @FXML public Label labelNombre;
    @FXML public Label labelNivel;
    @FXML public Label labelCantVida;
    @FXML public ProgressBar barraVida;

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
        this.setNombreProperty(pokemon.getNombre());
        this.setNivelProperty(("Nvl." + pokemon.getNivel()));
        this.setCantVidaProperty(pokemon.getVida() + "/" + pokemon.getVidaMax());
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
}
