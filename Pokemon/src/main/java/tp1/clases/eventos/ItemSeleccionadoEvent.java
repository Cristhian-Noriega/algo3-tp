package tp1.clases.eventos;

import javafx.event.Event;
import javafx.event.EventType;
import tp1.clases.modelo.Item;

public class ItemSeleccionadoEvent extends Event {
    private Item item;
    public static EventType<ItemSeleccionadoEvent> ITEM_SELECCIONADO_EVENT = new EventType<>("Item Seleccionado Event");
    public ItemSeleccionadoEvent(Item item) {
        super(ITEM_SELECCIONADO_EVENT);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
