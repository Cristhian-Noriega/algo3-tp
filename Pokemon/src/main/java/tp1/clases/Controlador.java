package tp1.clases;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import tp1.clases.errores.Error;
import tp1.clases.modelo.Batalla;
import tp1.clases.vista.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;


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
            System.out.printf("Turno de %s \n", this.batalla.getJugadorActual().getNombre());

            int op = interaccionConUsuario(VistaMenu.mostrarOpciones());

            if ((OpcionMenu.values().length <= op) | (op < 0)) {
                System.out.println("Opción no valida, fuera de rango");
                op = interaccionConUsuario(VistaMenu.mostrarOpciones());
            }

            OpcionMenu accion = OpcionMenu.getAccion(op);

            if (Objects.equals(accion, OpcionMenu.VER_CAMPO)){
                CampoVista campo = new CampoVista();
                System.out.println(campo.estadoJugador(this.batalla));
                continue;
            }

            if (Objects.equals(accion, OpcionMenu.RENDIRSE)) {
                String jugadorRendido = this.batalla.rendir(this.batalla.getJugadorActual()).getNombre();
                System.out.printf("El jugador %s se ha rendido.", jugadorRendido);
                this.juegoTerminado = true;
                break;
            }

            String siguienteAccion = siguienteAccion(accion);
            op = interaccionConUsuario(siguienteAccion);

            if (op == 0){ //volver atras
                continue;
            }

            while (true) {
                comando.definirOpcion(op);
                Optional<Error> err = comando.ejecutar();
                if (err.isPresent()) { // ver
                    op = interaccionConUsuario(siguienteAccion);
                    continue;
                }
                break;
            }
            break;
        }

        if (this.batalla.obtenerGanador().isPresent()) {
            this.juegoTerminado = true;
            return;
        }

        this.batalla.cambiarTurno();
    }

    private int interaccionConUsuario(String opciones) {

        String opcionElegida = reader.readLine("Elija su proxima acción: \n" + opciones + "\n" );

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

    private String siguienteAccion(OpcionMenu accion){
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
