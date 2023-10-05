package tp1.clases.vista;

import tp1.clases.modelo.Batalla;

import java.util.Map;

public class CampoVista {

    public static String crearBarraDeVida(int vida, int vidaMaxima) {
        int porcentajeVida = vida / vidaMaxima * 10;
        return "|".repeat(Math.max(0, porcentajeVida - 1)) +
                " ".repeat(Math.max(0, 10 - porcentajeVida));
    }
    public String estadoJugador(Batalla batalla) {
        StringBuilder campo = new StringBuilder();
        int contador = 0;
        for (Map<String, Object> datos: batalla.getDatosJugadores()) {
            int vida = (int) datos.get("Vida Actual");
            int vidaMaxima = (int) datos.get("Vida Max");

            String barraDeVida = "(" + crearBarraDeVida(vida, vidaMaxima) + ")";

            campo.append("\n").append(batalla.getJugadores().get(contador).getNombre()).append("\n");
            campo.append(datos.get("Pokemon")).append("   ");
            campo.append("NVL.").append(datos.get("Nivel")).append("   ");
            campo.append(vida).append("/").append(vidaMaxima).append(" ").append(barraDeVida).append("   ");
            campo.append(datos.get("Estado")).append("   \n");

            contador += 1;
        }

        return campo.toString();
    }
}
