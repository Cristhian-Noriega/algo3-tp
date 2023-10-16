import org.junit.jupiter.api.*;

public class ProbandoTest {

    @BeforeEach
    void setupEach() {
        System.out.println("**--- Ejecuta algo antes de cada test de esta clase ---**");
    }
    @AfterEach
    void clean() {
        System.out.println("**--- Ejecuta algo despues de cada test corrido de esta clase ---**");
    }
    @DisplayName("Nombre mas lindo que le doy a este test para que se muestre cuando corra :)") //ni idea q hace esto
    @Test
    void miPrimerTest() {
        System.out.println("**--- Estoy testeando !! ---**");
    }

    //@Ignore("Este test esta siendo ignorado") //el Ignore hace q el test no se ejecute, pero el before y el aftes de este test si
    @Disabled("Este test no deberia correrse, todavia no lo termine!") //se muestra el mensaje por la terminal pero no se ejecuta el test
    @Test
    void noDeberiaAparecer() {
        System.out.println("**--- Esto no se deberia printear ---**");
    }


}
