package tp1.clases;

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
    public String usar(Pokemon pokemon){
        if (pokemon.getEstado() == NORMAL) {
            return Error.pokemonNormal(pokemon.getNombre(), this.nombre);
        } else {
            pokemon.actualizarEstado(NORMAL);
        }
        return null;
    }

}
