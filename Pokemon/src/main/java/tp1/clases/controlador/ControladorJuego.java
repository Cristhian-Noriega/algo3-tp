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
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Pokemon;
import tp1.clases.vista.CampoVista;
import tp1.clases.vista.OpcionMenu;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ControladorJuego {

    private final Batalla batalla;
    private final LineReader reader;
    private Boolean juegoTerminado = false;
    private Comando comando;
    private final ControladorEstados controladorEstados;
    private ControladorMenu controladorMenu;

    public ControladorJuego(Batalla batalla, ControladorEstados controladorEstados) throws IOException {
        this.batalla = batalla;
        this.controladorEstados = controladorEstados;

        Terminal terminal = TerminalBuilder.terminal();
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }

    public void JugarTurno() {

        this.controladorMenu = new ControladorMenu(batalla);
        boolean turnoActivo = true;
        OpcionMenu accion = null;
        boolean puedeUsarHabilidad = this.controladorEstados.controlarEstado(this.batalla.getJugadorActual(), this.batalla.getTurno());


        while (turnoActivo){
            System.out.printf("Turno de %s \n \n", this.batalla.getJugadorActual().getNombre());
            //si el no hay pokemon vivo al empezar el turno, debe seleccionar un  pokemon
            //y se pasa el turno
            if (this.batalla.estaMuertoPokemonActual()) {
                seleccionarPokemonVivo();
                this.batalla.cambiarTurno();
                break;
            }

            //obtengo el menu actual en el que esta el jugador y su opcion elegida
            Menu menuActual = this.controladorMenu.obtenerMenuActual();
            int opcionElegida = interaccionConUsuario(menuActual);

            //verifico si la opcion es valida
            if (!opcionValida(opcionElegida, menuActual.cantidadOpciones())) {
                continue;
            }


            //me fijo que el menu actual sea el menu principal, si lo es, obtengo la opcion seleccionada
            if (menuActual.getCategoria().equals(CategoriaMenu.PRINCIPAL)){
                accion = OpcionMenu.getAccion(opcionElegida);
                //muestra el campo de batalla y vuelve al inicio para seguir con el turno
                if (Objects.equals(accion, OpcionMenu.VER_CAMPO)) {
                    CampoVista campo = new CampoVista();
                    System.out.println(campo.estadoJugador(batalla));
                    continue;
                }

                //si la opcion es rendirse, el juego se termina y salgo del while
                if (Objects.equals(accion, OpcionMenu.RENDIRSE)) {
                    this.juegoTerminado = seRindio();
                    break;
                }

                //setteo el comando segun la accion seleccionada
                setComando(accion);
                continue;
            }

            // si la opcion elegida es volver atras, voy al menu anterior con el controlador de menus
            if (opcionElegida == OpcionMenu.VOLVER_ATRAS.ordinal()) {
                this.controladorMenu.retroceder();
                continue;
            }

            //en caso de que la accion elegida sea usar un item pero todavia no se mostraron los pokemones disponibles
            //se agrega el menu de pokemones al controlador de menu para mostrar las opciones posibles
            if ((accion.equals(OpcionMenu.VER_ITEM)) || (menuActual.getCategoria().equals(CategoriaMenu.ITEMS))) {
                this.controladorMenu.actualizarMenu(new MenuPokemones(this.batalla.getPokemonesJugadorActual(), true));
                System.out.println("Seleccione el pokemon al cual aplicarle el item");
                int pokemonElegido = interaccionConUsuario(this.controladorMenu.obtenerMenuActual());
                if (!seleccionoPokemonItem(pokemonElegido)){
                    continue;
                }
            }

            //se verifica si el jugador puede usar sus habilidades, en caso de que se haya elegido la opcion de usar habilidad
            if (opcionElegida == OpcionMenu.VER_HABILIDAD.ordinal() && !puedeUsarHabilidad) {
                System.out.println("No puede usar la habilidad.");
                this.avanzarTurno();
                break;
            }

            int posicion = opcionElegida - 1;
            this.comando.definirOpcion(posicion);

            //ejecuto el comando segun la traduccion con la opcion elegida
            Optional<Error> err = this.comando.ejecutar();

            if (err.isPresent()) {
                err.get().mostrar();
                continue;
            }
            this.avanzarTurno();
            turnoActivo = false;
        }

    }

    private boolean seleccionoPokemonItem(int pokemonElegido){
        if (!opcionValida(pokemonElegido, this.controladorMenu.obtenerMenuActual().cantidadOpciones())) {
            return false;
        }
        if (pokemonElegido == OpcionMenu.VOLVER_ATRAS.ordinal()){
            this.controladorMenu.retroceder();
            return false;
        }
        this.comando.definirPokemon(pokemonElegido-1);
        this.controladorMenu.retroceder();
        return true;
    }

    private void avanzarTurno() {
        if (this.batalla.obtenerGanador().isPresent()) {
            this.juegoTerminado = true;
        } else {
            this.batalla.cambiarTurno();
        }
    }


    public int interaccionConUsuario(Menu menu) {
        System.out.println("Elija su proxima acción:");
        menu.mostrarOpciones();

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

    private void seleccionarPokemonVivo() {
        List<Pokemon> pokemones = this.batalla.getPokemonesJugadorActual();
        Menu opcinesDePokemon = new MenuPokemones(pokemones, false);
        while (this.batalla.estaMuertoPokemonActual()) {
            System.out.println("Pokemon debilitado, elija otro pokemon: ");
            int pokemon = interaccionConUsuario(opcinesDePokemon);
            if (pokemon <= 0 || pokemon > pokemones.size()){
                System.out.println("Opcion fuera de rango");
                continue;
            }
            this.batalla.cambiarPokemon(pokemon-1);
        }
    }

    private boolean opcionValida(int opcion, int cantOpciones) {
        if (opcion < 0 || opcion > cantOpciones){
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

    private void setComando(OpcionMenu accion){
        switch (accion) {
            case VER_ITEM -> {
                this.comando = new UsarItemComando(this.batalla, controladorEstados);
                this.controladorMenu.actualizarMenu(new MenuItems(this.batalla.getMapItemsJugadorActual(), this.batalla.getItemsJugadorActual()));
            }
            case VER_HABILIDAD -> {
                this.comando = new UsarHabilidadComando(this.batalla, controladorEstados);
                this.controladorMenu.actualizarMenu(new MenuHabilidades(this.batalla.getHabilidadesPokemonActual()));
            }
            case VER_POKEMONES -> {
                this.comando = new CambiarPokemonComando(this.batalla);
                this.controladorMenu.actualizarMenu(new MenuPokemones(this.batalla.getPokemonesJugadorActual(), true));
            }
        };
    }

    public boolean getJuegoTerminado() {
        return this.juegoTerminado;
    }

}
