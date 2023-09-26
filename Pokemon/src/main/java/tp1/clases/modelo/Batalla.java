package tp1.clases.modelo;

import java.util.ArrayList;
import java.util.Optional;

public class Batalla {
    private ArrayList<Jugador> jugadores;
    private int turno;

    public Batalla(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        determinarJugadorInicial();
    }

    private void determinarJugadorInicial() {
        int primero = 0;
        int mayorVelocidad = 0;
        for (int i = 0; i < this.jugadores.size(); i++) {
         if (this.jugadores.get(i).getPokemonActual().getVelocidad() > mayorVelocidad) {
             primero = i;
             mayorVelocidad = this.jugadores.get(i).getPokemonActual().getVelocidad();
         }
        }

        this.turno = primero;
    }

    public Optional<Jugador> obtenerGanador() {
       ArrayList<Jugador> jugadoresConVida = new ArrayList<>();
        for (Jugador jugador : this.jugadores) {
            if (jugador.tienePokemonesConVida()) {
                jugadoresConVida.add(jugador);
            }
        }
        if (jugadoresConVida.size() ==  1) {
            return Optional.of(jugadoresConVida.get(0));
        } else {
            return Optional.empty();
        }
    }

    public void cambiarTurno() {
        this.turno += 1;
    }

    public Jugador getTurno() {
        return this.jugadores.get(this.turno % this.jugadores.size());
    }


}