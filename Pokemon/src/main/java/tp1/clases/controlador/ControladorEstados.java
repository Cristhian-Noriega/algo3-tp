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
            jugador.setPokemonActual(new ParalizadoDecorator(pokemon));
        }
        if (pokemon.tieneEstado(Estado.ENVENENADO)) {
            jugador.setPokemonActual(new EnvenenadoDecorator(pokemon));
        }
        if (pokemon.tieneEstado(Estado.DORMIDO)) {
            jugador.setPokemonActual(new DormidoDecorator(pokemon));
        }
        if (pokemon.tieneEstado(Estado.CONFUNDIDO)) {
            jugador.setPokemonActual(new ConfundidoDecorator(pokemon));
        }
        jugador.getPokemonActual().aplicarEfectoEstado();
        this.batalla.getJugadorSiguiente().getPokemonActual().aplicarEfectoEstado();
    }





        //controlador de estados controlatods los estados del pokemon
//        this.despertar(jugador, turnoActual);
//        this.envenenar(jugador.getPokemonActual());
//        return this.paralizar(jugador.getPokemonActual());
    }

//    public void despertar(Jugador jugador, int turnoActual) {
//        Pokemon pokemon = jugador.getPokemonActual();
//        if (pokemon.getEstado() != Estado.DORMIDO) {
//            return;
//        }
//
//        if (Habilidad.probabilidad(Constantes.veinticinco * (turnoActual - turnoInicialEstados.get(jugador)))) {
//            pokemon.setEstado(Estado.NORMAL);
//        }
//    }
//
//    public void envenenar(Pokemon pokemon) {
//        if (pokemon.getEstado() != Estado.ENVENENADO) {
//            return;
//        }
//
//        double valor = Constantes.cinco * pokemon.getVidaMax();
//        pokemon.modificarVida((-1)*valor);
//    }
//
//    public boolean paralizar(Pokemon pokemon) {
//        if (pokemon.getEstado() != Estado.PARALIZADO) {
//            return true;
//        }
//        return (Habilidad.probabilidad(Constantes.probabilidadParalizado));
//    }
//
//    public boolean confundir(Pokemon pokemon){
//        return true;
//
//    }


