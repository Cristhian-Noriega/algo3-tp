package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.lang.invoke.StringConcatFactory;
import java.util.Optional;

public class ItemEstadistica implements Item{
    private final String nombre;
    private final Estadisticas estadistica;
    private final Categoria categoria = Categoria.ESTADISTICA;

    private final String descripcion;

    public ItemEstadistica(String nombre, Estadisticas estadistica, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estadistica = estadistica;
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

    public String getDescripcion() {
        return descripcion;
    }
}

