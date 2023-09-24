package tp1.clases;

public class ItemVida implements Item {

    private final String nombre;
    private final int vida;

    public ItemVida(String nombre, int vida){ //si vida es 0 entonces es pocion para revivir
        this.nombre = nombre;
        this.vida = vida;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void usar(Pokemon pokemon){
        if ((this.vida == 0) && pokemon.estaMuerto()) {
            pokemon.modificarVida(pokemon.getVidaMax());
            System.out.println("¡Pokemon " + pokemon.getNombre() + " ha revivido!!");
        } else if (this.vida == 0) {
            PokemonNoMuertoError(pokemon.getNombre(), this.nombre); //TO DO: organizar a donde van a ir los errores
        } else {
            pokemon.modificarVida(this.vida);
            System.out.println("¡" + this.nombre + " ha sido usada!");
            System.out.println("Vida actual de " + pokemon.getNombre() + ": " + pokemon.getVida());
        }
    }

}
