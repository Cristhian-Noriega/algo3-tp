package unitarios.modeloTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import tp1.clases.modelo.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class EstadoConfundidoTest {

    private EstadoComportamiento estadoConfundido;

    private Pokemon pokeConfundido;


    @BeforeEach
    public void setUp(){
        this.estadoConfundido = new Confundido(0);
        this.pokeConfundido = mock(Pokemon.class);
    }

    @DisplayName("Al aplicar efecto, solo lleva un turno confundido")
    @Test
    public void aplicarEfectoValidarPrimerTurnoTest(){
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

    @DisplayName("Luego de 3 turnos, deja de estar confundido")
    @Test
    public void dejaDeEstarConfundidoTest(){
        estadoConfundido.aplicarEfecto(pokeConfundido); // turno = 1
        estadoConfundido.aplicarEfecto(pokeConfundido); // turno = 2
        estadoConfundido.aplicarEfecto(pokeConfundido); // turno = 3

        assertFalse(this.pokeConfundido.getEstados().contains(Estado.CONFUNDIDO));
    }

    @DisplayName("Intenta usar habilidad, puede y termina autolesionandose")
    @Test
    public void usarHabilidadAutolesionaTest(){
        when(pokeConfundido.getVidaMax()).thenReturn(100);
        try (MockedStatic<Random> mockedRandom = mockStatic(Random.class)) {
            mockedRandom.when(() -> Random.probabilidad(1.0 / 3.0)).thenReturn(true);
            Habilidad habilidad = mock(HabilidadAtaque.class);

            boolean resultado = estadoConfundido.usarHabilidad(habilidad, pokeConfundido);

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
            Habilidad habilidad = mock(HabilidadAtaque.class);

            boolean resultado = estadoConfundido.usarHabilidad(habilidad, pokeConfundido);

            assertTrue(resultado);
            verify(pokeConfundido, never()).modificarVida(anyDouble());
        }
    }

}
