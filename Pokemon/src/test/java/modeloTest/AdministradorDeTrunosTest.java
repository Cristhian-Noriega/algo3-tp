package modeloTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tp1.clases.modelo.AdministradorDeTurnos;
import tp1.clases.modelo.Jugador;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdministradorDeTrunosTest {
    private AdministradorDeTurnos admn;
    private List<Jugador>  jugadores;


     @BeforeEach
     public void setUp(){
         Jugador jugadorA = mock(Jugador.class);
         Jugador jugadorB =  mock(Jugador.class);
         jugadores = List.of(jugadorA, jugadorB);
         this.admn = new AdministradorDeTurnos(jugadores);

         when(jugadores.get(0).getVelocidadPokemonActual()).thenReturn(10.0);
         when(jugadores.get(1).getVelocidadPokemonActual()).thenReturn(5.0);

         admn.determinarJugadorIncial();
     }

    @DisplayName("Verifica que jugador inicial es aquel con el pokemon mas veloz y turno inicial es el indice d dicho jugador")
    @Test
    public void determinaJugadorInicialAlMasVeloz(){
        Assertions.assertEquals(jugadores.get(0), this.admn.getJugadorActual());
        Assertions.assertEquals(0, this.admn.getTurno());
    }

    @DisplayName("Verifica que el siguiente jugador, luego de inicializar los turnos, sea el menos veloz")
    @Test
    public void jugadorSiguienteEsElMenosVelozTest(){
        Assertions.assertEquals(jugadores.get(1), admn.getJugadorSiguiente());
    }

    @DisplayName("Avanza el turno y verifica que el jugador actual sea el B, es decir el menos veloz, y turno actual el indice de dicho jugador")
    @Test
    public void avanzaTurnoYugadorActualEsBYTurnoEs1Test(){
        admn.siguienteTurno();

        Assertions.assertEquals(jugadores.get(1), admn.getJugadorActual());
        Assertions.assertEquals(1, admn.getTurno());
    }


    @DisplayName("Avanza el turno dos veces, el jugador actual es B y el turno actual es el indice correcto")
    @Test
    public void avanzaTurnoDosVecesVerificaJugadorYTurnoActualTest(){
        // turnoInicial = 0
        admn.siguienteTurno(); // turnoActual = 1
        admn.siguienteTurno(); // TurnoActual = 2

        Assertions.assertEquals(jugadores.get(0), admn.getJugadorActual());
        Assertions.assertEquals(2, admn.getTurno());
    }

    @DisplayName("Avanza el turno varias veces y verifica el correcto avance de turno")
    @Test
    public void avanzaTurnoMuchasVecesVerificaTurnoActualTest(){
        for (int i = 1; i < 100; i++){
            int turnoActual = admn.getTurno();
            admn.siguienteTurno();
            Assertions.assertEquals(turnoActual+1, admn.getTurno());
        }
    }

    @DisplayName("Avanza el turno varias veces y verifica que el jugador actual sea el correcto")
    @Test
    public void avamzaTurnoMuchasVecesVerificaJugadorActualTest(){
        for (int i = 1; i < 100; i++){
            int turnoActual = admn.getTurno();

            if (turnoActual % 2 == 0){
                Assertions.assertEquals(jugadores.get(0), admn.getJugadorActual());
            }else{
                Assertions.assertEquals(jugadores.get(1), admn.getJugadorActual());
            }

            admn.siguienteTurno();
        }
    }
}

