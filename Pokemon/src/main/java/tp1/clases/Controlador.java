package tp1.clases;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import tp1.clases.errores.Error;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Pokemon;
import tp1.clases.vista.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class Controlador {

    private final Batalla batalla;
    private final LineReader reader;
    private Boolean juegoTerminado = false;
    private Comando comando;

    private final ControladorEstados controladorEstados;

    public Controlador(Batalla batalla, ControladorEstados controladorEstados) throws IOException {
        this.batalla = batalla;
        this.controladorEstados = controladorEstados;

        Terminal terminal = TerminalBuilder.terminal();
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }

    public void Jugar(){
        boolean puedeUsarHabilidad = this.controladorEstados.controlarEstado(this.batalla.getJugadorActual().getPokemonActual(), this.batalla.getJugadorActual(), this.batalla.getTurno());
        // if puedeUsarHabilidad -> usarla; else -> siguienteTurno; esto si elige usar una habilidad
        boolean turnoActivo = true;
        while (turnoActivo) {
            System.out.printf("Turno de %s \n", this.batalla.getJugadorActual().getNombre());

            while (this.batalla.estaMuertoPokemonActual()) {
                System.out.println("Pokemon muerto, elija otro pokemon: ");
                int pokemon = interaccionConUsuario(VistaMenu.mostrarPokemones(this.batalla.getPokemonesJugadorActual()));
                this.batalla.cambiarPokemon(pokemon);
            }

            int op = interaccionConUsuario(VistaMenu.mostrarOpciones());

            if ((OpcionMenu.values().length <= op) | (op <= 0)) {
                System.out.println("Opción no valida, fuera de rango");
                continue;
            }

            OpcionMenu accion = OpcionMenu.getAccion(op);

            if (Objects.equals(accion, OpcionMenu.VER_CAMPO)){
                CampoVista campo = new CampoVista();
                System.out.println(campo.estadoJugador(this.batalla));
                continue;
            }

            if (Objects.equals(accion, OpcionMenu.RENDIRSE)) {
                String jugadorRendido = this.batalla.getJugadorActual().getNombre();
                this.batalla.rendir(this.batalla.getJugadorActual());
                System.out.printf("El jugador %s se ha rendido. \n", jugadorRendido);
                this.juegoTerminado = true;
                break;
            }

            boolean volverAtras = false;
            boolean opcionInvalida = true;
            while (opcionInvalida) {

                accion = OpcionMenu.getAccion(op);

                String siguienteAccion = siguienteAccion(accion);
                int opSig = interaccionConUsuario(siguienteAccion);

                if (op == OpcionMenu.VER_HABILIDAD.ordinal() && !puedeUsarHabilidad) {
                    break;
                }

                opSig = verificaItem(opSig, siguienteAccion, accion);

                if (opSig == OpcionMenu.VOLVER_ATRAS.ordinal()){
                    volverAtras = true;
                    break;
                }

                int posicion = opSig - 1;
                comando.definirOpcion(posicion);
                Optional<Error> err = comando.ejecutar();

                if (err.isPresent()) {
                    err.get().mostrar();
                    continue;
                }

                opcionInvalida = false;
            }

            if (volverAtras){
                continue;
            }

            if (opcionInvalida && !puedeUsarHabilidad){
                break;
            }

            turnoActivo = false;
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
                opcionElegida = reader.readLine("Acción no valida, ingrese el numero de acción elejida: \n");
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
                this.comando = new UsarHabilidadComando(this.batalla, controladorEstados);
                yield VistaMenu.mostrarHabilidades(this.batalla.getHabilidadesPokemonActual());
            }
            case VER_POKEMONES -> {
                this.comando = new CambiarPokemonComando(this.batalla);
                yield VistaMenu.mostrarPokemones(this.batalla.getPokemonesJugadorActual());
            }
            default -> null;
        };
    }
    
    private int verificaItem(int op, String siguienteAccion, OpcionMenu accion){
        List<Pokemon> listaPokemones = this.batalla.getPokemonesJugadorActual();

        while (accion == OpcionMenu.VER_ITEM){
            if (op == OpcionMenu.VOLVER_ATRAS.ordinal()){
                break;
            }
            System.out.println("Elija el pokemon al cual aplicarle el item");
            int opItem = interaccionConUsuario(VistaMenu.mostrarPokemones(listaPokemones));

            if ((opItem >= listaPokemones.size()) | (opItem <= 0)){
                if (opItem != OpcionMenu.VOLVER_ATRAS.ordinal()){
                    System.out.println("Opción no valida, fuera de rango");
                    continue;
                }
                op = interaccionConUsuario(siguienteAccion);
                continue;
            }
            this.comando.definirPokemon(opItem-1);
            break;
        }
        return op;
    }

    public Boolean getJuegoTerminado() {
        return juegoTerminado;
    }
}