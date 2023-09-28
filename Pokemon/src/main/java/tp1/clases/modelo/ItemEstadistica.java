package tp1.clases.modelo;

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
    public void usar(Pokemon pokemon){
        switch (this.estadistica){
            case ATAQUE:
                pokemon.modificarAtaque(pokemon.getAtaque()*0.1);
                System.out.println("¡El ataque de " + pokemon.getNombre() + " ha aumentado!");
            case DEFENSA:
                pokemon.modificarDefensa(pokemon.getDefensa()*0.1);
                System.out.println("¡La defensa de " + pokemon.getNombre() + " ha aumentado!");
            case VELOCIDAD:
                pokemon.modificadorVelocidad(pokemon.getVelocidad()*0.1);
                System.out.println("¡La velocidad de " + pokemon.getNombre() + " ha aumentado!");
        }
    }
}

