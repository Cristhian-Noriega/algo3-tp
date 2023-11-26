package tp1.clases.controlador;

import javafx.animation.PauseTransition;
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
import java.util.Optional;

public class ControladorPantallaEfecto implements Controlador {
    private Batalla batalla;
    private Habilidad habilidadSeleccionada;
    private Pokemon pokemonSeleccionado;
    private Item itemSeleccionado;
    private ArrayList<Pokemon> pokemones;
    private String texto = "Efecto";
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
        this.labelTexto.textProperty().bind(textoProperty);
        this.labelTexto.setWrapText(true);
        this.pane.setOnMouseMoved(this::cambiarMenuPrincipal);
    }

    @Override
    public void actualizarCampo() {
        System.out.println("se actualiza desde efecto");
    }

    public boolean mostrarAtaque() {
        this.setPokemones();

        Optional<Error> err = this.batalla.usarHabilidad(this.habilidadSeleccionada, this.batalla.getJugadorSiguiente());
        InfoHabilidad infoHabilidad = this.habilidadSeleccionada.getInfoHabilidad();

        if (err.isPresent()) {
            this.setTextoProperty(err.get().mostrar());
            return false;
        }

        if (!infoHabilidad.sePudoUsarHabilidad()) {
            this.setTextoProperty(pokemones.get(0).getNombre() + " no pudo usar la habilidad porque se encuentra " + infoHabilidad.getEstadoLimitante().toString().toLowerCase());
            return true;
        }

        String resultado = this.mostrarResultadoAtaque(infoHabilidad, habilidadSeleccionada.getNombre(), pokemones.get(0).getNombre());
        this.setTextoProperty(resultado);

        this.campoController.aplicarParpadeo(infoHabilidad.getJugadorAfectado());
        if ((infoHabilidad.getCategoria() == Categoria.ATAQUE) | ((infoHabilidad.getCategoria() == Categoria.ESTADISTICA) && (infoHabilidad.getEstadisticaModificada() == Estadisticas.VIDA))) {
            this.campoController.animarVida(pokemones.get(infoHabilidad.getJugadorAfectado().ordinal()));
        }

        //this.campoController.actualizar();
        return true;
    }

    private String mostrarResultadoAtaque(InfoHabilidad infoHabilidad, String habilidad, String pokemonAtacante) {
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


    private void setPokemones() {
        this.pokemones = new ArrayList<>();
        for (Jugador jugador: this.batalla.getJugadores()) {
            this.pokemones.add(jugador.getPokemonActual());
        }
    }

    public void cambiarMenuPrincipal(MouseEvent mouseEvent) {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));

        pause.setOnFinished(event -> {
            this.labelTexto.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
        });

        pause.play();
    }

    public void setTextoProperty(String textoProperty) {
        this.textoProperty.set(textoProperty);
    }

    public void setHabilidadSeleccionada(Habilidad habilidad) {
        this.habilidadSeleccionada = habilidad;
    }
}
