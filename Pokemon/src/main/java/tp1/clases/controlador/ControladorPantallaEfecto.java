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

    @FXML public Label labelTexto;
    @FXML public ControladorCampo campoController;
    @FXML public Pane pane;

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

    public void mostrarAtaque() {
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
            this.setTextoProperty(pokemones.get(JugadorEnum.ACTUAL.ordinal()).getNombre() + " no pudo usar la habilidad porque se encuentra " + infoHabilidad.getEstadoLimitante().toString().toLowerCase());
            this.campoController.actualizar();
            this.pane.setOnMouseClicked(event -> {
                cambiarMenuPrincipal(event, true);
            });
            return;
        }

        String resultado = this.mostrarResultado(infoHabilidad, habilidad.getNombre(), pokemones.get(JugadorEnum.ACTUAL.ordinal()).getNombre());
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

    public void mostrarEfectosClimaYEstados() {
        InfoTurno infoTurno = this.batalla.getInfoTurno();

        Queue<String> colaMensajes = new ArrayDeque<String>();

        for (Pokemon pokemon: infoTurno.getPokemonesAfectadosPorClima()) {
            colaMensajes.add(pokemon.getNombre() + " fue afectado por el clima actual y su vida actual ha disminuido.");
        }

        for (Pokemon pokemon: infoTurno.getPokemonesEnvenenados()) {
             colaMensajes.add(pokemon.getNombre() + " perdió vida por estar envenenado.");
        }

        for (Map.Entry<Pokemon, Estado> entrada: infoTurno.getEstadosReseteados().entrySet()){
            colaMensajes.add(entrada.getKey().getNombre() + " perdió el estado " +  entrada.getValue().name().toLowerCase());
        }

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), event -> this.setTextoProperty(colaMensajes.poll()))
        );
        timeline.setCycleCount(colaMensajes.size());
        timeline.play();
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
        } else {
            this.setTextoProperty(err.get().mostrar());
            this.pane.setOnMouseClicked(event -> {
                cambiarMenuPrincipal(event, false);
            });
            return;
        }

        this.campoController.aplicarCambioPokemon();
        this.pokemonSeleccionado = null;
        this.pane.setOnMouseClicked(event -> {
            cambiarMenuPrincipal(event, true);
        });
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
        this.pokemones.add(this.batalla.getJugadorActual().getPokemonActual());
        this.pokemones.add(this.batalla.getJugadorSiguiente().getPokemonActual());
    }

    public void cambiarMenuPrincipal(MouseEvent event, boolean cambioTurno) {
        if (cambioTurno) {
            this.batalla.cambiarTurno();
            this.mostrarEfectosClimaYEstados();
            this.campoController.actualizar();
        }
        this.labelTexto.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
    }

    public void mostrarPokemonMuerto(JugadorEnum jugador) {
        this.setTextoProperty(this.pokemones.get(1).getNombre() + " ha muerto.");

        this.campoController.aplicarDesaparicionPokemonMuerto(jugador);

        this.labelTexto.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
    }


    public void setTextoProperty(String textoProperty) {
        this.textoProperty.set(textoProperty);
    }

    public void setHabilidadSeleccionada(Habilidad habilidad) {
        this.habilidadSeleccionada = habilidad;
    }
}
