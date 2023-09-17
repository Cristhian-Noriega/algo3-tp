package tp1.clases;

public class ItemEstado extends Item{

    public ItemEstado(String nombre){
        super.nombre = nombre;
    }

    public void usar(Pokemon pokemon){
        if (pokemon.getEstado() == NORMAL) {
            System.out.println(pokemon.getNombre() + " no necesita el Item " + super.nombre);
        } else {
            pokemon.actualizarEstado(NORMAL);
        }
    }
}
