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
        String campo = "";
        int contador = 0;
        for (Map<String, Object> datos: batalla.getDatosJugadores()) {
            int vida = (int) datos.get("Vida Actual");
            int vidaMaxima = (int) datos.get("Vida Max");

            String barraDeVida = "(" + crearBarraDeVida(vida, vidaMaxima) + ")";

            campo += "\n" + batalla.getJugadores().get(contador).getNombre() + "\n";
            campo += datos.get("Pokemon") + "   ";
            campo += "NVL." + datos.get("Nivel") + "   ";
            campo += vida + "/" + vidaMaxima + " " + barraDeVida + "   ";
            campo += datos.get("Estado") + "   \n";

            contador += 1;
        }

        return campo;
    }
}
