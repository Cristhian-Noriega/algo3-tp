package tp1.clases;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import tp1.clases.modelo.Batalla;

import tp1.clases.vista.*;

import java.io.IOException;
import java.util.Objects;


public class Controlador {


    private final Batalla batalla;
    private final LineReader reader;
    private Boolean juegoTerminado = false;
    private Comando comando;

    public Controlador(Batalla batalla) throws IOException {
        this.batalla = batalla;

        Terminal terminal = TerminalBuilder.terminal();
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }

    public void Jugar(){

        while (true) {

            int op = InteraccionConUsuario(VistaMenu.mostrarOpciones());

            if ((OpcionMenu.values().length <= op) | (op < 0)) {
                System.out.println("Opción no valida, fuera de rango");
                op = InteraccionConUsuario(VistaMenu.mostrarOpciones());
            }

            OpcionMenu accion = OpcionMenu.getAccion(op);

            if (Objects.equals(accion, OpcionMenu.VER_CAMPO)){
                CampoView(this.batalla); //To Do: hacer que campoView reciba batalla
                continue;
            }

            if (Objects.equals(accion, OpcionMenu.RENDIRSE)) {
                String jugadorRendido = this.batalla.rendir(this.batalla.getJugadorActual()).getNombre();
                System.out.printf("El jugador %s se ha rendido.", jugadorRendido);
                break;
            }

            String siguienteAccion = SiguienteAccion(accion);
            op = InteraccionConUsuario(siguienteAccion);

            if (op == -1){ //volver atras **agregar en vistaMenu para todos los verAccion que la opcion cero sea volver atras
                continue;
            }

            while (true) {
                comando.definirOpcion(op);
                err = comando.ejecutar();
                if (err == null) {
                    break;
                }
                error.mostrar();
                op = InteraccionConUsuario(siguienteAccion);
            }
            break;
        }

        if (this.batalla.obtenerGanador().isPresent()) {
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
        return op-1;
    }

    private String SiguienteAccion(OpcionMenu accion){
        return switch (accion) {
            case VER_ITEM -> {
                this.comando = new UsarItemComando(this.batalla);
                yield VistaMenu.mostrarItems(this.batalla.getItemsJugadorActual());
            }
            case VER_HABILIDAD -> {
                this.comando = new UsarHabilidadComando(this.batalla);
                yield VistaMenu.mostrarHabilidades(this.batalla.getHabilidadesPokemonActual());
            }
            case VER_POKEMONES -> {
                this.comando = new CambiarPokemonComando(this.batalla);
                yield VistaMenu.mostrarPokemones(this.batalla.getPokemonesJugadorActual());
            }
            default -> null;
        };
    }


    public Boolean getJuegoTerminado() {
        return juegoTerminado;
    }
}