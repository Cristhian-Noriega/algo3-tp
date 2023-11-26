package tp1.clases.eventos;

import javafx.event.Event;
import javafx.event.EventType;
import tp1.clases.modelo.Habilidad;
import tp1.clases.modelo.Pokemon;

public class CambioDeEscenaEvent extends Event {
    private final int escena;
    private Habilidad habilidad;
    private Pokemon pokemon;
    public static EventType<CambioDeEscenaEvent> CAMBIO_DE_ESCENA_EVENT = new EventType<>("Cambio de Escena Event");

    public CambioDeEscenaEvent(int escena) {
        super(CAMBIO_DE_ESCENA_EVENT);
        this.escena = escena;
    }

    public int getEscena() {
        return this.escena;
    }

    public Habilidad getHabilidad() {
        return this.habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public void setPokemon(Pokemon pokemon){
        this.pokemon = pokemon;
    }
}
