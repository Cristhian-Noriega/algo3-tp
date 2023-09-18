package tp1.clases;

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

    public int calcularDanioAtaque(Pokemon atacante, Habilidad ataque, Pokemon defensor) {
        int nivelAtacante = atacante.getNivel();
        int poderAtaque = ataque.getPoder();
        int ataqueAtacante = atacante.getAtaque();
        int defensaDefensor = defensor.getDefensa();
        double tipoAtaqueEfectividad = tipoAtaque.getEfectividad(ataque.getTipo(), defensor.getTipo());
        double mismoTipo = (atacante.getTipo() == ataque.getTipo())? 1.5: 1;
        //me falta la variable random :/ ( multiplicacion por un numero
        //entero aleatorio uniformemente distribuido entre 217 y 255 (inclusive),
        //seguido de una division entera por 255)

        int danio = (int) (((2 * nivelAtacante * poderAtaque * (ataqueAtacante / defensaDefensor) / 5 + 2)   / 50 ) * tipoAtaqueEfectividad * mismoTipo);

        return danio;
    }


}
