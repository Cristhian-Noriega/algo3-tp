package tp1.clases;

import java.util.List;

public class Proveedor {
    private final List<Pokemon> listaJugador1;
    private final List<Pokemon> listaJugador2;

    // CREACION HABILIDADES
    Habilidad hab1 = new HabilidadAtaque("Arañazo", 35, TipoPokemon.Tipo.NORMAL, 40, "Araña con afiladas garras");
    Habilidad hab2 = new HabilidadAtaque("Cascada", 15, TipoPokemon.Tipo.AGUA, 80, "Embiste con gran impulso que puede hacer retroceder");
    Habilidad hab3 = new HabilidadAtaque("Demolicion", 15, TipoPokemon.Tipo.LUCHA, 75, "Potente ataque que tambien es capaz de destruir barreras");
    Habilidad hab4 = new HabilidadAtaque("Ala Bis", 10, TipoPokemon.Tipo.VOLADOR, 40, "Ataca al adversario golpeandolo dos veces con las alas");
    Habilidad hab5 = new HabilidadAtaque("Antiaereo", 15, TipoPokemon.Tipo.ROCA, 50, "Ataca lanzando una piedra o proyctil");
    Habilidad hab6 = new HabilidadAtaque("Bucle Arena", 15, TipoPokemon.Tipo.TIERRA, 35, "Enreda al objetivo en un remolino de arena");
    Habilidad hab7 = new HabilidadAtaque("Garra Umbria", 15, TipoPokemon.Tipo.FANTASMA, 70, "Ataca cpm una garra afilada hecha de sombras");
    Habilidad hab8 = new HabilidadAtaque("Corte Furia", 20, TipoPokemon.Tipo.BICHO, 40, "Ataque con una guadaña");
    Habilidad hab9 = new HabilidadAtaque("Cabezazo Zen", 15, TipoPokemon.Tipo.PSIQUICO, 80, "COncentra su energia psiquica en la cabeza para golpear");


    Habilidad hab10 = new HabilidadEstadistica("Amnesia", 20, TipoPokemon.Tipo.PSIQUICO, "El usuario olvida sus preocupaciones y aumenta mucho sus defensas", Estadisticas.DEFENSA, false);
    Habilidad hab11 = new HabilidadEstadistica("Danza Pluma", 15, TipoPokemon.Tipo.VOLADOR, "Envuelve al objetivo cpn un manto de plumas para reducir mucho su ataque", Estadisticas.ATAQUE, true);
    Habilidad hab12 = new HabilidadEstadistica("Campo de hierbas", 10, TipoPokemon.Tipo.PLANTA, "Convierte el terreno en un campo de hierba recuperando la vida de los Pokemon en batalla", Estadisticas.VIDA,false);
    Habilidad hab13 = new HabilidadEstadistica("Acua aro", 20, TipoPokemon.Tipo.AGUA, "Un manto de agua cubre al usuario", Estadisticas.VIDA, false);
    Habilidad hab14 = new HabilidadEstadistica("Rabia", 5, TipoPokemon.Tipo.FANTASMA, "Debilita al objetivo", Estadisticas.DEFENSA, true);
    Habilidad hab15 = new HabilidadEstadistica("Fertilizantes", 10, TipoPokemon.Tipo.TIERRA, "Labra la tierra haciendo que sea mas facil cultivarla logrando que aumente mucho el ataque", Estadisticas.ATAQUE, false);
    Habilidad hab16 = new HabilidadEstadistica("Cañon armadura", 5, TipoPokemon.Tipo.FUEGO, "Baja la defensa del rival", Estadisticas.DEFENSA, true);


    Habilidad hab17 = new HabilidadEstado("Bostezo", 10, TipoPokemon.Tipo.NORMAL, "Gran bostezo que induce al sueño del enemigo", Estado.DORMIDO);
    Habilidad hab18 = new HabilidadEstado("Gas venenoso", 40, TipoPokemon.Tipo.VENENO,"Lanza una nube de gas toxico a los rivales", Estado.ENVENENADO);
    Habilidad hab19 = new HabilidadEstado("Somnifero", 15, TipoPokemon.Tipo.PLANTA, "Esparce polvo que duerme al objetivo", Estado.DORMIDO);
    Habilidad hab20 = new HabilidadEstado("Hilo venenoso", 20, TipoPokemon.Tipo.VENENO, "Ataca al enemigo con hilillos venenosos", Estado.ENVENENADO);
    Habilidad hab21 = new HabilidadEstado("Onda Trueno", 20, TipoPokemon.Tipo.ELECTRICO, "Una ligera descarga que paraliza al enemigo", Estado.PARALIZADO);
    Habilidad hab22 = new HabilidadEstado("Chispa", 20, TipoPokemon.Tipo.ELECTRICO, "Ataque electrico que puede paralizar", Estado.PARALIZADO);


    // CREACION POKEMONES
    Pokemon poke1 = new Pokemon("Rapidash", 40, TipoPokemon.Tipo.FUEGO, List.of(hab1, hab2, hab10, hab18), 240, 193, 184, 130);
    Pokemon poke2 = new Pokemon("Toxtricity", 30, TipoPokemon.Tipo.ELECTRICO, List.of(hab3, hab4, hab11, hab17), 260, 139, 180, 130);
    Pokemon poke3 = new Pokemon("Golurk", 43, TipoPokemon.Tipo.TIERRA, List.of(hab5, hab6, hab13, hab19), 288, 103, 227, 148);
    Pokemon poke4 = new Pokemon("Naclastack", 24, TipoPokemon.Tipo.ROCA, List.of(hab7, hab8, hab14, hab20), 230, 67, 112, 184);
    Pokemon poke5 = new Pokemon("Appletun", 23, TipoPokemon.Tipo.DRAGON, List.of(hab9, hab1, hab15, hab21), 330, 58, 157, 148);
    Pokemon poke6 = new Pokemon("Cloyster", 32, TipoPokemon.Tipo.HIELO, List.of(hab3, hab6, hab16, hab22), 210, 130, 175, 328);
    Pokemon poke7 = new Pokemon("Charmander", 16, TipoPokemon.Tipo.PSIQUICO, List.of(hab9, hab10, hab11, hab21), 188, 121, 98, 81);
    Pokemon poke8 = new Pokemon("Thundurus", 50, TipoPokemon.Tipo.LUCHA, List.of(hab3, hab1, hab12, hab17), 268, 204, 211, 130);
    Pokemon poke9 = new Pokemon("Sandaconda", 36, TipoPokemon.Tipo.VENENO, List.of(hab5, hab8, hab13, hab18), 254, 132, 197, 229);
    Pokemon poke10 = new Pokemon("Kleavor", 44, TipoPokemon.Tipo.NORMAL, List.of(hab5, hab1, hab14, hab19), 250, 157, 247, 175);
    Pokemon poke11 = new Pokemon("Fraxure", 38, TipoPokemon.Tipo.BICHO, List.of(hab8, hab3, hab15, hab21), 242, 125, 215, 130);
    Pokemon poke12 = new Pokemon("Gastrodon",30 , TipoPokemon.Tipo.FANTASMA, List.of(hab2, hab7, hab16, hab22), 332, 74, 153, 126);


    public Proveedor() {
        this.listaJugador1 = List.of(poke1, poke2, poke3, poke4, poke5, poke6);
        this.listaJugador2 = List.of(poke7, poke8, poke9, poke10, poke11, poke12);
    }

    public List<Pokemon> getListaJugador1() {
        return listaJugador1;
    }

    public List<Pokemon> getListaJugador2() {
        return listaJugador2;
    }
}
