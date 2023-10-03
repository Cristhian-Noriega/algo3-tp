package tp1.clases.errores;

public class ErrorPokemonNormal extends Error{

    public ErrorPokemonNormal(String pokemon, String item) {
        super(pokemon + " no necesita el Item " + item);
    }

}
