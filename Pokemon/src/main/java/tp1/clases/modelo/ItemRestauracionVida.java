package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorPokemonMuerto;

import java.util.Optional;

public class ItemRestauracionVida implements Item {

    protected final String nombre;
    protected int vida;
    private final Categoria categoria = Categoria.VIDA;

    int MOLESTAALUMNOS = 0;

    private final String descripcion;

    public ItemRestauracionVida(String nombre, int vida, String descripcion){ //si vida es 0 entonces es pocion molesta alumnos
        this.nombre = nombre;
        this.vida = vida;
        this.descripcion = descripcion;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public Categoria getCategoria() {
        return this.categoria;
    }

    @Override
    public Optional<Error> usar(Pokemon pokemon){
        if (pokemon.estaMuerto()) {
            return Optional.of(new ErrorPokemonMuerto(pokemon.getNombre()));
        } else if (this.vida == MOLESTAALUMNOS){
            this.vida = pokemon.getVidaMax() / 3;
        }
        pokemon.modificarVida(this.vida);
        System.out.println("¡" + this.nombre + " ha sido usada!");
        System.out.println("Vida actual de " + pokemon.getNombre() + ": " + pokemon.getVida());
        return Optional.empty();
    }

    public String getDescripcion() {
        return descripcion;
    }
}
