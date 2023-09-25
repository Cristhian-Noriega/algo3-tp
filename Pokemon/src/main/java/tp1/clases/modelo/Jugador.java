package tp1.clases.modelo;

import java.util.List;
import java.util.Optional;

public class Jugador {

    private final List<Pokemon> pokemones;
    private Pokemon pokemonActual;
    private final List<Item> items;
    private final String nombre;

    public Jugador(String nombre, List<Pokemon> pokemones, List<Item> items){
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

    public Optional<String> seleccionarPokemon(int pokeElegido){
        if (pokeElegido <= this.pokemones.size() && !this.pokemones.get(pokeElegido).estaMuerto()){
            this.pokemonActual = this.pokemones.get(pokeElegido);
        }else{
            return Optional.of("No se encontro el pokemon seleccionado");
        }
        return Optional.empty();
    }

    public boolean tienePokemonesConVida(){
        for (Pokemon pokemon : this.pokemones) {
            if (pokemon.getVida() > 0) {
                return true;
            }
        }
        return false;
    }

    public int getVelocidadPokemonActual(){
        return this.pokemonActual.getVelocidad();
    }

    public List<Habilidad> getHabilidadesPokemonActual(){
        return this.pokemonActual.getHabilidades();
    }
}