package tp1.clases.controlador;

import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Habilidad;
import tp1.clases.vista.CampoVista;
import tp1.clases.vista.OpcionMenu;
import tp1.clases.vista.VistaMenu;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuPrincipal extends Menu {


//    private Map<OpcionMenu, Runnable> acciones = new HashMap<>();

    public MenuPrincipal() {}
//        acciones.put(OpcionMenu.VER_CAMPO, () -> {
//            CampoVista campo = new CampoVista();
//        });
//        acciones.put(OpcionMenu.VER_POKEMONES, () -> {
//
//        })

    
//    public void procesarOpcion(int opcion){
//
//
//    }


    public void mostrarOpciones(){
        System.out.println(VistaMenu.mostrarOpciones());
    }

    public int cantidadOpciones(){
        return OpcionMenu.values().length;
    }
}
