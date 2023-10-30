package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorNoPuedeAtacarDormido;

import java.util.Optional;

public class Confundido implements EstadosComportamiento{

    private int turnosConfundido = 0;

    @Override
    public void aplicarEfecto(Pokemon pokemon) {
        if (this.turnosConfundido == 3){
            pokemon.eliminarEstado(Estado.CONFUNDIDO);
            this.turnosConfundido = 0;
            return;
        }
        this.turnosConfundido++;
    }

    @Override
    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon pokemon) {
        boolean seAutolesiona = Habilidad.probabilidad(1.0/3.0);
        if (seAutolesiona){
            double danio = 0.15 * pokemon.getVidaMax();
            pokemon.modificarVida((-1) * danio);
            System.out.printf("%s esta cofundido y se autolesiono\n", pokemon.getNombre());
        }
        return Optional.empty();
    }
}
