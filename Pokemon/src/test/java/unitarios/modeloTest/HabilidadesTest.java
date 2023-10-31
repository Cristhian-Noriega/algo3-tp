package unitarios.modeloTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tp1.clases.errores.Error;
import tp1.clases.modelo.*;

import java.util.List;
import java.util.Optional;

@DisplayName("Pruebo unicamente la funcionalidad de las habilidades, la compatibilidades entre pokemones se prueba en BatallaTest")
public class HabilidadesTest {

    //To Do: agregar test de probabilidad

    Habilidad danio = new HabilidadAtaque("Arañazo", 1, Tipo.NORMAL, 100, "Hace daño al pokemon contrario");
    Habilidad aumentaAtaque = new HabilidadEstadistica("Acua aro", 1, Tipo.AGUA, "Aumenta el ataque del pokemon", Estadisticas.ATAQUE, false);
    Habilidad disminuyeAtaque = new HabilidadEstadistica("Danza Pluma", 1, Tipo.VOLADOR, "Disminuye el ataque del enemigo", Estadisticas.ATAQUE, true);
    Habilidad aumentaVida = new HabilidadEstadistica("Campo de hierbas", 1, Tipo.PLANTA, "Recupera la vida de los Pokemon", Estadisticas.VIDA,false);
    Habilidad aumentaDefensa = new HabilidadEstadistica("Amnesia", 1, Tipo.PSIQUICO, "Aumenta sus defensas", Estadisticas.DEFENSA, false);
    Habilidad disminuyeDefensa = new HabilidadEstadistica("Rabia", 1, Tipo.FANTASMA, "Debilita la defensa del objetivo", Estadisticas.DEFENSA, true);
    Habilidad dormir = new HabilidadEstado("Somnifero", 1, Tipo.PLANTA, "Duerme al objetivo", Estado.DORMIDO);
    Habilidad paralizar = new HabilidadEstado("Chispa", 1, Tipo.ELECTRICO, "Paraliza al enemigo", Estado.PARALIZADO);
    Habilidad envenenar = new HabilidadEstado("Hilo venenoso", 1, Tipo.VENENO, "Ataca al enemigo con hilillos venenosos", Estado.ENVENENADO);

    List<Habilidad> listaHabilidades = List.of(danio, aumentaAtaque, disminuyeAtaque, aumentaVida, aumentaDefensa, disminuyeDefensa, dormir, paralizar, envenenar);
    //la lista es innecesaria pero quedaba linda, la podemos usar dsp para pruebas con batalla y controlador(?

    Pokemon rataDeLaboratorio = new Pokemon("Rata de laboratorio", 20, Tipo.BICHO, listaHabilidades, 100, 193.0, 184.0, 130.0);
    Pokemon conejilloDeIndias = new Pokemon("Conejillo de Indias", 18, Tipo.HIELO, listaHabilidades, 98, 139.0, 180.0, 130.0);


    @DisplayName("uso habilidad de ataque para hacerle daño al pokemon enemigo")
    @Test
    void vidaDeEnemigoDisminuye(){
        Optional<Error> err = danio.usar();
        Assertions.assertTrue(conejilloDeIndias.getVida() < conejilloDeIndias.getVidaMax());
    }

    @DisplayName("uso habilidad de estadistica de ataque para aumentar el ataque del pokemon")
    @Test
    void pokeAumentaSuAtaque(){
        double ataqueInicial = rataDeLaboratorio.getAtaque();

        Optional<Error> err = aumentaAtaque.usar();
        Assertions.assertTrue(rataDeLaboratorio.getAtaque() > ataqueInicial);
    }

    @DisplayName("uso habilidad de estadistica de ataque para disminuir el ataque del pokemon enemigo")
    @Test
    void ataqueEnemigoDisminuye(){
        double ataqueInicial = conejilloDeIndias.getAtaque();

        Optional<Error> err = disminuyeAtaque.usar();
        Assertions.assertTrue(conejilloDeIndias.getAtaque() < ataqueInicial);
    }

    @DisplayName("uso habilidad de estadistica de vida para aumentar la vida del pokemon")
    @Test
    void pokeAumentaSuVida(){
        //inicializo bajando la vida del poke para poder hacer la prueba
        rataDeLaboratorio.modificarVida(-40);
        double vidaInicial = rataDeLaboratorio.getVida();

        Optional<Error> err = aumentaVida.usar();
        Assertions.assertTrue(rataDeLaboratorio.getVida() > vidaInicial);
    }

    @DisplayName("uso habilidad de estadistica de defensa para aumentar la defensa del pokemon")
    @Test
    void pokeAumentaSuDefensa(){
        double defensaInicial = rataDeLaboratorio.getDefensa();

        Optional<Error> err = aumentaDefensa.usar();
        Assertions.assertTrue(rataDeLaboratorio.getDefensa() > defensaInicial);
    }

    @DisplayName("uso habilidad de estadistica de defensa para disminuir la defensa del pokemon enemigo")
    @Test
    void defensaEnemigoDisminuye(){
        double defensaInicial = conejilloDeIndias.getDefensa();

        Optional<Error> err = disminuyeDefensa.usar();
        Assertions.assertTrue(conejilloDeIndias.getDefensa() < defensaInicial);
    }

    @DisplayName("uso habilidad de estado para dormir al pokemon enemigo")
    @Test
    void duermoEnemigo(){
        Optional<Error> err = dormir.usar();
        Assertions.assertTrue(conejilloDeIndias.getEstados().contains(Estado.DORMIDO));
    }

    @DisplayName("uso habilidad de estado para paralizar al pokemon enemigo")
    @Test
    void paralizoEnemigo(){
        Optional<Error> err = paralizar.usar();
        Assertions.assertTrue(conejilloDeIndias.getEstados().contains(Estado.PARALIZADO));
    }

    @DisplayName("uso habilidad de estado para envenenar al pokemon enemigo")
    @Test
    void envenenoEnemigo(){
        Optional<Error> err = envenenar.usar();
        Assertions.assertTrue(conejilloDeIndias.getEstados().contains(Estado.ENVENENADO));
    }

    @DisplayName("intento usar dos veces la habilidad danio pero esta solo tiene un unico uso")
    @Test
    void habilidadAtaqueSinUsosDisponibles(){
        //uso el unico uso de esta habilidad
        Optional<Error> error = danio.usar();

        //compruebo que no tenga mas usos
        Assertions.assertTrue(danio.sinUsosDisponibles());

        //pruebo usandola de nuevo
        Optional<Error> err = danio.usar();
        Assertions.assertTrue(err.isPresent());
    }

    @DisplayName("intento usar dos veces la habilidad aumentaAtaque pero esta solo tiene un unico uso")
    @Test
    void habilidadEstadisticaSinUsosDisponibles(){
        //uso el unico uso de esta habilidad
        Optional<Error> error = aumentaAtaque.usar();

        //compruebo que no tenga mas usos
        Assertions.assertTrue(aumentaAtaque.sinUsosDisponibles());

        //pruebo usandola de nuevo
        Optional<Error> err = aumentaAtaque.usar();
        Assertions.assertTrue(err.isPresent());
    }

    @DisplayName("intento usar dos veces la habilidad dormir pero esta solo tiene un unico uso")
    @Test
    void habilidadEstadoSinUsosDisponibles(){
        //uso el unico uso de esta habilidad
        Optional<Error> error = dormir.usar();

        //compruebo que no tenga mas usos
        Assertions.assertTrue(dormir.sinUsosDisponibles());

        //pruebo usandola de nuevo
        Optional<Error> err = dormir.usar();
        Assertions.assertTrue(err.isPresent());
    }


}