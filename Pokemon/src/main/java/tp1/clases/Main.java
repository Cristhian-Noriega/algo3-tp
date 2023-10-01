package tp1.clases;

import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Constantes;
import tp1.clases.modelo.Jugador;
import tp1.clases.vista.ResultadoView;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException{
        try{
            Inicializador inicializador = new Inicializador();
            Batalla batalla = new Batalla(inicializador.iniciarJugadores(Constantes.cantidadJugadores));
            Controlador controlador = new Controlador(batalla);

            boolean juegoTerminado = false;
            while (!juegoTerminado){
                controlador.Jugar();
                juegoTerminado = controlador.getJuegoTerminado();
            }

            // batalla.ObtenerGanador() esta devolviendo un Jugador y resultadoView necesita el nombre. Para que resultadoView
            // se abstraiga de obtener el nombre, que batalla devuelva directo el String del nombre
            Optional<Jugador> res = ResultadoView.mostrarResultado(batalla.obtenerGanador());
            
        } catch (IOException err){
            System.out.println("Hubo un error al inicializar el juego");
            // ??
        }
    }
}
