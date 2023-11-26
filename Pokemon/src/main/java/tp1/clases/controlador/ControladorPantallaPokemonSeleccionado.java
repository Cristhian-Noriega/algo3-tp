package tp1.clases.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tp1.clases.errores.Error;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Categoria;
import tp1.clases.modelo.Jugador;
import tp1.clases.modelo.Pokemon;

import java.util.ArrayList;
import java.util.Optional;

public class ControladorPantallaPokemonSeleccionado {
    private Batalla batalla;
    private Pokemon pokemon;
    private String texto = "Efecto";
    private StringProperty textoProperty = new SimpleStringProperty(this.texto);
    @FXML
    public ControladorCampo campoController;
    @FXML public Label labelTexto;

    public ControladorPantallaPokemonSeleccionado(){

    }

    public void inicializar(Batalla batalla, Pokemon nuevoPokemon) {
        this.batalla = batalla;
        this.pokemon = nuevoPokemon;
        this.campoController.inicializar(batalla);
        //this.labelTexto.setWrapText(true);
    }

    public void cambiarMenuPrincipal(MouseEvent event, boolean cambioTurno) {
        if (cambioTurno) {
            this.batalla.cambiarTurno();
        }
        this.labelTexto.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
    }

    public void setTextoProperty(String textoProperty) {
        this.textoProperty.set(textoProperty);
    }

    public void actualizar(Batalla batalla) {
        this.batalla = batalla;
       // this.labelTexto.textProperty().bind(textoProperty);
        Optional<Error> err = this.batalla.cambiarPokemon(pokemon);
        if (err.isEmpty()) {
            this.setTextoProperty("Cambiaste tu pokemon actual a " + pokemon.getNombre());
            this.campoController.aplicarCambioPokemon();
            // HABRIA Q ACTUALIZAR TODOS LOS CARTELES CON NFO DEL POKE
        }
    }
}
