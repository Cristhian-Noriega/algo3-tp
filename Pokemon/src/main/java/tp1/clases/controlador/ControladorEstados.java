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

//        if ((!pokemon.tieneDecorador(ParalizadoDecorator.class)) && (pokemon.tieneEstado(Estado.PARALIZADO))) {
//            pokemon.agregarDecorador(ParalizadoDecorator.class);
//            pokemon = new ParalizadoDecorator(pokemon);
//            jugador.setPokemonActual(pokemon);
//        }
//        if ((!pokemon.tieneDecorador(EnvenenadoDecorator.class)) && (pokemon.tieneEstado(Estado.ENVENENADO))) {
//            System.out.println("ENTRE A CREAR UN ENVENENADO Y AGREGARLE AL POKEMON");
//            pokemon.agregarDecorador(EnvenenadoDecorator.class);
//            pokemon = new EnvenenadoDecorator(pokemon);
//            jugador.setPokemonActual(pokemon);
//        }
//        if ((!pokemon.tieneDecorador(DormidoDecorator.class)) && (pokemon.tieneEstado(Estado.DORMIDO))) {
//            System.out.println("ENTRE A CREAR UN DORMIDO Y AGREGARLE AL POKEMON");
//            pokemon.agregarDecorador(DormidoDecorator.class);
//            pokemon = new DormidoDecorator(pokemon);
//            jugador.setPokemonActual(pokemon);
//        }
//        if ((!pokemon.tieneDecorador(ConfundidoDecorator.class)) && (pokemon.tieneEstado(Estado.CONFUNDIDO))) {
//            pokemon.agregarDecorador(ConfundidoDecorator.class);
//            pokemon = new ConfundidoDecorator(pokemon);
//            jugador.setPokemonActual(pokemon);
//        }
//
//
//
//    }


}
