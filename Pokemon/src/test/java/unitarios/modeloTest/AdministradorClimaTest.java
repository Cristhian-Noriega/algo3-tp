package unitarios.modeloTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tp1.clases.modelo.*;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdministradorClimaTest {
    Pokemon rataDeLaboratorio = new Pokemon("Rata de laboratorio", 20, Tipo.AGUA, List.of(), 100, 193.0, 184.0, 130.0, 1);
    Pokemon conejilloDeIndias = new Pokemon("Conejillo de Indias", 18, Tipo.TIERRA, List.of(), 98, 139.0, 180.0, 130.0, 2);
    @Test
    void actualizarTurnoTest(){
        AdministradorDeClima admin = new AdministradorDeClima();
        admin.cambiarClima(Clima.HURACAN);

        //1
        admin.ActualizarTurno();
        Assertions.assertEquals(admin.getClimaActual(), Clima.HURACAN);

        //2
        admin.ActualizarTurno();
        Assertions.assertEquals(admin.getClimaActual(), Clima.HURACAN);

        //3
        admin.ActualizarTurno();
        Assertions.assertEquals(admin.getClimaActual(), Clima.HURACAN);

        //4
        admin.ActualizarTurno();
        Assertions.assertEquals(admin.getClimaActual(), Clima.HURACAN);

        //5
        admin.ActualizarTurno();
        Assertions.assertEquals(admin.getClimaActual(), Clima.HURACAN);

        //6
        admin.ActualizarTurno();
        Assertions.assertEquals(admin.getClimaActual(), Clima.SIN_CLIMA);
    }

    @Test
    void afectarPokemonesPorClimaTest(){
        Jugador jugadorA = mock(Jugador.class);
        when(jugadorA.getPokemonActual()).thenReturn(rataDeLaboratorio);
        Jugador jugadorB = mock(Jugador.class);
        when(jugadorB.getPokemonActual()).thenReturn(conejilloDeIndias);
        InfoTurno infoTurno= mock(InfoTurno.class);

        AdministradorDeClima admin = new AdministradorDeClima();
        admin.cambiarClima(Clima.TORMENTA_DE_ARENA);

        admin.afectarJugadores(List.of(jugadorA, jugadorB), infoTurno);
        Assertions.assertEquals(conejilloDeIndias.getVida(), conejilloDeIndias.getVidaMax());
        Assertions.assertNotEquals(rataDeLaboratorio.getVida(), rataDeLaboratorio.getVidaMax());

    }


}
