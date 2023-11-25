package tp1.clases.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorPokemonMuerto;

import java.io.Serializable;
import java.util.Optional;

public class ItemRestauracionVida implements Item, Serializable, Cloneable {

    protected final String nombre;
    protected int vida;
    private final Categoria categoria = Categoria.VIDA;
    private final Integer id;

    int MOLESTAALUMNOS = 0;
    @JsonCreator
    public ItemRestauracionVida(@JsonProperty("nombre") String nombre, @JsonProperty("vida") int vida,
                                @JsonProperty("id") Integer id){ //si vida es 0 entonces es pocion molesta alumnos
        this.nombre = nombre;
        this.vida = vida;
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
    public Integer getId(){
        return this.id;
    }

    @Override
    public Optional<Error> usar(Pokemon pokemon){
        if (pokemon.estaMuerto()) {
            return Optional.of(new ErrorPokemonMuerto(pokemon.getNombre()));
        } else if (this.vida == MOLESTAALUMNOS){
            this.vida = pokemon.getVidaMax() / 3;
        }
        pokemon.modificarVida(this.vida);
        System.out.println("¡" + this.nombre + " ha sido usada!");
        System.out.println("Vida actual de " + pokemon.getNombre() + ": " + pokemon.getVida());
        return Optional.empty();
    }
    @Override
    public Item clone() throws CloneNotSupportedException {
        return (Item) super.clone();
    }

}