package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tp1.clases.modelo.Batalla;



public class ControladorMenuPrincipal implements Controlador{
    @FXML public ControladorCampo campoController;
    @FXML public Label dialogo;
    @FXML public Button botonAtacar;
    @FXML public Button botonMochila;
    @FXML public Button botonPokemon;
    @FXML public Button botonRendirse;
    private Batalla batalla;
    private ControladorVentana controladorVentana;

    public ControladorMenuPrincipal() {}

    public void setControladorVentana(ControladorVentana controladorVentana) {
        this.controladorVentana = controladorVentana;
    }

    public void inicializar(Batalla batalla) {
        this.batalla = batalla;
        this.campoController.inicializar(this.batalla);
        this.botonAtacar.setOnMouseClicked(this::cambiarMenuHabilidades);
    }

    @Override
    public void actualizar(Batalla batalla) {
        this.batalla = batalla;
    }

    @Override
    public void actualizarCampo(Batalla batalla) {
        this.campoController.actualizar(batalla);
    }

    public void cambiarMenuHabilidades(MouseEvent event) {
        this.controladorVentana.cambiarEscena(1);
    }
}
