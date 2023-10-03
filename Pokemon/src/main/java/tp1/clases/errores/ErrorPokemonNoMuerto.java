package tp1.clases.errores;

public class ErrorPokemonNoMuerto extends Error {

    public ErrorPokemonNoMuerto(String pokemon, String item){
        super("El pokemon " + pokemon + " no esta muerto, no se puede usar el Item " + item);
    }

}
