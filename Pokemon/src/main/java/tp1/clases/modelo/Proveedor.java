package tp1.clases.modelo;

import java.util.ArrayList;
import java.util.List;

public class Proveedor {
    private final List<ArrayList<Pokemon>> listasPokemones;
  
    private final List<List<Item>> listasItems;


        Habilidad hab1 = new HabilidadAtaque("Arañazo", 2, Tipo.NORMAL, 100, "Araña con afiladas garras");

        Habilidad hab2 = new HabilidadAtaque("Cascada", 5, Tipo.AGUA, 80, "Embiste con gran impulso que puede hacer retroceder");

        Habilidad hab3 = new HabilidadAtaque("Demolicion", 15, Tipo.LUCHA, 130, "Potente ataque que tambien es capaz de destruir barreras");

        Habilidad hab4 = new HabilidadAtaque("Ala Bis", 10, Tipo.VOLADOR, 40, "Ataca al adversario golpeandolo dos veces con las alas");

        Habilidad hab5 = new HabilidadAtaque("Antiaereo", 15, Tipo.ROCA, 50, "Ataca lanzando una piedra o proyctil");

        Habilidad hab6 = new HabilidadAtaque("Bucle Arena", 15, Tipo.TIERRA, 200, "Enrreda al objetivo en un remolino de arena");

        Habilidad hab7 = new HabilidadAtaque("Garra Umbria", 15, Tipo.FANTASMA, 200, "Ataca con una garra afilada hecha de sombras");

        Habilidad hab8 = new HabilidadAtaque("Corte Furia", 20, Tipo.BICHO, 150, "Ataca con una guadaña");

        Habilidad hab9 = new HabilidadAtaque("Cabezazo Zen", 15, Tipo.PSIQUICO, 180, "Concentra su energia psiquica en la cabeza para golpear");

        Habilidad hab10 = new HabilidadEstadistica("Amnesia", 20, Tipo.PSIQUICO, "Olvida sus preocupaciones y aumenta mucho sus defensas", Estadisticas.DEFENSA, false);

        Habilidad hab11 = new HabilidadEstadistica("Danza Pluma", 15, Tipo.VOLADOR, "Envuelve al objetivo cpn un manto de plumas para reducir mucho su ataque", Estadisticas.ATAQUE, true);

        Habilidad hab12 = new HabilidadEstadistica("Campo de hierbas", 10, Tipo.PLANTA, "Convierte el terreno en un campo de hierba recuperando la vida de los Pokemon en batalla", Estadisticas.VIDA,false);

        Habilidad hab13 = new HabilidadEstadistica("Acua aro", 20, Tipo.AGUA, "Lanza un manto de agua cubre al usuario", Estadisticas.ATAQUE, false);

        Habilidad hab14 = new HabilidadEstadistica("Rabia", 5, Tipo.FANTASMA, "Debilita al objetivo", Estadisticas.DEFENSA, true);

        Habilidad hab15 = new HabilidadEstadistica("Fertilizantes", 10, Tipo.TIERRA, "Labra la tierra haciendo que sea mas facil cultivarla logrando que aumente mucho el ataque", Estadisticas.ATAQUE, false);

        Habilidad hab16 = new HabilidadEstadistica("Cañon armadura", 5, Tipo.FUEGO, "Baja la defensa del rival", Estadisticas.DEFENSA, true);

        Habilidad hab17 = new HabilidadEstado("Bostezo", 10, Tipo.NORMAL, "Usa un gran bostezo que induce al sueño del enemigo", Estado.DORMIDO);

         Habilidad hab18 = new HabilidadEstado("Gas venenoso", 7, Tipo.VENENO,"Lanza una nube de gas toxico a los rivales", Estado.ENVENENADO);

        Habilidad hab19 = new HabilidadEstado("Somnifero", 15, Tipo.PLANTA, "Esparce polvo que duerme al objetivo", Estado.DORMIDO);

        Habilidad hab20 = new HabilidadEstado("Hilo venenoso", 20, Tipo.VENENO, "Ataca al enemigo con hilillos venenosos", Estado.ENVENENADO);
        Habilidad hab22 = new HabilidadEstado("Supersonico", 20, Tipo.NORMAL, "Genera ondas sonoras extrañas que confuden al enemigo", Estado.CONFUNDIDO);

        Habilidad hab21 = new HabilidadEstado("Onda Trueno", 20, Tipo.ELECTRICO, "Suelta una ligera descarga que paraliza al enemigo", Estado.PARALIZADO);

        Habilidad hab23 = new HabilidadAtaque("Antiaereo", 15, Tipo.ROCA, 50, "Ataca lanzando una piedra o proyctil");

