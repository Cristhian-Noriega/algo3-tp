package tp1.clases.controlador;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp1.clases.eventos.*;

import tp1.clases.modelo.*;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorEscenas implements EventHandler<ActionEvent> {
    private Batalla batalla;
    private Stage stage;
    private ArrayList<Scene> escenas;
    private ArrayList<Controlador> controladores;
    private int escenaActual;
    private int escenaAnterior;
    private ArrayList<SubscriptorEscena> subscriptores;


    public ControladorEscenas(Stage stage, Batalla batalla) {
        this.escenas = new ArrayList<>();
        this.controladores = new ArrayList<>();
        this.subscriptores = new ArrayList<>();
        this.batalla = batalla;
        this.stage = stage;
        try {
            cargarEscenas();
            ControladorMenuPokemon controlador = (ControladorMenuPokemon) this.controladores.get(Escena.MENU_POKEMONES.ordinal());
            this.agregarSubscriptor(controlador);
            ControladorPantallaInicial controladorInicial = (ControladorPantallaInicial) this.controladores.get(Escena.PANTALLA_INICIAL.ordinal());
            controladorInicial.setStage(this.stage);
            this.stage.setScene(this.escenas.get(0));
            this.stage.show();
        } catch (IOException e) {
            System.out.println("no se pudieron cargar las escenas");
            e.printStackTrace();
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

        this.stage.addEventHandler(ItemSeleccionadoEvent.ITEM_SELECCIONADO_EVENT , event -> {
            Item item = event.getItem();
            ControladorPantallaEfecto controladorPantallaEfecto = (ControladorPantallaEfecto) this.controladores.get(Escena.PANTALLA_EFECTO.ordinal());
            controladorPantallaEfecto.setItemSeleccionado(item);
            System.out.println("Item seleccionado: " + item.getNombre());
        });

        this.stage.addEventHandler(PokemonSeleccionadoEvent.POKEMON_SELECCIONADO_EVENT , event -> {
            Pokemon pokemon = event.getPokemon();
            ControladorPantallaEfecto controladorPantallaEfecto = (ControladorPantallaEfecto) this.controladores.get(Escena.PANTALLA_EFECTO.ordinal());
            controladorPantallaEfecto.setPokemonSeleccionado(pokemon);
            if (escenaAnterior == Escena.MENU_ITEMS.ordinal()) {
                controladorPantallaEfecto.mostrarItemAplicado();
            } else {
                controladorPantallaEfecto.mostrarCambioDePokemon();
            }
            System.out.println("Pokemon seteado: " + pokemon.getNombre());
        });

        this.stage.addEventHandler(AplicarItemEvent.APLICAR_ITEM_EVENT , event -> {
            Pokemon pokemon = event.getPokemon();
            System.out.println("Pokemon seteado a utilizar item: " + pokemon.getNombre());
        });

    }

    public void agregarSubscriptor(SubscriptorEscena subscriptor) {
        this.subscriptores.add(subscriptor);
    }

    public void eliminarSubscriptor(SubscriptorEscena subscriptor) {
        this.subscriptores.remove(subscriptor);
    }

    public void actualizarEscena(int escena) {
        this.escenaAnterior = this.escenaActual;
        this.escenaActual = escena;

        for (SubscriptorEscena subscriptor: this.subscriptores) {
            subscriptor.Update(escena);
        }
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

        cargarFXML("/Vistas/pantallaInicial.fxml");
        cargarFXML("/Vistas/menu-principal.fxml");
        cargarFXML("/Vistas/menu-habilidades.fxml");
        cargarFXML("/Vistas/menu-pokemon.fxml");
        cargarFXML("/Vistas/pantalla-efecto.fxml");
        cargarFXML("/Vistas/pantalla-poke-elegido.fxml");
        cargarFXML("/Vistas/pantalla-aplicar-item.fxml");
        cargarFXML("/items-view.fxml");
    }

    public void seleccionarHabilidad(Habilidad habilidad) {
        ControladorPantallaEfecto controlador = (ControladorPantallaEfecto) this.controladores.get(Escena.PANTALLA_EFECTO.ordinal());
        controlador.setHabilidadSeleccionada(habilidad);
        controlador.mostrarAtaque(this.batalla);
    }

    public void cambiarEscena(int escena) {
        this.actualizarEscena(escena);

        this.stage.setScene(this.escenas.get(escena));
        this.stage.show();

        System.out.println("TURNO DE " + this.batalla.getJugadorActual().getPokemonActual().getNombre());
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println(actionEvent.getEventType());
    }

}
