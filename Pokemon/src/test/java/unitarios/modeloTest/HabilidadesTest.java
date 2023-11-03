package unitarios.modeloTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tp1.clases.errores.Error;
import tp1.clases.modelo.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Pruebo unicamente la funcionalidad de las habilidades, la compatibilidades entre pokemones se prueba en BatallaTest")
public class HabilidadesTest {

    Pokemon rataDeLaboratorio = new Pokemon("Rata de laboratorio", 20, Tipo.AGUA, List.of(), 100, 193.0, 184.0, 130.0);
    Pokemon conejilloDeIndias = new Pokemon("Conejillo de Indias", 18, Tipo.TIERRA, List.of(), 98, 139.0, 180.0, 130.0);
    AdministradorDeClima administradorDeClima = mock(AdministradorDeClima.class);
    @DisplayName("uso habilidad de ataque para hacerle daño al pokemon enemigo")
    @Test
    void vidaDeEnemigoDisminuyeTest(){
        Habilidad danio = new HabilidadAtaque("Arañazo", 1, Tipo.NORMAL, 100, "Hace daño al pokemon contrario");
        when(administradorDeClima.getClimaActual()).thenReturn(Clima.SIN_CLIMA);
        danio.setAmbiente(administradorDeClima, List.of(rataDeLaboratorio, conejilloDeIndias));

        Optional<Error> err = danio.usar();
        Assertions.assertTrue(conejilloDeIndias.getVida() < conejilloDeIndias.getVidaMax());
    }

    @DisplayName("uso habilidad de estadistica de ataque para aumentar el ataque del pokemon")
    @Test
    void pokeAumentaSuAtaqueTest(){
        Habilidad aumentaAtaque = new HabilidadEstadistica("Acua aro", 1, Tipo.AGUA, "Aumenta el ataque del pokemon", Estadisticas.ATAQUE, false);
        double ataqueInicial = rataDeLaboratorio.getAtaque();
        when(administradorDeClima.getClimaActual()).thenReturn(Clima.SIN_CLIMA);
        aumentaAtaque.setAmbiente(administradorDeClima, List.of(rataDeLaboratorio, conejilloDeIndias));

        Optional<Error> err = aumentaAtaque.usar();
        Assertions.assertTrue(rataDeLaboratorio.getAtaque() > ataqueInicial);
    }

    @DisplayName("uso habilidad de estadistica de ataque para disminuir el ataque del pokemon enemigo")
    @Test
    void ataqueEnemigoDisminuyeTest(){
        double ataqueInicial = conejilloDeIndias.getAtaque();
        Habilidad disminuyeAtaque = new HabilidadEstadistica("Danza Pluma", 1, Tipo.VOLADOR, "Disminuye el ataque del enemigo", Estadisticas.ATAQUE, true);
        when(administradorDeClima.getClimaActual()).thenReturn(Clima.SIN_CLIMA);
        disminuyeAtaque.setAmbiente(administradorDeClima, List.of(rataDeLaboratorio, conejilloDeIndias));

        Optional<Error> err = disminuyeAtaque.usar();
        Assertions.assertTrue(conejilloDeIndias.getAtaque() < ataqueInicial);
    }

    @DisplayName("uso habilidad de estadistica de vida para aumentar la vida del pokemon")
    @Test
    void pokeAumentaSuVidaTest(){
        //inicializo bajando la vida del poke para poder hacer la prueba
        rataDeLaboratorio.modificarVida(-40);
        double vidaInicial = rataDeLaboratorio.getVida();
        Habilidad aumentaVida = new HabilidadEstadistica("Campo de hierbas", 1, Tipo.PLANTA, "Recupera la vida de los Pokemon", Estadisticas.VIDA,false);
        aumentaVida.setAmbiente(administradorDeClima, List.of(rataDeLaboratorio, conejilloDeIndias));

        Optional<Error> err = aumentaVida.usar();
        Assertions.assertTrue(rataDeLaboratorio.getVida() > vidaInicial);
    }

