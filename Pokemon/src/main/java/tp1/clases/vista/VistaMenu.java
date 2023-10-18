package tp1.clases.vista;

import tp1.clases.modelo.Habilidad;
import tp1.clases.modelo.Item;
import tp1.clases.modelo.Pokemon;

import java.util.List;
import java.util.Map;

public class VistaMenu {
    public static String mostrarOpciones() {
        StringBuilder menu = new StringBuilder("Seleccione una opcion:\n");
        for (OpcionMenu opcion: OpcionMenu.values()){
            if (opcion.ordinal() > 0) {
                String op = String.format("%d. %s\n", opcion.ordinal(), opcion.getDescripcion());
                menu.append(op);
            }
        }
        return menu.toString();
    }

    private static String mostrarEstadoPokemon(Pokemon pokemon) {
        String informacion = "";
        String barraDeVida = CampoVista.crearBarraDeVida(pokemon.getVida(), pokemon.getVidaMax());
        informacion += pokemon.getNombre() + "\n";
        informacion += pokemon.getVida() + "/" + pokemon.getVidaMax() + " " + barraDeVida + "   ";
        informacion += pokemon.getTipo().name() + "   ";
        informacion += pokemon.getEstado().name() + "   ";
        informacion += "NVL." + pokemon.getNivel() + "   ";
        informacion += "DEF." + pokemon.getDefensa() + "   ";
        informacion += "ATAQ." + pokemon.getAtaque() + "   ";
        informacion += "VEL." + pokemon.getVelocidad() + "   \n";

        return informacion;
    }

    public static String mostrarPokemones(List<Pokemon> pokemones, Boolean conVolverAtras) {
        StringBuilder listaPokemones = new StringBuilder("Pokemones disponibles:\n");
        if (conVolverAtras){
            listaPokemones = agregarOpcionVolverAtras(listaPokemones);
        }

        int contador = 1;
        for (Pokemon pokemon: pokemones) {
            String pokemonInfo = contador + ". " + mostrarEstadoPokemon(pokemon);
            listaPokemones.append(pokemonInfo);
            contador += 1;
        }
        return listaPokemones.toString();
    }

    public static String mostrarItems(Map<String, Long> mapCantidadItems, List<Item> items) {
        StringBuilder listaItems = new StringBuilder("Items disponibles:\n");
        listaItems = agregarOpcionVolverAtras(listaItems);

        int i = 1;
        for (Item item : items) {
            Long cantidad = mapCantidadItems.get(item.getNombre());
            String itemAct = String.format("%d. %s - cantidad: %d\n", i, item.getNombre(), cantidad);
            i++;
            listaItems.append(itemAct);
        }

        return listaItems.toString();
    }

    public static String mostrarInfoHabilidad(Habilidad habilidad) {
        String informacion = "";
        informacion += habilidad.getNombre() + "   ";
        informacion += habilidad.getTipo().name() + "   ";
        informacion += "USOS." + habilidad.getUsos() + "   \n";
        informacion += "   " + habilidad.getInfo() + "\n";
        return informacion;
    }

    public static String mostrarHabilidades(List<Habilidad> habilidades) {
        StringBuilder listaHabilidades = new StringBuilder("Habilidades disponibles: \n");
        listaHabilidades = agregarOpcionVolverAtras(listaHabilidades);

        int contador = 1;
        for (Habilidad habilidad: habilidades) {
            String infoHabilidad = contador + ". " +mostrarInfoHabilidad(habilidad);
            listaHabilidades.append(infoHabilidad);
            contador += 1;
        }
        return listaHabilidades.toString();
    }

    private static StringBuilder agregarOpcionVolverAtras(StringBuilder cadena){
        String volverAtras = String.format("%d. Volver atras\n", 0);
        return cadena.append(volverAtras);
    }
}