        Habilidad hab24 = new HabilidadAtaque("Antiaereo", 15, Tipo.ROCA, 50, "Ataca lanzando una piedra o proyctil");

        Habilidad hab25 = new HabilidadAtaque("Arañazo", 2, Tipo.NORMAL, 100, "Araña con afiladas garras");

        Habilidad hab26 = new HabilidadAtaque("Arañazo", 2, Tipo.NORMAL, 100, "Araña con afiladas garras");

        Habilidad hab27 = new HabilidadAtaque("Arañazo", 2, Tipo.NORMAL, 100, "Araña con afiladas garras");

        Habilidad hab28 = new HabilidadAtaque("Cascada", 5, Tipo.AGUA, 80, "Embiste con gran impulso que puede hacer retroceder");

        Habilidad hab29 = new HabilidadAtaque("Demolicion", 15, Tipo.LUCHA, 130, "Lanza un potente ataque que tambien es capaz de destruir barreras");

        Habilidad hab30 = new HabilidadAtaque("Demolicion", 15, Tipo.LUCHA, 130, "Lanza un potente ataque que tambien es capaz de destruir barreras");

        Habilidad hab31 = new HabilidadAtaque("Demolicion", 15, Tipo.LUCHA, 130, "Lanza un potente ataque que tambien es capaz de destruir barreras");

        Habilidad hab32 = new HabilidadAtaque("Bucle Arena", 15, Tipo.TIERRA, 200, "Enrreda al objetivo en un remolino de arena");

        Habilidad hab33 = new HabilidadAtaque("Garra Umbria", 15, Tipo.FANTASMA, 200, "Ataca con una garra afilada hecha de sombras");

        Habilidad hab34 = new HabilidadAtaque("Corte Furia", 20, Tipo.BICHO, 150, "Ataca con una guadaña");

        Habilidad hab35 = new HabilidadAtaque("Corte Furia", 20, Tipo.BICHO, 150, "Ataca con una guadaña");

        Habilidad hab36 = new HabilidadAtaque("Cabezazo Zen", 15, Tipo.PSIQUICO, 180, "Concentra su energia psiquica en la cabeza para golpear");

        Habilidad hab37 = new HabilidadEstadistica("Amnesia", 20, Tipo.PSIQUICO, "Olvida sus preocupaciones y aumenta mucho sus defensas", Estadisticas.DEFENSA, false);

        Habilidad hab38 = new HabilidadEstadistica("Danza Pluma", 15, Tipo.VOLADOR, "Envuelve al objetivo cpn un manto de plumas para reducir mucho su ataque", Estadisticas.ATAQUE, true);

        Habilidad hab39 = new HabilidadEstadistica("Acua aro", 20, Tipo.AGUA, "Lanza un manto de agua cubre al usuario", Estadisticas.ATAQUE, false);

        Habilidad hab40 = new HabilidadEstadistica("Rabia", 5, Tipo.FANTASMA, "Debilita al objetivo", Estadisticas.DEFENSA, true);

        Habilidad hab41 = new HabilidadEstadistica("Fertilizantes", 10, Tipo.TIERRA, "Labra la tierra haciendo que sea mas facil cultivarla logrando que aumente mucho el ataque", Estadisticas.ATAQUE, false);

        Habilidad hab42 = new HabilidadEstadistica("Cañon armadura", 5, Tipo.FUEGO, "Baja la defensa del rival", Estadisticas.DEFENSA, true);

        Habilidad hab43 = new HabilidadEstado("Bostezo", 10, Tipo.NORMAL, "Usa un gran bostezo que induce al sueño del enemigo", Estado.DORMIDO);
        Habilidad hab44 = new HabilidadEstado("Gas venenoso", 7, Tipo.VENENO,"Lanza una nube de gas toxico a los rivales", Estado.ENVENENADO);
        Habilidad hab45 = new HabilidadEstado("Somnifero", 15, Tipo.PLANTA, "Esparce polvo que duerme al objetivo", Estado.DORMIDO);
        Habilidad hab46 = new HabilidadEstado("Onda Trueno", 20, Tipo.ELECTRICO, "Lanza una ligera descarga que paraliza al enemigo", Estado.PARALIZADO);
        Habilidad hab47 = new HabilidadEstado("Danza caos", 20, Tipo.NORMAL, "Realiza una danza tambaleante que confunde al rival", Estado.CONFUNDIDO);
        Habilidad hab48 = new HabilidadEstado("Chispa", 20, Tipo.ELECTRICO, "Lanza un ataque electrico que puede paralizar", Estado.PARALIZADO);
        Habilidad hab49 = new HabilidadClima("Lluvia Mágica", 20, Tipo.AGUA, "Lanza una lluvia que convierte el campo en un cuento de hadas", Clima.LLUVIA);
        Habilidad hab50 = new HabilidadClima("Dia de playa", 20, Tipo.FUEGO, "Hace salir el sol para disfrutar un buen bronceado", Clima.SOLEADO);



