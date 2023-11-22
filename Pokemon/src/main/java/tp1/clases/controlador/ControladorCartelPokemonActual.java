package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import tp1.clases.modelo.Pokemon;
import tp1.clases.modelo.Jugador;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class ControladorCartelPokemonActual implements Initializable {

    @FXML
    public Label labelNombre;
    @FXML
    public Label labelNivel;
    @FXML
    public Label labelVida;
    @FXML
    public ProgressBar barraVida;
    @FXML
    public ImageView imagenPokemonActual;
    @FXML
    public VBox contenedorCaja;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contenedorCaja.setOnMouseEntered(this::handleMouseEntered);
        contenedorCaja.setOnMouseExited(this::handleMouseExited);
    }

    public void setDatosPokemonActual(Pokemon pokemon) {
        this.labelNombre.setText(pokemon.getNombre());
        this.labelNivel.setText("Nvl." + pokemon.getNivel());
        this.labelVida.setText(pokemon.getVida() + "/" + pokemon.getVidaMax());
        this.barraVida.setProgress((double) pokemon.getVida() / pokemon.getVidaMax());
        this.setImagenPokemonActual(pokemon.getNombre());
    }

    private void setImagenPokemonActual(String pokemon){
        Image imagen = new Image("file:/home/emilia/Desktop/Algo3/TP/algo3-tp/Pokemon/src/main/resources/images/pokemon/" + pokemon + ".gif");
        this.imagenPokemonActual.setImage(imagen);
    }

    private void handleMouseEntered(MouseEvent event) {
        contenedorCaja.setStyle("-fx-border-color:#e77a00; -fx-border-radius: 3%; -fx-border-width: 5;");
    }

    private void handleMouseExited(MouseEvent event) {
        contenedorCaja.setStyle("-fx-border-color:black; -fx-border-radius: 3%; -fx-border-width: 3;");
    }

}
