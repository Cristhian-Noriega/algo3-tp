package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tp1.clases.controlador.comandos.Comando;
import tp1.clases.modelo.AdministradorDeClima;
import tp1.clases.modelo.Habilidad;
import tp1.clases.modelo.Pokemon;

import java.io.IOException;
import java.util.List;

public class ControladorMenuHabilidades {
    @FXML public ControladorCampo campoController;
    @FXML public Button botonVolver;
    @FXML public Label texto;

    @FXML public Label labelTipoHabilidad;
    @FXML public Label labelTipo;
    @FXML public Label labelCantUsos;
    @FXML public Label labelUsos;
    @FXML public VBox botonesHabilidades;

    private AdministradorDeClima AdministradorDeClima;
    private Pokemon pokemonActual;
    private Pokemon pokemonRival;

    public ControladorMenuHabilidades() {}

    public void inicializar(Pokemon pokemonActual, Pokemon pokemonRival, AdministradorDeClima clima) {
        this.AdministradorDeClima = clima;
        this.pokemonActual = pokemonActual;
        this.pokemonRival = pokemonRival;
        this.campoController.inicializar(pokemonActual, pokemonRival, clima.getClimaActual().name());
        this.setOpacidadInfo(0);
        this.botonVolver.setOnMouseClicked(event -> {cambiarMenuPrincipal(event, pokemonActual, pokemonRival, clima);});
        this.setHabilidades(pokemonActual.getHabilidades());
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        int i = 0;
        for (Habilidad habilidad: habilidades) {
            System.out.println(habilidad.getNombre());
            Button boton = (Button) this.botonesHabilidades.getChildren().get(i);
            boton.setText(habilidad.getNombre());
            boton.setOnMouseEntered(event -> {mostrarInfoHabilidad(habilidad);});
            boton.setOnMouseExited(event -> {mostrarTexto();});
            boton.setOnMouseClicked(event -> {seleccionarHabilidad(event, habilidad);});
            boton.setOnMouseClicked(event -> {cambiarPantallaEfecto(event, pokemonActual, pokemonRival, AdministradorDeClima, habilidad);});
            i++;
        }
    }

    private void mostrarTexto() {
        this.setOpacidadInfo(0);
        this.setOpacidadTexto(100);
    }

    private void mostrarInfoHabilidad(Habilidad habilidad) {
        this.setOpacidadTexto(0);
        this.setOpacidadInfo(100);
        this.labelCantUsos.setText(habilidad.getUsos().toString());
        this.labelTipoHabilidad.setText(habilidad.getTipo().toString());
    }


    public void setOpacidadTexto(double opacidad) {
        this.texto.setOpacity(opacidad);
    }

    public void setOpacidadInfo(double opacidad) {
        this.labelTipoHabilidad.setOpacity(opacidad);
        this.labelTipo.setOpacity(opacidad);
        this.labelCantUsos.setOpacity(opacidad);
        this.labelUsos.setOpacity(opacidad);
    }

    public void cambiarMenuPrincipal(MouseEvent event, Pokemon pokemonActual, Pokemon pokemonRival, AdministradorDeClima clima) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu-principal.fxml"));
            Parent root = loader.load();
            ControladorMenuPrincipal controladorMenuPrincipal = loader.getController();
            controladorMenuPrincipal.inicializar(pokemonActual, pokemonRival, clima);
            Scene escenaHabilidades = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(escenaHabilidades);
        } catch (IOException e) {
            System.out.println("No se pudo cambiar al menú de habilidades.");
        }
    }

    public void cambiarPantallaEfecto(MouseEvent event, Pokemon pokemonActual, Pokemon pokemonRival, AdministradorDeClima clima, Habilidad habilidadSeleccionada) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pantalla-efecto.fxml"));
            Parent root = loader.load();
            ControladorPantallaEfecto controladorPantallaEfecto = loader.getController();
            controladorPantallaEfecto.inicializarAtaque(List.of(this.pokemonActual, this.pokemonRival), clima, habilidadSeleccionada);
            Scene escenaEfecto = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(escenaEfecto);
        } catch (IOException e) {
            System.out.println("No se pudo cambiar la pantalla.");
        }
    }

    public void seleccionarHabilidad(MouseEvent event, Habilidad habilidad) {
        cambiarPantallaEfecto(event, pokemonActual, pokemonRival, this.AdministradorDeClima, habilidad);
    }

}
