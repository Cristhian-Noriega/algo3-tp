package tp1.clases.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tp1.clases.errores.Error;
import tp1.clases.eventos.CambioDeTurnoEvent;
import tp1.clases.modelo.AdministradorDeClima;
import tp1.clases.modelo.Categoria;
import tp1.clases.modelo.Habilidad;
import tp1.clases.modelo.Pokemon;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ControladorPantallaEfecto {
    private String texto = "Efecto";
    private StringProperty textoProperty = new SimpleStringProperty(this.texto);
    @FXML public Label labelTexto;
    @FXML public ControladorCampo campoController;

    public ControladorPantallaEfecto() {}

    public void inicializarAtaque(List<Pokemon> pokemones, AdministradorDeClima clima, Habilidad habilidad) {
        this.campoController.inicializar(pokemones.get(0), pokemones.get(1), clima.getClimaActual().name());
        this.labelTexto.textProperty().bind(textoProperty);

        Optional<Error> err = pokemones.get(0).usarHabilidad(habilidad, pokemones.get(1), clima);
        if (err.isEmpty()) {
            this.campoController.actualizarCampo(pokemones.get(0), pokemones.get(1), clima.getClimaActual().name());
            this.setTextoProperty(pokemones.get(0).getNombre() + " ha utilizado la habilidad " + habilidad.getNombre());
            if (habilidad.getCategoria() != Categoria.ESTADISTICA) {
                this.campoController.aplicarParpadeo(JugadorEnum.RIVAL);
            }
            this.campoController.getCampoPane().fireEvent(new CambioDeTurnoEvent());
        }
    }

    public void setTextoProperty(String textoProperty) {
        this.textoProperty.set(textoProperty);
    }


}
