package tp1.clases.controlador;

import java.util.Stack;

public class ControladorMenu {
    private Stack<Menu> pilaMenu = new Stack<>();

    public ControladorMenu(){
        this.pilaMenu.push(new MenuPrincipal());
    }

    public void actualizarMenu(Menu nuevoMenu) {
        pilaMenu.push(nuevoMenu);
    }

    public void retroceder(){
        if (!pilaMenu.isEmpty() && pilaMenu.size() > 1){
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
