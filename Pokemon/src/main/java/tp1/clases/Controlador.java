package tp1.clases;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import tp1.clases.modelo.Batalla;
import tp1.clases.vista.VistaMenu;

import java.io.IOException;

public class Controlador {

    private Batalla batalla;
    private VistaMenu vistaMenu;
    private LineReader reader;

    public Controlador(Batalla batalla) throws IOException {
        this.batalla = batalla;
        this.vistaMenu = new VistaMenu(); //To Do: completar la vista del menu (que pasarle por parametro, batalla(? )

        Terminal terminal = TerminalBuilder.terminal();
        reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }

    public void Jugar(){
        //vistaMenu me lo copie del video pero ni idea como va a ser
        String opcionElegida = reader.readLine("Elija su proxima acción: \n" + vistaMenu.mostrar()); //To Do: determinar como se van a imprimir los mensajes (una vez ya tenga vistaMenu)

        while(true) {
            try {
                int op = Integer.parseInt(opcionElegida);
                break;
            } catch (NumberFormatException err) { //no se si deberia estar en el archivo de errores
                opcionElegida = reader.readLine("Acción no valida, ingrese el numero de acción elejida: ");
            }
        }

        //definir la variable accion llamando a vistaMenu y pidiendole que especifique quien es op
        //hacer battala.accion(), tmb se puede hacer un metodo aparte con un switch de las opciones elegidas (metodo privado Accion(accion))

        this.batalla.cambiarTurno(); //To Do: agregar un if si la partida esta terminada, hacer como metodo privado(? aparte
    }

    private void Accion(String accion){ //que accion sea un String es momentaneo hasta decidir q va a devolver vistaMenu
        switch (accion){
            case "ver campo":
                //llamar a vistaBatalla(?
                //devolver para que Juego() pregunte que pregunte como seguir(?
            case "item":
                //llamar a vistaMenu(? para consultar los items con sus usos disponibles
                //devolver para que Juego() pregunte que items usar(?
            case "pokemon":
                //llamar a vistaMenu(? para consultar los pokemones disponibles con sus respectivas vidas
                //devolver para que Juego() pregunte que pokemon usar(?
            case "habilidad":
                //llamar a vistaMenu(? para consultar las habilidades
                //devolver para que Juego() pregunte que habilidad usar(?
            case "rendirse":
                this.batalla.obtenerGanador();
        }

    }
}
