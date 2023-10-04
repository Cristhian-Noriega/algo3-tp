package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorIndiceFueraDeRango;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Batalla {
    private final ArrayList<Jugador> jugadores;
    private int turno;

    public Batalla(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        determinarJugadorInicial();
    }

    // se puede borrar este metodo? nadie lo usa
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    private void determinarJugadorInicial() {
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

    public Optional<String> obtenerGanador() {
        List<Jugador> jugadoresConVida = jugadores.stream()
                .filter(Jugador::tienePokemonesConVida)
                .toList();
        return jugadoresConVida.size() == 1 ? Optional.of(jugadoresConVida.get(0).getNombre()) : Optional.empty();
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

    public void rendir(Jugador jugador) {
        this.jugadores.remove(jugador);
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

    public Optional<Error> usarItem(int itemElegido) {
        if (itemElegido < 0 || itemElegido >= this.getItemsJugadorActual().size()) {
            return Optional.of(new ErrorIndiceFueraDeRango());
        }
        Item item = this.getItemsJugadorActual().get(itemElegido);
        return item.usar(this.getJugadorActual().getPokemonActual());
    }

    public Optional<Error> cambiarPokemon(int pokemon) {
        if (pokemon < 0 | pokemon >= this.getPokemonesJugadorActual().size()) {
            return Optional.of(new ErrorIndiceFueraDeRango());
        }
        return this.getJugadorActual().seleccionarPokemon(pokemon);
    }

    public Map<String, Object> getDatosJugadorActual(){
        return this.getJugadorActual().getDatos();
    }

    public List<Map<String, Object>> getDatosJugadores(){
        List<Map<String, Object>> datos = new ArrayList<>();
        for (Jugador jugador: this.jugadores) {
            datos.add(jugador.getDatos());
        }
        return datos;
    }
}