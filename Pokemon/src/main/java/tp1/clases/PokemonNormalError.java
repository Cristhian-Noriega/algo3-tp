package tp1.clases;

public class PokemonNormalError extends RuntimeException {
    public PokemonNormalError(String pokemon, String item) {
        super("El pokemon " + pokemon + " no esta muerto, no se puede usar el Item " + item);
    }

}
