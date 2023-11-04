package unitarios.controladorTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tp1.clases.controlador.*;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Habilidad;
import tp1.clases.modelo.Item;
import tp1.clases.modelo.Pokemon;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class ControladorMenuTest {

    private ControladorMenu controlMenu;

    @BeforeEach
    public void setUp(){
        Batalla batalla = mock(Batalla.class);
        controlMenu = new ControladorMenu(batalla);
    }

    @DisplayName("Verifico que al inicializar el controlador Menu, el menu inicial sea el principal")
    @Test
    public void verificoMenuInicialTest(){
        Assertions.assertEquals(CategoriaMenu.PRINCIPAL, controlMenu.obtenerMenuActual().getCategoria());
    }

    @DisplayName("Verifico que se agregue correctamente el nuevo menuHabilidades")
    @Test

    public void verificoAgregarMenuHabilidadesTest(){
        MenuHabilidades menuHabil = new MenuHabilidades(List.of(mock(Habilidad.class)));
        controlMenu.actualizarMenu(menuHabil);

        Assertions.assertEquals(CategoriaMenu.HABILIDADES, controlMenu.obtenerMenuActual().getCategoria());
    }

    @DisplayName("Verifico que se agregue correctamente el nuevo menuPokemones")
    @Test
    public void verificoAgregarMenuPokemonesTest(){
        MenuPokemones menuPoke = new MenuPokemones(List.of(mock(Pokemon.class)), false);
        controlMenu.actualizarMenu(menuPoke);

        Assertions.assertEquals(CategoriaMenu.POKEMONES, controlMenu.obtenerMenuActual().getCategoria());
    }

    @DisplayName("Verifico que se agregue correctamente el nuevo menuPokemonesItems")
    @Test
    public void verificoAgregarMenuItemsTest(){
        MenuItems menuItem = new MenuItems(Map.of("Item", 0L), List.of(mock(Item.class)));
        controlMenu.actualizarMenu(menuItem);

        Assertions.assertEquals(CategoriaMenu.ITEMS, controlMenu.obtenerMenuActual().getCategoria());
    }

    @DisplayName("Agrego varios menu y retrocedo, verificando el actual")
    @Test
    public void agregarYEliminarMenuTest(){
        MenuItems menuItem = new MenuItems(Map.of("Item", 0L), List.of(mock(Item.class)));
        controlMenu.actualizarMenu(menuItem);
        Assertions.assertEquals(CategoriaMenu.ITEMS, controlMenu.obtenerMenuActual().getCategoria());

        MenuPrincipal menuPrincip = new MenuPrincipal();
        controlMenu.actualizarMenu(menuPrincip);
        Assertions.assertEquals(CategoriaMenu.PRINCIPAL, controlMenu.obtenerMenuActual().getCategoria());

        controlMenu.retroceder();
        Assertions.assertEquals(CategoriaMenu.ITEMS, controlMenu.obtenerMenuActual().getCategoria());

        MenuPokemones menuPoke = new MenuPokemones(List.of(mock(Pokemon.class)), true);
        controlMenu.actualizarMenu(menuPoke);
        Assertions.assertEquals(CategoriaMenu.POKEMONES, controlMenu.obtenerMenuActual().getCategoria());
    }

    @DisplayName("Retrocedo sin agregar ningun menu, y no se puede")
    @Test
    public void retrocederNoQuedaVacioTest() {
        controlMenu.retroceder();

        Assertions.assertNotNull(controlMenu.obtenerMenuActual());
    }

    @DisplayName("Retrocedo dos veces sin agregar ningun menu, y no se puede quedar sin menuActual")
    @Test
    public void retrocederDosNoQuedaVacioTest() {
        controlMenu.retroceder();
        controlMenu.retroceder();

        Assertions.assertNotNull(controlMenu.obtenerMenuActual());
    }

}
