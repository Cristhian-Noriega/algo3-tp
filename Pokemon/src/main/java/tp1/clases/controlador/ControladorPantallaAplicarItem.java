package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tp1.clases.modelo.Batalla;
import tp1.clases.errores.Error;
import tp1.clases.modelo.Item;
import tp1.clases.modelo.Pokemon;

import java.util.Optional;

public class ControladorPantallaAplicarItem implements Controlador{
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

    public void actualizar(Pokemon pokemon, Item item) {
       this.pokemon = pokemon;
       this.item = item;

       Optional<Error> err = this.batalla.usarItem(item, pokemon);
        if (err.isEmpty()) {
            this.labelTexto.setText("Se aplico " + item.getNombre() + "a " +pokemon.getNombre());
            this.campoController.aplicarItem(pokemon);
        }

       }
}
