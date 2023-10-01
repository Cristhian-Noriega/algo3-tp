package tp1.clases;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import tp1.clases.modelo.Batalla;

import tp1.clases.vista.OpcionesMenu;
import tp1.clases.vista.VistaMenu;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Controlador {

    private final OpcionesMenu acciones = new OpcionesMenu();
    private final Batalla batalla;
    private final LineReader reader;
    private Boolean juegoTerminado = false;

    public Controlador(Batalla batalla) throws IOException {
        this.batalla = batalla;

        Terminal terminal = TerminalBuilder.terminal();
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }

    public void Jugar(){

        int op = InteraccionConUsuario(VistaMenu.mostrarOpciones());

        if ((acciones.getSize() <= op) | (op <= 0)){
            op = OpcionNoValida(VistaMenu.mostrarOpciones());
        }

        List accion = acciones.getOpcion(op);

        if (accion.get(0).equals("rendirse")) {
            this.juegoTerminado = true;
            return;
        }

        op = InteraccionConUsuario(accion.get(1).toString()); //quizas rompe devoloviendo doble comillas

        while (true){
            try {
                int posicion = op - 1;
                ConcretarAccion(accion.get(0).toString(), posicion); //quizas rompe devoloviendo doble comillas o con op-1
                break;
            } catch (OutOfRangeError err){
                op = OpcionNoValida((String) accion.get(1));
            }
        }


        if (this.batalla.finalizada()) {
            this.juegoTerminado = true;
            return;
        }
        this.batalla.cambiarTurno();
    }

    private int InteraccionConUsuario(String opciones) {

        String opcionElegida = reader.readLine("Elija su proxima acción: \n" + opciones);

        int op;
        while (true) {
            try {
                op = Integer.parseInt(opcionElegida);
                break;
            } catch (NumberFormatException err) {
                opcionElegida = reader.readLine("Acción no valida, ingrese el numero de acción elejida: ");
            }
        }
        return op;
    }

    private void ConcretarAccion(String accion, int op){
        switch (accion){
            case "UsarItems":
                this.batalla.usarItem(op);
            case "UsarHabilidad":
                this.batalla.usarHabilidad(op);
            case "CambiarPokemon":
                this.batalla.cambiarPokemon(op);
            case "VolverAtras":
                Jugar();
        }
    }

    private int OpcionNoValida(String opciones){
        System.out.println("Opción no valida, fuera de rango");
        return InteraccionConUsuario(opciones);
    }

    public Boolean getJuegoTerminado() {
        return juegoTerminado;
    }
}
