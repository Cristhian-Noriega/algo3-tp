package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import tp1.clases.modelo.Batalla;
import tp1.clases.errores.Error;
import tp1.clases.modelo.Item;
import tp1.clases.modelo.Pokemon;

import java.util.Optional;

public class ControladorPantallaAplicarItem implements Controlador{

    @FXML
    private Pane pane;
    @FXML
    public Label labelTexto;
    private Batalla batalla;
    private Item item;
    private Pokemon pokemon;



    @FXML
    public ControladorCampo campoController;
    @Override
    public void inicializar(Batalla batalla) {
        this.batalla = batalla;
        this.campoController.inicializar(batalla);
    }
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
    public void setItem(Item item){
        this.item = item;
    }


}
