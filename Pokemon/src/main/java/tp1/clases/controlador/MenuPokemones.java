package tp1.clases.controlador;

import tp1.clases.modelo.Pokemon;

import java.util.List;

public class MenuPokemones extends Menu {

    private Boolean conVolverAtras;
    private List<Pokemon> pokemones;

    public MenuPokemones(List<Pokemon> pokemones, Boolean conVolverAtras){
        this.conVolverAtras = conVolverAtras;
        this.pokemones = pokemones;
    }

    public void procesarOpcion(int opcion){

    }

    public void mostrarOpciones(){ //no olvidar la opcion conVolverAtras

    }

    public void retroceder(){

    }

}