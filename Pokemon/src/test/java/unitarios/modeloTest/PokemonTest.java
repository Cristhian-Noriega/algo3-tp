package unitarios.modeloTest;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import tp1.clases.errores.Error;
import tp1.clases.modelo.*;

import java.util.List;
import java.util.Optional;

public class PokemonTest {
    //To Do: test de estados y test habilidades e items con estados

    Habilidad aumentaDefensa = new HabilidadEstadistica("Amnesia", 1, Tipo.PSIQUICO, "Aumenta sus defensas", Estadisticas.DEFENSA, false);
    Pokemon pokeVivo = new Pokemon("pokeVivo", 18, Tipo.HIELO, List.of(aumentaDefensa), 98, 139.0, 180.0, 130.0);
    Pokemon pokeMuerto = new Pokemon("pokeMuerto", 20, Tipo.BICHO, List.of(), 0, 193.0, 184.0, 130.0);
    Pokemon otroPokeVivo = new Pokemon("El otroPokeVivo", 18, Tipo.HIELO, List.of(), 98, 139.0, 180.0, 130.0);

    @Test
    public void usarHabilidadDisponible(){
        double defensaInicial = pokeVivo.getDefensa();

        Optional<Error> err = pokeVivo.usarHabilidad(0, otroPokeVivo);
        Assertions.assertTrue(err.isEmpty());
        //confirmo que haga algo
        Assertions.assertNotEquals(defensaInicial, pokeVivo.getDefensa());
    }

    @Test
    public void usarHabilidadFueraDeRango(){
        Optional<Error> error = pokeVivo.usarHabilidad(-1, otroPokeVivo);
        Assertions.assertTrue(error.isPresent());

        Optional<Error> err = pokeVivo.usarHabilidad(1, otroPokeVivo);
        Assertions.assertTrue(err.isPresent());
    }

    //faltan test de habilidad cuando tiene un estado


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
