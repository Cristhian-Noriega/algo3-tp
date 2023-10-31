package tp1.clases.errores;

public class ErrorCambiarPokemonEnBatalla extends Error{
    public ErrorCambiarPokemonEnBatalla(String pokemonEnBatalla){
        super(String.format("%s ya esta en el campo de batalla, elija otro pokemon", pokemonEnBatalla));
    }
}