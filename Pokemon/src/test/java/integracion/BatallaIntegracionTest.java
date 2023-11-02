package integracion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tp1.clases.errores.Error;
import tp1.clases.modelo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BatallaIntegracionTest {

    ArrayList<Jugador> jugadores;

    Batalla batalla;

    private ArrayList<Jugador> setUp(){
        Habilidad hab1 = new HabilidadAtaque("Arañazo", 4, Tipo.NORMAL, 122, "Araña con afiladas garras");
        Habilidad hab2 = new HabilidadEstadistica("Amnesia", 2, Tipo.PSIQUICO, "El usuario olvida sus preocupaciones y aumenta mucho sus defensas", Estadisticas.DEFENSA, false);
        Habilidad hab3 = new HabilidadEstado("Bostezo", 7, Tipo.NORMAL, "Gran bostezo que induce al sueño del enemigo", Estado.DORMIDO);
        Habilidad hab4 = new HabilidadClima("Dia de playa", 3, Tipo.FUEGO, "Hace salir el sol para disfrutar un buen bronceado", Clima.SOLEADO);

        Item item1 = new ItemEstado("Sacar estado");
        Item item2 = new ItemEstadistica("Batido proteico", Estadisticas.ATAQUE);
        Item item3 = new ItemRestauracionVida("Mega Pocion", 50);
        List<Item> listaItems1 = new ArrayList<Item>();
        listaItems1.add(item1);
        listaItems1.add(item2);
        listaItems1.add(item3);

        Pokemon poke1 = new Pokemon("Naclastack", 7, Tipo.ROCA, List.of(hab1, hab2), 60, 93.0, 102.0, 84.0);
        Pokemon poke2 = new Pokemon("Rapidash", 6, Tipo.FUEGO, List.of(hab3, hab4), 70, 67.0, 94.0, 50.0);
        ArrayList<Pokemon> listaPokes1 = new ArrayList<>(List.of(poke1, poke2));

        Jugador jugador1 = new Jugador("Pepito", listaPokes1, listaItems1);


        Habilidad hab5 = new HabilidadAtaque("Corte Furia", 4, Tipo.BICHO, 100, "Ataque con una guadaña");
        Habilidad hab6 = new HabilidadEstadistica("Danza Pluma", 5, Tipo.VOLADOR, "Envuelve al objetivo cpn un manto de plumas para reducir mucho su ataque", Estadisticas.ATAQUE, true);
        Habilidad hab7 = new HabilidadEstado("Gas venenoso", 2, Tipo.VENENO,"Lanza una nube de gas toxico a los rivales", Estado.ENVENENADO);
        Habilidad hab8 = new HabilidadClima("Dios del viento", 5, Tipo.AGUA, "Sopla y sopla hasta que se genera un huracan", Clima.HURACAN);

        Item item4 = new ItemEstadistica("Jugo de apio", Estadisticas.DEFENSA);
        Item item5 = new ItemRestauracionVida("Molesta Alumnos", 0);
        Item item6 = new ItemRevivir("Revivir", 0);
        List<Item> listaItems2 = new ArrayList<Item>();
        listaItems2.add(item4);
        listaItems2.add(item5);
        listaItems2.add(item6);

        Pokemon poke3 = new Pokemon("Fraxure", 6, Tipo.DRAGON, List.of(hab5, hab6), 59, 75.0, 99.0, 52.0);
        Pokemon poke4 = new Pokemon("Gastrodon",5 , Tipo.AGUA, List.of(hab7, hab8), 68, 64.0, 113.0, 76.0);
        ArrayList<Pokemon> listaPokes2 = new ArrayList<>(List.of(poke3, poke4));

        Jugador jugador2 = new Jugador("Fulanito", listaPokes2, listaItems2);

        return new ArrayList<>(List.of(jugador1, jugador2));
    }

    @Test
    public void juegoCortoTest() {
        jugadores = setUp();
        batalla = new Batalla(jugadores);

        // El jugador inicial es Pepito (1), ya que su pokemon actual tiene mayor velocidad que el de Fulanito
        Assertions.assertEquals(jugadores.get(0), batalla.getJugadorActual());

        // Pruebo que el jugador1 actual use una habilidad (Arañazo-->habilidad ataque)
        batalla.usarHabilidad(0, batalla.getJugadorSiguiente());
        // Aun no hay ganador, pero el pokemon del jugador rival fue atacado.
        Assertions.assertEquals(Optional.empty(), batalla.obtenerGanador());
        Pokemon pokeRival = batalla.getJugadorSiguiente().getPokemonActual();
        Assertions.assertTrue(pokeRival.getVida() < pokeRival.getVidaMax());

        batalla.cambiarTurno();

        // Pruebo que el jugador2 actual use un item (Jugo de apio-->item estadistica)
        Pokemon pokeAct = batalla.getJugadorActual().getPokemonActual();
        double defensaPokeAct = pokeAct.getDefensa();
        batalla.usarItem(0, 0);
        // Verifico que la defensa haya mejorado
        Assertions.assertTrue(pokeAct.getDefensa() > defensaPokeAct);

        batalla.cambiarTurno();

        // Pruebo que el jugador1 use una habildiad de estadistica que mejora la defensa propia (Amnesia-->habilidad estadistica)
        pokeAct = batalla.getJugadorActual().getPokemonActual();
        defensaPokeAct = pokeAct.getDefensa();
        batalla.usarHabilidad(1, batalla.getJugadorSiguiente());
        // Verifico que la defensa haya mejorado
        Assertions.assertTrue(pokeAct.getDefensa() > defensaPokeAct);

        batalla.cambiarTurno();

        // Pruebo que el jugador2 cambie su pokemon (Fraxure-->Gastrodon)
        pokeAct = batalla.getJugadorActual().getPokemonActual();
        batalla.cambiarPokemon(1);
        // Verifico que se haya cambiado correctamente
        Assertions.assertNotEquals(pokeAct, batalla.getJugadorActual().getPokemonActual());
        Assertions.assertEquals(batalla.getJugadorActual().getPokemonActual(), batalla.getJugadorActual().getListaPokemones().get(1));

        batalla.cambiarTurno();

        // Pruebo que el jugador1 cambie su pokemon (Naclastack-->Rapidash)
        pokeAct = batalla.getJugadorActual().getPokemonActual();
        batalla.cambiarPokemon(1);
        // Verifico que se haya cambiado correctamente
        Assertions.assertNotEquals(pokeAct, batalla.getJugadorActual().getPokemonActual());
        Assertions.assertEquals(batalla.getJugadorActual().getPokemonActual(), batalla.getJugadorActual().getListaPokemones().get(1));

        batalla.cambiarTurno();

        // Pruebo que el jugador2 cambie el clima
        int vidaPokeJug1 = batalla.getJugadorSiguiente().getPokemonActual().getVida();
        int vidaPokeJug2 = batalla.getJugadorActual().getPokemonActual().getVida();
        batalla.usarHabilidad(1, batalla.getJugadorSiguiente());
        Assertions.assertEquals(Clima.HURACAN, batalla.getClima());

        batalla.cambiarTurno();

        // Verifica que el clima afecte la vida de ambos pokemones (turnos con clima = 1)
        double modificadorPokeJug1 = batalla.getJugadorActual().getPokemonActual().getVidaMax() * 0.03;
        double modificadorPokeJug2 = batalla.getJugadorSiguiente().getPokemonActual().getVidaMax() * 0.03;
        Assertions.assertEquals((int) (vidaPokeJug1 - modificadorPokeJug1), batalla.getJugadorActual().getPokemonActual().getVida());
        Assertions.assertEquals((int) (vidaPokeJug2 - modificadorPokeJug2), batalla.getJugadorSiguiente().getPokemonActual().getVida());

        // Pruebo que el jugador1 le cambie el estado al jugador2
        batalla.usarHabilidad(0, batalla.getJugadorSiguiente());
        Assertions.assertTrue(batalla.getJugadorSiguiente().getPokemonActual().getEstados().contains(Estado.DORMIDO));

        vidaPokeJug1 = batalla.getJugadorActual().getPokemonActual().getVida();
        vidaPokeJug2 = batalla.getJugadorSiguiente().getPokemonActual().getVida();
        batalla.cambiarTurno();

        // Verifica que el clima afecte la vida de ambos pokemones (turnos con clima = 2, posibles turnos jug2 dormido= 1)
        modificadorPokeJug1 = batalla.getJugadorSiguiente().getPokemonActual().getVidaMax() * 0.03;
        modificadorPokeJug2 = batalla.getJugadorActual().getPokemonActual().getVidaMax() * 0.03;
        Assertions.assertEquals((int) (vidaPokeJug1 - modificadorPokeJug1), batalla.getJugadorSiguiente().getPokemonActual().getVida());
        Assertions.assertEquals((int) (vidaPokeJug2 - modificadorPokeJug2), batalla.getJugadorActual().getPokemonActual().getVida());

        // Pruebo que el jugador2 no pueda usar un item revivir ya que esta muerto
        Optional<Error> err = batalla.usarItem(2, 0);
        Assertions.assertNotEquals(Optional.empty(), err);

        vidaPokeJug1 = batalla.getJugadorSiguiente().getPokemonActual().getVida();
        vidaPokeJug2 = batalla.getJugadorActual().getPokemonActual().getVida();
        batalla.cambiarTurno();

        // Verifica que el clima afecte la vida de ambos pokemones (turnos con clima = 3, posibles turnos jug2 dormido= 2)
        modificadorPokeJug1 = batalla.getJugadorActual().getPokemonActual().getVidaMax() * 0.03;
        modificadorPokeJug2 = batalla.getJugadorSiguiente().getPokemonActual().getVidaMax() * 0.03;
        Assertions.assertEquals((int) (vidaPokeJug1 - modificadorPokeJug1), batalla.getJugadorActual().getPokemonActual().getVida());
        Assertions.assertEquals((int) (vidaPokeJug2 - modificadorPokeJug2), batalla.getJugadorSiguiente().getPokemonActual().getVida());

        // Pruebo que el jugador1 cambie de pokemon (Rapidash-->Naclastack)
        pokeAct = batalla.getJugadorActual().getPokemonActual();
        batalla.cambiarPokemon(0);
        // Verifico que se haya cambiado correctamente
        Assertions.assertNotEquals(pokeAct, batalla.getJugadorActual().getPokemonActual());
        Assertions.assertEquals(batalla.getJugadorActual().getPokemonActual(), batalla.getJugadorActual().getListaPokemones().get(0));

        batalla.cambiarTurno();


        System.out.println(batalla.getJugadorActual().getPokemonActual().getVida());
        System.out.println(batalla.getJugadorSiguiente().getPokemonActual().getVida());
    }

}
