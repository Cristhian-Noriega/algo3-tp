package tp1.clases.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import tp1.clases.errores.Error;

import java.io.Serializable;
import java.util.Optional;

public class ItemEstadistica implements Item, Serializable, Cloneable {
    private final String nombre;
    private final Estadisticas estadistica;
    private final Categoria categoria = Categoria.ESTADISTICA;
    private final Integer id;
    @JsonCreator
    public ItemEstadistica(@JsonProperty("nombre") String nombre, @JsonProperty("estadistica") Estadisticas estadistica,
                           @JsonProperty("id") Integer id) {
        this.nombre = nombre;
        this.estadistica = estadistica;
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
    public Integer getId(){
        return this.id;
    }

    @Override
    public Optional<Error> usar(Pokemon pokemon){
        double porcentajeDeAumento = 0.1;
        switch (this.estadistica){
            case ATAQUE:
                pokemon.modificarAtaque(pokemon.getAtaque()* porcentajeDeAumento);
                System.out.println("¡El ataque de " + pokemon.getNombre() + " ha aumentado! \n");
                return Optional.empty();
            case DEFENSA:
                pokemon.modificarDefensa(pokemon.getDefensa()* porcentajeDeAumento);
                System.out.println("¡La defensa de " + pokemon.getNombre() + " ha aumentado! \n");
                return Optional.empty();
            case VELOCIDAD:
                pokemon.modificadorVelocidad(pokemon.getVelocidad()* porcentajeDeAumento);
                System.out.println("¡La velocidad de " + pokemon.getNombre() + " ha aumentado! \n");
                return Optional.empty();
        }
        return Optional.empty();
    }
    @Override
    public Item clone() throws CloneNotSupportedException {
        return (Item) super.clone();
    }

}

