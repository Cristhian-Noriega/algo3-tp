package tp1.clases.errores;

public class ErrorPokemonMuerto extends Error{
    public ErrorPokemonMuerto() {
        super("Este pokemon está muerto, no se puede usar");
    }
}
