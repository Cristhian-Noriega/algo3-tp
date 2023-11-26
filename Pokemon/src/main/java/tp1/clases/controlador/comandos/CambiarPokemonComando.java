package tp1.clases.controlador.comandos;

import tp1.clases.errores.Error;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Pokemon;

import java.util.Optional;

public class CambiarPokemonComando implements Comando {

    private final Batalla batalla;
    private Pokemon pokemon;


    public CambiarPokemonComando(Batalla batalla) {
        this.batalla = batalla;
    }

    public void definirOpcion(Pokemon op){
        this.pokemon = op;
    }

    public Optional<Error> ejecutar(){
         Optional<Error> err = this.batalla.cambiarPokemon(this.pokemon);
         if (err.isEmpty()) {
             System.out.printf("¡%s sale al campo de batalla!\n", this.batalla.getJugadorActual().getPokemonActual().getNombre());
         }
         return err;
    }
}
