package tp1.clases.errores;

public class ErrorNoPuedeAtacarDormido extends Error{
    public ErrorNoPuedeAtacarDormido(String pokemonEnBatalla){
        super(String.format("%s no puede atacar estando dormido", pokemonEnBatalla));
    }
}
