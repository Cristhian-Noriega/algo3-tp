package tp1.clases;

public class ItemEstadistica extends Item{

    private Estadisticas estadistica;
    public ItemEstadistica(String nombre, Estadisticas estadistica) {
        super.nombre = nombre;
        this.estadistica = estadistica;
    }

    public void usar(Pokemon pokemon){ //agregar comentarios necesarios
        switch (this.estadistica){
            case ATAQUE -> pokemon.aumentarAtaque(pokemon.getAtaque()*0.1);
            case DEFENSA -> pokemon.aumentarDefensa(pokemon.getDefensa()*0.1);
            case VELOCIDAD -> pokemon.aumentarVelocidad(pokemon.getVelocidad()*0.1);
        }

    }
}

