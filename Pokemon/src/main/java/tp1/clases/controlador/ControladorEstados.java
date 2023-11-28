package tp1.clases.controlador;

import tp1.clases.modelo.AdministradorDeTurnos;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Pokemon;
import tp1.clases.modelo.Subscriptor;

import java.util.ArrayList;
import java.util.List;

public class ControladorEstados implements Subscriptor {
    private AdministradorDeTurnos administradorDeTurnos;
    private ArrayList<Pokemon> pokemonesEnBatalla;

    public ControladorEstados() { this.pokemonesEnBatalla = new ArrayList<>();}

    public void inicializar(Batalla batalla){
        this.administradorDeTurnos = batalla.getAdministradorTurnos();
        this.pokemonesEnBatalla.add(batalla.getJugadorActual().getPokemonActual());
        this.pokemonesEnBatalla.add(batalla.getJugadorSiguiente().getPokemonActual());
        this.administradorDeTurnos.agregarSubscriptor(this);
    }

    @Override
    public void Update(){
        for (Pokemon pokemon: this.pokemonesEnBatalla){
            pokemon.aplicarEfectoEstados();
        }
    }

    public void desuscribirse() {
        this.administradorDeTurnos.eliminarSubscriptor(this);
    }
}
