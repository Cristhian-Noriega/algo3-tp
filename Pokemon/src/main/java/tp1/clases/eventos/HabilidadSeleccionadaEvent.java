package tp1.clases.eventos;

import javafx.event.Event;
import javafx.event.EventType;
import tp1.clases.modelo.Habilidad;

public class HabilidadSeleccionadaEvent extends Event {
    private Habilidad habilidad;
    public static EventType<HabilidadSeleccionadaEvent> HABILIDAD_SELECCIONADA_EVENT = new EventType<>("Habilidad Seleccionada Event");
    public HabilidadSeleccionadaEvent(Habilidad habilidad) {
        super(HABILIDAD_SELECCIONADA_EVENT);
        this.habilidad = habilidad;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }
}
