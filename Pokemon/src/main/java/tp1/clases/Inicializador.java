//package tp1.clases;
//
//import org.jline.reader.LineReader;
//import org.jline.reader.LineReaderBuilder;
//import org.jline.terminal.Terminal;
//import org.jline.terminal.TerminalBuilder;
//import tp1.clases.modelo.Item;
//import tp1.clases.modelo.Jugador;
//import tp1.clases.modelo.Pokemon;
//import tp1.clases.modelo.Proveedor;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Inicializador {
//    private final LineReader reader;
//
//    private final ArrayList<Jugador> jugadores;
//
//    public Inicializador() throws IOException {
//        this.jugadores = new ArrayList<Jugador>();
//
//        Terminal terminal = TerminalBuilder.terminal();
//        reader = LineReaderBuilder.builder()
//                .terminal(terminal)
//                .build();
//    }
//
//    public ArrayList<Jugador> iniciarJugadores(int cantJugadores){
//        Proveedor proveedor = new Proveedor();
//        List<ArrayList<Pokemon>> pokemones = proveedor.getPokemones();
//        List<List<Item>> items = proveedor.getItems();
//
//        int i = 1;
//        while (i <= cantJugadores){
//            System.out.println("Por favor ingrese el nombre del jugador " + i);
//            String nombre = reader.readLine();
//            if (nombre.length() > 50) {
//                System.out.println("El nombre no puede tener mas de 50 caracteres \n");
//                continue;
//            }
//            Jugador jugador = new Jugador(nombre, pokemones.get(i-1), items.get(i-1));
//            i++;
//            this.jugadores.add(jugador);
//        }
//
//        return this.jugadores;
//    }
//
//}
