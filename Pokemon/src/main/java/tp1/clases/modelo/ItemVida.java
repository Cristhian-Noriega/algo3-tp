package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorPokemonNoMuerto;

import java.util.Optional;

public class ItemVida implements Item {

    private final String nombre;
    private final int vida;

    int REVIVIR = 0;

    public ItemVida(String nombre, int vida){ //si vida es 0 entonces es pocion para revivir
        this.nombre = nombre;
        this.vida = vida;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public Optional<Error> usar(Pokemon pokemon){
        if ((this.vida == REVIVIR) && pokemon.estaMuerto()) {
            pokemon.modificarVida(pokemon.getVidaMax());
            System.out.println("¡Pokemon " + pokemon.getNombre() + " ha revivido!!");
            return Optional.empty();
        } else if (this.vida == REVIVIR) {
            return Optional.of(new ErrorPokemonNoMuerto(pokemon.getNombre(), this.nombre));
        } else {
            pokemon.modificarVida(this.vida);
            System.out.println("¡" + this.nombre + " ha sido usada!");
            System.out.println("Vida actual de " + pokemon.getNombre() + ": " + pokemon.getVida());
            return Optional.empty();
        }
    }

}
