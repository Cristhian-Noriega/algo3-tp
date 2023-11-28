package tp1.clases.eventos;

import javafx.event.Event;
import javafx.event.EventType;
import tp1.clases.modelo.Pokemon;
import tp1.clases.modelo.Item;
import tp1.clases.modelo.Batalla;

public class AplicarItemEvent extends Event {
    private Pokemon pokemon;
    private Item item;

    public static EventType<AplicarItemEvent> APLICAR_ITEM_EVENT = new EventType<>("Aplicar Item Event");

    //meli: no deberia ser aplicar item event acá arriba?

    public AplicarItemEvent(Pokemon pokemon, Item item, Batalla batalla){
        super(APLICAR_ITEM_EVENT);
        this.pokemon = pokemon;
        this.item = batalla.getItemsJugadorActual().get(0);
    }

    public Pokemon getPokemon(){
        return this.pokemon;
    }

    public Item getItem(){
        return this.item;
    }

}

