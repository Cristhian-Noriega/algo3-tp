package tp1.clases.vista;

import tp1.clases.modelo.Batalla;

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

    public void ejecutar(){
         this.batalla.cambiarPokemon(this.pokemon);
    }
}
