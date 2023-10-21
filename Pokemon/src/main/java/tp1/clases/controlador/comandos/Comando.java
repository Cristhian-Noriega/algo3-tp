package tp1.clases.controlador.comandos;

import tp1.clases.errores.Error;

import java.util.Optional;

public interface Comando {
    Optional<Error> ejecutar();
    String mostrar();

    default void definirPokemon(int pokemon) {}

    default void definirOpcion(int op) {}
}
