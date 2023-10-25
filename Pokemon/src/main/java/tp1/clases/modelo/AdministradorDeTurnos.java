package tp1.clases.modelo;

import java.util.List;

public class AdministradorDeTurnos {
    public List<Jugador> jugadores;
    public int turno;

    public AdministradorDeTurnos(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        determinarJugadorIncial();
    }

    public void determinarJugadorIncial() {
        int primero = 0;
        double mayorVelocidad = 0.0;
        for (int i = 0; i < this.jugadores.size(); i++) {
            if (this.jugadores.get(i).getVelocidadPokemonActual() > mayorVelocidad) {
                primero = i;
                mayorVelocidad = this.jugadores.get(i).getVelocidadPokemonActual();
            }
        }

        this.turno = primero;
    }

    public void siguienteTurno() {
        this.turno += 1;
    }

    public int getTurno() {
        return this.turno;
    }

    public Jugador getJugadorActual() {
        return this.jugadores.get(this.turno % this.jugadores.size());
    }

    public Jugador getJugadorSiguiente() {
        return this.jugadores.get((this.turno + 1) % this.jugadores.size());
    }

}


