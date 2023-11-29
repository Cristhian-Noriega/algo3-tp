package tp1.clases.controlador;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp1.clases.Finalizador;
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
        } catch (IOException e) {;
            e.printStackTrace();
        }

        this.stage.addEventHandler(HabilidadSeleccionadaEvent.HABILIDAD_SELECCIONADA_EVENT, event -> {
            Habilidad habilidad = event.getHabilidad();
            this.seleccionarHabilidad(habilidad);
        });

        this.stage.addEventHandler(CambioDeEscenaEvent.CAMBIO_DE_ESCENA_EVENT, event -> {
            int escena = event.getEscena();
            this.cambiarEscena(escena);
        });

        this.stage.addEventHandler(ItemSeleccionadoEvent.ITEM_SELECCIONADO_EVENT , event -> {
            Item item = event.getItem();
            ControladorPantallaEfecto controladorPantallaEfecto = (ControladorPantallaEfecto) this.controladores.get(Escena.PANTALLA_EFECTO.ordinal());
            controladorPantallaEfecto.setItemSeleccionado(item);
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
        });

        this.stage.addEventHandler(AplicarItemEvent.APLICAR_ITEM_EVENT , event -> {
            Pokemon pokemon = event.getPokemon();
        });

        this.stage.addEventHandler(RendirseEvent.RENDIRSE_EVENT , event -> {
            this.batalla.rendir(this.batalla.getJugadorActual());
            try {
                partidaTerminada();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void agregarSubscriptor(SubscriptorEscena subscriptor) {
        this.subscriptores.add(subscriptor);
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
        cargarFXML("/Vistas/pantalla-inicial.fxml");
        cargarFXML("/Vistas/menu-principal.fxml");
        cargarFXML("/Vistas/menu-habilidades.fxml");
        cargarFXML("/Vistas/menu-pokemon.fxml");
        cargarFXML("/Vistas/pantalla-efecto.fxml");
        cargarFXML("/items-view.fxml");
    }

    public void seleccionarHabilidad(Habilidad habilidad) {
        ControladorPantallaEfecto controlador = (ControladorPantallaEfecto) this.controladores.get(Escena.PANTALLA_EFECTO.ordinal());
        controlador.setHabilidadSeleccionada(habilidad);
        controlador.mostrarAtaque();
    }

    public void cambiarEscena(int escena) {
        if (this.batalla.obtenerGanador().isPresent()){
            try {
                partidaTerminada();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        this.actualizarEscena(escena);

        if ((this.escenaAnterior != Escena.MENU_PRINCIPAL.ordinal()) | (escena == Escena.MENU_PRINCIPAL.ordinal())) {
            ControladorPantallaEfecto controlador = (ControladorPantallaEfecto) this.controladores.get(Escena.PANTALLA_EFECTO.ordinal());
            if (this.batalla.getJugadorSiguiente().getPokemonActual().estaMuerto()) {
                controlador.mostrarPokemonMuerto(JugadorEnum.RIVAL);
            }

            if (this.batalla.getJugadorActual().getPokemonActual().estaMuerto()) {
                controlador.mostrarPokemonMuerto(JugadorEnum.ACTUAL);
            }
        }

        this.stage.setScene(this.escenas.get(escena));
        this.stage.show();

    }

    private void partidaTerminada() throws IOException {
        cargarFXML("/Vistas/pantalla-final.fxml");
        this.stage.setScene(this.escenas.get(Escena.PANTALLA_FINAL.ordinal()));
        this.stage.show();

        Finalizador finalizador = new Finalizador(batalla.getJugadores().get(0), batalla.getRendidos().get(0));
        finalizador.crearJsonPartida();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
    }

}
