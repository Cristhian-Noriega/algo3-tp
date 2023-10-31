package tp1.clases.controlador.comandos;

import tp1.clases.errores.Error;
import tp1.clases.modelo.Batalla;

import java.util.Optional;

public class CambiarPokemonComando implements Comando {

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
         Optional<Error> err = this.batalla.cambiarPokemon(this.pokemon);
         if (err.isEmpty()) {
             System.out.printf("¡%s sale al campo de batalla!\n", this.batalla.getJugadorActual().getPokemonActual().getNombre());
         }
         return err;
    }
}
