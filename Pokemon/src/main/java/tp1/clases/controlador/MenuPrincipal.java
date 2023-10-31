package tp1.clases.controlador;

import tp1.clases.vista.OpcionMenu;
import tp1.clases.vista.VistaMenu;

public class MenuPrincipal extends Menu {

    public MenuPrincipal() {
        super.categoria = CategoriaMenu.PRINCIPAL;
    }

    public void mostrarOpciones(){
        System.out.println(VistaMenu.mostrarOpciones());
    }

    public int cantidadOpciones(){
        return OpcionMenu.values().length - 1;
    }

    public CategoriaMenu getCategoria(){
        return super.categoria;
    }

}
