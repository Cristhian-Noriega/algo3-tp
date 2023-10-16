package modeloTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tp1.clases.errores.Error;
import tp1.clases.modelo.*;

import java.util.List;
import java.util.Optional;

@DisplayName("Pruebo unicamente la funcionalidad de las habilidades, la disminucion de los usos y compatibilidades entre pokemones se prueba en BatallaTest")
public class HabilidadesTest {

    //To Do: agregar test de probabilidad, agregar test de errores

    Habilidad danio = new HabilidadAtaque("Arañazo", 2, Tipo.NORMAL, 100, "Hace daño al pokemon contrario");
    Habilidad aumentaAtaque = new HabilidadEstadistica("Acua aro", 20, Tipo.AGUA, "Aumenta el ataque del pokemon", Estadisticas.ATAQUE, false);
    Habilidad disminuyeAtaque = new HabilidadEstadistica("Danza Pluma", 15, Tipo.VOLADOR, "Disminuye el ataque del enemigo", Estadisticas.ATAQUE, true);
    Habilidad aumentaVida = new HabilidadEstadistica("Campo de hierbas", 10, Tipo.PLANTA, "Recupera la vida de los Pokemon", Estadisticas.VIDA,false);
    Habilidad aumentaDefensa = new HabilidadEstadistica("Amnesia", 20, Tipo.PSIQUICO, "Aumenta sus defensas", Estadisticas.DEFENSA, false);
    Habilidad disminuyeDefensa = new HabilidadEstadistica("Rabia", 5, Tipo.FANTASMA, "Debilita la defensa del objetivo", Estadisticas.DEFENSA, true);
    Habilidad dormir = new HabilidadEstado("Somnifero", 15, Tipo.PLANTA, "Duerme al objetivo", Estado.DORMIDO);
    Habilidad paralizar = new HabilidadEstado("Chispa", 20, Tipo.ELECTRICO, "Paraliza al enemigo", Estado.PARALIZADO);
    Habilidad envenenar = new HabilidadEstado("Hilo venenoso", 20, Tipo.VENENO, "Ataca al enemigo con hilillos venenosos", Estado.ENVENENADO);

    List<Habilidad> listaHabilidades = List.of(danio, aumentaAtaque, disminuyeAtaque, aumentaVida, aumentaDefensa, disminuyeDefensa, dormir, paralizar, envenenar);
    //la lista es innecesaria pero quedaba linda, la podemos usar dsp para pruebas con batalla y controlador(?

    Pokemon rataDeLaboratorio = new Pokemon("Rata de laboratorio", 20, Tipo.BICHO, List.copyOf(listaHabilidades), 100, 193.0, 184.0, 130.0);
    Pokemon conejilloDeIndias = new Pokemon("Conejillo de Indias", 18, Tipo.HIELO, List.copyOf(listaHabilidades), 98, 139.0, 180.0, 130.0);


    @DisplayName("uso habilidad de ataque para hacerle daño al pokemon enemigo")
    @Test
    void vidaDeEnemigoDisminuye(){
        Optional<Error> err = danio.usar(rataDeLaboratorio, conejilloDeIndias);
        Assertions.assertTrue(conejilloDeIndias.getVida() < conejilloDeIndias.getVidaMax());
        //devuelvo el pokemon a su estado original para poder seguir haciendo pruebas
        conejilloDeIndias.modificarVida(conejilloDeIndias.getVidaMax());
        Assertions.assertEquals(conejilloDeIndias.getVida(), conejilloDeIndias.getVidaMax());
    }

    @DisplayName("uso habilidad de estadistica de ataque para aumentar el ataque del pokemon")
    @Test
    void pokeAumentaSuAtaque(){
        double ataqueInicial = rataDeLaboratorio.getAtaque();
        Optional<Error> err = aumentaAtaque.usar(rataDeLaboratorio, conejilloDeIndias);
        Assertions.assertTrue(rataDeLaboratorio.getAtaque() > ataqueInicial);
        //devuelvo el pokemon a su estado original para poder seguir haciendo pruebas
        rataDeLaboratorio.modificarAtaque(ataqueInicial - rataDeLaboratorio.getAtaque());
        Assertions.assertEquals(rataDeLaboratorio.getAtaque(), ataqueInicial);
    }

