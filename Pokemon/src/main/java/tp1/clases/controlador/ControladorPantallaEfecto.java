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
        this.actualizarPokemones();
        this.campoController.inicializar(batalla);
        this.labelTexto.textProperty().bind(textoProperty);
        this.labelTexto.setWrapText(true);
    }

    public void mostrarAtaque(Batalla batalla) {
        this.batalla = batalla;
        this.actualizarPokemones();

        Habilidad habilidad = this.habilidadSeleccionada;
        this.habilidadSeleccionada = null;

        Optional<Error> err = this.batalla.usarHabilidad(habilidad, this.batalla.getJugadorSiguiente());
        InfoHabilidad infoHabilidad = habilidad.getInfoHabilidad();

        if (err.isPresent()) {
            this.setTextoProperty(err.get().mostrar());
            this.pane.setOnMouseClicked(event -> {
                cambiarMenuPrincipal(event, false);
            });
            return;
        }

        if (!infoHabilidad.sePudoUsarHabilidad()) {
            this.setTextoProperty(pokemones.get(0).getNombre() + " no pudo usar la habilidad porque se encuentra " + infoHabilidad.getEstadoLimitante().toString().toLowerCase());
            this.campoController.actualizar();
            this.pane.setOnMouseClicked(event -> {
                cambiarMenuPrincipal(event, true);
            });
            return;
        }

        String resultado = this.mostrarResultado(infoHabilidad, habilidad.getNombre(), pokemones.get(0).getNombre());
        System.out.println(resultado);
        this.setTextoProperty(resultado);

        this.campoController.aplicarParpadeo(infoHabilidad.getJugadorAfectado());
        if ((infoHabilidad.getCategoria() == Categoria.ATAQUE) | ((infoHabilidad.getCategoria() == Categoria.ESTADISTICA) && (infoHabilidad.getEstadisticaModificada() == Estadisticas.VIDA))) {
            this.campoController.animarVida(pokemones.get(infoHabilidad.getJugadorAfectado().ordinal()));
        }

        this.campoController.actualizar();
        this.pane.setOnMouseClicked(event -> {
            cambiarMenuPrincipal(event, true);
        });
    }

    private String mostrarResultado(InfoHabilidad infoHabilidad, String habilidad, String pokemonAtacante) {
        String resultado = pokemonAtacante + " ha usado la habilidad " + habilidad + ". ";
        switch (infoHabilidad.getCategoria()) {
            case ATAQUE -> resultado += this.mensajeAtaque(infoHabilidad.getDanio(), infoHabilidad.beneficiadoPorClima());
            case CLIMA -> resultado += this.mensajeResultadoClima(infoHabilidad.getClimaModificado());
            case ESTADO -> resultado += this.mensajeResultadoEstado(infoHabilidad.getEstadoModificado());
            case ESTADISTICA -> resultado += this.mensajeResultadoEstadistica(infoHabilidad.getEstadisticaModificada(), infoHabilidad.getJugadorAfectado());
        }
        return resultado;
    }

    public String mensajeAtaque(double danio, boolean beneficiado) {
        String resultado = "";
        if (beneficiado) {
            resultado += "El ataque ha sido beneficiado por el clima actual. ";
        }
        if (danio > 0) {
            return resultado + "¡Qué eficaz!";
        } else {
            return resultado + "No parece hacer mucho efecto.";
        }
    }

    public String mensajeResultadoClima(Clima clima) {
        return "Ahora el clima es " + clima.name().toLowerCase();
    }

    public String mensajeResultadoEstado(Estado estado) {
        return "Ahora el enemigo se encuentra " + estado.name().toLowerCase();
    }

    public String mensajeResultadoEstadistica(Estadisticas estadisticas, JugadorEnum jugadorAfectado) {
        if (jugadorAfectado == JugadorEnum.ACTUAL) {
            return "Su " + estadisticas.name().toLowerCase() + " ha aumentado";
        } else {
            return "La " + estadisticas.name().toLowerCase() + " del rival ha disminuido";
        }
    }

    public void mostrarCambioDePokemon() {
        Optional<Error> err = this.batalla.cambiarPokemon(this.pokemonSeleccionado);
        this.setTextoProperty("antes del if");
        if (err.isEmpty()) {
            this.setTextoProperty("Cambiaste tu pokemon a " + this.pokemonSeleccionado.getNombre() + "!");
            this.campoController.aplicarCambioPokemon();
        } else {
            this.setTextoProperty(err.get().mostrar());
        }

        this.campoController.aplicarCambioPokemon();
        this.pokemonSeleccionado = null;
        this.pane.setOnMouseClicked(event -> {
            cambiarMenuPrincipal(event, true);
        });
    }

    public void mostrarItemAplicado() {
        Optional<Error> err = this.batalla.usarItem(this.itemSeleccionado, this.pokemonSeleccionado);
        this.setTextoProperty("antes del if");
        if (err.isEmpty()) {
            this.setTextoProperty("Se aplicó " + this.itemSeleccionado.getNombre() + " a " + this.pokemonSeleccionado.getNombre());
            System.out.println("Se aplico " + this.itemSeleccionado.getNombre() + " a " + this.pokemonSeleccionado.getNombre());
            System.out.println(this.texto );
            this.campoController.aplicarItem(this.pokemonSeleccionado);
            this.campoController.actualizar();
        } else {
            this.setTextoProperty(err.get().mostrar());
            this.pane.setOnMouseClicked(event -> {
                cambiarMenuPrincipal(event, false);
            });
            return;
        }

        this.pokemonSeleccionado = null;
        this.itemSeleccionado = null;

        this.pane.setOnMouseClicked(event -> {
            cambiarMenuPrincipal(event, true);
        });
    }

    public void setPokemonSeleccionado(Pokemon pokemon) {
        this.pokemonSeleccionado = pokemon;
    }

    public void setItemSeleccionado(Item item) {
        this.itemSeleccionado = item;
    }


    private void actualizarPokemones() {
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
}
