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
    private Clima clima;

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
        if (numeroHabilidad < 0 || numeroHabilidad >= this.getHabilidadesPokemonActual().size()) {
            return Optional.of(new ErrorIndiceFueraDeRango());
        }
        Habilidad habilidad = getHabilidadesPokemonActual().get(numeroHabilidad);
        return habilidad.usar(this.getJugadorActual().getPokemonActual(), rival.getPokemonActual());
    }

    public Optional<Error> usarItem(int itemElegido, int pokemon) {
        if (itemElegido < 0 || itemElegido >= this.getItemsJugadorActual().size()) {
            return Optional.of(new ErrorIndiceFueraDeRango());
        }
        Item item = this.getItemsJugadorActual().get(itemElegido);
        if (this.getMapItemsJugadorActual().get(item.getNombre()) <= 0 ){
            return Optional.of(new ErrorItemNoValido(item.getNombre()));
        }
        Optional<Error> err = item.usar(this.getPokemonesJugadorActual().get(pokemon));
        if (err.isEmpty()){
            this.getJugadorActual().eliminarItem(item);
        }
        return err;
    }

    public Optional<Error> cambiarPokemon(int pokemon) {
        if (pokemon < 0 | pokemon >= this.getPokemonesJugadorActual().size()) {
            return Optional.of(new ErrorIndiceFueraDeRango());
        }
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
    }
}