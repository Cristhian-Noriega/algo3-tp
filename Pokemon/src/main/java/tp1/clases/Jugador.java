package tp1.clases;

import java.util.List;

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

    public void seleccionarPokemon(int pokeElegido){
        if (pokeElegido <= this.pokemones.size() && !this.pokemones.get(pokeElegido).estaMuerto()){
            this.pokemonActual = this.pokemones.get(pokeElegido);
        }else{
            //devolver error
        }
    }

    public boolean tienePokemonesConVida(){
        for (Pokemon pokemon : this.pokemones) {
            if (pokemon.getVida() > 0) {
                return true;
            }
        }
        return false;
    }
}