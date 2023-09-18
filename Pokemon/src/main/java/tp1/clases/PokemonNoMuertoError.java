package tp1.clases;

public class PokemonNoMuertoError extends RuntimeException {
    public PokemonNoMuertoError(String pokemon, String item) {
        super(pokemon + " no necesita el Item " + item);
    }

}
