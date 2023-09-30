package tp1.clases;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import tp1.clases.modelo.Batalla;
import tp1.clases.vista.VistaMenu;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class Controlador {

    private Batalla batalla;
    private VistaMenu vistaMenu;
    private final LineReader reader;
    private Boolean juegoTerminado = false;

    public Controlador(Batalla batalla) throws IOException {
        this.batalla = batalla;
        this.vistaMenu = new VistaMenu(); //To Do: completar la vista del menu (que pasarle por parametro, batalla(? )

        Terminal terminal = TerminalBuilder.terminal();
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }

    public void Jugar(){

        int op = InteraccionConUsuario(vistaMenu.mostrarOpciones());

        //listaOpciones es la lista q me devuelve vistaMenu para poder saber que accion equivale a que opcion
        List<String> listaOpciones = vistaMenu.getListaOpciones();
        if (listaOpciones.size() < op) {
            System.out.println("Opción no valida, fuera de rango");  //no se si deberia estar en el archivo de errores
            op = InteraccionConUsuario(vistaMenu.mostrarOpciones());
        }
        String accion = listaOpciones.get(op); // no tiene q ser necesariamente un string
        String proximasOpciones = siguienteAccion(accion);

        if (accion.equals("rendirse")) {
            this.juegoTerminado = true;
            System.out.printf("¡Juego terminado! \n Ganador de la partida: %s", proximasOpciones);
            return;
        }

        op = InteraccionConUsuario(proximasOpciones);
        ConcretarAccion(accion, op);

        this.batalla.cambiarTurno(); //To Do: agregar un if si la partida esta terminada (si queda solo un jugador con vida)
    }

    private int InteraccionConUsuario(String opciones) {

        String opcionElegida = reader.readLine("Elija su proxima acción: \n" + opciones);

        int op;
        while (true) {
            try {
                op = Integer.parseInt(opcionElegida);
                break;
            } catch (NumberFormatException err) { //no se si deberia estar en el archivo de errores
                opcionElegida = reader.readLine("Acción no valida, ingrese el numero de acción elejida: ");
            }
        }
        return op;
    }

    private String siguienteAccion(String accion){
        String res = null;
        switch (accion){
            case "ver campo":
                 res = vistaMenu.verCampo(); //¿dsp de esto le vuelve  mostrar las opciones?
            case "item":
                res = vistaMenu.verItems();
            case "pokemon":
                res = vistaMenu.verPokemones();
            case "habilidad":
                res = vistaMenu.verHabilidades();
            case "rendirse":
                res = (this.batalla.obtenerGanador()).toString(); //¿agregar mensaje de si esta seguro?
        }
        return res;
    }

    private void ConcretarAccion(String accion, int op){
        switch (accion){
            case "item":
                //le paso a batalla por parametro la posicion en la que esta el item a usar y batalla se encarga de q el jugador actual use el item
                this.batalla.usarItem(op);
            case "habilidad":
                this.batalla.usarHabilidad(op);
            case "pokemon":
                this.batalla.cambiarPokemon(op);
            case "volver atras":
                //To Do: le vuelve a mostrar las opciones del menu
        }
    }

    public Boolean getJuegoTerminado() {
        return juegoTerminado;
    }
}
