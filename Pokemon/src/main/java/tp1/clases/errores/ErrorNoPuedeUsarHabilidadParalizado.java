package tp1.clases.errores;

public class ErrorNoPuedeUsarHabilidadParalizado extends Error{
    public ErrorNoPuedeUsarHabilidadParalizado(String pokemonEnBatalla){
        super(String.format("%s esta paralizado, no pudo usar la habilidad", pokemonEnBatalla));
    }
}
