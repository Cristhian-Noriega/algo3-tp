package tp1.clases;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tp1.clases.modelo.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Inicializador {

    public static ArrayList<Jugador> iniciarJugadores() throws IOException{


        ArrayList<Item> listaItems = crearListaItems();

        ArrayList<Habilidad> listaHabilidades = crearListaHabilidades();

        ArrayList<Pokemon> listaPokemones = crearListaPokemones(listaHabilidades);

        return crearListaJugadores(listaPokemones, listaItems);

    }

    private static ArrayList<Item> crearListaItems() throws IOException {
        String pathItems = "resources/items.json";

        ArrayList<Item> listaItems = new ArrayList<Item>();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode listaItemsNode = objectMapper.readTree(new File(pathItems));
        for (JsonNode itemNode : listaItemsNode) {
            Categoria categoria = Categoria.valueOf(itemNode.get("categoria").asText());
            String nombre = itemNode.get("nombre").asText();
            int id = itemNode.get("id").asInt();

            Item item = null;
            switch (categoria){
                case ESTADO:
                    item = new ItemEstado(nombre, id);
                    listaItems.add(item);
                    continue;
                case ESTADISTICA:
                    Estadisticas estadistica = Estadisticas.valueOf(itemNode.get("estadistica").asText());
                    item = new ItemEstadistica(nombre, estadistica, id);
                    listaItems.add(item);
                    continue;
                case VIDA:
                    int vida = itemNode.get("vida").asInt();
                    if (Objects.equals(nombre, "Revivir")){
                        item = new ItemRevivir(nombre, vida, id);
                    } else {
                        item = new ItemRestauracionVida(nombre, vida, id);
                    }
                    listaItems.add(item);
            }
        }
        return listaItems;
    }


    private static ArrayList<Habilidad> crearListaHabilidades() throws IOException {
        String pathHabilidades = "resources/habilidades.json";

        ArrayList<Habilidad> listaHabilidades = new ArrayList<Habilidad>();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode listaHabilidadesNode = objectMapper.readTree(new File(pathHabilidades));
        for (JsonNode habilidadNodo : listaHabilidadesNode) {
            Categoria categoria = Categoria.valueOf(habilidadNodo.get("categoria").asText());
            String nombre = habilidadNodo.get("nombre").asText();
            int id = habilidadNodo.get("id").asInt();
            String info = habilidadNodo.get("info").asText();
            Tipo tipo = Tipo.valueOf(habilidadNodo.get("tipo").asText());
            int usos = habilidadNodo.get("usos").asInt();

            Habilidad habilidad = null;
            switch (categoria){
                case ATAQUE:
                    int poder = habilidadNodo.get("poder").asInt();
                    habilidad = new HabilidadAtaque(nombre, usos, tipo, poder, info, id);
                    listaHabilidades.add(habilidad);
                    continue;
                case ESTADO:
                    Estado estado = Estado.valueOf(habilidadNodo.get("estado").asText());
                    habilidad = new HabilidadEstado(nombre, usos, tipo, info, estado, id);
                    listaHabilidades.add(habilidad);
                    continue;
                case ESTADISTICA:
                    Estadisticas estadistica = Estadisticas.valueOf(habilidadNodo.get("estadistica").asText());
                    boolean contraRival = habilidadNodo.get("contraRival").asBoolean();
                    habilidad = new HabilidadEstadistica(nombre, usos, tipo, info, estadistica, contraRival, id);
                    listaHabilidades.add(habilidad);
                    continue;
                case CLIMA:
                    Clima clima = Clima.valueOf(habilidadNodo.get("clima").asText());
                    habilidad = new HabilidadClima(nombre, usos, tipo, info, clima, id);
                    listaHabilidades.add(habilidad);
            }
        }
        return listaHabilidades;

    }

    private static ArrayList<Pokemon> crearListaPokemones(ArrayList<Habilidad> listaHabilidades) throws IOException {
        String pathPokemones = "resources/pokemones.json";

        ArrayList<Pokemon> listaPokemones = new ArrayList<Pokemon>();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode pokemonesNode = objectMapper.readTree(new File(pathPokemones));
        for (JsonNode pokeNode : pokemonesNode) {
            double ataque = pokeNode.get("ataque").asDouble();
            double defensa = pokeNode.get("defensa").asDouble();

            ArrayList<Habilidad> habilidades =  new ArrayList<Habilidad>();
            JsonNode habilidadesNode = pokeNode.get("habilidades");
            if (habilidadesNode != null && habilidadesNode.isArray()) {
                Iterator<JsonNode> habilidadesElements = habilidadesNode.elements();
                while (habilidadesElements.hasNext()) {
                    int habilidadIndex = habilidadesElements.next().asInt();
                    if (habilidadIndex < listaHabilidades.size()){
                        habilidades.add(listaHabilidades.get(habilidadIndex));

                    }
                }
            }

            int id = pokeNode.get("id").asInt();
            int nivel = pokeNode.get("nivel").asInt();
            String nombre = pokeNode.get("nombre").asText();
            Tipo tipo = Tipo.valueOf(pokeNode.get("tipo").asText());
            double velocidad = pokeNode.get("velocidad").asDouble();
            int vidaMax = pokeNode.get("vidaMax").asInt();

            Pokemon poke = new Pokemon(nombre, nivel, tipo, habilidades, vidaMax, velocidad, ataque, defensa, id);
            listaPokemones.add(poke);
        }

        return listaPokemones;
    }

    private static ArrayList<Jugador> crearListaJugadores(ArrayList<Pokemon> listaPokemones, ArrayList<Item> listaItems) throws IOException {
        String pathJugadores = "resources/jugadores.json";

        ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jugadoresNode = objectMapper.readTree(new File(pathJugadores));
        for (JsonNode jugNode : jugadoresNode) {
            String nombre = jugNode.get("nombre").asText();

            ArrayList<Pokemon> pokemones = new ArrayList<Pokemon>();
            JsonNode pokeJugadorNode = jugNode.get("pokemones");
            if (pokeJugadorNode != null && pokeJugadorNode.isArray()) {
                Iterator<JsonNode> pokeElem = pokeJugadorNode.elements();
                while (pokeElem.hasNext()) {
                    int pokeIndex = pokeElem.next().asInt();
                    if (pokeIndex < listaPokemones.size()){
                        pokemones.add(listaPokemones.get(pokeIndex));
                    }
                }
            }

            ArrayList<Item> items = new ArrayList<Item>();
            JsonNode itemsNode = jugNode.get("items");
            if (itemsNode != null && itemsNode.isArray()) {
                Iterator<JsonNode> itemElem = itemsNode.elements();
                while (itemElem.hasNext()) {
                    int itemIndex = itemElem.next().asInt();
                    if (itemIndex < listaItems.size()){
                        items.add(listaItems.get(itemIndex));

                    }
                }
            }

            Jugador jugador = new Jugador(nombre, pokemones, items);
            listaJugadores.add(jugador);
        }

        return listaJugadores;
    }




}
