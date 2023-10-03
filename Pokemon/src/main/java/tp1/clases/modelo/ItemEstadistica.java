package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.util.Optional;

public class ItemEstadistica implements Item{

    private final String nombre;
    private final Estadisticas estadistica;

    public ItemEstadistica(String nombre, Estadisticas estadistica) {
        this.nombre = nombre;
        this.estadistica = estadistica;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public Optional<Error> usar(Pokemon pokemon){
        double porcentajeDeAumento = 0.1;
        switch (this.estadistica){
            case ATAQUE:
                pokemon.modificarAtaque(pokemon.getAtaque()* porcentajeDeAumento);
                System.out.println("¡El ataque de " + pokemon.getNombre() + " ha aumentado!");
            case DEFENSA:
                pokemon.modificarDefensa(pokemon.getDefensa()* porcentajeDeAumento);
                System.out.println("¡La defensa de " + pokemon.getNombre() + " ha aumentado!");
            case VELOCIDAD:
                pokemon.modificadorVelocidad(pokemon.getVelocidad()* porcentajeDeAumento);
                System.out.println("¡La velocidad de " + pokemon.getNombre() + " ha aumentado!");
        }
        return Optional.empty();
    }
}

