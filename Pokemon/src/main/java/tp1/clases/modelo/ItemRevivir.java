package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorPokemonNoMuerto;

import java.util.Optional;

public class ItemRevivir implements Item {
    protected final String nombre;
    protected final int vida;
    private final Categoria categoria = Categoria.VIDA;
    public ItemRevivir(String nombre, int vida){
        this.nombre = nombre;
        this.vida = vida;
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
        if (!pokemon.estaMuerto()) {
            return Optional.of(new ErrorPokemonNoMuerto(pokemon.getNombre(), this.nombre));
        }
        pokemon.modificarVida(pokemon.getVidaMax());
        System.out.println("¡Pokemon " + pokemon.getNombre() + " ha revivido!!");
        return Optional.empty();
    }
}
