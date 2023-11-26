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
import tp1.clases.eventos.EstadoEvento;
import tp1.clases.modelo.*;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class ControladorPantallaEfecto implements Controlador {
    private Batalla batalla;
    private Habilidad habilidadSeleccionada;
    private Pokemon pokemonSeleccionado;
    private Item itemSeleccionado;
    private ArrayList<Pokemon> pokemones;
    private String texto = "Efecto";

    private Queue<String> colaMensajes = new LinkedList<>();

    private boolean mostrandoMensaje = false;
    private StringProperty textoProperty = new SimpleStringProperty(this.texto);
    @FXML public Label labelTexto;
    @FXML public ControladorCampo campoController;
    @FXML public Pane pane;

    public ControladorPantallaEfecto() {
        this.pokemones = new ArrayList<>();
    }

    public void inicializar(Batalla batalla) {
        System.out.println("batalla en pantalla efecto " + batalla);
        this.batalla = batalla;
        this.setPokemones();
        this.campoController.inicializar(batalla);
        this.labelTexto.setWrapText(true);
//        labelTexto.addEventHandler(EstadoEvento.ESTADO_EVENT_TYPE, event -> {
//            String mensaje = ((EstadoEvento) event).getMensaje();
//            agregarMensaje(mensaje);
//        });
    }

    public void actualizar(Batalla batalla) {
        this.batalla = batalla;
        this.setPokemones();
        this.labelTexto.textProperty().bind(textoProperty);
        System.out.println(pokemones.get(0).getHabilidades() + "   " + pokemones.get(1).getNombre());
        double vidaAntRival = pokemones.get(1).getVida();
        Optional<Error> err = this.batalla.usarHabilidad(this.habilidadSeleccionada, this.batalla.getJugadorSiguiente());
        if (err.isEmpty()) {
            this.setTextoProperty(pokemones.get(0).getNombre() + " " + this.habilidadSeleccionada.getInfo().toLowerCase());
            if (this.habilidadSeleccionada.getCategoria() != Categoria.ESTADISTICA) {
                this.campoController.aplicarParpadeo(JugadorEnum.RIVAL);
                double cambio = pokemones.get(1).getVida() - vidaAntRival;
                this.campoController.animarVida(cambio / pokemones.get(1).getVidaMax());
            }

            this.campoController.actualizar();
            this.pane.setOnMouseClicked(event -> {
                cambiarMenuPrincipal(event, true);
            });
        } else {
            this.setTextoProperty(err.get().mostrar());
            this.pane.setOnMouseClicked(event -> {
                cambiarMenuPrincipal(event, false);
            });
        }
    }

    private void setPokemones() {
        this.pokemones = new ArrayList<>();
        for (Jugador jugador: this.batalla.getJugadores()) {
            this.pokemones.add(jugador.getPokemonActual());
        }
    }

    public void cambiarMenuPrincipal(MouseEvent event, boolean cambioTurno) {
        if (cambioTurno) {
            this.batalla.cambiarTurno();
        }
        this.labelTexto.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
    }

    public void setTextoProperty(String textoProperty) {
        this.textoProperty.set(textoProperty);
    }

    public void setHabilidadSeleccionada(Habilidad habilidad) {
        this.habilidadSeleccionada = habilidad;
    }

    public void agregarMensaje(String mensaje) {
        colaMensajes.offer(mensaje); // Agrega un mensaje a la cola
        if (!mostrandoMensaje) { // Si no se está mostrando un mensaje actualmente, comienza a mostrar los mensajes
            mostrarSiguienteMensaje();
        }
    }

    private void mostrarSiguienteMensaje() {
        if (!colaMensajes.isEmpty()) {
            String mensaje = colaMensajes.poll();
            labelTexto.setText(mensaje);

            mostrandoMensaje = true;

            // Manejo del tiempo antes de mostrar el siguiente mensaje
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(3), e -> {
                        mostrandoMensaje = false;
                        mostrarSiguienteMensaje();
                    })
            );
            timeline.play();
        }
    }

}
