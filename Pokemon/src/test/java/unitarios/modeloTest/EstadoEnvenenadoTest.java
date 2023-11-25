package unitarios.modeloTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tp1.clases.modelo.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class EstadoEnvenenadoTest {
    private EstadoComportamiento estadoEnvenenado;

    private Pokemon pokeEnvenenado;

    @BeforeEach
    public void setUp(){
        this.estadoEnvenenado = new Envenenado(0);
        this.pokeEnvenenado = mock(Pokemon.class);
    }

    @DisplayName("Intenta aplicar efecto, es decir envenenar, pero solo lleva un turno envenenado, por lo tanto no lo hace")
    @Test
    public void noAplicaEfectoTest(){
        estadoEnvenenado.aplicarEfecto(pokeEnvenenado);

        assertEquals(1, estadoEnvenenado.getTurnos());
    }

    @DisplayName("Intenta aplicar efecto, es decir desenvenenar, pero solo lleva un turno envenenado, por lo tanto sigue")
    @Test
    public void aplicaEfectoTest(){
        when(pokeEnvenenado.getVidaMax()).thenReturn(1);

        estadoEnvenenado.aplicarEfecto(pokeEnvenenado); // turno 0-->sigue
        estadoEnvenenado.aplicarEfecto(pokeEnvenenado); // turno 1--> envenena

        verify(pokeEnvenenado).modificarVida((-1) * Constantes.porcentajeDeEnvenamiento);
        assertEquals(2, estadoEnvenenado.getTurnos());
    }


    @DisplayName("Al llamar a usarHabilidad simplemente devuelve true, ya que este estado no niega el uso en ningun caso")
    @Test
    public void puedeUsarHabilidadTrueTest(){
        Habilidad hab = mock(HabilidadAtaque.class);
        assertTrue(estadoEnvenenado.usarHabilidad(hab, pokeEnvenenado));
    }
}
