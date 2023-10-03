package tp1.clases.vista;

import tp1.clases.modelo.Batalla;

import java.util.Map;

public class CampoVista {

    public String estadoJugador(Batalla batalla) {
        Map<String, Object> datos = batalla.getDatosJugadorActual();
        int vida = (int) datos.get("Vida Actual");
        int vidaMaxima = (int) datos.get("Vida Max");
        int nivel = (int) datos.get("Nivel");

        int porcentajeVida = vida / vidaMaxima * 10;
        String barraDeVida = "|".repeat(Math.max(0, porcentajeVida - 1)) +
                " ".repeat(Math.max(0, 10 - porcentajeVida));
        return String.format("%s \n %s  %d/%d (%s)  Nvl. %d  %s", batalla.getJugadorActual().getNombre(), datos.get("Pokemon"), vida, vidaMaxima, barraDeVida, nivel, datos.get("Estado"));
    }
}
