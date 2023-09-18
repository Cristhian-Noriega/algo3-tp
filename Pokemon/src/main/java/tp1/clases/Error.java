package tp1.clases;

public class Error {

    public static String pokemonNoMuerto(String pokemon, String item){
        return "El pokemon " + pokemon + " no esta muerto, no se puede usar el Item " + item;
    }

    public static String pokemonNormal(String pokemon, String item){
        return pokemon + " no necesita el Item " + item;
    }
}
