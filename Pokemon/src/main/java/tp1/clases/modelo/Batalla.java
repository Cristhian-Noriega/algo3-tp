package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorPokemonMuerto;

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
        for (int i = 0; i < this.jugadores.size(); i++) {
            if (this.jugadores.get(i).getVelocidadPokemonActual() > mayorVelocidad) {
                primero = i;
                mayorVelocidad = this.jugadores.get(i).getVelocidadPokemonActual();            }
        }

        this.turno = primero;
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

    public List<Pokemon> getPokemonesJugadorActual(){
        return this.getJugadorActual().getListaPokemones();
    }

    public List<Habilidad> getHabilidadesPokemonActual() {
        return this.getJugadorActual().getHabilidadesPokemonActual();
    }

    public List<Item> getItemsJugadorActual() {
        return this.getJugadorActual().getListaItems();
    }

    public void usarAtaque(int habilidadElegida) {
        Habilidad habilidad = getHabilidadesPokemonActual().get(habilidadElegida); //perdon
        habilidad.usar( this.getJugadorActual().getPokemonActual(), this.jugadores.get((this.turno+1) % this.jugadores.size()).getPokemonActual());
    }

    public void usarItem(int itemElegido) {
        Item item = getItemsJugadorActual().get(itemElegido);
        item.usar( this.getJugadorActual().getPokemonActual());
    }

    public Optional<Error> cambiarPokemon(int pokemon) {
        //ejemplo unicamente, borrar despues:
        if (pokemon == 1) {
            return Error.ErrorPokemonMuerto();
        }
        
        return this.getJugadorActual().seleccionarPokemon(pokemon);
    }

}