package tp1.clases.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import tp1.clases.errores.*;
import tp1.clases.errores.Error;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Jugador implements Serializable {

    private final ArrayList<Pokemon> pokemones;
    private Pokemon pokemonActual;
    private final List<Item> items;
    private final String nombre;
    private final Map<String, Long> mapCantidadItems;

    @JsonCreator
    public Jugador(@JsonProperty("nombre") String nombre, @JsonProperty("pokemones") ArrayList<Pokemon> pokemones, @JsonProperty("items") List<Item> items) {
        this.nombre = nombre;
        this.pokemones = pokemones;
        this.mapCantidadItems = this.contarFrecuenciaItems(items);
        this.items = this.organizarItems(items);
        this.pokemonActual = pokemones.get(0);
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

    public Optional<Error> seleccionarPokemon(Pokemon nuevoPokemon){
        //se podria agregar un listaPokemon.contains(nuevoPokemon) quiazs?
        if (nuevoPokemon.estaMuerto()){
            return Optional.of(new ErrorPokemonMuerto(nuevoPokemon.getNombre()));
        }
        if (nuevoPokemon == pokemonActual){
            return Optional.of(new ErrorCambiarPokemonEnBatalla(pokemonActual.getNombre()));
        }
        this.pokemonActual = nuevoPokemon;
        return Optional.empty();
    }

    public boolean tienePokemonesConVida() {
        for (Pokemon pokemon : this.pokemones) {
            if (!pokemon.estaMuerto()) {
                return true;
            }
        }
        return false;
    }

    public Optional<Error> usarItem(Item itemElegido, Pokemon pokemon) {
        if (this.mapCantidadItems.get(itemElegido.getNombre()) <= 0) {
            return Optional.of(new ErrorItemNoValido(itemElegido.getNombre()));
        }

        Optional<Error> err = itemElegido.usar(pokemon);
        if (err.isEmpty()){
            this.eliminarItem(itemElegido);
        }
        return err;
    }

    public double getVelocidadPokemonActual() {
        return this.pokemonActual.getVelocidad();
    }

    public List<Habilidad> getHabilidadesPokemonActual() {
        return this.pokemonActual.getHabilidades();
    }

    public Map<String, Long> getMapCantidadItems(){
        return this.mapCantidadItems;
    }

    public void eliminarItem(Item item){
        this.mapCantidadItems.put(item.getNombre(), this.mapCantidadItems.get(item.getNombre())-1);
    }

    public Map<String, Object> getDatos(){
        Map<String, Object> datosPokemonActual = new HashMap<>();
        datosPokemonActual.put("Pokemon", this.pokemonActual.getNombre());
        datosPokemonActual.put("Vida Actual", this.pokemonActual.getVida());
        datosPokemonActual.put("Vida Max", this.pokemonActual.getVidaMax());
        datosPokemonActual.put("Nivel", this.pokemonActual.getNivel());
        datosPokemonActual.put("Estado", this.pokemonActual.getEstadosString());

        return datosPokemonActual;
    }

    private Map<String, Long> contarFrecuenciaItems(List<Item> items) {
        return items.stream().
                collect(Collectors.groupingBy(
                        Item::getNombre,
                        Collectors.counting()
                ));

    }
    private List<Item> organizarItems(List<Item> items) {
        return items.stream().
                distinct().
                toList();
    }
}