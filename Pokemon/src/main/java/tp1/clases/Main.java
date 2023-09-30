package tp1.clases;

import tp1.clases.modelo.Batalla;

import java.io.IOException;

public class Main {
    Inicializador inicializador = new Inicializador();

    Batalla batalla = new Batalla(inicializador.getJugadores());

    Controlador controlador = new Controlador(batalla);

    while !controlador.getJuegoTerminado(){
        controlador.Jugar();
    }

    String res = ResultadoView.mostrarResultado(batalla.obtenerGanador());

    public Main() throws IOException { //como trabajar el IOException
    }

    System.out.println(res);
}
