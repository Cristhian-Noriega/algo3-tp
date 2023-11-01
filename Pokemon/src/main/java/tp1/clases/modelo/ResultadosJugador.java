package tp1.clases.modelo;

import java.util.List;
import java.util.Map;

public class ResultadosJugador {
    private String nombre;
    private boolean ganador;
    private Map<String, Integer> items;
    private List<DatosPokemon> pokemons;

    public void ResultadosJugador(String nombre, boolean ganador, Map<String, Integer> items, List<DatosPokemon> pokemons){
        this.nombre = nombre;
        this.ganador = ganador;
        this.items = items;
        this.pokemons = pokemons;
    }
}
