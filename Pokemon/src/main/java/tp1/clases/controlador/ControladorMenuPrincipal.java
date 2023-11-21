package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tp1.clases.modelo.Pokemon;

import java.io.IOException;
import java.util.EventObject;

public class ControladorMenuPrincipal {
    @FXML public ControladorCampo campoController;
    @FXML public Label dialogo;
    @FXML public Button botonAtacar;
    @FXML public Button botonMochila;
    @FXML public Button botonPokemon;
    @FXML public Button botonRendirse;

    public ControladorMenuPrincipal() {}

    public void inicializar(Pokemon pokemonActual, Pokemon pokemonRival, String clima) {
        this.campoController.inicializar(pokemonActual, pokemonRival, clima);
        this.botonAtacar.setOnMouseClicked(event -> {cambiarMenuHabilidades(event, pokemonActual, pokemonRival, clima);});
    }

    public void cambiarMenuHabilidades(MouseEvent event, Pokemon pokemonActual, Pokemon pokemonRival, String clima) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("menu-habilidades.fxml"));
            Parent root = loader.load();
            ControladorMenuHabilidades controladorMenuHabilidades = loader.getController();
            controladorMenuHabilidades.inicializar(pokemonActual, pokemonRival, clima, pokemonActual.getHabilidades());
            Scene escenaHabilidades = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(escenaHabilidades);
        } catch (IOException e) {
            System.out.println("No se pudo cambiar al menú de habilidades.");
            e.printStackTrace();
        }
    }
}
