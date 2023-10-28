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

//        EstadosComposite estadosComposite = new EstadosComposite(pokemon);
        if (pokemon.tieneEstado(Estado.PARALIZADO)) {
            pokemon = new ParalizadoDecorator(pokemon);
            jugador.setPokemonActual(pokemon);
            System.out.println(jugador.getPokemonActual().getVida());
//            estadosComposite.agregarDecorator(new ParalizadoDecorator(pokemon));
//            jugador.setPokemonActual(estadosComposite);
        }
        if (pokemon.tieneEstado(Estado.ENVENENADO)) {
            pokemon = new EnvenenadoDecorator(pokemon);
            jugador.setPokemonActual(pokemon);
//            estadosComposite.agregarDecorator(new EnvenenadoDecorator(pokemon));
//            jugador.setPokemonActual(estadosComposite);
        }
        if (pokemon.tieneEstado(Estado.DORMIDO)) {
            pokemon = new DormidoDecorator(pokemon);
            jugador.setPokemonActual(pokemon);
//            estadosComposite.agregarDecorator(new DormidoDecorator(pokemon));
//            jugador.setPokemonActual(estadosComposite);
        }
        if (pokemon.tieneEstado(Estado.CONFUNDIDO)) {
            pokemon = new ConfundidoDecorator(pokemon);
            jugador.setPokemonActual(pokemon);
//            estadosComposite.agregarDecorator(new ConfundidoDecorator(pokemon));
//            jugador.setPokemonActual(estadosComposite);
        }
//        jugador.getPokemonActual().aplicarEfectoEstado();
        jugador.getPokemonActual().aplicarEfectoEstado();
        this.batalla.getJugadorSiguiente().getPokemonActual().aplicarEfectoEstado();
    }
}



