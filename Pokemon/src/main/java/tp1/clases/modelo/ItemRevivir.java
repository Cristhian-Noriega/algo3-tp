package tp1.clases.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorPokemonNoMuerto;

import java.io.Serializable;
import java.util.Optional;

public class ItemRevivir implements Item, Serializable, Cloneable {
    protected final String nombre;
    protected final int vida;
    private final Categoria categoria = Categoria.VIDA;
    final protected String info;
    private final Integer id;
    @JsonCreator
    public ItemRevivir(@JsonProperty("nombre") String nombre,@JsonProperty("vida") int vida, @JsonProperty("info") String info, @JsonProperty("id") Integer id){
        this.nombre = nombre;
        this.vida = vida;
        this.info = info;
        this.id = id;
    }

    @Override
    public String getNombre() {
        return nombre;
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
        if (!pokemon.estaMuerto()) {
            return Optional.of(new ErrorPokemonNoMuerto(pokemon.getNombre(), this.nombre));
        }
        pokemon.modificarVida(pokemon.getVidaMax());
        System.out.println("¡Pokemon " + pokemon.getNombre() + " ha revivido!!");
        return Optional.empty();
    }
    @Override
    public Item clone() throws CloneNotSupportedException {
        return (Item) super.clone();
    }
}
