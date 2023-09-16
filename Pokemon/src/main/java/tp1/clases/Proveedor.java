package tp1.clases;

import java.util.List;

public class Proveedor {
    private final List<Pokemon> listaJugador1;
    private final List<Pokemon> listaJugador2;

    Pokemon poke1 = new Pokemon("Rapidash", 40, "Fuego", List.of("Run Away", "FLash Fire", "Flame Body", "Chlorophyll"), 240, 193, 184, 130);
    Pokemon poke2 = new Pokemon("Toxtricity", 30, "Electrico", List.of("Punk Rock", "Plus", "Technician", "Insomnio"), 260, 139, 180, 130);
    Pokemon poke3 = new Pokemon("Golurk", 43, "Tierra", List.of("Iron Fist", "Klutz", "No Guard", "White Smoke"), 288, 103, 227, 148);
    Pokemon poke4 = new Pokemon("Naclastack", 24, "Roca", List.of("Purifying Salt", "Sturdy", "Clear Body", "Corrosion"), 230, 67, 112, 184);
    Pokemon poke5 = new Pokemon("Appletun", 23, "Dragon", List.of("Ripen", "Gluttony", "Thick Fat", "Overgrow"), 330, 58, 157, 148);
    Pokemon poke6 = new Pokemon("Cloyster", 32, "Agua", List.of("Shell Armor", "Skill Link", "Overcoat", "Swarm"), 210, 130, 175, 328);

    Pokemon poke7 = new Pokemon("Charmander", 16, "Fuego", List.of("Blaze", "Solar Power", "Sniper", "Rivalry"), 188, 121, 98, 81);
    Pokemon poke8 = new Pokemon("Thundurus", 50, "Electrico", List.of("Prankster", "Defiant", "Poison Point", "Shield Dust"), 268, 204, 211, 130);
    Pokemon poke9 = new Pokemon("Sandaconda", 36, "Tierra", List.of("Sand Spit", "Shed Skin", "Sand Veil", "Swift Swim"), 254, 132, 197, 229);
    Pokemon poke10 = new Pokemon("Kleavor", 44, "Roca", List.of("Swarm", "Sheer Force", "Sharpness", "Effect Spore"), 250, 157, 247, 175);
    Pokemon poke11 = new Pokemon("Fraxure", 38, "Dragon", List.of("Rivalry", "Mold Breaker", "Unnerve", "Drought"), 242, 125, 215, 130);
    Pokemon poke12 = new Pokemon("Gastrodon",30 , "Agua", List.of("Sticky Hold", "Storm Drain", "Sand Force", "Magma Armour"), 332, 74, 153, 126);

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
