package tp1.clases;

import java.util.*;
import java.util.stream.Collectors;

public class Jugador {

    private List<Pokemon> pokemones;
    private Pokemon pokemonActual;
    private List<String> items;
    private final String nombre;
    private Jugador oponente;

    public Jugador(String nombre){
        this.nombre = nombre;
    }

    // METODOS PRINCIPALES
    public void seleccionarPokemon(int pokeElegido){
        if (pokeElegido <= this.pokemones.size() && !this.pokemones.get(pokeElegido).estaMuerto()){
            this.pokemonActual = this.pokemones.get(pokeElegido);
        }else{
            //volver al main
        }
    }

    public void atacar(int habilidadElegida){
        // El metodo de pokemon deberia recibir un numero, que es lo que ingresa el usuario.
        // Como puse en seleccionar poke. Y dentro de ese metodo, se encarga de verificar que es vabildo y esta disp
        this.pokemonActual.atacar(habilidadElegida, oponente.pokemonActual);
    }

    public String rendirse(){
        return obtenerFrasesGanador() + oponente.nombre + "\n" + obtenerFrasesPerdedor() + this.nombre;
    }

    public String obtenerCampo(){
        // ESTA HORRIBLE YA SE
        String res = "CAMPO DE BATALLA \n";

        res = res.concat("Tu pokemon es: " + this.pokemonActual + "\n" + "Nivel: " +this.pokemonActual.getNivel() + ". Estado: " + this.pokemonActual.getEstado() + ". Vida: " + this.pokemonActual.getVida() + "\n");
        res = res.concat("El pokemon de tu oponente es: " + oponente.pokemonActual + "\n"+ "Nivel: " + oponente.pokemonActual.getNivel() + ". Estado: " + oponente.pokemonActual.getEstado() + ". Vida: " + oponente.pokemonActual.getVida() + "\n");

        return res;
    }

    public void aplicarItem(int itemElegido){
        if (itemElegido <= this.items.size() && this.pokemonActual.puedeUsarItem(this.items.get(itemElegido))){
            //aca no entiendo bien que tiene que hacer
        }
    }

    // METODOS AUXILIARES
    public String mostrarPokemones() {
        String res = "Tienes los siguientes pokemones disponibles: \n";

        for (int i = 0; i < this.pokemones.size(); i++) {
            Pokemon pokemonI = this.pokemones.get(i);
            res = res.concat((i + 1) + ". " + pokemonI + "\n");
        }

        return res;
    }

    public String mostrarItems() {
        String res = "Tienes los siguientes items disponibles: \n";

        Map<String, Integer> itemsPorCant = this.items.stream()
                .collect(Collectors.groupingBy(
                        elemento -> elemento,
                        Collectors.summingInt(e -> 1)
                ));

        for (int i = 0; i < this.items.size(); i++) {
            String item = this.items.get(i);
            int cantidad = itemsPorCant.getOrDefault(item, 0);
            res = res.concat((i + 1) + ". " + item + " (" + cantidad + ")\n");
        }

        return res;
    }

    //esto chau ?
    private String obtenerFrasesGanador() {
        String[] elementosG = {"¡Enhorabuena! El ganador es ", "WOW ¡Que partida! Felicitaciones ", "¡Bravo! El ganador de esta partida es ", "¡¡Que gran partida!! Felicitaciones ganador ", "Sigue así ¡Que genio! "};
        List<String> frasesGanador = new ArrayList<>(Arrays.asList(elementosG));
        Random random = new Random();
        int indiceAleatorioG = random.nextInt(frasesGanador.size());
        return frasesGanador.get(indiceAleatorioG);
    }
    private String obtenerFrasesPerdedor(){
        String[] elementosP = {"Más suerte la proxima ", "Eres mas fuerte de lo que crees, sigue practicando ", "Te hace falta mas práctica ", "La proxima es tu partida ¡a seguir!", "Arriba ese ánimo ", "Un tropezón no es caída "};
        List<String> frasesPerdedor = new ArrayList<>(Arrays.asList(elementosP));
        Random random = new Random();
        int indiceAleatorioP = random.nextInt(frasesPerdedor.size());
        return frasesPerdedor.get(indiceAleatorioP);
    }

}

