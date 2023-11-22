package tp1.clases;

import tp1.clases.controlador.ControladorJuego;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Constantes;
import tp1.clases.modelo.Jugador;
import tp1.clases.vista.ResultadoVista;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ArrayList<Jugador> jugadores = Inicializador.iniciarJugadores();

        Batalla batalla = new Batalla(jugadores);

        ControladorJuego controlador = new ControladorJuego(batalla);

        while (!controlador.getJuegoTerminado()) {
            controlador.jugarTurno();
        }

        Finalizador finalizador = new Finalizador(batalla.getJugadores().get(0), batalla.getRendidos().get(0));
        finalizador.crearJsonPartida();

        String res = ResultadoVista.mostrarResultado(batalla.obtenerGanador());

        System.out.println(res);


    }
}