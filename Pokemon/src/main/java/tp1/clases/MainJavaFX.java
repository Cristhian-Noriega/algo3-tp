package tp1.clases;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp1.clases.controlador.ControladorMenuPrincipal;
import tp1.clases.eventos.CambioDeTurnoEvent;
import tp1.clases.modelo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainJavaFX extends Application implements EventHandler<CambioDeTurnoEvent> {
    private Batalla batalla;
    private Jugador jugadorActual;
    private Jugador jugadorSiguiente;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Proveedor proveedor = new Proveedor();
        List<ArrayList<Pokemon>> pokemones = proveedor.getPokemones();
        List<List<Item>> items = proveedor.getItems();
        Jugador jugador1 = new Jugador("jugador1", pokemones.get(0), items.get(0));
        Jugador jugador2 = new Jugador("jugador2", pokemones.get(1), items.get(1));

        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        listaJugadores.add(jugador1);
        listaJugadores.add(jugador2);
        this.batalla = new Batalla(listaJugadores);

        this.jugadorActual = this.batalla.getJugadorActual();
        this.jugadorSiguiente = this.batalla.getJugadorSiguiente();

        AdministradorDeClima administradorDeClima = this.batalla.getAdministradorDeClima();

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("menu-principal.fxml"));
        Parent root = loader.load();
        ControladorMenuPrincipal controladorMenuPrincipal = loader.getController();
        if (controladorMenuPrincipal != null) {
            controladorMenuPrincipal.inicializar(this.jugadorActual.getPokemonActual(), this.jugadorSiguiente.getPokemonActual(), administradorDeClima);
        } else {
            System.out.println("el controlador de menu principal es null");
        }

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Pokemon");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(CambioDeTurnoEvent cambioDeTurnoEvent) {
        this.batalla.cambiarTurno();
        this.jugadorActual = this.batalla.getJugadorActual();
        this.jugadorSiguiente = this.batalla.getJugadorSiguiente();
    }
}
