package unitarios.modeloTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import tp1.clases.modelo.Confundido;
import tp1.clases.modelo.EstadosComportamiento;
import tp1.clases.modelo.Pokemon;
import tp1.clases.modelo.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class EstadoConfundidoTest {

    private EstadosComportamiento estadoConfundido;

    private Pokemon pokeConfundido;


    @BeforeEach
    public void setUp(){
        this.estadoConfundido = new Confundido(0);
        this.pokeConfundido = mock(Pokemon.class);
    }

    @DisplayName("Al aplicar efecto, se puede ya que lleva solo 0 turnos confundido")
    @Test
    public void aplicarEfectoValidoTest(){
        estadoConfundido.aplicarEfecto(pokeConfundido);

        assertEquals(1, estadoConfundido.getTurnos());
    }

    @DisplayName("Al aplicar efecto, no se puede ya que lleva mas de 3 turnos confundido")
    @Test
    public void aplicarEfectoNoValidoTest(){
        estadoConfundido.aplicarEfecto(pokeConfundido); // turno = 1
        estadoConfundido.aplicarEfecto(pokeConfundido); // turno = 2
        estadoConfundido.aplicarEfecto(pokeConfundido); // turno = 3
        estadoConfundido.aplicarEfecto(pokeConfundido); // turno = 4

        // Verifico que resetea los turnos que lleva confundido
        assertEquals(0, estadoConfundido.getTurnos());
    }

    @DisplayName("Intenta usar habilidad, puede y termina autolesionandose")
    @Test
    public void usarHabilidadAutolesionaTest(){
        when(pokeConfundido.getVidaMax()).thenReturn(100);
        try (MockedStatic<Random> mockedRandom = mockStatic(Random.class)) {
            mockedRandom.when(() -> Random.probabilidad(1.0 / 3.0)).thenReturn(true);

            boolean resultado = estadoConfundido.usarHabilidad(0, pokeConfundido);

            double danio =  0.15 * 100;
            verify(pokeConfundido).modificarVida((-1) * danio);
            assertTrue(resultado);
        }
    }

    @DisplayName("Intenta usar habilidad , pero no se autolesiona, igualmente puede usar la habilidad")
    @Test
    public void usarHabilidadNoAutolesionaTest(){
        try (MockedStatic<Random> mockedRandom = mockStatic(Random.class)) {
            mockedRandom.when(() -> Random.probabilidad(1.0 / 3.0)).thenReturn(false);

            boolean resultado = estadoConfundido.usarHabilidad(0, pokeConfundido);

            assertTrue(resultado);
            verify(pokeConfundido, never()).modificarVida(anyDouble());
        }
    }

}
