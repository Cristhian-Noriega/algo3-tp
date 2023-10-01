package tp1.clases;

public class Main {
    Controlador controlador = new Controlador();

    while !controlador.HayGanador(){
        controlador.ManejarTurno();
    }

    controlador.MostrarGanador()
}
