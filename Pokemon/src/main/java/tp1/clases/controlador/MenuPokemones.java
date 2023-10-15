package tp1.clases.controlador;

import tp1.clases.modelo.Pokemon;
import tp1.clases.vista.VistaMenu;

import java.util.List;

public class MenuPokemones extends Menu {

    private Boolean conVolverAtras;
    private List<Pokemon> pokemones;


    public MenuPokemones(List<Pokemon> pokemones, Boolean conVolverAtras){
        this.conVolverAtras = conVolverAtras;
        this.pokemones = pokemones;
    }


    public void mostrarOpciones(){
        System.out.println(VistaMenu.mostrarPokemones(this.pokemones, this.conVolverAtras));
    }

   public int cantidadOpciones(){
        return pokemones.size();
   }
}