package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tp1.clases.errores.Error;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Pokemon;

import java.util.Optional;

public class ControladorPantallaPokemonSeleccionado implements Controlador{
    private Batalla batalla;
    private Pokemon pokemon;
    private String texto = "Efecto";
    @FXML
    public ControladorCampo campoController;
    @FXML public Label labelTexto;

    public ControladorPantallaPokemonSeleccionado(){

    }

    @Override
    public void inicializar(Batalla batalla) {
        this.batalla = batalla;
        this.campoController.inicializar(batalla);
    }

    public void cambiarMenuPrincipal(MouseEvent event, boolean cambioTurno) {
        if (cambioTurno) {
            this.batalla.cambiarTurno();
        }
        this.labelTexto.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
    }

    public void actualizar(Pokemon pokemon) {
        this.pokemon = pokemon;

        Optional<Error> err = this.batalla.cambiarPokemon(pokemon);
        if (err.isEmpty()) {
            this.labelTexto.setText("Cambiaste tu pokemon a " + pokemon.getNombre() + "!");
            this.campoController.aplicarCambioPokemon();
        }
    }

}