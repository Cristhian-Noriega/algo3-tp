package tp1.clases.errores;

public class ErrorPokemonMuerto extends Error{
    public ErrorPokemonMuerto(String pokemon) {
        super(String.format("%s está debilitado, no puede salir al campo de batalla.", pokemon));

    }
}
