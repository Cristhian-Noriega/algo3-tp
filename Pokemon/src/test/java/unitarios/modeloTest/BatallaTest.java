package unitarios.modeloTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Jugador;
import tp1.clases.modelo.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class BatallaTest {

    private Batalla batalla;
    private Jugador jugadorA;
    private Jugador jugadorB;

    @BeforeEach
    public void setUp() {
        jugadorA = mock(Jugador.class);
        jugadorB = mock(Jugador.class);
        ArrayList<Jugador> jugadores = new ArrayList<>(Arrays.asList(jugadorA, jugadorB));
        batalla = new Batalla(jugadores);
    }

    @DisplayName("Verifica que se obtenga el jugador actual a traves del administrador de turnos")
    @Test
    public void obtieneJugadores(){
        // TODO: ver como solucionar
        // batalla.getJugadorActual();
        // no puedo llamar al administrador d turnos xq es privado y agregarle un getter solo apra esto no me parece
        //verify(jugadorA, batalla.administradorTurnos.getJugadorActual());
    }

    @DisplayName("Verifica que se se llame al metodo getListaPokemones cuando se quiere obtener los pokemones del jugador actual")
    @Test
    public void obtienePokemonesJugadorActual(){
        batalla.getPokemonesJugadorActual();

        verify(jugadorA).getListaPokemones();
    }
    @DisplayName("Verifica que se se llame al metodo getHabilidadesPokemonActual cuando se quieren obtener las habilidades pokemones actual")
    @Test
    public void obtieneHabilidadesPokemonActual(){
        batalla.getHabilidadesPokemonActual();

        verify(jugadorA).getHabilidadesPokemonActual();
    }

    @DisplayName("Verifica que se se llame al metodo getListaItems del jugador actual cuando se quieren obtener los items de este")
    @Test
    public void obtieneItemsJugadoorActual(){
        batalla.getItemsJugadorActual();

        verify(jugadorA).getListaItems();
    }

    @DisplayName("Se verifica que al usar habilidad se llame al usar del pokemon actual")
    @Test
    public void usarHabilidadEnBatalla(){
        Pokemon pokePropio = mock(Pokemon.class);
        Pokemon pokeRival = mock(Pokemon.class);
        when(batalla.getJugadorActual().getPokemonActual()).thenReturn(pokePropio);
        when(batalla.getJugadorActual().getPokemonActual()).thenReturn(pokeRival);

        batalla.usarHabilidad(0);

        verify(batalla.getJugadorActual().getPokemonActual()).usarHabilidad(0, batalla.getJugadorActual().getPokemonActual());
    }

    @DisplayName("Se verifica que al usar item se llame al usar del jugador actual")
    @Test
    public void usarItemEnBatalla(){
        batalla.usarItem(0, 0);

        verify(batalla.getJugadorActual()).usarItem(0,0);
    }

    @DisplayName("Se verifica que al cambiar pokemon se llame al metodo seleccionarPokemon del jugador actual")
    @Test
    public void cambiarPokemonEnBatalla(){
        batalla.cambiarPokemon(0);

        verify(batalla.getJugadorActual()).seleccionarPokemon(0);
    }

    @DisplayName("Al verificar si el pokemon en campo de juego esta muerto debe devolver false")
    @Test
    public void deberiaDevolverFalsePokemonSigueVivo(){
        when(batalla.getJugadorActual().getPokemonActual()).thenReturn(mock(Pokemon.class));
        when(batalla.getJugadorActual().getPokemonActual().estaMuerto()).thenReturn(false);

        Assertions.assertFalse(batalla.estaMuertoPokemonActual());
    }

    @DisplayName("Al verificar si el pokemon en campo de juego esta muerto debe devolver true")
    @Test
    public void deberiaDevolverTruePokemonEstaMuerto(){
        // medio q este y el anterior estoy poniendo el when.Then a lo q literalmente devuelve, es obvio q va a
        // devolver lo esperado, esta bien igual?
        when(batalla.getJugadorActual().getPokemonActual()).thenReturn(mock(Pokemon.class));
        when(batalla.getJugadorActual().getPokemonActual().estaMuerto()).thenReturn(true);

        Assertions.assertTrue(batalla.estaMuertoPokemonActual());
    }

    @DisplayName("Rinde al jugador A y verifica que se elimine de la lista de jugadores")
    @Test
    public void jugadorAseRinde(){
        batalla.rendir(jugadorA);

        ArrayList<Jugador> jugadoresActual = batalla.getJugadores();

        Assertions.assertEquals(1, jugadoresActual.size());
        Assertions.assertEquals(jugadorB, jugadoresActual.get(0));
    }

    @DisplayName("Verifica que el ganador sea el correcto, es decir jugadorB")
    @Test
    public void jugadorBesElGanador() {
        // TODO: ver como solucionar
        // no se como mockear t.odo lo que hace obtener ganador
        // idem con get datos jugadores
        // creo q es xq itera listas y crea nuevas listas ahi adentro q no tengo manera d acceder o mockear
    }

}
