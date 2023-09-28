package tp1.clases.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Batalla {
    private final ArrayList<Jugador> jugadores;
    private ArrayList<Jugador> jugadoresEnJuego;
    private int turno;

    public Batalla(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.jugadoresEnJuego = jugadores;
        determinarJugadorInicial();
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    private void determinarJugadorInicial() {
        int primero = 0;
        int mayorVelocidad = 0;
        for (int i = 0; i < this.jugadoresEnJuego.size(); i++) {
         if (this.jugadoresEnJuego.get(i).getVelocidadPokemonActual() > mayorVelocidad) {
             primero = i;
             mayorVelocidad = this.jugadoresEnJuego.get(i).getVelocidadPokemonActual();
         }
        }

        this.turno = primero;
    }

    public Optional<Jugador> obtenerGanador() {
        if (this.jugadoresEnJuego.size() == 1) {
            return Optional.of(this.jugadoresEnJuego.get(0));
        }

        ArrayList<Jugador> jugadoresConVida = new ArrayList<>();
        for (Jugador jugador : this.jugadoresEnJuego) {
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

    public Jugador getJugadorActual() {
        return this.jugadores.get(this.turno % this.jugadores.size());
    }

    public void rendir(Jugador jugador) {
        this.jugadoresEnJuego.remove(jugador);
    }

    public List<Habilidad> getHabilidadesPokemonActual() {
        return this.getJugadorActual().getHabilidadesPokemonActual;
    }
    public void usarAtaque(Habilidad habilidad, Jugador jugadorRival) {
        habilidad.usar(this.getJugadorActual().getPokemonActual(), jugadorRival.getPokemonActual());
    }

    public void usarItem(Item item) {
        item.usar(this.getJugadorActual().getPokemonActual());
    }

    public void cambiarPokemon(Pokemon pokemon) {
        this.getJugadorActual().seleccionarPokemon(pokemon); //creo que seleccionar pokemon debería recibir el pokemon en lugar del índice en la lista
    }
}