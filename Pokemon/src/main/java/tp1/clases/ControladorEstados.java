package tp1.clases;

import tp1.clases.modelo.Constantes;
import tp1.clases.modelo.Estado;
import tp1.clases.modelo.Habilidad;
import tp1.clases.modelo.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class ControladorEstados {
    private final List<Integer> turnoInicialEstados;

    public ControladorEstados() {
        this.turnoInicialEstados = new ArrayList<Integer>();
    }

    public void setTurnoInicial(int jugador, int turnoActual) {
        this.turnoInicialEstados.set(jugador, turnoActual); //no estoy segura si este es el método que quiero usar
    }

    public boolean controlarEstado(Pokemon pokemon, int jugador, int turnoActual){
        this.despertar(pokemon, jugador, turnoActual);
        this.envenenar(pokemon);
        return this.paralizar(pokemon);
    }

    public void despertar(Pokemon pokemon, int jugador, int turnoActual) {
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
}
