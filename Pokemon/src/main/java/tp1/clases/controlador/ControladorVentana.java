package tp1.clases.controlador;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp1.clases.eventos.CambioDeTurnoEvent;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Habilidad;

import java.io.IOException;
import java.util.ArrayList;

public class ControladorVentana implements EventHandler<CambioDeTurnoEvent> {
    private Batalla batalla;
    private Stage stage;
    private ArrayList<Scene> escenas; //orden: MenuPrincipal, MenuHabilidades, PantallaEfecto
    private ArrayList<Controlador> controladores; //idem

    public ControladorVentana(Stage stage, Batalla batalla) {
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
    }

    public void cargarFXML(String ruta) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
        Scene scene = new Scene(loader.load());
        this.escenas.add(scene);
        Controlador controlador = loader.getController();
        controlador.setControladorVentana(this);
        controlador.inicializar(this.batalla);
        this.controladores.add(controlador);
    }

    public void cargarEscenas() throws IOException {
        cargarFXML("/menu-principal.fxml");
        cargarFXML("/menu-habilidades.fxml");
        cargarFXML("/pantalla-efecto.fxml");
    }

    public void actualizarEscenas() {
        for (Controlador controlador: this.controladores) {
            controlador.actualizar(this.batalla);
        }
    }

    @Override
    public void handle(CambioDeTurnoEvent cambioDeTurnoEvent) {
        this.batalla.cambiarTurno();
        this.actualizarEscenas();
    }

    public void cambiarTurno() {
        this.batalla.cambiarTurno();
        this.actualizarEscenas();
        System.out.println("TURNO DE " + this.batalla.getJugadorActual().getPokemonActual().getNombre());
    }

    public void seleccionarHabilidad(Habilidad habilidad) {
        ControladorPantallaEfecto controlador = (ControladorPantallaEfecto) this.controladores.get(2);
        controlador.setHabilidadSeleccionada(habilidad);
        controlador.actualizar(this.batalla);
    }

    public void cambiarEscena(int i) {
        this.controladores.get(i).actualizarCampo(this.batalla);
        this.stage.setScene(this.escenas.get(i));
        this.stage.show();
    }
}
