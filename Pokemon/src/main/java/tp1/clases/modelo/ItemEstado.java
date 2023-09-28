package tp1.clases.modelo;

import static tp1.clases.modelo.Estado.NORMAL;

public class ItemEstado implements Item{

    private final String nombre;

    public ItemEstado(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public void usar(Pokemon pokemon){
        if (pokemon.getEstado() == NORMAL) {
            PokemonNormalError(pokemon.getNombre(), this.nombre); //TO DO: organizar a donde van a ir los errores
        } else {
            pokemon.setEstado(NORMAL);
        }
    }

}
