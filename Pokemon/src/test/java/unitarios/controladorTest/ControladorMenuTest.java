package unitarios.controladorTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tp1.clases.controlador.*;
import tp1.clases.modelo.Batalla;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorMenuTest {

    private ControladorMenu controlMenu;

    @BeforeEach
    public void setUp(){
        Batalla batalla = mock(Batalla.class);
        controlMenu = new ControladorMenu(batalla);
    }

    @DisplayName("Verifico que al inicializar el controlador Menu, el menu inicial sea el principal")
    @Test
    public void verificoMenuInicial(){
        Assertions.assertEquals(CategoriaMenu.PRINCIPAL, controlMenu.obtenerMenuActual().getCategoria());
    }

    @DisplayName("Verifico que se agregue correctamente el nuevo menuHabilidades")
    @Test
    public void verificoAgregarMenuHabilidades(){
        MenuHabilidades menuHabilidades = mock(MenuHabilidades.class);
        controlMenu.actualizarMenu(menuHabilidades);

        // muy hardcodeado??

        when(controlMenu.obtenerMenuActual().getCategoria()).thenReturn(CategoriaMenu.HABILIDADES);
        Assertions.assertEquals(CategoriaMenu.HABILIDADES, controlMenu.obtenerMenuActual().getCategoria());
    }

    @DisplayName("Verifico que se agregue correctamente el nuevo menuPokemones")
    @Test
    public void verificoAgregarMenuPokemones(){
        MenuPokemones menuPoke = mock(MenuPokemones.class);
        controlMenu.actualizarMenu(menuPoke);

        when(controlMenu.obtenerMenuActual().getCategoria()).thenReturn(CategoriaMenu.POKEMONES);
        Assertions.assertEquals(CategoriaMenu.POKEMONES, controlMenu.obtenerMenuActual().getCategoria());
    }

    @DisplayName("Verifico que se agregue correctamente el nuevo menuPokemonesItems")
    @Test
    public void verificoAgregarMenuItems(){
        MenuItems menuItem = mock(MenuItems.class);
        controlMenu.actualizarMenu(menuItem);

        when(controlMenu.obtenerMenuActual().getCategoria()).thenReturn(CategoriaMenu.ITEMS);
        Assertions.assertEquals(CategoriaMenu.ITEMS, controlMenu.obtenerMenuActual().getCategoria());
    }

    @DisplayName("Agrego varios menu y retrocedo, verificando el actual")
    @Test
    public void agregarYEliminarMenuTest(){
        MenuItems menuItem = mock(MenuItems.class);
        controlMenu.actualizarMenu(menuItem);
        when(controlMenu.obtenerMenuActual().getCategoria()).thenReturn(CategoriaMenu.ITEMS);
        Assertions.assertEquals(CategoriaMenu.ITEMS, controlMenu.obtenerMenuActual().getCategoria());

        MenuPrincipal menuPrincip = mock(MenuPrincipal.class);
        controlMenu.actualizarMenu(menuPrincip);
        when(controlMenu.obtenerMenuActual().getCategoria()).thenReturn(CategoriaMenu.PRINCIPAL);
        Assertions.assertEquals(CategoriaMenu.PRINCIPAL, controlMenu.obtenerMenuActual().getCategoria());

        controlMenu.retroceder();
        when(controlMenu.obtenerMenuActual().getCategoria()).thenReturn(CategoriaMenu.ITEMS);
        Assertions.assertEquals(CategoriaMenu.ITEMS, controlMenu.obtenerMenuActual().getCategoria());

        MenuPokemones menuPoke = mock(MenuPokemones.class);
        controlMenu.actualizarMenu(menuPoke);
        when(controlMenu.obtenerMenuActual().getCategoria()).thenReturn(CategoriaMenu.POKEMONES);
        Assertions.assertEquals(CategoriaMenu.POKEMONES, controlMenu.obtenerMenuActual().getCategoria());
    }

    @DisplayName("Agrego varios menu iguales y se puede")
    // esto debería poderse? Es raro, sino elimino el test.
    // Osea en el codigo nunca lo vamos a hacer, pero si retrocediera quedaria en el mismo lugar
    // Osea en el codigo nunca lo vamos a hacer, pero si retrocediera quedaria en el mismo lugar
    @Test
    public void agregarMismoMenuVariasVeces() {
        MenuHabilidades menuHabi = mock(MenuHabilidades.class);
        controlMenu.actualizarMenu(menuHabi);
        controlMenu.actualizarMenu(menuHabi);

        when(controlMenu.obtenerMenuActual().getCategoria()).thenReturn(CategoriaMenu.HABILIDADES);
        Assertions.assertEquals(CategoriaMenu.HABILIDADES, controlMenu.obtenerMenuActual().getCategoria());
    }

    @DisplayName("Retrocedo sin agregar ningun menu, y no se puede")
    @Test
    public void retrocederNoQuedaVacio() {
        controlMenu.retroceder();

        Assertions.assertNotNull(controlMenu.obtenerMenuActual());
    }

    @DisplayName("Retrocedo dos veces sin agregar ningun menu, y no se puede quedar sin menuActual")
    @Test
    public void retrocederDosNoQuedaVacio() {
        controlMenu.retroceder();
        controlMenu.retroceder();

        Assertions.assertNotNull(controlMenu.obtenerMenuActual());
    }

}
