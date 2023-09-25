package tp1.clases.modelo;

public class Batalla {
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;


    public Batalla(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugadorActual = determinarJugadorInicial();
    }

    private Jugador determinarJugadorInicial() {
        if (this.jugador1.getPokemonActual().getVelocidad() > this.jugador2.getPokemonActual().getVelocidad()) {
            return this.jugador1;
        } else {
            return this.jugador2;
        }
    }
    public Jugador obtenerGanador() {
        if (this.jugador1.tienePokemonesConVida() && this.jugador2.tienePokemonesConVida()){
            return null;
        }
        if (!this.jugador1.tienePokemonesConVida()) {
            return this.jugador2;
        }else{
            return this.jugador1;
        }
    }

    public void cambiarTurno() {
        if (this.jugadorActual == this.jugador1) {
            this.jugadorActual = this.jugador2;
        } else {
            this.jugadorActual = this.jugador1;
        }
    }
}