package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Batalla {
    private final ArrayList<Jugador> jugadores;
    private final ArrayList<Jugador> rendidos;
    private final AdministradorDeTurnos administradorTurnos;
    private final AdministradorDeClima administradorDeClima;
    private InfoTurno infoTurno;
    private final AdministradorDeEstados administradorDeEstados;

    public Batalla(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.administradorTurnos = new AdministradorDeTurnos(jugadores);
        this.administradorDeClima = new AdministradorDeClima();
        this.administradorDeEstados = new AdministradorDeEstados(this);
        this.rendidos = new ArrayList<Jugador>();
        this.infoTurno = new InfoTurno();
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public InfoTurno getInfoTurno() {
        return this.infoTurno;
    }

    public Jugador getJugadorActual() {
        return administradorTurnos.getJugadorActual();
    }

    public Jugador getJugadorSiguiente() {
        return administradorTurnos.getJugadorSiguiente();
    }

    public ArrayList<Jugador> getRendidos(){
        return this.rendidos;
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

    public Clima getClima(){
        return administradorDeClima.getClimaActual();
    }

    public AdministradorDeTurnos getAdministradorTurnos() {
        return administradorTurnos;
    }

    public void cambiarTurno() {
        this.infoTurno.resetearInfo();
        this.administradorDeClima.afectarJugadores(this.getJugadores(), this.infoTurno);
        this.administradorTurnos.siguienteTurno();
        this.administradorDeClima.ActualizarTurno();
        this.administradorDeEstados.aplicarEfectoEstado(this.infoTurno);
    }

    public void rendir(Jugador jugador) {
        this.jugadores.remove(jugador);
        this.rendidos.add(jugador);
    }

    public Optional<String> obtenerGanador() {
        List<Jugador> jugadoresConVida = jugadores.stream()
                .filter(Jugador::tienePokemonesConVida)
                .toList();
        if (jugadoresConVida.size() == 1){
            this.rendidos.add(getJugadorSiguiente());
        }
        return jugadoresConVida.size() == 1 ? Optional.of(jugadoresConVida.get(0).getNombre()) : Optional.empty();
    }

    public Optional<Error> usarHabilidad(Habilidad habilidad, Jugador rival) {
        Pokemon pokemonJugadorActual = this.getJugadorActual().getPokemonActual();
        Pokemon pokemonJugadorRival = rival.getPokemonActual();
        return pokemonJugadorActual.usarHabilidad(habilidad, pokemonJugadorRival, administradorDeClima);
    }

    public Optional<Error> usarItem(Item itemElegido, Pokemon pokemon) {
        return this.getJugadorActual().usarItem(itemElegido, pokemon);
    }

    public Optional<Error> cambiarPokemon(Pokemon pokemon) {
        return this.getJugadorActual().seleccionarPokemon(pokemon);
    }

    public void cambiarPokemonMuertoJugadorSiguiente(){
        this.getJugadorSiguiente().cambiarPokemonMuerto();
    }

}