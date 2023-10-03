package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorIndiceFueraDeRango;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Batalla {
    private final ArrayList<Jugador> jugadores;
    private int turno;

    public Batalla(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        determinarJugadorInicial();
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    private void determinarJugadorInicial() {
        int primero = 0;
        int mayorVelocidad = 0;
        for (int i = 0; i < this.jugadores.size(); i++) {
            if (this.jugadores.get(i).getVelocidadPokemonActual() > mayorVelocidad) {
                primero = i;
                mayorVelocidad = this.jugadores.get(i).getVelocidadPokemonActual();
            }
        }

        this.turno = primero;
    }


    public Optional<Jugador> obtenerGanador() {
        List<Jugador> jugadoresConVida = jugadores.stream()
                .filter(Jugador::tienePokemonesConVida)
                .toList();
        return jugadoresConVida.size() == 1 ? Optional.of(jugadoresConVida.get(0)) : Optional.empty();
    }

    public void cambiarTurno() {
        this.turno += 1;
    }

    public Jugador getJugadorActual() {
        return this.jugadores.get(this.turno % this.jugadores.size());
    }

    public Jugador getJugadorSiguiente() {
        return this.jugadores.get((this.turno + 1) % this.jugadores.size());
    }

    public Jugador rendir(Jugador jugador) {
        this.jugadores.remove(jugador);
        return this.jugadores.get(0);
    }

    public List<Pokemon> getPokemonesJugadorActual() {
        return this.getJugadorActual().getListaPokemones();
    }

    public List<Habilidad> getHabilidadesPokemonActual() {
        return this.getJugadorActual().getHabilidadesPokemonActual();
    }

    public List<Item> getItemsJugadorActual() {
        return this.getJugadorActual().getListaItems();
    }

    public Optional<Error> usarHabilidad(int numeroHabilidad, Jugador rival) {
        if (numeroHabilidad < 0 || numeroHabilidad >= this.getHabilidadesPokemonActual().size()) {
            return Optional.of(new ErrorIndiceFueraDeRango());
        }
        Habilidad habilidad = getHabilidadesPokemonActual().get(numeroHabilidad);
        return habilidad.usar(this.getJugadorActual().getPokemonActual(), rival.getPokemonActual());
    }

    public Optional<Error> usarHabilidad(int numeroHabilidad) {
        return this.usarHabilidad(numeroHabilidad, this.getJugadorSiguiente());
    }

    //es ese wrapper o lo siguiente, no sé cual es mejor:
    //public void usarHabilidad(int numeroHabilidad) {
    //    Habilidad habilidad = getHabilidadesPokemonActual().get(numeroHabilidad);
    //    habilidad.usar(this.getJugadorActual().getPokemonActual(), this.getJugadorSiguiente().getPokemonActual());
    //}
    //básicamente es lo mismo, pero quizás no les gusta tener las dos funciones. A mi me parece que queda bien (?)

    public Optional<Error> usarItem(int itemElegido) {
        if (itemElegido < 0 || itemElegido >= this.getItemsJugadorActual().size()) {
            return Optional.of(new ErrorIndiceFueraDeRango());
        }
        Item item = this.getItemsJugadorActual().get(itemElegido);
        item.usar(this.getJugadorActual().getPokemonActual());
        return Optional.empty();
    }

    public Optional<Error> cambiarPokemon(int pokemon) {
        if (pokemon < 0 | pokemon >= this.getPokemonesJugadorActual().size()) {
            return Optional.of(new ErrorIndiceFueraDeRango());
        }
        return this.getJugadorActual().seleccionarPokemon(pokemon);
    }

}