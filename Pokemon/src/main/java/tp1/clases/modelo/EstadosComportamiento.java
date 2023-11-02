package tp1.clases.modelo;

public interface EstadosComportamiento {
    Boolean usarHabilidad(int numeroHabilidad, Pokemon pokemon);

    void aplicarEfecto(Pokemon pokemon);

    int getTurnos();
}