    @DisplayName("uso habilidad de estadistica de ataque para disminuir el ataque del pokemon enemigo")
    @Test
    void ataqueEnemigoDisminuye(){
        double ataqueInicial = conejilloDeIndias.getAtaque();
        Optional<Error> err = disminuyeAtaque.usar(rataDeLaboratorio, conejilloDeIndias);
        Assertions.assertTrue(conejilloDeIndias.getAtaque() < ataqueInicial);
        //devuelvo el pokemon a su estado original para poder seguir haciendo pruebas
        conejilloDeIndias.modificarAtaque(ataqueInicial - conejilloDeIndias.getAtaque());
        Assertions.assertEquals(conejilloDeIndias.getAtaque(), ataqueInicial);
    }

    @DisplayName("uso habilidad de estadistica de vida para aumentar la vida del pokemon")
    @Test
    void pokeAumentaSuVida(){
        //inicializo bajando la vida del poke para poder hacer la prueba
        rataDeLaboratorio.modificarVida(-40);
        double vidaInicial = rataDeLaboratorio.getVida();
        Optional<Error> err = aumentaVida.usar(rataDeLaboratorio, conejilloDeIndias);
        Assertions.assertTrue(rataDeLaboratorio.getVida() > vidaInicial);
        //devuelvo el pokemon a su estado original para poder seguir haciendo pruebas
        rataDeLaboratorio.modificarVida(rataDeLaboratorio.getVidaMax());
        Assertions.assertEquals(rataDeLaboratorio.getVida(), rataDeLaboratorio.getVidaMax());
    }

    @DisplayName("uso habilidad de estadistica de defensa para aumentar la defensa del pokemon")
    @Test
    void pokeAumentaSuDefensa(){
        double defensaInicial = rataDeLaboratorio.getDefensa();
        Optional<Error> err = aumentaDefensa.usar(rataDeLaboratorio, conejilloDeIndias);
        Assertions.assertTrue(rataDeLaboratorio.getDefensa() > defensaInicial);
        //devuelvo el pokemon a su estado original para poder seguir haciendo pruebas
        rataDeLaboratorio.modificarDefensa(defensaInicial - rataDeLaboratorio.getDefensa());
        Assertions.assertEquals(rataDeLaboratorio.getDefensa(), defensaInicial);
    }

    @DisplayName("uso habilidad de estadistica de defensa para disminuir la defensa del pokemon enemigo")
    @Test
    void defensaEnemigoDisminuye(){
        double defensaInicial = conejilloDeIndias.getDefensa();
        Optional<Error> err = disminuyeDefensa.usar(rataDeLaboratorio, conejilloDeIndias);
        Assertions.assertTrue(conejilloDeIndias.getDefensa() < defensaInicial);
        //devuelvo el pokemon a su estado original para poder seguir haciendo pruebas
        rataDeLaboratorio.modificarDefensa(defensaInicial - rataDeLaboratorio.getDefensa());
        Assertions.assertEquals(rataDeLaboratorio.getDefensa(), defensaInicial);
    }

    @DisplayName("uso habilidad de estado para dormir al pokemon enemigo")
    @Test
    void duermoEnemigo(){
        Optional<Error> err = dormir.usar(rataDeLaboratorio, conejilloDeIndias);
        Assertions.assertEquals(conejilloDeIndias.getEstado(), Estado.DORMIDO);
        //devuelvo el pokemon a su estado original para poder seguir haciendo pruebas
        conejilloDeIndias.setEstado(Estado.NORMAL);
    }

    @DisplayName("uso habilidad de estado para paralizar al pokemon enemigo")
    @Test
    void paralizoEnemigo(){
        Optional<Error> err = paralizar.usar(rataDeLaboratorio, conejilloDeIndias);
        Assertions.assertEquals(conejilloDeIndias.getEstado(), Estado.PARALIZADO);
        //devuelvo el pokemon a su estado original para poder seguir haciendo pruebas
        conejilloDeIndias.setEstado(Estado.NORMAL);
    }

    @DisplayName("uso habilidad de estado para envenenar al pokemon enemigo")
    @Test
    void envenenoEnemigo(){
        Optional<Error> err = envenenar.usar(rataDeLaboratorio, conejilloDeIndias);
        Assertions.assertEquals(conejilloDeIndias.getEstado(), Estado.ENVENENADO);
        //devuelvo el pokemon a su estado original para poder seguir haciendo pruebas
        conejilloDeIndias.setEstado(Estado.NORMAL);
    }

}