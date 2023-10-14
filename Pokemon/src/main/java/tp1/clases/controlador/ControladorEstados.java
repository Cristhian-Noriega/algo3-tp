package tp1.clases.controlador;

import tp1.clases.modelo.*;

import java.util.HashMap;

public class ControladorEstados {
    private final HashMap<Jugador, Integer> turnoInicialEstados;
    private Batalla batalla;

    public ControladorEstados(Batalla batalla) {
        this.batalla = batalla;
        this.turnoInicialEstados = new HashMap<Jugador, Integer>();
        for (Jugador jugador : batalla.getJugadores()){
            this.turnoInicialEstados.put(jugador, 0);
        }
    }

    public void setTurnoInicial(Jugador jugador) {
        this.turnoInicialEstados.put(jugador, batalla.getTurno());
    }

    public boolean controlarEstado(Jugador jugador){
        this.despertar(jugador);
        this.envenenar(jugador.getPokemonActual());
        return this.paralizar(jugador.getPokemonActual());
    }

    public boolean despertar(Jugador jugador) {
        Pokemon pokemon = jugador.getPokemonActual();
        if (pokemon.getEstado() != Estado.DORMIDO) {
            return true;
        }

        if (Random.probabilidad(Constantes.probabilidadDespertar * (batalla.getTurno() - turnoInicialEstados.get(jugador)))) {
            pokemon.setEstado(Estado.NORMAL);
        }

        return pokemon.getEstado() == Estado.NORMAL;
    }

    public void envenenar(Pokemon pokemon) {
        if (pokemon.getEstado() != Estado.ENVENENADO) {
            return;
        }

        double valor = Constantes.porcentajeDeEnvenamiento * pokemon.getVidaMax();
        pokemon.modificarVida((-1)*valor);
    }

    public boolean paralizar(Pokemon pokemon) {
        if (pokemon.getEstado() != Estado.PARALIZADO) {
            return true;
        }
        return (Random.probabilidad(Constantes.probabilidadParalizado));
    }

}
