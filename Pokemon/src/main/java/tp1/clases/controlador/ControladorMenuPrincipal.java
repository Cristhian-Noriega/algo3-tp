package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.eventos.RendirseEvent;
import tp1.clases.modelo.Batalla;


public class ControladorMenuPrincipal implements Controlador {
    @FXML public ControladorCampo campoController;
    @FXML public Label dialogo;
    @FXML public Button botonAtacar;
    @FXML public Button botonMochila;
    @FXML public Button botonPokemon;
    @FXML public Button botonRendirse;
    public Pane rendirsePane;
    public ImageView botonNo;
    public ImageView botonSi;

    private Batalla batalla;

    public ControladorMenuPrincipal() {
    }

    public void inicializar(Batalla batalla) {
        this.batalla = batalla;
        this.campoController.inicializar(batalla);
        this.botonAtacar.setOnMouseClicked(this::cambiarMenuHabilidades);
        this.botonMochila.setOnMouseClicked(this::cambiarMenuItems);
        this.botonPokemon.setOnMouseClicked(this::cambiarMenuPokemones);
        this.botonRendirse.setOnMouseClicked(this::confirmacionRendirse);

        this.rendirsePane.setOpacity(0);
        this.botonNo.setOnMouseClicked(this::noRendirse);
        this.botonNo.setDisable(true);
        this.botonSi.setOnMouseClicked(this::rendirse);
        this.botonSi.setDisable(true);
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

    public void rendirse(MouseEvent event){
        this.dialogo.fireEvent(new RendirseEvent());
    }

    public void confirmacionRendirse(MouseEvent event){
        this.botonAtacar.setDisable(true);
        this.botonMochila.setDisable(true);
        this.botonPokemon.setDisable(true);
        this.botonRendirse.setDisable(true);

        this.botonSi.setDisable(false);
        this.botonNo.setDisable(false);

        this.rendirsePane.setOpacity(1);

    }

    private void noRendirse(MouseEvent event) {
        this.rendirsePane.setOpacity(0);

        this.botonAtacar.setDisable(false);
        this.botonMochila.setDisable(false);
        this.botonPokemon.setDisable(false);
        this.botonRendirse.setDisable(false);

        this.botonSi.setDisable(true);
        this.botonNo.setDisable(true);
    }

}
