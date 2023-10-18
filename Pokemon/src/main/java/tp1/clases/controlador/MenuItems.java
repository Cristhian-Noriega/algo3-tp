package tp1.clases.controlador;

import tp1.clases.modelo.Item;
import tp1.clases.vista.VistaMenu;

import java.util.List;
import java.util.Map;

public class MenuItems extends Menu {
    private Map<String, Long> mapCantidadItems;

    private List<Item> items;


    public MenuItems(Map<String, Long> mapCantidadItems,List<Item> items){
        this.mapCantidadItems = mapCantidadItems;
        this.items = items;

    }

    public void mostrarOpciones(){
        System.out.println(VistaMenu.mostrarItems(this.mapCantidadItems, this.items));
    }

    public int cantidadOpciones(){
        return this.items.size();
    }

}