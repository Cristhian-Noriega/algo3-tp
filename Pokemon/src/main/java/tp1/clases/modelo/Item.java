package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.io.Serializable;
import java.util.Optional;

public interface Item {
    String getNombre();
    Optional<Error> usar(Pokemon pokemon);
    Categoria getCategoria();
    Integer getId();
}
