package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import tp1.clases.modelo.Pokemon;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp1.clases.controlador.comandos.Comando;
import javafx.scene.Node;


import java.io.IOException;
import java.util.List;

public class ControladorCartelPokemon implements Initializable {
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
    @FXML
    public ImageView imagenPokemonOpcion;
    @FXML
    public HBox contenedorBoton;
    //@FXML
    //private Button boton;

    private Pokemon pokemon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contenedorBoton.setOnMouseEntered(this::handleMouseEntered);
        contenedorBoton.setOnMouseExited(this::handleMouseExited);
    }

    public void setDatosPokemon(Pokemon pokemon) {
        this.labelNombre.setText(pokemon.getNombre()); // DEJO DE FUNCIONAR :(
        this.labelNivel.setText("Nvl." + pokemon.getNivel());
        this.labelVida.setText(pokemon.getVida() + "/" + pokemon.getVidaMax());
        this.barraVida.setProgress((double) pokemon.getVida() / pokemon.getVidaMax());
        this.setImagenPokemonOpcion(pokemon.getNombre());
    }

    private void setImagenPokemonOpcion(String pokemon){
        Image imagen = new Image("file:/home/emilia/Desktop/Algo3/TP/algo3-tp/Pokemon/src/main/resources/images/pokemon/" + pokemon + ".gif");
        this.imagenPokemonOpcion.setImage(imagen);
    }

    private void handleMouseEntered(MouseEvent event) {
        contenedorBoton.setStyle("-fx-border-color:#e77a00; -fx-border-radius: 3%; -fx-border-width: 5;");
    }

    private void handleMouseExited(MouseEvent event) {
        contenedorBoton.setStyle("-fx-border-color:black; -fx-border-radius: 3%; -fx-border-width: 3;");
    }
}