package tp1.clases.vista;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

public class OpcionesMenu {
      private static final Map<Integer, List> opciones = new HashMap<>();

      public OpcionesMenu() {
          opciones.put(1, List.of("VistaCampo", VistaMenu.mostrarCampo()));
          opciones.put(2, List.of("UsarItem", VistaMenu.mostrarItems()));
          opciones.put(3, List.of("CambiarPokemon", VistaMenu.mostrarPokemones()));
          opciones.put(4, List.of("UsarHabilidad", VistaMenu.mostrarHabilidades()));
          opciones.put(5, List.of("rendirse"));
          opciones.put(6, List.of("VolverAtras"));
      }

      public List getOpcion(int numero) {
          return opciones.getOrDefault(numero, null);
      }

      public int getSize() {
          return opciones.size();
      }

}
