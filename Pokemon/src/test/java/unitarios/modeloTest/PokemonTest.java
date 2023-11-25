package unitarios.modeloTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tp1.clases.errores.Error;
import tp1.clases.modelo.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PokemonTest {
    Habilidad aumentaDefensa = new HabilidadEstadistica("Amnesia", 1, Tipo.PSIQUICO, "Aumenta sus defensas", Estadisticas.DEFENSA, false, 1);
    Pokemon pokeVivo = new Pokemon("pokeVivo", 18, Tipo.HIELO, List.of(aumentaDefensa), 98, 139.0, 180.0, 130.0, 1);
    Pokemon pokeMuerto = new Pokemon("pokeMuerto", 20, Tipo.BICHO, List.of(), 0, 193.0, 184.0, 130.0, 2);
    Pokemon otroPokeVivo = new Pokemon("El otroPokeVivo", 18, Tipo.HIELO, List.of(), 98, 139.0, 180.0, 130.0, 3);

    @Test
    public void usarHabilidadDisponible(){
        double defensaInicial = pokeVivo.getDefensa();
        AdministradorDeClima administradorDeClima = mock(AdministradorDeClima.class);
        Habilidad habilidad = mock(HabilidadAtaque.class);
        when(administradorDeClima.getClimaActual()).thenReturn(Clima.SIN_CLIMA);

        Optional<Error> err = pokeVivo.usarHabilidad(habilidad, otroPokeVivo, administradorDeClima);
        Assertions.assertTrue(err.isEmpty());

        //confirmo que haga algo
        Assertions.assertNotEquals(defensaInicial, pokeVivo.getDefensa());
    }

//    @Test
//    public void usarHabilidadFueraDeRango(){
//        AdministradorDeClima administradorDeClima = mock(AdministradorDeClima.class);
//        when(administradorDeClima.getClimaActual()).thenReturn(Clima.SIN_CLIMA);
//
//        Optional<Error> error = pokeVivo.usarHabilidad(-1, otroPokeVivo, administradorDeClima);
//        Assertions.assertTrue(error.isPresent());
//
//        Optional<Error> err = pokeVivo.usarHabilidad(1, otroPokeVivo, administradorDeClima);
//        Assertions.assertTrue(err.isPresent());
//    }


//    @Test
//    void usarHabilidadSinUsosDisponibles(){
//        AdministradorDeClima administradorDeClima = mock(AdministradorDeClima.class);
//        when(administradorDeClima.getClimaActual()).thenReturn(Clima.SIN_CLIMA);
//
//        //uso la habilidad para sacarle su ultimo uso
//        Optional<Error> err = pokeVivo.usarHabilidad(0, otroPokeVivo, administradorDeClima);
//        Assertions.assertTrue(err.isEmpty());
//
//        Optional<Error> error = pokeVivo.usarHabilidad(0, otroPokeVivo, administradorDeClima);
//        Assertions.assertTrue(error.isPresent());
//
//    }
//
//    @Test
//    void usarHabilidadEstandoDormido(){
//        AdministradorDeClima administradorDeClima = mock(AdministradorDeClima.class);
//        when(administradorDeClima.getClimaActual()).thenReturn(Clima.SIN_CLIMA);
//        pokeVivo.setEstado(Estado.DORMIDO);
//        int vidaInicial = otroPokeVivo.getVida();
//
//        Optional<Error> error = pokeVivo.usarHabilidad(0, otroPokeVivo, administradorDeClima);
//        Assertions.assertTrue(error.isEmpty());
//        Assertions.assertEquals(vidaInicial, otroPokeVivo.getVida());
//    }

    @Test
    void aplicarEfectoEstadoEnvenenado(){
        double vidaInicial = pokeVivo.getVida();
        pokeVivo.setEstado(Estado.ENVENENADO);

        //paso el turno
        pokeVivo.aplicarEfectoEstados();

        pokeVivo.aplicarEfectoEstados();
        Assertions.assertTrue(vidaInicial > pokeVivo.getVida());
    }


    @Test
    public void estaMuertoTrue(){
        boolean muerto = pokeMuerto.estaMuerto();
        Assertions.assertTrue(muerto);
    }

    @Test
    public void estaMuertoFalse(){
        boolean muerto = pokeVivo.estaMuerto();
        Assertions.assertFalse(muerto);
    }

    @Test
    public void modificarVidaNoCambia() {
        pokeVivo.modificarVida(100);
        Assertions.assertEquals(pokeVivo.getVida(), pokeVivo.getVidaMax());
    }

    @Test
    public void modificarVidaAMuerto(){
        pokeVivo.modificarVida((-1)*pokeVivo.getVidaMax());
        Assertions.assertEquals(pokeVivo.getVida(), 0);
    }

    @Test
    public void modificaraVidaAMasQueMuerto(){
        pokeVivo.modificarVida((-1)*pokeVivo.getVida() - 100);
        Assertions.assertEquals(pokeVivo.getVida(), 0);
    }

}
