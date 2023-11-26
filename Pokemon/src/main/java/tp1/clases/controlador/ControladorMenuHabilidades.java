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
import tp1.clases.modelo.Subscriptor;

import java.util.ArrayList;
import java.util.List;

public class ControladorMenuHabilidades implements Controlador, Subscriptor {
    @FXML public ControladorCampo campoController;
    @FXML public Button botonVolver;
    @FXML public Label texto;

    @FXML public Label labelTipoHabilidad;
    @FXML public Label labelTipo;
    @FXML public Label labelCantUsos;
    @FXML public Label labelUsos;
    @FXML public VBox botonesHabilidades;

    private Batalla batalla;
    private ArrayList<Pokemon> pokemones;

    public ControladorMenuHabilidades() {
        this.pokemones = new ArrayList<>();
    }

    public void inicializar(Batalla batalla) {
        System.out.println("batalla en habilidades " + batalla);
        this.batalla = batalla;
        batalla.getAdministradorTurnos().agregarSubscriptor(this);
        this.pokemones.add(batalla.getJugadorActual().getPokemonActual());
        this.pokemones.add(batalla.getJugadorSiguiente().getPokemonActual());

        this.campoController.inicializar(batalla);
        this.setOpacidadInfo(0);
        this.botonVolver.setOnMouseClicked(this::cambiarMenuPrincipal);
        this.setHabilidades(this.pokemones.get(0).getHabilidades());
    }

    @Override
    public void actualizarCampo() {
        System.out.println("actualiza de menu hab");
    }

    public void actualizar() {
        this.setHabilidades(this.batalla.getHabilidadesPokemonActual());
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        int i = 0;
        for (Habilidad habilidad: habilidades) {
            System.out.println(habilidad.getNombre());
            Button boton = (Button) this.botonesHabilidades.getChildren().get(i);
            boton.setText(habilidad.getNombre());

            if (habilidad.sinUsosDisponibles()) {
                boton.setDisable(true);
                i++;
                continue;
            } else {
                boton.setDisable(false);
            }

            boton.setOnMouseEntered(event -> {mostrarInfoHabilidad(habilidad);});
            boton.setOnMouseExited(event -> {mostrarTexto();});


            boton.setOnMouseClicked(event -> {cambiarPantallaEfecto(event, habilidad);});
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

    public void cambiarMenuPrincipal(MouseEvent event) {
        this.texto.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
    }

    public void cambiarPantallaEfecto(MouseEvent event, Habilidad habilidadSeleccionada) {
        this.texto.fireEvent(new HabilidadSeleccionadaEvent(habilidadSeleccionada));
        CambioDeEscenaEvent evento = new CambioDeEscenaEvent(Escena.PANTALLA_EFECTO.ordinal());
        evento.setHabilidad(habilidadSeleccionada);
        this.texto.fireEvent(evento);
    }

    @Override
    public void Update() {
        this.actualizar();
    }
}
