package tp1.clases.errores;

public class ErrorPokemonMuerto extends Error{
    public static ErrorPokemonMuerto() {
        super("Este pokemon está muerto, no se puede usar");
    }
}