    @DisplayName("uso habilidad de estadistica de defensa para aumentar la defensa del pokemon")
    @Test
    void pokeAumentaSuDefensaTest(){
        double defensaInicial = rataDeLaboratorio.getDefensa();
        when(administradorDeClima.getClimaActual()).thenReturn(Clima.SIN_CLIMA);
        Habilidad aumentaDefensa = new HabilidadEstadistica("Amnesia", 1, Tipo.PSIQUICO, "Aumenta sus defensas", Estadisticas.DEFENSA, false);
        aumentaDefensa.setAmbiente(administradorDeClima, List.of(rataDeLaboratorio, conejilloDeIndias));

        Optional<Error> err = aumentaDefensa.usar();
        Assertions.assertTrue(rataDeLaboratorio.getDefensa() > defensaInicial);
    }

    @DisplayName("uso habilidad de estadistica de defensa para disminuir la defensa del pokemon enemigo")
    @Test
    void defensaEnemigoDisminuyeTest(){
        double defensaInicial = conejilloDeIndias.getDefensa();
        when(administradorDeClima.getClimaActual()).thenReturn(Clima.SIN_CLIMA);
        Habilidad disminuyeDefensa = new HabilidadEstadistica("Rabia", 1, Tipo.FANTASMA, "Debilita la defensa del objetivo", Estadisticas.DEFENSA, true);
        disminuyeDefensa.setAmbiente(administradorDeClima, List.of(rataDeLaboratorio, conejilloDeIndias));

        Optional<Error> err = disminuyeDefensa.usar();
        Assertions.assertTrue(conejilloDeIndias.getDefensa() < defensaInicial);
    }

    @DisplayName("uso habilidad de estado para dormir al pokemon enemigo")
    @Test
    void duermoEnemigoTest(){
        when(administradorDeClima.getClimaActual()).thenReturn(Clima.SIN_CLIMA);
        Habilidad dormir = new HabilidadEstado("Somnifero", 1, Tipo.PLANTA, "Duerme al objetivo", Estado.DORMIDO);
        dormir.setAmbiente(administradorDeClima, List.of(rataDeLaboratorio, conejilloDeIndias));

        Optional<Error> err = dormir.usar();
        Assertions.assertTrue(conejilloDeIndias.getEstados().contains(Estado.DORMIDO));

    }

    @DisplayName("uso habilidad de estado para paralizar al pokemon enemigo")
    @Test
    void paralizoEnemigoTest(){
        when(administradorDeClima.getClimaActual()).thenReturn(Clima.SIN_CLIMA);
        Habilidad paralizar = new HabilidadEstado("Chispa", 1, Tipo.ELECTRICO, "Paraliza al enemigo", Estado.PARALIZADO);
        paralizar.setAmbiente(administradorDeClima, List.of(rataDeLaboratorio, conejilloDeIndias));

        Optional<Error> err = paralizar.usar();
        Assertions.assertTrue(conejilloDeIndias.getEstados().contains(Estado.PARALIZADO));
    }

    @DisplayName("uso habilidad de estado para envenenar al pokemon enemigo")
    @Test
    void envenenoEnemigoTest(){
        when(administradorDeClima.getClimaActual()).thenReturn(Clima.SIN_CLIMA);
        Habilidad envenenar = new HabilidadEstado("Hilo venenoso", 1, Tipo.VENENO, "Ataca al enemigo con hilillos venenosos", Estado.ENVENENADO);
        envenenar.setAmbiente(administradorDeClima, List.of(rataDeLaboratorio, conejilloDeIndias));

        Optional<Error> err = envenenar.usar();
        Assertions.assertTrue(conejilloDeIndias.getEstados().contains(Estado.ENVENENADO));
    }

    @Test
    void cambioDeClimaTest(){
        AdministradorDeClima administradorDeClima = new AdministradorDeClima();
        administradorDeClima.cambiarClima(Clima.SIN_CLIMA);
        Habilidad arena = new HabilidadClima("tormenta de arena", 1, Tipo.TIERRA, "", Clima.TORMENTA_DE_ARENA);
        arena.setAmbiente(administradorDeClima, List.of(rataDeLaboratorio, conejilloDeIndias));

        Optional<Error> error = arena.usar();
        Assertions.assertEquals(administradorDeClima.getClimaActual(), Clima.TORMENTA_DE_ARENA);

        Habilidad sacarClima = new HabilidadClima("sin clima", 1, Tipo.NORMAL, "", Clima.SIN_CLIMA);
        sacarClima.setAmbiente(administradorDeClima, List.of(rataDeLaboratorio, conejilloDeIndias));

        Optional<Error> err = sacarClima.usar();
        Assertions.assertEquals(administradorDeClima.getClimaActual(), Clima.SIN_CLIMA);
    }


}