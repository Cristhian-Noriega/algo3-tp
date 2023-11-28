package tp1.clases.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorPokemonNormal;

import java.io.Serializable;
import java.util.Optional;

public class ItemEstado implements Item, Serializable, Cloneable {

    private final String nombre;
    private final Categoria categoria = Categoria.ESTADO;
    final protected String info;
    private final Integer id;
    @JsonCreator
    public ItemEstado(@JsonProperty("nombre") String nombre, @JsonProperty("info") String info, @JsonProperty("id") Integer id){
        this.nombre = nombre;
        this.info = info;
        this.id = id;
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
    public String getInfo() {
        return this.info;
    }

    @Override
    public Integer getId(){
        return this.id;
    }

    @Override
    public Optional<Error> usar(Pokemon pokemon){
        if (pokemon.getEstados().contains(Estado.NORMAL)) {
            return Optional.of(new ErrorPokemonNormal(pokemon.getNombre(), this.nombre));
        }
        pokemon.setEstado(Estado.NORMAL);
        return Optional.empty();
    }
    @Override
    public Item clone() throws CloneNotSupportedException {
        return (Item) super.clone();
    }

}