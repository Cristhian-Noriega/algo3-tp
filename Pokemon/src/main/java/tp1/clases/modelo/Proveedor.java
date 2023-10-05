package tp1.clases.modelo;

import java.util.ArrayList;
import java.util.List;

public class Proveedor {
    private final List<List<Pokemon>> listasPokemones;
  
    private final List<List<Item>> listasItems;

    Habilidad hab1 = new HabilidadAtaque("Arañazo", 2, Tipo.NORMAL, 100, "Araña con afiladas garras");

    Habilidad hab2 = new HabilidadAtaque("Cascada", 5, Tipo.AGUA, 80, "Embiste con gran impulso que puede hacer retroceder");

    Habilidad hab3 = new HabilidadAtaque("Demolicion", 15, Tipo.LUCHA, 130, "Potente ataque que tambien es capaz de destruir barreras");

    Habilidad hab4 = new HabilidadAtaque("Ala Bis", 10, Tipo.VOLADOR, 40, "Ataca al adversario golpeandolo dos veces con las alas");

    Habilidad hab5 = new HabilidadAtaque("Antiaereo", 15, Tipo.ROCA, 50, "Ataca lanzando una piedra o proyctil");

    Habilidad hab6 = new HabilidadAtaque("Bucle Arena", 15, Tipo.TIERRA, 200, "Enreda al objetivo en un remolino de arena");

    Habilidad hab7 = new HabilidadAtaque("Garra Umbria", 15, Tipo.FANTASMA, 200, "Ataca cpm una garra afilada hecha de sombras");

    Habilidad hab8 = new HabilidadAtaque("Corte Furia", 20, Tipo.BICHO, 150, "Ataque con una guadaña");

    Habilidad hab9 = new HabilidadAtaque("Cabezazo Zen", 15, Tipo.PSIQUICO, 180, "COncentra su energia psiquica en la cabeza para golpear");

    Habilidad hab10 = new HabilidadEstadistica("Amnesia", 20, Tipo.PSIQUICO, "El usuario olvida sus preocupaciones y aumenta mucho sus defensas", Estadisticas.DEFENSA, false);

    Habilidad hab11 = new HabilidadEstadistica("Danza Pluma", 15, Tipo.VOLADOR, "Envuelve al objetivo cpn un manto de plumas para reducir mucho su ataque", Estadisticas.ATAQUE, true);

    Habilidad hab12 = new HabilidadEstadistica("Campo de hierbas", 10, Tipo.PLANTA, "Convierte el terreno en un campo de hierba recuperando la vida de los Pokemon en batalla", Estadisticas.VIDA,false);

    Habilidad hab13 = new HabilidadEstadistica("Acua aro", 20, Tipo.AGUA, "Un manto de agua cubre al usuario", Estadisticas.ATAQUE, false);

    Habilidad hab14 = new HabilidadEstadistica("Rabia", 5, Tipo.FANTASMA, "Debilita al objetivo", Estadisticas.DEFENSA, true);

    Habilidad hab15 = new HabilidadEstadistica("Fertilizantes", 10, Tipo.TIERRA, "Labra la tierra haciendo que sea mas facil cultivarla logrando que aumente mucho el ataque", Estadisticas.ATAQUE, false);

    Habilidad hab16 = new HabilidadEstadistica("Cañon armadura", 5, Tipo.FUEGO, "Baja la defensa del rival", Estadisticas.DEFENSA, true);

    Habilidad hab17 = new HabilidadEstado("Bostezo", 10, Tipo.NORMAL, "Gran bostezo que induce al sueño del enemigo", Estado.DORMIDO);

    Habilidad hab18 = new HabilidadEstado("Gas venenoso", 7, Tipo.VENENO,"Lanza una nube de gas toxico a los rivales", Estado.ENVENENADO);

    Habilidad hab19 = new HabilidadEstado("Somnifero", 15, Tipo.PLANTA, "Esparce polvo que duerme al objetivo", Estado.DORMIDO);

