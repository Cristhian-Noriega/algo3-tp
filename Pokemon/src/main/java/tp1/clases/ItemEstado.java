package tp1.clases;

import static tp1.clases.Estado.NORMAL;

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
            throw new PokemonNormalError(pokemon.getNombre(), this.nombre);
        } else {
            pokemon.actualizarEstado(NORMAL);
        }
    }

}
