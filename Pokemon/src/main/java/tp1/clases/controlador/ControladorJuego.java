package tp1.clases.controlador;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import tp1.clases.controlador.comandos.CambiarPokemonComando;
import tp1.clases.controlador.comandos.Comando;
import tp1.clases.controlador.comandos.UsarHabilidadComando;
import tp1.clases.controlador.comandos.UsarItemComando;
import tp1.clases.errores.Error;
import tp1.clases.modelo.AdministradorDeTurnos;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Pokemon;
import tp1.clases.vista.CampoVista;
import tp1.clases.vista.OpcionMenu;
import tp1.clases.vista.VistaMenu;

import java.io.IOException;
import java.util.*;


public class ControladorJuego {

    private final Batalla batalla;
    private AdministradorDeTurnos administradorDeTurnos;
    private final LineReader reader;
    private Boolean juegoTerminado = false;
    private Comando comando;
    private final ControladorEstados controladorEstados;
    private ControladorMenu controladorMenu;

    public ControladorJuego(Batalla batalla, ControladorEstados controladorEstados) throws IOException {
        this.batalla = batalla;
        this.controladorEstados = controladorEstados;
        this.administradorDeTurnos = new AdministradorDeTurnos(this.batalla.getJugadores());
        this.controladorMenu = new ControladorMenu();

        Terminal terminal = TerminalBuilder.terminal();
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }

    public void Jugar() {
        while (!juegoTerminado) {
            System.out.printf("Turno de %s \n \n", this.batalla.getJugadorActual().getNombre());

            if (this.batalla.estaMuertoPokemonActual()) {
                seleccionarPokemonVivo();
                this.avanzarTurno();
                continue;
            }

            int opcion = interaccionConUsuario(VistaMenu.mostrarOpciones());

            if (!opcionValida(opcion)) {
                continue;
            }

            OpcionMenu accion = OpcionMenu.getAccion(opcion);

            if (Objects.equals(accion, OpcionMenu.VER_CAMPO)) {
                mostrarCampo();
                continue;
            }

            if (Objects.equals(accion, OpcionMenu.RENDIRSE)) {
                this.juegoTerminado = seRindio();
                break;
            }

            if (realizarTurno(accion)) {
                avanzarTurno();
            }
        }
    }

public void Jugar2() {
    while (!this.juegoTerminado) {

        System.out.printf("Turno de %s \n \n", this.batalla.getJugadorActual().getNombre());

        if (this.batalla.estaMuertoPokemonActual()) {
            seleccionarPokemonVivo();
            this.avanzarTurno();
            continue;
        }


        Menu menuActual = controladorMenu.obtenerMenuActual();

        int opcion = interaccionConUsuario(menuActual.mostrarOpciones());

//        if (opcion == OpcionMenu.VOLVER_ATRAS.ordinal()) {
//            this.estadoMenu.retroceder();
//            continue;
//        }

        if (!opcionValida(opcion)){
            continue;
        }

        menuActual.actualizarMenu(opcion);

        cambiarEstado(opcion, this.estadoMenu.mostrarAcciones());
        this.estadoMenu.avanzar();
        this.administradorDeTurnos.siguienteTurno();
    }
}



    private boolean realizarTurno(OpcionMenu accion){
        boolean puedeUsarHabilidad = this.controladorEstados.controlarEstado(this.batalla.getJugadorActual(), this.administradorDeTurnos.getTurno());

        while (true) {
            setComando(accion);
            String opcionesDisponibles = this.comando.mostrar();

            int opcionSiguiente = interaccionConUsuario(opcionesDisponibles);

            if (opcionSiguiente == OpcionMenu.VOLVER_ATRAS.ordinal()){
                return false;
            }

            if (!opcionValida(opcionSiguiente)){
                continue;
            }

            if (accion == OpcionMenu.VER_ITEM){
                opcionSiguiente = aplicarItemPokemon(opcionSiguiente, opcionesDisponibles, accion);
                if (opcionSiguiente == OpcionMenu.VOLVER_ATRAS.ordinal()) {
                    return false;
                }
            }

            if (opcionSiguiente == OpcionMenu.VER_HABILIDAD.ordinal() && !puedeUsarHabilidad) {
                System.out.println("No puede usar la habilidad.");
                return false;
            }

            int posicion = opcionSiguiente - 1;

            this.comando.definirOpcion(posicion);

            Optional<Error> err = this.comando.ejecutar();

            if (err.isPresent()) {
                err.get().mostrar();
                continue;
            }
            return true;

        }
    }

