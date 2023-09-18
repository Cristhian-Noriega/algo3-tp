package tp1.clases;

public class PokemonNormalError extends RuntimeException {

    //En el caso de que un jugador quiera usar un item para sacar de un estado al pokemon y este no lo requiera se envia un mensaje de error
    public PokemonNormalError(String pokemon, String item) {
        super(pokemon + " no necesita el Item " + item);
    }

}
