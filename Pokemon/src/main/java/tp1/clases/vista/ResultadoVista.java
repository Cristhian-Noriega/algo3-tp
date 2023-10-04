package tp1.clases.vista;

import java.util.Optional;

public class ResultadoVista {
    public static String mostrarResultado(Optional<String> ganador) {
        return String.format("¡%s ha ganado la partida! ¡Felicidades!\n", ganador.get()); //agrege el .get() para q no se muetre Optional en el resultado
    }
}