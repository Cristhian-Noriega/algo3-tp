package tp1.clases;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import tp1.clases.modelo.Item;
import tp1.clases.modelo.Jugador;
import tp1.clases.modelo.Pokemon;
import tp1.clases.modelo.Proveedor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Inicializador {
    private final LineReader reader;

    private ArrayList<Jugador> jugadores;

    public Inicializador() throws IOException {
        this.jugadores = new ArrayList<Jugador>();

        Terminal terminal = TerminalBuilder.terminal();
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }

    public ArrayList<Jugador> iniciarJugadores(int cantJugadores){
        Proveedor proveedor = new Proveedor();
        List<List<Pokemon>> pokemones = proveedor.getPokemones();
        List<List<Item>> items = proveedor.getItems();

        for (int i = 1; i <= cantJugadores; i++){
            String nombre = reader.readLine("Por favor ingrese el nombre del jugador " + i + " \n");
            Jugador jugador = new Jugador(nombre, pokemones.get(i-1), items.get(i-1));
            this.jugadores.add(jugador);
        }
        return this.jugadores;
    }

}
