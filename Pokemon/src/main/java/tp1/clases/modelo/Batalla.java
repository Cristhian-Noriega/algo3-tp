package tp1.clases.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Batalla {
    private final ArrayList<Jugador> jugadores;
    private int turno ;

    public Batalla(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores ;
        determinarJugadorInicial() ;
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

        this.turno = primero ;
    }

    public Optional<Jugador> obtenerGanador() {
        List<Jugador> jugadoresConVida =  jugadores.stream()
                .filter(Jugador::tienePokemonesConVida)
                .toList();
        return jugadoresConVida.size()  == 1 ? Optional.of(jugadoresConVida.get(0)) : Optional.empty();
    }

    public void cambiarTurno() {
        this.turno += 1;
    }

    public Jugador getJugadorActual() {
        return  this.jugadores.get(this.turno % this.jugadores.size());
    }

    public Jugador rendir(Jugador jugador) {
        this.jugadores.remove(jugador) ;
        return this.jugadores.get(0) ;
    }

    public List<Habilidad> getHabilidadesPokemonActual() {
        return this.getJugadorActual().getHabilidadesPokemonActual;    }
    public void usarAtaque(Habilidad habilidad, Jugador jugadorRival) {
        habilidad.usar( this.getJugadorActual().getPokemonActual(), jugadorRival.getPokemonActual());
    }

    public void usarItem(Item item) {
        item.usar( this.getJugadorActual().getPokemonActual());
    }

    public void cambiarPokemon(int pokemon) {
         this.getJugadorActual().seleccionarPokemon(pokemon);
    }
}