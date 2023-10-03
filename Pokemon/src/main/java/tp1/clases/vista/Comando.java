package tp1.clases.vista;

import tp1.clases.errores.Error;

import java.util.Optional;

public interface Comando {
    Optional<Error> ejecutar();

    default void definirOpcion(int op) {

    }
}
