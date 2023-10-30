package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.util.Optional;

public interface EstadosComportamiento {
    Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon pokemon);

    void aplicarEfecto(Pokemon pokemon);
}
