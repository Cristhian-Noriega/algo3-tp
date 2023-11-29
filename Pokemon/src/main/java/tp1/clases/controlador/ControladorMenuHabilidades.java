package tp1.clases.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.eventos.HabilidadSeleccionadaEvent;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Habilidad;
import tp1.clases.modelo.Pokemon;
import tp1.clases.modelo.SubscriptorTurno;

import java.util.ArrayList;
import java.util.List;

public class ControladorMenuHabilidades implements Controlador, SubscriptorTurno {
    @FXML public ControladorCampo campoController;
    @FXML private Button botonVolver;
    @FXML private Label texto;
    @FXML private Label labelTipoHabilidad;
    @FXML private Label labelTipo;
    @FXML private Label labelCantUsos;
    @FXML private Label labelUsos;
    @FXML private VBox botonesHabilidades;

    private Batalla batalla;
    private ArrayList<Pokemon> pokemones;

    public ControladorMenuHabilidades() {
        this.pokemones = new ArrayList<>();
    }

    public void inicializar(Batalla batalla) {
        this.batalla = batalla;
        batalla.getAdministradorTurnos().agregarSubscriptor(this);

        this.actualizarPokemones();

        this.campoController.inicializar(batalla);
        this.setOpacidadInfo(0);
        this.setHabilidades();
        this.botonVolver.setOnMouseClicked(this::cambiarMenuPrincipal);
    }

    public void actualizar() {
        this.actualizarPokemones();
        this.setHabilidades();
    }

    public void actualizarPokemones() {
        this.pokemones = new ArrayList<>();
        this.pokemones.add(this.batalla.getJugadorActual().getPokemonActual());
        this.pokemones.add(this.batalla.getJugadorSiguiente().getPokemonActual());
    }

    public void setHabilidades() {
        List<Habilidad> habilidades = this.batalla.getHabilidadesPokemonActual();
        int i = 0;
        for (Habilidad habilidad: habilidades) {
            Button boton = (Button) this.botonesHabilidades.getChildren().get(i);
            boton.setText(habilidad.getNombre());
            i++;
            if (habilidad.sinUsosDisponibles()) {
                boton.setDisable(true);
                continue;
            } else {
                boton.setDisable(false);
            }
            boton.setOnMouseEntered(event -> {mostrarInfoHabilidad(habilidad);});
            boton.setOnMouseExited(event -> {mostrarTexto();});

            boton.setOnMouseClicked(event -> {cambiarPantallaEfecto(event, habilidad);});
        }
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

    private void mostrarTexto() {
        this.setOpacidadInfo(0.0);
        this.setOpacidadTexto(1.0);
    }

    private void mostrarInfoHabilidad(Habilidad habilidad) {
        this.setOpacidadTexto(0.0);
        this.setOpacidadInfo(1.0);
        this.labelCantUsos.setText(habilidad.getUsos().toString());
        this.labelTipoHabilidad.setText(habilidad.getTipo().toString());
    }

    public void cambiarMenuPrincipal(MouseEvent event) {
        this.texto.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
    }

    public void cambiarPantallaEfecto(MouseEvent event, Habilidad habilidadSeleccionada) {
        this.texto.fireEvent(new HabilidadSeleccionadaEvent(habilidadSeleccionada));
        this.texto.fireEvent(new CambioDeEscenaEvent(Escena.PANTALLA_EFECTO.ordinal()));
    }

    @Override
    public void Update() {
        this.actualizar();
    }
}
