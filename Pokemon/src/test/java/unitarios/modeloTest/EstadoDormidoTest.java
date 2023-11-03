package unitarios.modeloTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import tp1.clases.modelo.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class EstadoDormidoTest {

    private EstadoComportamiento estadoDormido;

    private Pokemon pokeDormido;


    @BeforeEach
    public void setUp(){
        this.estadoDormido = new Dormido(0);
        this.pokeDormido = mock(Pokemon.class);
    }

    @DisplayName("Al inicio del turno, se intenta aplicar efecto. En este caso sería levantarlo, pero sigue durmiendo")
    @Test
    public void sigueDormidoTest() {
        try (MockedStatic<Random> mockedRandom = mockStatic(Random.class)) {
            mockedRandom.when(() -> Random.probabilidad(Constantes.probabilidadDespertar)).thenReturn(false);

            estadoDormido.aplicarEfecto(pokeDormido);
            verify(pokeDormido, never()).eliminarEstado(Estado.DORMIDO);
            // Debería avanzar el turno
            Assertions.assertEquals(1, estadoDormido.getTurnos());
        }
    }

    @DisplayName("Luego de algunos turnos, levanta al pokemon")
    @Test
    public void levantaDormidoTest() {
        for (int i = 0; i < 4; i++){ // avanzo 3 vece el turno
            try (MockedStatic<Random> mockedRandom = mockStatic(Random.class)) {
                mockedRandom.when(() -> Random.probabilidad(Constantes.probabilidadDespertar)).thenReturn(false);

                estadoDormido.aplicarEfecto(pokeDormido);
            }
        }

        try (MockedStatic<Random> mockedRandom = mockStatic(Random.class)) {
            double probabibilidadDespertar = Constantes.probabilidadDespertar + Constantes.probabilidadDespertar * estadoDormido.getTurnos();
            mockedRandom.when(() -> Random.probabilidad(probabibilidadDespertar)).thenReturn(true);

            estadoDormido.aplicarEfecto(pokeDormido);
            verify(pokeDormido).eliminarEstado(Estado.DORMIDO);
            // Deberia resetear los turnos
            Assertions.assertEquals(0, estadoDormido.getTurnos());
        }
    }

    @DisplayName("El pokemon dormido intenta usar una habilidad pero no puede")
    @Test
    public void noPuedeUsarHabilidadTest(){
        Habilidad hab = mock(Habilidad.class);
        List<Habilidad> habilidades = List.of(hab);
        when(pokeDormido.getHabilidades()).thenReturn(habilidades);

        boolean resultado = estadoDormido.usarHabilidad(0, pokeDormido);

        assertFalse(resultado);
    }

}
