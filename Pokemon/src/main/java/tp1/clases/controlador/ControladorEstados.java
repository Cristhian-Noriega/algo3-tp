package tp1.clases.controlador;

import tp1.clases.modelo.*;

import java.util.HashMap;

public class ControladorEstados {
//    private final HashMap<Jugador, Integer> turnoInicialEstados;
    private Batalla batalla;

    public ControladorEstados(Batalla batalla) {
        this.batalla = batalla;
//        this.turnoInicialEstados = new HashMap<Jugador, Integer>();
//        for (Jugador jugador : batalla.getJugadores()){
//            this.turnoInicialEstados.put(jugador, 0);
//        }
    }
//controlador estados deberia controlar solo los estados que posea el pokemon en ese momento
//    public void setTurnoInicial(Jugador jugador, int turnoActual) {
//        this.turnoInicialEstados.put(jugador, turnoActual);
//    }

    public boolean controlarEstado(Jugador jugador, int turnoActual){
        Pokemon pokemon = jugador.getPokemonActual();
        if (pokemon.tieneEstado(Estado.PARALIZADO)){
            pokemon.aplicarDecorador(new ParalizadoDecorator(pokemon));
        }
        if (pokemon.tieneEstado(Estado.ENVENENADO)){
            pokemon.aplicarDecorador(new EnvenenadoDecorator(pokemon));
        }
        if (pokemon.tieneEstado(Estado.DORMIDO)){
            pokemon.aplicarDecorador(new ParalizadoDecorator(pokemon));
        }
        if (pokemon.tieneEstado(Estado.CONFUNDIDO)){
            pokemon.aplicarDecorador(new ConfundidoDecorator(pokemon));
        }


        //controlador de estados controlatods los estados del pokemon
        this.despertar(jugador, turnoActual);
        this.envenenar(jugador.getPokemonActual());
        return this.paralizar(jugador.getPokemonActual());
    }

    public void despertar(Jugador jugador, int turnoActual) {
        Pokemon pokemon = jugador.getPokemonActual();
        if (pokemon.getEstado() != Estado.DORMIDO) {
            return;
        }

        if (Habilidad.probabilidad(Constantes.veinticinco * (turnoActual - turnoInicialEstados.get(jugador)))) {
            pokemon.setEstado(Estado.NORMAL);
        }
    }

    public void envenenar(Pokemon pokemon) {
        if (pokemon.getEstado() != Estado.ENVENENADO) {
            return;
        }

        double valor = Constantes.cinco * pokemon.getVidaMax();
        pokemon.modificarVida((-1)*valor);
    }

    public boolean paralizar(Pokemon pokemon) {
        if (pokemon.getEstado() != Estado.PARALIZADO) {
            return true;
        }
        return (Habilidad.probabilidad(Constantes.probabilidadParalizado));
    }

    public boolean confundir(Pokemon pokemon){
        return true;

    }

}
