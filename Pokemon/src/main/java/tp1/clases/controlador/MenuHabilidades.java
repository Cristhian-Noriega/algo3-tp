package tp1.clases.controlador;

import tp1.clases.modelo.Habilidad;
import tp1.clases.vista.OpcionMenu;

import java.util.List;

public class MenuHabilidades extends Menu {

    public void procesarOpcion(int opcion){
        if (opcion == OpcionMenu.VOLVER_ATRAS.ordinal()){
            return new MenuPrincipal();
        }else{
            //
        }
    }

    public void mostrarOpciones(){

    }

    public void retroceder(){

    }

}
