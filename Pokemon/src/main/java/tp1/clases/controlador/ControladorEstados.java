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

    public void setTurnoInicial(Jugador jugador, int turnoActual) {
        this.turnoInicialEstados.put(jugador, turnoActual);
    }

    public boolean controlarEstado(Jugador jugador, int turnoActual){
        this.despertar(jugador, turnoActual);
        this.envenenar(jugador.getPokemonActual());
        return this.paralizar(jugador.getPokemonActual());
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void despertar(Jugador jugador, int turnoActual) {
=======
    public boolean despertar(Jugador jugador) {
>>>>>>> 270de4e (bosquejo inicial climas)
=======
    public boolean despertar(Jugador jugador) {
>>>>>>> 4f5c1277241255de37e7d486195a94fc4e99bf77
        Pokemon pokemon = jugador.getPokemonActual();
        if (pokemon.getEstado() != Estado.DORMIDO) {
            return true;
        }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        if (Habilidad.probabilidad(Constantes.veinticinco * (turnoActual - turnoInicialEstados.get(jugador)))) {
=======
        if (Random.probabilidad(Constantes.veinticincoPorCiento * (batalla.getTurno() - turnoInicialEstados.get(jugador)))) {
>>>>>>> 270de4e (bosquejo inicial climas)
=======
        if (Random.probabilidad(Constantes.probabilidadDespertar * (batalla.getTurno() - turnoInicialEstados.get(jugador)))) {
>>>>>>> f6730ce (cambio constantes)
            pokemon.setEstado(Estado.NORMAL);
        }

<<<<<<< HEAD
=======
=======
        if (Random.probabilidad(Constantes.probabilidadDespertar * (batalla.getTurno() - turnoInicialEstados.get(jugador)))) {
            pokemon.setEstado(Estado.NORMAL);
        }

>>>>>>> 4f5c1277241255de37e7d486195a94fc4e99bf77
        return pokemon.getEstado() == Estado.NORMAL;
    }

>>>>>>> 270de4e (bosquejo inicial climas)
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
