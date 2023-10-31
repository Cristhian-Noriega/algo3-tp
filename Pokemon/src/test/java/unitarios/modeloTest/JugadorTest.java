package unitarios.modeloTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tp1.clases.errores.Error;
import tp1.clases.modelo.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JugadorTest {

    Pokemon pokeVivo = new Pokemon("pokeVivo", 18, Tipo.HIELO, List.of(), 98, 139.0, 180.0, 130.0);
    Pokemon pokeMuerto = new Pokemon("pokeMuerto", 20, Tipo.BICHO, List.of(), 0, 193.0, 184.0, 130.0);
    Pokemon otroPokeVivo = new Pokemon("El otroPokeVivo", 18, Tipo.HIELO, List.of(), 98, 139.0, 180.0, 130.0);

    Item defensa = new ItemEstadistica("mejoro defensa", Estadisticas.DEFENSA);
    ArrayList<Pokemon> pokemones = new ArrayList<>(Arrays.asList(pokeVivo, pokeMuerto, otroPokeVivo));
    Jugador jugador = new Jugador("puppet", pokemones, List.of(defensa));

    @Test
    void seleccionarPokemonFueraDeRango(){
        Optional<Error> errNegativo = jugador.seleccionarPokemon(-1);
        Assertions.assertTrue(errNegativo.isPresent());

        Optional<Error> errPasado = jugador.seleccionarPokemon(3);
        Assertions.assertTrue(errPasado.isPresent());
    }

    @Test
    void seleccionarPokemonDisponible(){
        Optional<Error> err = jugador.seleccionarPokemon(2);
        Assertions.assertTrue(err.isEmpty());

    }

    @Test
    void seleccionarPokemonYaEnUso(){
        Optional<Error> err = jugador.seleccionarPokemon(0);
        Assertions.assertTrue(err.isPresent());
    }

    @Test
    void seleccionarPokemonNoDisponible(){
        Optional<Error> err = jugador.seleccionarPokemon(1);
        Assertions.assertTrue(err.isPresent());
    }

    @Test
    void usarItemValido(){
        double defensaInicialPokemon = jugador.getPokemonActual().getDefensa();
        int itemDefensa = 0; //posicion en la lista
        int pokemonActual = 0; //pokemon inicial es el primero en la lista y esta con vida

        Optional<Error> err = jugador.usarItem(itemDefensa, pokemonActual);
        Assertions.assertTrue(err.isEmpty());

        //me aseguro que este haciendo algo
        Assertions.assertNotEquals(jugador.getPokemonActual().getDefensa(), defensaInicialPokemon);

    }

    @Test
    void usarItemFueraDeRango(){
        int pokemonActual = 0; //pokemon inicial es el primero en la lista y esta con vida

        Optional<Error> error = jugador.usarItem(-1, pokemonActual);
        Assertions.assertTrue(error.isPresent());

        Optional<Error> err = jugador.usarItem(1, pokemonActual);
        Assertions.assertTrue(err.isPresent());
    }

    @Test
    void usarItemNoDisponible(){
        int itemDefensa = 0; //posicion en la lista
        int pokemonActual = 0; //pokemon inicial es el primero en la lista y esta con vida
        jugador.usarItem(itemDefensa, pokemonActual);

        Optional<Error> err = jugador.usarItem(itemDefensa, pokemonActual);
        Assertions.assertTrue(err.isPresent());
    }

    @Test
    void tienePokemonesConVidaTrue(){
        Assertions.assertTrue(jugador.tienePokemonesConVida());
    }

    @Test
    void tienePokemonesConVidaFalse(){
        Pokemon pokeVivo = jugador.getPokemonActual();
        pokeVivo.modificarVida(pokeVivo.getVida()*(-1));
        jugador.seleccionarPokemon(2);
        Pokemon otroPokeVivo = jugador.getPokemonActual();
        otroPokeVivo.modificarVida(otroPokeVivo.getVida()*(-1));

        Assertions.assertFalse(jugador.tienePokemonesConVida());
    }

}
