package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorPokemonNormal;

import java.util.Optional;

public class ItemEstado implements Item{

    private final String nombre;

    private final Categoria categoria = Categoria.ESTADO;

    private final String descripcion;

    public ItemEstado(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    @Override
    public Optional<Error> usar(Pokemon pokemon){
        if (pokemon.getEstados().contains(Estado.NORMAL)) {
            return Optional.of(new ErrorPokemonNormal(pokemon.getNombre(), this.nombre));
        }
        pokemon.setEstado(Estado.NORMAL);
        return Optional.empty();
    }

    public String getDescripcion() {
        return descripcion;
    }
}
