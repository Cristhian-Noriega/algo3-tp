package tp1.clases.vista;

import tp1.clases.errores.Error;
import tp1.clases.modelo.Batalla;

import java.util.Optional;

public class CambiarPokemonComando implements Comando{

    private final Batalla batalla;
    private int pokemon;

    public CambiarPokemonComando(Batalla batalla) {
        this.batalla = batalla;
    }

    @Override
    public void definirOpcion(int op){
        this.pokemon = op;
    }

    public Optional<Error> ejecutar(){
         return this.batalla.cambiarPokemon(this.pokemon);
    }
}
