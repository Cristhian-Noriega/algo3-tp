package tp1.clases.controlador;

import tp1.clases.modelo.Pokemon;
import tp1.clases.vista.VistaMenu;

import java.util.List;

public class MenuPokemones extends Menu {

    private final Boolean conVolverAtras;
    private final List<Pokemon> pokemones;


    public MenuPokemones(List<Pokemon> pokemones, Boolean conVolverAtras){
        this.conVolverAtras = conVolverAtras;
        this.pokemones = pokemones;
        super.categoria = CategoriaMenu.POKEMONES;
    }


    public void mostrarOpciones(){
        System.out.println(VistaMenu.mostrarPokemones(this.pokemones, this.conVolverAtras));
    }

   public int cantidadOpciones(){
        return pokemones.size();
   }

    public CategoriaMenu getCategoria(){
        return super.categoria;
    }
}