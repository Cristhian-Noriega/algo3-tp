package tp1.clases.modelo;

public interface EstadoComportamiento {
    Boolean usarHabilidad(int numeroHabilidad, Pokemon pokemon);

    void aplicarEfecto(Pokemon pokemon);

    int getTurnos();
}
