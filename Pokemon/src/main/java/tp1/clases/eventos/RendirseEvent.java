package tp1.clases.eventos;

import javafx.event.Event;
import javafx.event.EventType;

public class RendirseEvent extends Event {
    public static EventType<RendirseEvent> RENDIRSE_EVENT = new EventType<>("Rendirse Event");
    public RendirseEvent() {
        super(RENDIRSE_EVENT);
    }
}
