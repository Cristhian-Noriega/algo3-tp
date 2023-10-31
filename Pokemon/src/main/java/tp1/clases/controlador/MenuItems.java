package tp1.clases.controlador;

import tp1.clases.modelo.Item;
import tp1.clases.vista.VistaMenu;

import java.util.List;
import java.util.Map;

public class MenuItems extends Menu {

    private final Map<String, Long> mapCantidadItems;

    private final List<Item> items;

    public MenuItems(Map<String, Long> mapCantidadItems,List<Item> items){
        this.mapCantidadItems = mapCantidadItems;
        this.items = items;
        super.categoria = CategoriaMenu.ITEMS;
    }

    public void mostrarOpciones(){
        System.out.println(VistaMenu.mostrarItems(this.mapCantidadItems, this.items));
    }

    public int cantidadOpciones(){
        return this.items.size();
    }

    public CategoriaMenu getCategoria(){
        return super.categoria;
    }

}