package tp1.clases.modelo;

import java.util.ArrayList;
import java.util.List;

public class AdministradorDeTurnos {
    public List<Jugador> jugadores;
    public int turno;

    public ArrayList<Subscriptor> subscriptores;


    public AdministradorDeTurnos(List<Jugador> jugadores) {
        this.subscriptores = new ArrayList<>();
        this.jugadores = jugadores;
        determinarJugadorIncial();
    }

    public void determinarJugadorIncial() {
        int primero = 0;
        double mayorVelocidad = 0.0;
        for (int i = 0; i < this.jugadores.size(); i++) {
            if (this.jugadores.get(i).getVelocidadPokemonActual() > mayorVelocidad) {
                primero = i;
                mayorVelocidad = this.jugadores.get(i).getVelocidadPokemonActual();
            }
        }

        this.turno = primero;
    }

    public void siguienteTurno() {
        this.turno += 1;
        for (Subscriptor subscriptor: this.subscriptores) {
            subscriptor.Update();
        }
    }
    public void agregarSubscriptor(Subscriptor subscriptor) {
        this.subscriptores.add(subscriptor);
    }

    public void eliminarSubscriptor(Subscriptor subscriptor) {
        this.subscriptores.remove(subscriptor);
    }

    public int getTurno() {
        return this.turno;
    }

    public Jugador getJugadorActual() {
        return this.jugadores.get(this.turno % this.jugadores.size());
    }

    public Jugador getJugadorSiguiente() {
        return this.jugadores.get((this.turno + 1) % this.jugadores.size());
    }

}