    Habilidad hab20 = new HabilidadEstado("Hilo venenoso", 20, Tipo.VENENO, "Ataca al enemigo con hilillos venenosos", Estado.ENVENENADO);

    Habilidad hab21 = new HabilidadEstado("Onda Trueno", 20, Tipo.ELECTRICO, "Una ligera descarga que paraliza al enemigo", Estado.PARALIZADO);

    Habilidad hab22 = new HabilidadEstado("Chispa", 20, Tipo.ELECTRICO, "Ataque electrico que puede paralizar", Estado.PARALIZADO);


    Pokemon poke1 = new Pokemon("Rapidash", 20, Tipo.FUEGO, List.of(hab1, hab2, hab10, hab18), 100, 193.0, 184.0, 130.0);

    Pokemon poke2 = new Pokemon("Toxtricity", 18, Tipo.ELECTRICO, List.of(hab3, hab4, hab11, hab17), 98, 139.0, 180.0, 130.0);

    Pokemon poke3 = new Pokemon("Golurk", 23, Tipo.TIERRA, List.of(hab5, hab6, hab13, hab19), 122, 103.0, 227.0, 148.0);

    Pokemon poke4 = new Pokemon("Naclastack", 24, Tipo.ROCA, List.of(hab7, hab8, hab14, hab20), 111, 67.0, 112.0, 184.0);

    Pokemon poke5 = new Pokemon("Appletun", 23, Tipo.DRAGON, List.of(hab9, hab1, hab15, hab21), 153, 58.0, 157.0, 148.0);

    Pokemon poke6 = new Pokemon("Cloyster", 17, Tipo.AGUA, List.of(hab3, hab6, hab16, hab22), 115, 130.0, 175.0, 328.0);

    Pokemon poke7 = new Pokemon("Charmander", 16, Tipo.FUEGO, List.of(hab9, hab10, hab11, hab21), 80, 121.0, 98.0, 81.0);

    Pokemon poke8 = new Pokemon("Thundurus", 25, Tipo.ELECTRICO, List.of(hab3, hab1, hab12, hab17), 132, 204.0, 211.0, 130.0);

    Pokemon poke9 = new Pokemon("Sandaconda", 18, Tipo.TIERRA, List.of(hab5, hab8, hab13, hab18), 126, 132.0, 197.0, 229.0);

    Pokemon poke10 = new Pokemon("Kleavor", 12, Tipo.ROCA, List.of(hab5, hab1, hab14, hab19), 130, 157.0, 247.0, 175.0);

    Pokemon poke11 = new Pokemon("Fraxure", 16, Tipo.DRAGON, List.of(hab8, hab3, hab15, hab21), 117, 125.0, 215.0, 130.0);

    Pokemon poke12 = new Pokemon("Gastrodon",15 , Tipo.AGUA, List.of(hab2, hab7, hab16, hab22), 158, 74.0, 153.0, 126.0);


    Item item1 = new ItemEstado("Sacar estado");

    Item item2 = new ItemEstadistica("Jugo de apio", Estadisticas.DEFENSA);

    Item item3 = new ItemEstadistica("Batido proteico", Estadisticas.ATAQUE);

    Item item4 = new ItemEstadistica("Red Bull", Estadisticas.VELOCIDAD);

    Item item5 = new ItemVida("Pocion", 20);

    Item item6 = new ItemVida("Mega Pocion", 50);

    Item item7 = new ItemVida("Hiper Pocion", 100);

    Item item8 = new ItemVida("Revivir", 0);

    public Proveedor() {
        // Lista = (lista items/pokemones jugador 1, lista items/pokemones jugador 2)
        this.listasPokemones = List.of(List.of(poke1, poke2, poke3, poke4, poke5, poke6), List.of(poke7, poke8, poke9, poke10, poke11, poke12));
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
        listaItems2.add(item1);
        listaItems2.add(item5);
        this.listasItems.add(listaItems1);
        this.listasItems.add(listaItems2);
    }

    public List<List<Pokemon>> getPokemones() {
        return this.listasPokemones;
    }

    public List<List<Item>> getItems(){
        return this.listasItems;
    }

}