package tp1.clases.modelo;

public interface EstadoComportamiento {
    Boolean usarHabilidad(Habilidad habilidad, Pokemon pokemon);

    void aplicarEfecto(Pokemon pokemon);

    int getTurnos();
}
