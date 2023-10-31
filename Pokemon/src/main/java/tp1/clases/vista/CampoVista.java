package tp1.clases.vista;

import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Clima;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class CampoVista {

    public static String crearBarraDeVida(int vida, int vidaMaxima) {
        double porcentajeVida = ((double) vida / (double) vidaMaxima) * 10;
        String barra = "(";
        for (double i=0; i < porcentajeVida; i++) {
            barra += "|";
        }

        for (double i = porcentajeVida; i < 10; i++) {
            barra += " ";
        }

        barra += ")";
        return barra;
    }

    public String estadoJugador(Batalla batalla) {
        StringBuilder campo = new StringBuilder();
        int contador = 0;

        campo.append("CAMPO DE BATALLA: \n");
        campo.append("Clima actual: ")
                .append(this.mostrarClima(batalla))
                .append("\n");

        for (Map<String, Object> datos: batalla.getDatosJugadores()) {
            int vida = (int) datos.get("Vida Actual");
            int vidaMaxima = (int) datos.get("Vida Max");

            String barraDeVida = crearBarraDeVida(vida, vidaMaxima);

            campo.append("\n").append(batalla.getJugadores().get(contador).getNombre()).append("\n")
                    .append(datos.get("Pokemon")).append("   ")
                    .append("NVL.").append(datos.get("Nivel")).append("   ")
                    .append(vida).append("/").append(vidaMaxima).append(" ").append(barraDeVida).append("   ")
                    .append(datos.get("Estado")).append("   \n");

            contador += 1;
        }

        return campo.toString();
    }


    private String mostrarClima(Batalla batalla) {
        String clima = batalla.getClima().toString();
        Optional<String> resultado = Arrays.stream(clima.toLowerCase().split("_")).reduce((x, y) -> x + " " + y);
        return resultado.get();

    }
}
