package tp1.clases;

public class PokemonNoMuertoError extends RuntimeException {

    //En el caso de que un jugador intente revivir un pokemon no muerto (no debilitado) se envia un mensaje de error
    public PokemonNoMuertoError(String pokemon, String item) {
        super("El pokemon " + pokemon + " no esta muerto, no se puede usar el Item " + item);
    }

}
