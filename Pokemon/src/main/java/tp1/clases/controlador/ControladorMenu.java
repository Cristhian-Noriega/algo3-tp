package tp1.clases.controlador;

import tp1.clases.modelo.Batalla;

import java.util.Stack;

public class ControladorMenu {
    private final Stack<Menu> pilaMenu = new Stack<>();

    public ControladorMenu(Batalla batalla){
        this.pilaMenu.push(new MenuPrincipal());
    }

    public void actualizarMenu(Menu nuevoMenu) {
        pilaMenu.push(nuevoMenu);
    }

    public void retroceder(){
        if (!pilaMenu.isEmpty()){
            pilaMenu.pop();
        }
    }
    public Menu obtenerMenuActual(){
        if (pilaMenu.isEmpty()){
            return null;
        }
        return pilaMenu.peek();
    }
 }
