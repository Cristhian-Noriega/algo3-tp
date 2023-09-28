package tp1.clases.vista;

public class CampoView {
    public static String estadoJugador(String jugador, String pokemon, int vida, int vidaMaxima, int nivel, String estado) {
        int porcentajeVida = vida / vidaMaxima * 10;
        String barraDeVida = "|".repeat(Math.max(0, porcentajeVida - 1)) +
                " ".repeat(Math.max(0, 10 - porcentajeVida));
        return String.format("%s \n %s  %d/%d (%s)  Nvl. %d  %s", jugador, pokemon, vida, vidaMaxima, barraDeVida, nivel, estado);
    }
}
