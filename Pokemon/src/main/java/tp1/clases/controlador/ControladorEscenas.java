package tp1.clases.controlador;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp1.clases.eventos.AplicarItemEvent;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.eventos.HabilidadSeleccionadaEvent;
import tp1.clases.eventos.PokemonSeleccionadoEvent;

import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Habilidad;
import tp1.clases.modelo.Item;
import tp1.clases.modelo.Pokemon;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorEscenas implements EventHandler<ActionEvent> {
    private Batalla batalla;
    private Stage stage;
    private ArrayList<Scene> escenas;
    private ArrayList<Controlador> controladores;
    private ControladorMenuPokemon controladorMenuPokemon;
    private int escenaActual;
    private int escenaAnterior;
    private Item itemAUtilizar;

    public ControladorEscenas(Stage stage, Batalla batalla) {
        this.escenas = new ArrayList<>();
        this.controladores = new ArrayList<>();
        this.batalla = batalla;
        this.stage = stage;
        try {
            cargarEscenas();
            this.stage.setScene(this.escenas.get(0));
            this.stage.show();
        } catch (IOException e) {
            System.out.println("no se pudieron cargar las escenas");
        }

        this.stage.addEventHandler(HabilidadSeleccionadaEvent.HABILIDAD_SELECCIONADA_EVENT, event -> {
            Habilidad habilidad = event.getHabilidad();
            this.seleccionarHabilidad(habilidad);
            System.out.println("habilidad seteada: " + habilidad.getNombre());
        });

        this.stage.addEventHandler(CambioDeEscenaEvent.CAMBIO_DE_ESCENA_EVENT, event -> {
            int escena = event.getEscena();
            this.cambiarEscena(escena);
            System.out.println("Evento recibido con escena: " + escena);
        });

        this.stage.addEventHandler(PokemonSeleccionadoEvent.POKEMON_SELECCIONADO_EVENT , event -> {
            Pokemon pokemon = event.getPokemon();
            this.seleccionarPokemon(pokemon);
            System.out.println("Pokemon seteado: " + pokemon.getNombre());
        });

        this.stage.addEventHandler(AplicarItemEvent.APLICAR_ITEM_EVENT , event -> {
            Pokemon pokemon = event.getPokemon();
            this.seleccionarPokemon(pokemon);
            System.out.println("Pokemon seteado a utilizar item: " + pokemon.getNombre());
        });

    }

    public void cargarFXML(String ruta) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
        Scene scene = new Scene(loader.load());
        this.escenas.add(scene);
        Controlador controlador = loader.getController();
        controlador.inicializar(this.batalla);
        this.controladores.add(controlador);
    }

    public void cargarEscenas() throws IOException {

        //cargarFXML("/Vistas/pantallaInicial.fxml"); TODO:solucionar que este menú tenga un controlador que implemente Controlador
        cargarFXML("/Vistas/menu-principal.fxml");
        cargarFXML("/Vistas/menu-habilidades.fxml");
        cargarFXML("/Vistas/menu-pokemon.fxml");
        cargarFXML("/Vistas/pantalla-efecto.fxml");
        cargarFXML("/Vistas/pantalla-poke-elegido.fxml");
        cargarFXML("/Vistas/pantalla-aplicar-item.fxml"); // ¿es distinta de la pantalla-efecto?

    }

    public void seleccionarHabilidad(Habilidad habilidad) {
        ControladorPantallaEfecto controlador = (ControladorPantallaEfecto) this.controladores.get(Escena.PANTALLA_EFECTO.ordinal());
        controlador.setHabilidadSeleccionada(habilidad);
        controlador.actualizar(this.batalla);
    }

    public void cambiarEscena(int escena) {
        this.escenaAnterior = this.escenaActual;
        this.escenaActual = escena;

        this.stage.setScene(this.escenas.get(escena));
        this.stage.show();

        System.out.println("TURNO DE " + this.batalla.getJugadorActual().getPokemonActual().getNombre());
    }

    public void actualizarItemAutilizar(Item item){
        this.itemAUtilizar = item; // solo se llamaria desde controlador item y se accede solo si la pantalla ant es esa
    }

    public void seleccionarPokemon(Pokemon pokemon){ //meli: este metodo no deberia estar, los controladores no pueden tener referencias a controlador escena

        if (escenaAnterior == Escena.MENU_ITEMS.ordinal()){
            ControladorPantallaAplicarItem controlador = (ControladorPantallaAplicarItem) this.controladores.get(5);
            this.actualizarItemAutilizar(batalla.getItemsJugadorActual().get(0));
            controlador.actualizar(pokemon, this.itemAUtilizar);

        }else if (escenaAnterior == Escena.MENU_PRINCIPAL.ordinal()){ //seleccionarPokemon o Pokemon muerto (el ult viene de ahí?)
            ControladorPantallaPokemonSeleccionado controlador = (ControladorPantallaPokemonSeleccionado) this.controladores.get(4);
            controlador.actualizar(pokemon);
        }
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println(actionEvent.getEventType());

    }
}
