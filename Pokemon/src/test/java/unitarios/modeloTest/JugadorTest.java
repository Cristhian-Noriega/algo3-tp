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

    Pokemon pokeVivo = new Pokemon("pokeVivo", 18, Tipo.HIELO, List.of(), 98, 139.0, 180.0, 130.0, 1);
    Pokemon pokeMuerto = new Pokemon("pokeMuerto", 20, Tipo.BICHO, List.of(), 0, 193.0, 184.0, 130.0, 2);
    Pokemon otroPokeVivo = new Pokemon("El otroPokeVivo", 18, Tipo.HIELO, List.of(), 98, 139.0, 180.0, 130.0, 3);
    Item defensa = new ItemEstadistica("mejoro defensa", Estadisticas.DEFENSA, "info",1);
    ArrayList<Pokemon> pokemones = new ArrayList<>(Arrays.asList(pokeVivo, pokeMuerto, otroPokeVivo));
    Jugador jugador = new Jugador("puppet", pokemones, List.of(defensa));


    @Test
    void seleccionarPokemonDisponible(){
        Optional<Error> err = jugador.seleccionarPokemon(otroPokeVivo);
        Assertions.assertTrue(err.isEmpty());

    }

    @Test
    void seleccionarPokemonYaEnUso(){
        Optional<Error> err = jugador.seleccionarPokemon(pokeVivo);
        Assertions.assertTrue(err.isPresent());
    }

    @Test
    void seleccionarPokemonNoDisponible(){
        Optional<Error> err = jugador.seleccionarPokemon(pokeMuerto);
        Assertions.assertTrue(err.isPresent());
    }

    @Test
    void usarItemValido(){
        double defensaInicialPokemon = jugador.getPokemonActual().getDefensa();

        Optional<Error> err = jugador.usarItem(defensa, pokeVivo);
        Assertions.assertTrue(err.isEmpty());

        //me aseguro que este haciendo algo
        Assertions.assertNotEquals(jugador.getPokemonActual().getDefensa(), defensaInicialPokemon);

    }

    @Test
    void usarItemNoDisponible(){
        //uso el unico itemDefensa que tengo
        jugador.usarItem(defensa, pokeVivo);

        Optional<Error> err = jugador.usarItem(defensa, pokeVivo);
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
        jugador.seleccionarPokemon(otroPokeVivo);
        Pokemon otroPokeVivo = jugador.getPokemonActual();
        otroPokeVivo.modificarVida(otroPokeVivo.getVida()*(-1));

        Assertions.assertFalse(jugador.tienePokemonesConVida());
    }

}
