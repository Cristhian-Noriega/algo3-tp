package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorPokemonMuerto;

import java.util.List;
import java.util.Optional;

public class Jugador {

    private final List<Pokemon> pokemones;
    private Pokemon pokemonActual;
    private final List<Item> items;
    private final String nombre;

    public Jugador(String nombre, List<Pokemon> pokemones, List<Item> items) {
        this.nombre = nombre;
        this.pokemones = pokemones;
        this.items = items;
    }

    public Pokemon getPokemonActual() {
        return pokemonActual;
    }

    public List<Pokemon> getListaPokemones() {
        return pokemones;
    }

    public List<Item> getListaItems() {
        return items;
    }

    public String getNombre() {
        return nombre;
    }

    public Optional<Error> seleccionarPokemon(int pokeElegido){
        Pokemon nuevoPokemon = this.pokemones.get(pokeElegido);
        if (nuevoPokemon.estaMuerto()){
            return Optional.of(new ErrorPokemonMuerto(nuevoPokemon.getNombre()));
        }
        this.pokemonActual = nuevoPokemon;
        return Optional.empty();
    }

    public boolean tienePokemonesConVida() {
        for (Pokemon pokemon : this.pokemones) {
            if (pokemon.getVida() > 0) {
                return true;
            }
        }
        return false;
    }

    public int getVelocidadPokemonActual() {
        return this.pokemonActual.getVelocidad();
    }

    public List<Habilidad> getHabilidadesPokemonActual() {
        return this.pokemonActual.getHabilidades();
    }

}