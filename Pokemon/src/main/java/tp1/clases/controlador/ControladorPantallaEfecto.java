package tp1.clases.controlador;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tp1.clases.errores.Error;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.modelo.*;


import java.util.*;

public class ControladorPantallaEfecto implements Controlador {
    private Batalla batalla;
    private Habilidad habilidadSeleccionada;
    private Pokemon pokemonSeleccionado;
    private Item itemSeleccionado;
    private ArrayList<Pokemon> pokemones;
    private final String texto = "Efecto";
    private final StringProperty textoProperty = new SimpleStringProperty(this.texto);

    @FXML public ControladorCampo campoController;
    @FXML private Label labelTexto;
    @FXML private Pane pane;

    public ControladorPantallaEfecto() {
        this.pokemones = new ArrayList<>();
    }

    public void inicializar(Batalla batalla) {
        this.batalla = batalla;
        this.actualizarPokemones();
        this.campoController.inicializar(batalla);
        this.labelTexto.textProperty().bind(textoProperty);
        this.labelTexto.setWrapText(true);
    }

    public void setPokemonSeleccionado(Pokemon pokemon) {
        this.pokemonSeleccionado = pokemon;
    }

    public void setItemSeleccionado(Item item) {
        this.itemSeleccionado = item;
    }

    public void setTextoProperty(String textoProperty) {
        this.textoProperty.set(textoProperty);
    }

    public void setHabilidadSeleccionada(Habilidad habilidad) {
        this.habilidadSeleccionada = habilidad;
    }

    private void actualizarPokemones() {
        this.pokemones = new ArrayList<>();
        this.pokemones.add(this.batalla.getJugadorActual().getPokemonActual());
        this.pokemones.add(this.batalla.getJugadorSiguiente().getPokemonActual());
    }

    public void mostrarAtaque() {
        this.actualizarPokemones();

        Habilidad habilidad = this.habilidadSeleccionada;
        this.habilidadSeleccionada = null;

        Optional<Error> err = this.batalla.usarHabilidad(habilidad, this.batalla.getJugadorSiguiente());
        InfoHabilidad infoHabilidad = habilidad.getInfoHabilidad();

        if (err.isPresent()) {
            this.setTextoProperty(err.get().mostrar());
            this.setEventoCambioDeEscena(false);
            return;
        }

        if (!infoHabilidad.sePudoUsarHabilidad()) {
            this.setTextoProperty(pokemones.get(JugadorEnum.ACTUAL.ordinal()).getNombre() + " no pudo usar la habilidad porque se encuentra " + infoHabilidad.getEstadoLimitante().toString().toLowerCase());
            this.campoController.actualizar();
            this.setEventoCambioDeEscena(true);
            return;
        }

        String resultado = MensajesPantallaEfecto.mostrarResultado(infoHabilidad, habilidad.getNombre(), pokemones.get(JugadorEnum.ACTUAL.ordinal()).getNombre());
        this.setTextoProperty(resultado);

        this.campoController.aplicarParpadeo(infoHabilidad.getJugadorAfectado(), habilidad.getTipo().name().toLowerCase());

        if ((infoHabilidad.getCategoria() == Categoria.ATAQUE) | ((infoHabilidad.getCategoria() == Categoria.ESTADISTICA) && (infoHabilidad.getEstadisticaModificada() == Estadisticas.VIDA))) {
            this.campoController.animarVida(pokemones.get(infoHabilidad.getJugadorAfectado().ordinal()), infoHabilidad.getJugadorAfectado());
        }

        this.campoController.actualizar();
        this.setEventoCambioDeEscena(true);
    }

    public void mostrarCambioDePokemon() {
        Optional<Error> err = this.batalla.cambiarPokemon(this.pokemonSeleccionado);

        if (err.isEmpty()) {
            this.setTextoProperty("¡Cambiaste tu pokemon a " + this.pokemonSeleccionado.getNombre() + "!");

        } else {
            this.setTextoProperty(err.get().mostrar());
            this.setEventoCambioDeEscena(false);
            return;
        }

        this.campoController.aplicarIntercambioDePokemon();
        this.pokemonSeleccionado = null;
        this.setEventoCambioDeEscena(true);
    }

    public void mostrarItemAplicado() {
        Optional<Error> err = this.batalla.usarItem(this.itemSeleccionado, this.pokemonSeleccionado);

        if (err.isEmpty()) {
            this.setTextoProperty("Se aplicó " + this.itemSeleccionado.getNombre() + " a " + this.pokemonSeleccionado.getNombre());

           this.campoController.aplicarItem(this.pokemonSeleccionado);
            if (this.pokemonSeleccionado.equals(this.batalla.getJugadorActual().getPokemonActual())){
                this.campoController.aplicarEfectoItem();
            }
            this.campoController.actualizar();

        } else {
            this.setTextoProperty(err.get().mostrar());
            this.setEventoCambioDeEscena(false);
            return;
        }

        this.pokemonSeleccionado = null;
        this.itemSeleccionado = null;

        this.setEventoCambioDeEscena(true);
    }

    public void mostrarPokemonMuerto(JugadorEnum jugador) {
        this.setTextoProperty(this.pokemones.get(1).getNombre() + " ha muerto.");

        this.campoController.aplicarDesaparicionPokemonMuerto(jugador);

        this.labelTexto.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
    }

    public void mostrarEfectosClimaYEstados() {

        Timeline timelineInicial = new Timeline(
                new KeyFrame(Duration.seconds(5), event -> this.setTextoProperty(this.textoProperty.get()))
        );
        timelineInicial.setCycleCount(1);
        timelineInicial.play();

        Queue<String> colaMensajes = MensajesPantallaEfecto.encolarMensajes(this.batalla.getInfoTurno());

        if (colaMensajes.isEmpty()) {
            return;
        }

        Timeline timelineEfectos = new Timeline(
                new KeyFrame(Duration.seconds(5), event -> this.setTextoProperty(colaMensajes.poll()))
        );
        timelineEfectos.setCycleCount(colaMensajes.size());
        timelineEfectos.play();
    }

    public void cambiarMenuPrincipal(MouseEvent event, boolean cambioTurno) {
        if (cambioTurno) {
            this.batalla.cambiarTurno();
            this.mostrarEfectosClimaYEstados();
        }
        this.labelTexto.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
    }

    public void setEventoCambioDeEscena(boolean cambioTurno) {
        this.pane.setOnMouseClicked(event -> {
            cambiarMenuPrincipal(event, cambioTurno);
        });
    }
}
