package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorIndiceFueraDeRango;
import tp1.clases.errores.ErrorItemNoValido;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Batalla {
    private final ArrayList<Jugador> jugadores;
    private final AdministradorDeTurnos administradorTurnos;

    public Batalla(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.administradorTurnos = new AdministradorDeTurnos(jugadores);
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Optional<String> obtenerGanador() {
        List<Jugador> jugadoresConVida = jugadores.stream()
                .filter(Jugador::tienePokemonesConVida)
                .toList();
        return jugadoresConVida.size() == 1 ? Optional.of(jugadoresConVida.get(0).getNombre()) : Optional.empty();
    }

    public void cambiarTurno() {
        administradorTurnos.siguienteTurno();
    }

    public Jugador getJugadorActual() {
        return administradorTurnos.getJugadorActual();
    }

    public Jugador getJugadorSiguiente() {
        return administradorTurnos.getJugadorSiguiente();
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

    public List<Item> getItemsJugadorActual(){
        return this.getJugadorActual().getListaItems();
    }

    public Map<String, Long> getMapItemsJugadorActual() {
        return this.getJugadorActual().getMapCantidadItems();
    }

    public Optional<Error> usarHabilidad(int numeroHabilidad, Jugador rival) {
        Pokemon pokemonJugadorActual = this.getJugadorActual().getPokemonActual();
        Pokemon pokemonJugadorRival = rival.getPokemonActual();
        return pokemonJugadorActual.usarHabilidad(numeroHabilidad, pokemonJugadorRival);
    }

    public Optional<Error> usarItem(int itemElegido, int pokemon) {
        return this.getJugadorActual().usarItem(itemElegido, pokemon);

    }

    public Optional<Error> cambiarPokemon(int pokemon) {
        return this.getJugadorActual().seleccionarPokemon(pokemon);
    }

    public List<Map<String, Object>> getDatosJugadores(){
        List<Map<String, Object>> datos = new ArrayList<>();
        for (Jugador jugador: this.jugadores) {
            datos.add(jugador.getDatos());
        }
        return datos;
    }

    public boolean estaMuertoPokemonActual(){
        return this.getJugadorActual().getPokemonActual().estaMuerto();
    }

    public int getTurno(){
        return administradorTurnos.getTurno(); //esta mal esto, porque con el
        //admin de turnos no tiene sentido hacer batalla.get turno,  lo dejo momentaneamente
        //aunque si lo queremos usar en controlador estados(el unico lugar que se usa),
        // deberiamos tener el admin de turnos en controlador estados
        //solo por este metodo, raro
    }
}