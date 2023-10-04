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

    private static String mostrarEstadoPokemon(Pokemon pokemon) {
        String informacion = "";
        String barraDeVida = CampoVista.crearBarraDeVida(pokemon.getVida(), pokemon.getVidaMax());
        informacion += pokemon.getNombre() + "\n";
        informacion += pokemon.getVida() + "/" + pokemon.getVidaMax() + " " + "(" + barraDeVida + ")" + "   ";
        informacion += pokemon.getTipo().name() + "   ";
        informacion += pokemon.getEstado().name() + "   ";
        informacion += "NVL." + pokemon.getNivel() + "   ";
        informacion += "DEF." + pokemon.getDefensa() + "   ";
        informacion += "ATAQ." + pokemon.getAtaque() + "   ";
        informacion += "VEL." + pokemon.getVelocidad() + "   \n";

        return informacion;
    }


    public static String mostrarPokemones(List<Pokemon> pokemones) {
        StringBuilder listaPokemones = new StringBuilder("Pokemones disponibles:\n");
        listaPokemones = agregarOpcionVolverAtras(listaPokemones);
        int contador = 1;
        for (Pokemon pokemon: pokemones) {
            String pokemonInfo = contador + ". " + mostrarEstadoPokemon(pokemon);
            listaPokemones.append(pokemonInfo);
            contador += 1;
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

    public static String mostrarInfoHabilidad(Habilidad habilidad) {
        String informacion = "";
        informacion += habilidad.getNombre() + "   ";
        informacion += habilidad.getTipo().name() + "   ";
        informacion += "USOS." + habilidad.getUsos() + "   \n";
        informacion += habilidad.getInfo() + "\n \n";
        return informacion;
    }


    public static String mostrarHabilidades(List<Habilidad> habilidades) {
        StringBuilder listaHabilidades = new StringBuilder("Habilidades disponibles: \n");
        listaHabilidades = agregarOpcionVolverAtras(listaHabilidades);
        listaHabilidades.append("\n");
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


