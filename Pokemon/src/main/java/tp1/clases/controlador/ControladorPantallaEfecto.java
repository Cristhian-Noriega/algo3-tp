package tp1.clases.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tp1.clases.errores.Error;
import tp1.clases.eventos.CambioDeTurnoEvent;
import tp1.clases.modelo.*;


import java.util.ArrayList;
import java.util.Optional;

public class ControladorPantallaEfecto implements Controlador{
    private Batalla batalla;
    private Habilidad habilidadSeleccionada;
    private ArrayList<Pokemon> pokemones;
    private String texto = "Efecto";
    private StringProperty textoProperty = new SimpleStringProperty(this.texto);
    @FXML public Label labelTexto;
    @FXML public ControladorCampo campoController;
    @FXML public Pane pane;
    private ControladorVentana controladorVentana;

    public ControladorPantallaEfecto() {
        this.pokemones = new ArrayList<>();
    }

    public void inicializar(Batalla batalla) {
        this.batalla = batalla;
        this.pokemones.add(batalla.getJugadorActual().getPokemonActual());
        this.pokemones.add(batalla.getJugadorSiguiente().getPokemonActual());

        this.campoController.inicializar(batalla);
    }

    public void actualizar(Batalla batalla) {
        this.batalla = batalla;

        this.labelTexto.textProperty().bind(textoProperty);
        System.out.println(pokemones.get(0).getHabilidades() + "   " + pokemones.get(1).getNombre());
        double vidaAntRival = pokemones.get(1).getVida();
        Optional<Error> err = this.batalla.usarHabilidad(this.habilidadSeleccionada, this.batalla.getJugadorSiguiente());
        if (err.isEmpty()) {
            this.setTextoProperty(pokemones.get(0).getNombre() + " ha utilizado la habilidad " + this.habilidadSeleccionada.getNombre());
            if (this.habilidadSeleccionada.getCategoria() != Categoria.ESTADISTICA) {
                this.campoController.aplicarParpadeo(JugadorEnum.RIVAL);
                this.campoController.animarVida((vidaAntRival - pokemones.get(1).getVida() ) / pokemones.get(1).getVidaMax());
            }
            this.campoController.actualizar(this.batalla);
            this.campoController.getCampoPane().fireEvent(new CambioDeTurnoEvent());
            this.pane.setOnMouseClicked(this::cambiarMenuPrincipal);
        }
    }

    @Override
    public void actualizarCampo(Batalla batalla) {
        this.campoController.actualizar(batalla);
    }

    @Override
    public void setControladorVentana(ControladorVentana controladorVentana) {
        this.controladorVentana = controladorVentana;
    }

    public void cambiarMenuPrincipal(MouseEvent event) {
        this.controladorVentana.cambiarTurno();
        this.controladorVentana.cambiarEscena(0);
    }

    public void setTextoProperty(String textoProperty) {
        this.textoProperty.set(textoProperty);
    }
    public void setHabilidadSeleccionada(Habilidad habilidad) {
        this.habilidadSeleccionada = habilidad;
    }
}
