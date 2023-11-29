package tp1.clases.modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class InfoTurno {
    private ArrayList<Pokemon> pokemonesAfectadosPorClima;
    private HashMap<Pokemon, Estado> estadosReseteados;
    private ArrayList<Pokemon> pokemonesEnvenenados;
        
    public InfoTurno() {
       this.resetearInfo();
    }

    public void resetearInfo() {
        this.pokemonesAfectadosPorClima = new ArrayList<>();
        this.pokemonesEnvenenados = new ArrayList<>();
        this.estadosReseteados = new HashMap<>();
    }

    public void agregarPokemonAfectado(Pokemon pokemon) {
        this.pokemonesAfectadosPorClima.add(pokemon);
    }

    public void agregarEstadoReseteado(Pokemon pokemon, Estado estado) {
        this.estadosReseteados.put(pokemon, estado);
    }
    
    public void agregarPokemonEnvenenado(Pokemon pokemon) {
        this.pokemonesEnvenenados.add(pokemon);
    }

    public ArrayList<Pokemon> getPokemonesEnvenenados() {
        return this.pokemonesEnvenenados;
    }
    
    public HashMap<Pokemon, Estado>  getEstadosReseteados() {
        return this.estadosReseteados;
    }
    
    public ArrayList<Pokemon> getPokemonesAfectadosPorClima() {
        return this.pokemonesAfectadosPorClima;
    }
}
