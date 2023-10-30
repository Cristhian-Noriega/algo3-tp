package tp1.clases.modelo;

import java.util.List;

public class AdministradorDeClima {
    private Clima climaActual;
    private int turnoInicial;
    private int turnoActual;

    public AdministradorDeClima() {
        this.climaActual = Clima.getClimaRandom();
    }

    public Clima getClimaActual() {
        return this.climaActual;
    }

    public void ActualizarTurno(){
        if (this.turnoActual - this.turnoInicial >= 5) {
            this.limpiarClima();
        }
        this.turnoActual += 1;
    }

    public void afectarJugadores(List<Jugador> jugadores) {
        for (Jugador jugador: jugadores) {
            climaActual.lastimarPorClima(jugador.getPokemonActual());
        }
    }

    public void cambiarClima(Clima clima) {
        this.turnoInicial = this.turnoActual;
        this.climaActual = clima;
    }

    public void limpiarClima() {
        this.climaActual = Clima.SIN_CLIMA;
    }
}