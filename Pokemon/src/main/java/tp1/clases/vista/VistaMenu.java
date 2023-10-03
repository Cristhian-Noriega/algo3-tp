package tp1.clases.vista;

import tp1.clases.modelo.Habilidad;
import tp1.clases.modelo.Item;
import tp1.clases.modelo.Jugador;
import tp1.clases.modelo.Pokemon;

import java.util.Arrays;
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
        for (int i = 0; i < pokemones.size(); i++) {
            Pokemon pokemon = pokemones.get(i);
            String pokemonInfo = String.format("%d. %s (%d de vida)\n", i, pokemon, pokemon.getVida());
            listaPokemones.append(pokemonInfo);
        }
        return listaPokemones.toString();
    }

    //el enunciado dice que ademas del nombre se debe imprimir la cantidad de usos restantes del item
    // no me queda claro, porque cada item se puede usar una sola vez
    public static String mostrarItems(List<Item> items) {
        StringBuilder listaItems = new StringBuilder("Items disponibles:\n");
        for (int i= 0; i < items.size(); i++) {
            String item = String.format("%d. %s\n", i, items.get(i).getNombre());
            listaItems.append(item);
        }
        return listaItems.toString();
    }

    public static String mostrarHabilidades(List<Habilidad> habilidades) {
        StringBuilder listaHabilidades = new StringBuilder("Habilidades disponibles: \n");
        for (int i = 0; i < habilidades.size(); i++) {
            String habilidad = String.format("%d. %s (usos: %s)\n", i, habilidades.get(i).getNombre(), habilidades.get(i).getUsos());
            listaHabilidades.append(habilidad);
        }
        return listaHabilidades.toString();
    }


}


