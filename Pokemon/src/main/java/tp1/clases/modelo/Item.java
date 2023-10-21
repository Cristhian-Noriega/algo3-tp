package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.util.Optional;

public interface Item {
    //La interface Item existe para poder agrupar todos los items (ej hacer una lista de items)
    String getNombre();
    Optional<Error> usar(Pokemon pokemon);
    Categoria getCategoria() ;
}
