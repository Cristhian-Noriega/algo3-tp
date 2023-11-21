package tp1.clases.controlador.comandos;

import tp1.clases.errores.Error;

import java.util.Optional;

public interface Comando {
    Optional<Error> ejecutar();
}
