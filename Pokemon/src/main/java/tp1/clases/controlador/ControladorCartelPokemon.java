package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import tp1.clases.modelo.Pokemon;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorCartelPokemon implements Initializable {
    @FXML
    public Pane contenedorCartel;
    @FXML
    public HBox contenedorDatos;
    @FXML
    public Label labelNombre;
    @FXML
    public Label labelNivel;
    @FXML
    public javafx.scene.control.ProgressBar barraVida;
    @FXML
    public Label labelVida;

    private Pokemon pokemon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setDatosPokemon(Pokemon pokemon) {
        this.labelNombre.setText(pokemon.getNombre());
        this.labelNivel.setText("Nvl." + pokemon.getNivel());
        this.labelVida.setText(pokemon.getVida() + "/" + pokemon.getVidaMax());
        this.barraVida.setProgress((double) pokemon.getVida() / pokemon.getVidaMax());
    }

}