package tp1.clases.vista;

import tp1.clases.modelo.Habilidad;
import tp1.clases.modelo.Item;
import tp1.clases.modelo.Pokemon;

import java.util.List;

public class VistaMenu {
    public static String mostrarOpciones() {
        StringBuilder menu = new StringBuilder("Seleccione una opcion:\n");
        for (OpcionMenu opcion: OpcionMenu.values()){
            String op = String.format("%d. %s\n", opcion.ordinal()+1, opcion.getDescripcion()); //no me gusta ese +1 :(
            menu.append(op);
        }
        return menu.toString();
    }

    public static String mostrarPokemones(List<Pokemon> pokemones) {
        StringBuilder listaPokemones = new StringBuilder("Pokemones disponibles:\n");
        listaPokemones = agregarOpcionVolverAtras(listaPokemones);
        for (int i = 1; i < pokemones.size(); i++) {
            Pokemon pokemon = pokemones.get(i);
            String pokemonInfo = String.format("%d. %s (%d de vida)\n", i, pokemon.getNombre(), pokemon.getVida());
            listaPokemones.append(pokemonInfo);
        }
        return listaPokemones.toString();
    }

    public static String mostrarItems(List<Item> items) {
        StringBuilder listaItems = new StringBuilder("Items disponibles:\n");
        listaItems = agregarOpcionVolverAtras(listaItems);
        for (int i= 1; i < items.size(); i++) {
            String item = String.format("%d. %s\n", i, items.get(i).getNombre());
            listaItems.append(item);
        }
        return listaItems.toString();
    }

    public static String mostrarHabilidades(List<Habilidad> habilidades) {
        StringBuilder listaHabilidades = new StringBuilder("Habilidades disponibles: \n");
        listaHabilidades = agregarOpcionVolverAtras(listaHabilidades);
        for (int i = 1; i < habilidades.size(); i++) {
            String habilidad = String.format("%d. %s (usos: %s)\n", i, habilidades.get(i).getNombre(), habilidades.get(i).getUsos());
            listaHabilidades.append(habilidad);
        }
        return listaHabilidades.toString();
    }

    private static StringBuilder agregarOpcionVolverAtras(StringBuilder cadena){
        String volverAtras = String.format("%d. Volver atras\n", 0);
        return cadena.append(volverAtras);
    }
}