        Pokemon poke1 = new Pokemon("Rapidash", 20, Tipo.FUEGO, List.of(hab25, hab2, hab10, hab18), 100, 193.0, 184.0, 130.0);

        Pokemon poke2 = new Pokemon("Ampharos", 18, Tipo.ELECTRICO, List.of(hab3, hab4, hab11, hab17), 98, 139.0, 180.0, 130.0);

        Pokemon poke3 = new Pokemon("Golurk", 23, Tipo.TIERRA, List.of(hab5, hab6, hab13, hab19), 122, 103.0, 227.0, 148.0);

        Pokemon poke4 = new Pokemon("Golem", 24, Tipo.ROCA, List.of(hab7, hab8, hab14, hab20), 111, 67.0, 112.0, 184.0);

        Pokemon poke5 = new Pokemon("Dragonair", 23, Tipo.DRAGON, List.of(hab9, hab1, hab15, hab21), 153, 58.0, 157.0, 148.0);
        Pokemon poke6 = new Pokemon("Cloyster", 17, Tipo.AGUA, List.of(hab29, hab32, hab49, hab22), 115, 130.0, 175.0, 328.0);

        Pokemon poke7 = new Pokemon("Charmander", 16, Tipo.FUEGO, List.of(hab36, hab37, hab50, hab46), 80, 121.0, 98.0, 81.0);

        Pokemon poke8 = new Pokemon("Thundurus", 25, Tipo.ELECTRICO, List.of(hab30, hab26, hab12, hab43), 132, 204.0, 211.0, 130.0);

        Pokemon poke9 = new Pokemon("Cubone", 18, Tipo.TIERRA, List.of(hab23, hab34, hab39, hab44), 126, 132.0, 197.0, 229.0);

        Pokemon poke10 = new Pokemon("Sudowoodo", 12, Tipo.ROCA, List.of(hab24, hab27, hab40, hab45), 130, 157.0, 247.0, 175.0);

        Pokemon poke11 = new Pokemon("Fraxure", 16, Tipo.DRAGON, List.of(hab35, hab31, hab41, hab47), 117, 125.0, 215.0, 130.0);

        Pokemon poke12 = new Pokemon("Gastrodon",15 , Tipo.AGUA, List.of(hab28, hab33, hab42, hab48), 158, 74.0, 153.0, 126.0);



        Item item1 = new ItemEstado("Sacar estado");

        Item item2 = new ItemEstadistica("Jugo de apio", Estadisticas.DEFENSA);

        Item item3 = new ItemEstadistica("Batido proteico", Estadisticas.ATAQUE);

        Item item4 = new ItemEstadistica("Red Bull", Estadisticas.VELOCIDAD);


    Item item5 = new ItemRestauracionVida("Molesta Alumnos", 0);

    Item item6 = new ItemRestauracionVida("Mega Pocion", 50);

    Item item7 = new ItemRestauracionVida("Hiper Pocion", 100);

    Item item8 = new ItemRevivir("Revivir", 0);


    public Proveedor() {
        // Lista = (lista items/pokemones jugador 1, lista items/pokemones jugador 2)

        List<Pokemon> listaPokemones1 = List.of(poke1, poke2, poke3, poke4, poke5, poke6);
        List<Pokemon> listaPokemones2 = List.of(poke7, poke8, poke9, poke10, poke11, poke12);

        ArrayList<Pokemon> lista1 = new ArrayList<>();
        lista1.addAll(listaPokemones1);
        ArrayList<Pokemon> lista2 = new ArrayList<>();
        lista2.addAll(listaPokemones2);

        this.listasPokemones = List.of(lista1,lista2);
        this.listasItems = new ArrayList<List<Item>>();
        List<Item> listaItems1 = new ArrayList<Item>();
        listaItems1.add(item1);
        listaItems1.add(item2);
        listaItems1.add(item4);
        listaItems1.add(item6);
        listaItems1.add(item8);
        listaItems1.add(item6);
        List<Item> listaItems2 = new ArrayList<Item>();
        listaItems2.add(item1);
        listaItems2.add(item3);
        listaItems2.add(item5);
        listaItems2.add(item7);
        listaItems2.add(item3);
        listaItems2.add(item5);
        this.listasItems.add(listaItems1);
        this.listasItems.add(listaItems2);
    }

    public List<ArrayList<Pokemon>> getPokemones() {
        return this.listasPokemones;
    }

    public List<List<Item>> getItems(){
        return this.listasItems;
    }

}