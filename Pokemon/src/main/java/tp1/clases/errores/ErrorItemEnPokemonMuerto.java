package tp1.clases.errores;

public class ErrorItemEnPokemonMuerto extends Error{
    public ErrorItemEnPokemonMuerto(String pokemon) {
        super(String.format("%s está debilitado, no puede usar el item seleccionado", pokemon));

    }
}