    private void mostrarCampo() {
        CampoVista campo = new CampoVista();
        System.out.println(campo.estadoJugador(batalla));
    }

    private void avanzarTurno() {
        if (this.batalla.obtenerGanador().isPresent()) {
            this.juegoTerminado = true;
        } else {
            this.batalla.cambiarTurno();
        }
    }

    private boolean opcionValida(int opcion) {
        if (opcion <= 0 || opcion > OpcionMenu.values().length){
            System.out.println("Opcion fuera de rango");
            return false;
        }
        return true;
    }

    private boolean seRindio(){
            String jugadorRendido = this.batalla.getJugadorActual().getNombre();
            this.batalla.rendir(this.batalla.getJugadorActual());
            System.out.printf("El jugador %s se ha rendido. \n", jugadorRendido);
            return true;
    }

    public void seleccionarPokemonVivo() {
        List<Pokemon> pokemones = this.batalla.getPokemonesJugadorActual();
        while (this.batalla.estaMuertoPokemonActual()) {
            System.out.println("Pokemon debilitado, elija otro pokemon: ");
            int pokemon = interaccionConUsuario(VistaMenu.mostrarPokemones(pokemones, false));
            if (pokemon <= 0 || pokemon > pokemones.size()){
                System.out.println("Opcion fuera de rango");
                continue;
            }
            this.batalla.cambiarPokemon(pokemon-1);
        }
    }


    private int interaccionConUsuario(String opciones) {
        System.out.printf("Elija su proxima acción: \n%s", opciones);

        int opcion;
        while (true) {
            try {
                String opcionElegida = reader.readLine();
                opcion = Integer.parseInt(opcionElegida);
                break;
            } catch (NumberFormatException err) {
                System.out.println("Acción no valida, ingrese el numero de acción elejida:\n\n");
            }
        }
        return opcion;
    }

    private void setComando(OpcionMenu accion){
        switch (accion) {
            case VER_ITEM -> {
                this.comando = new UsarItemComando(this.batalla, controladorEstados);
            }
            case VER_HABILIDAD -> {
                this.comando = new UsarHabilidadComando(this.batalla, controladorEstados);
            }
            case VER_POKEMONES -> {
                this.comando = new CambiarPokemonComando(this.batalla);
            }
        };
    }
    
    private int aplicarItemPokemon(int opcion, String siguienteAccion, OpcionMenu accion){
        List<Pokemon> listaPokemones = this.batalla.getPokemonesJugadorActual();

        while (accion == OpcionMenu.VER_ITEM) {
            if (opcion == OpcionMenu.VOLVER_ATRAS.ordinal()){
                break;
            }

            System.out.println("Elija el pokemon al cual aplicarle el item");
            int pokemonElegido = interaccionConUsuario(VistaMenu.mostrarPokemones(listaPokemones, true));

            if ((pokemonElegido >= listaPokemones.size()) | (pokemonElegido <= 0)){
                if (pokemonElegido == OpcionMenu.VOLVER_ATRAS.ordinal()){
                    return opcion;
                }
                System.out.println("Opción no valida, fuera de rango");
                opcion = interaccionConUsuario(siguienteAccion);
                continue;
            }
            System.out.println("DEBUG: opcion seleccionada: " + opcion);
            this.comando.definirPokemon(pokemonElegido-1);
            break;
        }
        return opcion;
    }

    private int aplicarItem(int item) {
       Stack<OpcionMenu> acciones = new Stack<>();
       List<Pokemon> listaPokemones = this.batalla.getPokemonesJugadorActual();
       boolean accionConcretada = false;
    }

    public Boolean getJuegoTerminado() {
        return juegoTerminado;
    }
}