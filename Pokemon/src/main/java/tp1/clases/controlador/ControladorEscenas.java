package tp1.clases.controlador;


import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import tp1.clases.Finalizador;
import tp1.clases.eventos.*;

import tp1.clases.modelo.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
            this.cargarEscenas();
            ControladorMenuPokemon controladorMenuPokemon = (ControladorMenuPokemon) this.controladores.get(Escena.MENU_POKEMONES.ordinal());
            this.agregarSubscriptor(controladorMenuPokemon);
            ControladorPantallaInicial controladorInicial = (ControladorPantallaInicial) this.controladores.get(Escena.PANTALLA_INICIAL.ordinal());
            controladorInicial.setStage(this.stage);
            this.stage.setScene(this.escenas.get(0));

            String ruta = "/Pokemon_FireRed___LeafGreen.YbcPfDOB.wav.part";
            Media media = new Media(Objects.requireNonNull(Archivos.class.getResource(ruta)).toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.volumeProperty().set(0.05);
            mediaPlayer.setAutoPlay(true);
            this.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.stage.addEventHandler(HabilidadSeleccionadaEvent.HABILIDAD_SELECCIONADA_EVENT, event -> {
            this.seleccionarHabilidad(event.getHabilidad());
        });

        this.stage.addEventHandler(CambioDeEscenaEvent.CAMBIO_DE_ESCENA_EVENT, event -> {
            this.cambiarEscena(event.getEscena());
        });

        this.stage.addEventHandler(ItemSeleccionadoEvent.ITEM_SELECCIONADO_EVENT, event -> {
            this.seleccionarItem(event.getItem());
        });

        this.stage.addEventHandler(PokemonSeleccionadoEvent.POKEMON_SELECCIONADO_EVENT, event -> {
            this.seleccionarPokemon(event.getPokemon());
        });

        this.stage.addEventHandler(RendirseEvent.RENDIRSE_EVENT, event -> {
            this.rendirse();
        });
    }

    public void agregarSubscriptor(SubscriptorEscena subscriptor) {
        this.subscriptores.add(subscriptor);
    }

    public void actualizarEscena(int escena) {
        this.escenaAnterior = this.escenaActual;
        this.escenaActual = escena;

        for (SubscriptorEscena subscriptor : this.subscriptores) {
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
        this.cargarFXML("/Vistas/pantalla-inicial.fxml");
        this.cargarFXML("/Vistas/menu-principal.fxml");
        this.cargarFXML("/Vistas/menu-habilidades.fxml");
        this.cargarFXML("/Vistas/menu-pokemon.fxml");
        this.cargarFXML("/Vistas/menu-items.fxml");
        this.cargarFXML("/Vistas/pantalla-efecto.fxml");
    }

    public void seleccionarHabilidad(Habilidad habilidad) {
        ControladorPantallaEfecto controlador = (ControladorPantallaEfecto) this.controladores.get(Escena.PANTALLA_EFECTO.ordinal());
        controlador.setHabilidadSeleccionada(habilidad);
        controlador.mostrarAtaque();
    }

    public void seleccionarPokemon(Pokemon pokemon ){
        ControladorPantallaEfecto controladorPantallaEfecto = (ControladorPantallaEfecto) this.controladores.get(Escena.PANTALLA_EFECTO.ordinal());
        controladorPantallaEfecto.setPokemonSeleccionado(pokemon);
        if (escenaAnterior == Escena.MENU_ITEMS.ordinal()) {
            controladorPantallaEfecto.mostrarItemAplicado();
        } else {
            controladorPantallaEfecto.mostrarCambioDePokemon();
        }
    }

    public void seleccionarItem(Item item) {
        ControladorPantallaEfecto controladorPantallaEfecto = (ControladorPantallaEfecto) this.controladores.get(Escena.PANTALLA_EFECTO.ordinal());
        controladorPantallaEfecto.setItemSeleccionado(item);
    }

    public void rendirse() {
        this.batalla.rendir(this.batalla.getJugadorActual());
        try {
            this.partidaTerminada();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cambiarEscena(int escena) {
        if (this.batalla.obtenerGanador().isPresent()) {
            try {
                partidaTerminada();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        this.actualizarEscena(escena);

        if ((this.escenaAnterior != Escena.MENU_PRINCIPAL.ordinal()) | escena == Escena.MENU_PRINCIPAL.ordinal()) {
            ControladorPantallaEfecto controlador = (ControladorPantallaEfecto) this.controladores.get(Escena.PANTALLA_EFECTO.ordinal());
            if (this.batalla.getJugadorSiguiente().getPokemonActual().estaMuerto()) {
                controlador.mostrarPokemonMuerto(JugadorEnum.RIVAL);
            }

            if (this.batalla.getJugadorActual().getPokemonActual().estaMuerto()) {
                controlador.mostrarPokemonMuerto(JugadorEnum.ACTUAL);
            }
        }

        if (
                (escena != Escena.MENU_HABILIDADES.ordinal())
                        && (escenaAnterior != Escena.MENU_HABILIDADES.ordinal())
                        && (escenaAnterior != Escena.PANTALLA_EFECTO.ordinal())
        ) {
            this.sacarEscenaAnterior(escenaActual);
            this.stage.setScene(this.escenas.get(escena));
            this.ponerEscenaNueva(escena);
        } else {
            this.stage.setScene(this.escenas.get(escena));
        }

        this.stage.show();
    }

    private void partidaTerminada() throws IOException {
        cargarFXML("/Vistas/pantalla-final.fxml");
        this.stage.setScene(this.escenas.get(Escena.PANTALLA_FINAL.ordinal()));
        this.stage.show();

        Finalizador finalizador = new Finalizador(batalla.getJugadores().get(0), batalla.getRendidos().get(0));
        finalizador.crearJsonPartida();
    }

    public void sacarEscenaAnterior(int escena) {
        Animation transition = new Transition() {
            {
                setCycleDuration(Duration.millis(1000));
                setInterpolator(Interpolator.EASE_OUT);
            }

            @Override
            protected void interpolate(double frac) {
                escenas.get(escena).getRoot().setOpacity(stage.getOpacity() * (1 - frac));
                escenas.get(escena).setFill(javafx.scene.paint.Color.BLACK.interpolate(javafx.scene.paint.Color.TRANSPARENT, frac));
            }
        };
        transition.play();
    }

    public void ponerEscenaNueva(int escena) {
        Animation transition = new Transition() {
            {
                setCycleDuration(Duration.millis(1000));
                setInterpolator(Interpolator.EASE_IN);
            }

            @Override
            protected void interpolate(double frac) {
                escenas.get(escena).getRoot().setOpacity(stage.getOpacity() * frac);
                escenas.get(escena).setFill(javafx.scene.paint.Color.BLACK.interpolate(javafx.scene.paint.Color.TRANSPARENT, frac));
            }
        };
        transition.play();
    }


    @Override
    public void handle(ActionEvent actionEvent) {}
}
