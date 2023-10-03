package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorPokemonNormal;

import java.util.Optional;

public class ItemEstado implements Item{

    private final String nombre;

    public ItemEstado(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public Optional<Error> usar(Pokemon pokemon){
        if (pokemon.getEstado() == null) {
            return Optional.of(new ErrorPokemonNormal(pokemon.getNombre(), this.nombre));
        } else {
            pokemon.setEstado(null);
            return Optional.empty();
        }
    }

}
