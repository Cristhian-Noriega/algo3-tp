package tp1.clases.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.eventos.CambioDeTurnoEvent;
import tp1.clases.eventos.HabilidadSeleccionadaEvent;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Habilidad;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorEscenas implements EventHandler<ActionEvent> {
    private Batalla batalla;
    private Stage stage;
    private ArrayList<Scene> escenas;
    private ArrayList<Controlador> controladores;

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
            System.out.println("recibo evento con escena: " + escena);
            this.cambiarEscena(escena);
            System.out.println("salgo de cambiar escena " + escena);
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
        cargarFXML("/Vistas/menu-principal.fxml");
        cargarFXML("/Vistas/menu-habilidades.fxml");
        cargarFXML("/Vistas/pantalla-efecto.fxml");
    }

    public void seleccionarHabilidad(Habilidad habilidad) {
        ControladorPantallaEfecto controlador = (ControladorPantallaEfecto) this.controladores.get(2);
        controlador.setHabilidadSeleccionada(habilidad);
        boolean cambiarTurno = controlador.mostrarAtaque();
        if (cambiarTurno) {
            this.batalla.cambiarTurno();
            System.out.println("Turno cambiado " + this.batalla.getJugadorActual().getPokemonActual().getNombre());
        }
    }

    public void cambiarEscena(int escena) {
        System.out.println("llego a cambiar escena con " + escena);
        this.stage.setScene(this.escenas.get(escena));
        System.out.println("escena cambiada");
        this.stage.show();
        if (escena == Escena.MENU_PRINCIPAL.ordinal()) {
            System.out.println("entro al if de cambiar escena");
            ControladorMenuPrincipal controlador = (ControladorMenuPrincipal) this.controladores.get(escena);
            controlador.actualizarCampo();
        }
        System.out.println("TURNO DE " + this.batalla.getJugadorActual().getPokemonActual().getNombre());
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println(actionEvent.getEventType());
    }
}
