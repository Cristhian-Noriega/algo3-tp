package tp1.clases.vista;

import tp1.clases.modelo.Item;
import tp1.clases.modelo.Jugador;
import tp1.clases.modelo.Pokemon;

import java.util.Arrays;
import java.util.List;

public class VistaMenu {
    private static List<String> opciones = Arrays.asList("Atacar", "Usar Item", "Ver Pokemones", "Ver campo de batalla", "Rendirse");
    public static String mostrarOpciones() {
        StringBuilder menu = new StringBuilder("Seleccione una opcion:\n");
        for (int i = 0; i < opciones.size(); i++) {
            String opcion = String.format("%d. %s\n", i, opciones.get(i));
            menu.append(opcion);
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


}


