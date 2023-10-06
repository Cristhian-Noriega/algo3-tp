package tp1.clases.comandos;

import tp1.clases.errores.Error;

import java.util.Optional;

public interface Comando {
    Optional<Error> ejecutar();

    default void definirPokemon(int pokemon) {

    }

    default void definirOpcion(int op) {

    }
}
