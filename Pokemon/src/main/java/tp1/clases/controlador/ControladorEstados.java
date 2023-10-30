package tp1.clases.controlador;

import tp1.clases.modelo.*;

import java.util.HashMap;
import java.util.HashSet;

public class ControladorEstados {

    private Batalla batalla;

    public ControladorEstados(Batalla batalla) {
        this.batalla = batalla;
    }

    public void controlarEstados(Jugador jugador) {
        Pokemon pokemon = jugador.getPokemonActual();

        pokemon.aplicarEfectoEstados();
        this.batalla.getJugadorSiguiente().getPokemonActual().aplicarEfectoEstados();

    }

}
