package tp1.clases.modelo;

public class Random {
    public static boolean probabilidad(double probabilidad) {
        double rand = Math.random();
        return rand <= probabilidad;
    }

    public static int getRandom(int inicio, int fin) {
        return inicio + ((int) (Math.random() * (fin - inicio)));
    }
}