package tp1.clases;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tp1.clases.modelo.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Inicializador {

    public static ArrayList<Jugador> iniciarJugadores() throws IOException, CloneNotSupportedException {

        Map<Integer, Item> listaItems = crearListaItems();

        Map<Integer, Habilidad> listaHabilidades = crearListaHabilidades();

        Map<Integer, Pokemon> listaPokemones = crearListaPokemones(listaHabilidades);

        return crearListaJugadores(listaPokemones, listaItems);

    }

    private static Map<Integer, Item> crearListaItems() throws IOException {
        String pathItems = "/json/items.json";

        Map<Integer,Item> listaItems = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();

        URL urlItems = MainJavaFX.class.getResource(pathItems);

        JsonNode listaItemsNode = objectMapper.readTree(urlItems);
        for (JsonNode itemNode : listaItemsNode) {
            Categoria categoria = Categoria.valueOf(itemNode.get("categoria").asText());
            String nombre = itemNode.get("nombre").asText();
            int id = itemNode.get("id").asInt();
            String info = itemNode.get("info").asText();

            Item item = null;
            switch (categoria){
                case ESTADO:
                    item = new ItemEstado(nombre,info, id);
                    listaItems.put(id, item);
                    continue;
                case ESTADISTICA:
                    Estadisticas estadistica = Estadisticas.valueOf(itemNode.get("estadistica").asText());
                    item = new ItemEstadistica(nombre, estadistica,info, id);
                    listaItems.put(id, item);
                    continue;
                case VIDA:
                    int vida = itemNode.get("vida").asInt();
                    if (Objects.equals(nombre, "Revivir")){
                        item = new ItemRevivir(nombre, vida, info, id);
                    } else {
                        item = new ItemRestauracionVida(nombre, vida, info, id);
                    }
                    listaItems.put(id, item);
            }
        }
        return listaItems;
    }


    private static Map<Integer, Habilidad> crearListaHabilidades() throws IOException {

        String pathHabilidades = "/json/habilidades.json";

        Map<Integer,Habilidad> listaHabilidades = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();

        URL urlHabilidades = MainJavaFX.class.getResource(pathHabilidades);

        JsonNode listaHabilidadesNode = objectMapper.readTree(urlHabilidades);
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
                    listaHabilidades.put(id, habilidad);
                    continue;

                case ESTADO:
                    Estado estado = Estado.valueOf(habilidadNodo.get("estado").asText());
                    habilidad = new HabilidadEstado(nombre, usos, tipo, info, estado, id);
                    listaHabilidades.put(id, habilidad);
                    continue;

                case ESTADISTICA:
                    Estadisticas estadistica = Estadisticas.valueOf(habilidadNodo.get("estadistica").asText());
                    boolean contraRival = habilidadNodo.get("contraRival").asBoolean();
                    habilidad = new HabilidadEstadistica(nombre, usos, tipo, info, estadistica, contraRival, id);
                    listaHabilidades.put(id, habilidad);
                    continue;

                case CLIMA:
                    Clima clima = Clima.valueOf(habilidadNodo.get("clima").asText());
                    habilidad = new HabilidadClima(nombre, usos, tipo, info, clima, id);
                    listaHabilidades.put(id, habilidad);
            }
        }
        return listaHabilidades;

    }

    private static Map<Integer, Pokemon> crearListaPokemones(Map<Integer, Habilidad> listaHabilidades) throws IOException, CloneNotSupportedException {

        String pathPokemones = "/json/pokemones.json";


        Map<Integer, Pokemon> listaPokemones = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();

        URL urlPokemones = MainJavaFX.class.getResource(pathPokemones);

        JsonNode pokemonesNode = objectMapper.readTree(urlPokemones);
        for (JsonNode pokeNode : pokemonesNode) {
            double ataque = pokeNode.get("ataque").asDouble();
            double defensa = pokeNode.get("defensa").asDouble();

            ArrayList<Habilidad> habilidades =  new ArrayList<Habilidad>();
            JsonNode habilidadesNode = pokeNode.get("habilidades");
            if (habilidadesNode != null && habilidadesNode.isArray()) {
                Iterator<JsonNode> habilidadesElements = habilidadesNode.elements();
                while (habilidadesElements.hasNext()) {
                    int habilidadIndex = habilidadesElements.next().asInt();
                    if (habilidadIndex <= listaHabilidades.size()){
                        Habilidad habilidad = listaHabilidades.get(habilidadIndex);
                        habilidades.add(habilidad.clone());
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
            listaPokemones.put(id, poke);
        }

        return listaPokemones;
    }

    private static ArrayList<Jugador> crearListaJugadores(Map<Integer, Pokemon> listaPokemones, Map<Integer, Item> listaItems) throws IOException, CloneNotSupportedException {

        String pathJugadores = "/json/jugadores.json";


        ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();

        ObjectMapper objectMapper = new ObjectMapper();

        URL urlJugadores = MainJavaFX.class.getResource(pathJugadores);

        JsonNode jugadoresNode = objectMapper.readTree(urlJugadores);
        for (JsonNode jugNode : jugadoresNode) {
            String nombre = jugNode.get("nombre").asText();

            ArrayList<Pokemon> pokemones = new ArrayList<Pokemon>();
            JsonNode pokeJugadorNode = jugNode.get("pokemones");
            if (pokeJugadorNode != null && pokeJugadorNode.isArray()) {
                Iterator<JsonNode> pokeElem = pokeJugadorNode.elements();
                while (pokeElem.hasNext()) {
                    int pokeIndex = pokeElem.next().asInt();
                    if (pokeIndex <= listaPokemones.size()){
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
                    if (itemIndex <= listaItems.size()){
                        Item item = listaItems.get(itemIndex);
                        items.add(item.clone());
                    }
                }
            }

            Jugador jugador = new Jugador(nombre, pokemones, items);
            listaJugadores.add(jugador);
        }


        return listaJugadores;
    }

}
