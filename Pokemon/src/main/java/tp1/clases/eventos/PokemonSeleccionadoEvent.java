package tp1.clases.eventos;

import javafx.event.Event;
import javafx.event.EventType;
import tp1.clases.modelo.Pokemon;

public class PokemonSeleccionadoEvent extends Event  {
    private Pokemon pokemon;
    public static EventType<PokemonSeleccionadoEvent> POKEMON_SELECCIONADO_EVENT = new EventType<>("Pokemon Seleccionado Event");

    public PokemonSeleccionadoEvent(Pokemon pokemon){
        super(POKEMON_SELECCIONADO_EVENT);
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon(){
        return this.pokemon;
    }

}

