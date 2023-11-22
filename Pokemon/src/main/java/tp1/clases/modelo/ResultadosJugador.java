
package tp1.clases.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.*;

public class ResultadosJugador implements Serializable {
    private final String nombre;
    private final boolean ganador;
    private final Map<String, Long> items;
    private final ArrayList<Map<String, String>> pokemons;

    @JsonCreator
    public ResultadosJugador(@JsonProperty("nombre") String nombre, @JsonProperty("ganador") boolean ganador,
                             @JsonProperty("items") Map<String, Long> items, @JsonProperty("pokemones") List<Pokemon> pokemones){
        this.nombre = nombre;
        this.ganador = ganador;
        this.items = items;
        this.pokemons = mapPokemones(pokemones);

    }

    private ArrayList<Map<String, String>> mapPokemones(List<Pokemon> pokemones){
        ArrayList<Map<String, String>> listaDatosPokemones = new ArrayList<Map<String, String>>();
        int i;
        for (Pokemon pokemon : pokemones) {
            Map<String, String> poke = new HashMap<String, String>();

            poke.put("id", pokemon.getId().toString());
            poke.put("vidaRestante", pokemon.getVida().toString());
            poke.put("estado", pokemon.getEstadosString());

            listaDatosPokemones.add(poke);
        }
        return listaDatosPokemones;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isGanador() {
        return ganador;
    }

    public Map<String, Long> getItems() {
        return items;
    }

    public ArrayList<Map<String, String>> getPokemons() {
        return pokemons;
    }
}
