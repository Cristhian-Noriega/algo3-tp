package unitarios.modeloTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tp1.clases.errores.Error;
import tp1.clases.modelo.*;

import java.util.List;
import java.util.Optional;


public class ItemsTest {

    Pokemon pokemonDePrueba = new Pokemon("Rata de laboratorio", 20, Tipo.BICHO, List.of(), 100, 193.0, 184.0, 130.0);

    Item itemVida = new ItemRestauracionVida("mas vida", 40);
    Item revivir = new ItemRevivir("revivir", 0);
    Item estado = new ItemEstado("recupero estado");
    Item velocidad = new ItemEstadistica("mejoro velocidad", Estadisticas.VELOCIDAD);
    Item ataque = new ItemEstadistica("mejoro atque", Estadisticas.ATAQUE);
    Item defensa = new ItemEstadistica("mejoro defensa", Estadisticas.DEFENSA);

    @DisplayName("uso item que sube la vida con la vida del pokemon *ya* al maximo")
    @Test
    void noDeberiaPoderSubirLaVidaTest(){
        Optional<Error> err = itemVida.usar(pokemonDePrueba);
        Assertions.assertEquals(pokemonDePrueba.getVidaMax(), pokemonDePrueba.getVida());
    }

    @DisplayName("uso item que sube la vida con la vida del pokemon")
    @Test
    void deberiaSubirLaVida(){
        //inicializo bajando la vida del poke para poder hacer la prueba
        pokemonDePrueba.modificarVida(-40);

        Optional<Error> err = itemVida.usar(pokemonDePrueba);
        Assertions.assertEquals(pokemonDePrueba.getVidaMax(), pokemonDePrueba.getVida());
    }

    @DisplayName("uso item para revivir un pokemon muerto")
    @Test
    void intentoRevivirPokemon(){
        //inicializo bajando la vida del poke para poder hacer la prueba
        pokemonDePrueba.modificarVida(-100);
        Assertions.assertTrue(pokemonDePrueba.estaMuerto());

        Optional<Error> err = revivir.usar(pokemonDePrueba);
        Assertions.assertEquals(pokemonDePrueba.getVidaMax(), pokemonDePrueba.getVida());
    }

    @DisplayName("uso item para revivir un pokemon *no* muerto")
    @Test
    void noPuedoRevivirPokemonNoMuerto(){
        Optional<Error> err = revivir.usar(pokemonDePrueba);
        Assertions.assertTrue(err.isPresent());
    }

    @DisplayName("uso item para normalizar el estado del pokemon acualmente dormido")
    @Test
    void pasoDeDormidoANormal(){
        //inicializo seteando el estado del poke para poder hacer la prueba
        pokemonDePrueba.setEstado(Estado.DORMIDO);
        Assertions.assertTrue(pokemonDePrueba.getEstados().contains(Estado.DORMIDO));

        Optional<Error> err = estado.usar(pokemonDePrueba);
        Assertions.assertTrue(pokemonDePrueba.getEstados().contains(Estado.NORMAL));
    }

    @DisplayName("uso item para normalizar el estado del pokemon acualmente envenenado")
    @Test
    void pasoDeEnvenenadoANormal(){
        //inicializo seteando el estado del poke para poder hacer la prueba
        pokemonDePrueba.setEstado(Estado.ENVENENADO);
        Assertions.assertTrue(pokemonDePrueba.getEstados().contains(Estado.ENVENENADO));

        Optional<Error> err = estado.usar(pokemonDePrueba);
        Assertions.assertTrue(pokemonDePrueba.getEstados().contains(Estado.NORMAL));
    }

    @DisplayName("uso item para normalizar el estado del pokemon acualmente paralizado")
    @Test
    void pasoDeParalizadoANormal(){
        //inicializo seteando el estado del poke para poder hacer la prueba
        pokemonDePrueba.setEstado(Estado.PARALIZADO);
        Assertions.assertTrue(pokemonDePrueba.getEstados().contains(Estado.PARALIZADO));

        Optional<Error> err = estado.usar(pokemonDePrueba);
        Assertions.assertTrue(pokemonDePrueba.getEstados().contains(Estado.NORMAL));
    }

    @DisplayName("uso item para normalizar el estado del pokemon acualmente confundido")
    @Test
    void pasoDeConfundidoANormal(){
        //inicializo seteando el estado del poke para poder hacer la prueba
        pokemonDePrueba.setEstado(Estado.CONFUNDIDO);
        Assertions.assertTrue(pokemonDePrueba.getEstados().contains(Estado.CONFUNDIDO));

        Optional<Error> err = estado.usar(pokemonDePrueba);
        Assertions.assertTrue(pokemonDePrueba.getEstados().contains(Estado.NORMAL));
    }
    @DisplayName("uso item para normalizar el estado del pokemon acualmente con mas de un estado activo")
    @Test
    void pasoDeMasDeUnEstadoANormal(){
        //inicializo seteando el estado del poke para poder hacer la prueba
        pokemonDePrueba.setEstado(Estado.DORMIDO);
        pokemonDePrueba.setEstado(Estado.ENVENENADO);
        Assertions.assertTrue(pokemonDePrueba.getEstados().contains(Estado.DORMIDO));
        Assertions.assertTrue(pokemonDePrueba.getEstados().contains(Estado.ENVENENADO));

        Optional<Error> err = estado.usar(pokemonDePrueba);
        Assertions.assertTrue(pokemonDePrueba.getEstados().contains(Estado.NORMAL));
        Assertions.assertEquals(1, pokemonDePrueba.getEstados().size());
    }

    @DisplayName("uso item para normalizar pokemon *ya* normal")
    @Test
    void noPuedoNormalizarPokemonNormal(){
        Assertions.assertTrue(pokemonDePrueba.getEstados().contains(Estado.NORMAL));

        Optional<Error> err = estado.usar(pokemonDePrueba);
        Assertions.assertTrue(err.isPresent());
    }

    @DisplayName("uso item para mejorar la velocidad actual del pokemon un 10%")
    @Test
    void mejoroVelocidadUnDiezPorciento(){
        double velocidadInicial = pokemonDePrueba.getVelocidad();
        double loQueDeberiaDevolver = ((velocidadInicial*0.1) + velocidadInicial);

        Optional<Error> err = velocidad.usar(pokemonDePrueba);
        Assertions.assertEquals(loQueDeberiaDevolver, pokemonDePrueba.getVelocidad());
    }

    @DisplayName("uso item para mejorar el ataque actual del pokemon un 10%")
    @Test
    void mejoroAtaqueUnDiezPorciento(){
        double ataqueInicial = pokemonDePrueba.getAtaque();
        double loQueDeberiaDevolver = ((ataqueInicial*0.1) + ataqueInicial);

        Optional<Error> err = ataque.usar(pokemonDePrueba);
        Assertions.assertEquals(loQueDeberiaDevolver, pokemonDePrueba.getAtaque());
    }

    @DisplayName("uso item para mejorar la defensa actual del pokemon un 10%")
    @Test
    void mejoroDefensaUnDiezPorciento(){
        double defensaInicial = pokemonDePrueba.getDefensa();
        double loQueDeberiaDevolver = ((defensaInicial*0.1) + defensaInicial);

        Optional<Error> err = defensa.usar(pokemonDePrueba);
        Assertions.assertEquals(loQueDeberiaDevolver, pokemonDePrueba.getDefensa());
    }
}
