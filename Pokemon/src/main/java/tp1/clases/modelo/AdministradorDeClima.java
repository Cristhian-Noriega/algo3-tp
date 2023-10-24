package tp1.clases.modelo;

import java.util.List;

public class AdministradorDeClima {
    private Clima climaActual;
    private int turnoInicial;

    public AdministradorDeClima() {
        this.climaActual = Clima.getClimaRandom();
    }

    public void afectarJugadores(List<Jugador> jugadores) {
        for (Jugador jugador: jugadores) {
            climaActual.lastimarPorClima(jugador.getPokemonActual());
        }
    }

    public void cambiaClima(Clima clima, int turno) {
        this.turnoInicial = turno;
        this.climaActual = clima;
    }

    public void limpiarClima() {
        this.climaActual = Clima.NORMAL;
    }

    public void mejoraAtaque(Habilidad habilidad) {
        if (habilidad.getCategoria() == Categoria.ATAQUE && this.climaActual != Clima.NORMAL) {

        }
    }
}
