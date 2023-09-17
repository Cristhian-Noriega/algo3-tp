package tp1.clases;

public class ItemEstadistica extends Item{

    private final Estadisticas estadistica;
    public ItemEstadistica(String nombre, Estadisticas estadistica) {
        super.nombre = nombre;
        this.estadistica = estadistica;
    }

    public void usar(Pokemon pokemon){
        switch (this.estadistica){
            case ATAQUE:
                pokemon.aumentarAtaque(pokemon.getAtaque()*0.1);
                System.out.println("¡El ataque de " + pokemon.getNombre() + " ha aumentado!");
            case DEFENSA:
                pokemon.aumentarDefensa(pokemon.getDefensa()*0.1);
                System.out.println("¡La defensa de " + pokemon.getNombre() + " ha aumentado!");
            case VELOCIDAD:
                pokemon.aumentarVelocidad(pokemon.getVelocidad()*0.1);
                System.out.println("¡La velocidad de " + pokemon.getNombre() + " ha aumentado!");
        }

    }
}

