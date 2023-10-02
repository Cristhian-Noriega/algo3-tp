package tp1.clases;

import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Inicializador;
import tp1.clases.vista.ResultadoView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Inicializador inicializador = new Inicializador();

        Batalla batalla = new Batalla(inicializador.getJugadores());

        Controlador controlador = new Controlador(batalla);

        while (!controlador.getJuegoTerminado())

        {
            controlador.Jugar();
        }

        String res = ResultadoView.mostrarResultado(batalla.obtenerGanador());


        System.out.println(res);
    }
}
