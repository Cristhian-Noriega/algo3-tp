package tp1.clases.modelo;

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

    public void selecconarPokemon(int pokeElegido) {
        if (!this.verificarPokemon(pokeElegido)) {
            throw error.ErrorPokemonInvalido();
        }
        this.pokemonActual = this.pokemones.get(pokeElegido);
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

    public Optional<String> usarItem(int itemElegido) {
        if (!this.verificarItem(itemElegido)) {
            return Optional.of("Este item no esta disponible");
        }

        Item item = this.items.get(itemElegido);
        item.usar(this.pokemonActual);
        return Optional.empty();
    }

    private boolean verificarPokemon(int pokeElegido) {
        return pokeElegido <= this.pokemones.size() && !this.pokemones.get(pokeElegido).estaMuerto();
    }

    private boolean verificarItem(int itemElegido){
        return itemElegido <= this.items.size();
    }
}