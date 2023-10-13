package tp1.clases.controlador;

import tp1.clases.vista.OpcionMenu;

import java.util.ArrayList;

public class MenuPrincipal extends Menu {

    public Menu procesarOpcion(int opcion){
        if (opcion == OpcionMenu.VER_ITEM.ordinal()){
            return new MenuItems();
        }else if (opcion == OpcionMenu.VER_POKEMONES.ordinal()){
            return new MenuHabilidades();
        }else {

        }


    }

    public void mostrarOpciones(){

    }
}
