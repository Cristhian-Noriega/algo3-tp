package tp1.clases.controlador;

import tp1.clases.modelo.Habilidad;
import tp1.clases.vista.OpcionMenu;
import tp1.clases.vista.VistaMenu;

import java.util.List;

public class MenuHabilidades extends Menu {

    private final List<Habilidad> habilidades;

    public MenuHabilidades(List<Habilidad> habilidades){
        this.habilidades = habilidades;
    }


    public void mostrarOpciones(){
        System.out.println(VistaMenu.mostrarHabilidades(this.habilidades));
    }

    public int cantidadOpciones(){
        return this.habilidades.size();
    }

}
