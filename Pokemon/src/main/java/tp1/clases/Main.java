package tp1.clases;

import tp1.clases.controlador.ControladorEstados;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Constantes;
import tp1.clases.vista.ResultadoVista;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Inicializador inicializador = new Inicializador();

        Batalla batalla = new Batalla(inicializador.iniciarJugadores(Constantes.cantidadJugadores));

        ControladorEstados controladorEstados = new ControladorEstados(batalla);

        ControladorJuego controlador = new ControladorJuego(batalla, controladorEstados);

        while (!controlador.getJuegoTerminado()) {
            controlador.JugarTurno();
        }

        String res = ResultadoVista.mostrarResultado(batalla.obtenerGanador());

        System.out.println(res);
    }
}