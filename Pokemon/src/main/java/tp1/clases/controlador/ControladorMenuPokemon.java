package tp1.clases.controlador;

import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tp1.clases.modelo.Pokemon;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorMenuPokemon implements Initializable {

    public VBox contenedorPokemon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPokemones(List<Pokemon> pokemones){
        VBox vbox = new VBox();
        for (Pokemon pokemon : pokemones){
            Pane pane = new Pane();
        }
    }
}
