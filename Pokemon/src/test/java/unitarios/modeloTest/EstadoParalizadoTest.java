package unitarios.modeloTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import tp1.clases.modelo.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class EstadoParalizadoTest {
    private EstadoComportamiento estadoParalizado;

    private Pokemon pokeParalizado;

    private List<Habilidad> habilidades;

    @BeforeEach
    public void setUp(){
        this.estadoParalizado = new Paralizado(0);
        this.pokeParalizado = mock(Pokemon.class);
        Habilidad hab = mock(Habilidad.class);
        this.habilidades = List.of(hab);
    }

    @DisplayName("Verifica que no pueda usar la habilidad cuando la probabilidad asi lo indica")
    @Test
    public void noPuedeUsarHabilidadTest(){
        when(pokeParalizado.getHabilidades()).thenReturn(habilidades);
        Habilidad hab = mock(Habilidad.class);
        try (MockedStatic<Random> mockedRandom = mockStatic(Random.class)) {
            mockedRandom.when(() -> Random.probabilidad(Constantes.probabilidadParalizado)).thenReturn(false);
            Habilidad habilidad = mock(HabilidadAtaque.class);

            boolean resultado = estadoParalizado.usarHabilidad(hab, pokeParalizado);

            assertFalse(resultado);
        }
    }

    @DisplayName("Verifica que si pueda usar la habilidad cuando la probabilidad asi lo indica")
    @Test
    public void puedeUsarHabilidadTest(){
        when(pokeParalizado.getHabilidades()).thenReturn(habilidades);
        Habilidad hab = mock(Habilidad.class);
        try (MockedStatic<Random> mockedRandom = mockStatic(Random.class)) {
            mockedRandom.when(() -> Random.probabilidad(Constantes.probabilidadParalizado)).thenReturn(true);
            Habilidad habilidad = mock(HabilidadAtaque.class);

            boolean resultado = estadoParalizado.usarHabilidad(hab, pokeParalizado);

            assertTrue(resultado);
        }
    }
}
