package tp1.clases;

import tp1.clases.modelo.*;

import java.util.HashMap;
import java.util.List;

public class ControladorEstados {
    private final HashMap<Jugador, Integer> turnoInicialEstados;

    public ControladorEstados(List<Jugador> jugadores) {
        this.turnoInicialEstados = new HashMap<Jugador, Integer>();
        for (Jugador jugador : jugadores){
            this.turnoInicialEstados.put(jugador, 0);
        }
    }

    public void setTurnoInicial(Jugador jugador, int turnoActual) {
        this.turnoInicialEstados.put(jugador, turnoActual);
    }

    public boolean controlarEstado(Pokemon pokemon, Jugador jugador, int turnoActual){
        this.despertar(pokemon, jugador, turnoActual);
        this.envenenar(pokemon);
        return this.paralizar(pokemon);
    }

    public void despertar(Pokemon pokemon, Jugador jugador, int turnoActual) {
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
        System.out.println("vida a restar por envenenado " + valor);
        pokemon.modificarVida((-1)*valor);
    }

    public boolean paralizar(Pokemon pokemon) {
        if (pokemon.getEstado() != Estado.PARALIZADO) {
            return true;
        }
        return (Habilidad.probabilidad(Constantes.probabilidadParalizado));
    }

}
