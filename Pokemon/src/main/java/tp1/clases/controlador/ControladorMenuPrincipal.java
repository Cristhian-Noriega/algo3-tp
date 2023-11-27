package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.modelo.Batalla;


public class ControladorMenuPrincipal implements Controlador {
    @FXML
    public ControladorCampo campoController;
    @FXML
    public Label dialogo;
    @FXML
    public Button botonAtacar;
    @FXML
    public Button botonMochila;
    @FXML
    public Button botonPokemon;
    @FXML
    public Button botonRendirse;

    public ControladorMenuPrincipal() {
    }

    public void inicializar(Batalla batalla) {
        System.out.println("batalla en menu principla " + batalla);
        this.campoController.inicializar(batalla);
        this.botonAtacar.setOnMouseClicked(this::cambiarMenuHabilidades);
        this.botonMochila.setOnMouseClicked(this::cambiarMenuItems);
        this.botonPokemon.setOnMouseClicked(this::cambiarMenuPokemones);
    }

    public void cambiarMenuHabilidades(MouseEvent event) {
        this.dialogo.fireEvent(new CambioDeEscenaEvent(Escena.MENU_HABILIDADES.ordinal()));
    }

    public void cambiarMenuItems(MouseEvent event) {
        this.dialogo.fireEvent(new CambioDeEscenaEvent(Escena.MENU_ITEMS.ordinal()));

    }
    public void cambiarMenuPokemones(MouseEvent event){
            this.dialogo.fireEvent(new CambioDeEscenaEvent(Escena.MENU_POKEMONES.ordinal()));
    }
}
