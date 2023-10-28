package tp1.clases.controlador;

import tp1.clases.modelo.*;

import java.util.HashMap;

public class ControladorEstados {
    private Batalla batalla;

    public ControladorEstados(Batalla batalla) {
        this.batalla = batalla;

    }

    public void controlarEstados(Jugador jugador) {
        Pokemon pokemon = jugador.getPokemonActual();

        if (pokemon.tieneEstado(Estado.PARALIZADO)) {
            pokemon = new ParalizadoDecorator(pokemon);
            jugador.setPokemonActual(pokemon);
        }
        if (pokemon.tieneEstado(Estado.ENVENENADO)) {
            pokemon = new EnvenenadoDecorator(pokemon);
            jugador.setPokemonActual(pokemon);
        }
        if (pokemon.tieneEstado(Estado.DORMIDO)) {
            pokemon = new DormidoDecorator(pokemon);
            jugador.setPokemonActual(pokemon);
        }
        if (pokemon.tieneEstado(Estado.CONFUNDIDO)) {
            pokemon = new ConfundidoDecorator(pokemon);
            jugador.setPokemonActual(pokemon);
        }

        jugador.getPokemonActual().aplicarEfectoEstado();
        this.batalla.getJugadorSiguiente().getPokemonActual().aplicarEfectoEstado();
    }
}



