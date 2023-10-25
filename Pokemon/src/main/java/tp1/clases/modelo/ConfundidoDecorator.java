package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorNoPuedeUsarHabilidad;

import java.util.Optional;

public class ConfundidoDecorator extends PokemonDecorator{

    private int turnosConfundido;

    public ConfundidoDecorator(Pokemon pokemon) {
        super(pokemon);
    }


    @Override
    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival){
        boolean seAutolesiona = Habilidad.probabilidad(1.0/3.0);
        if (seAutolesiona){
            double danio = 0.15 * pokemonDecorado.getVidaMax();
            pokemonDecorado.modificarVida((-1) * danio);
            System.out.printf("%s esta cofundido y se autolesiono. Perdio %.2f de vida", pokemonDecorado.getNombre(), danio);
        }
        return super.usarHabilidad(numeroHabilidad, rival);
    }
    @Override
    public void aplicarEfectoEstado() {
        this.turnosConfundido++;
        if (this.turnosConfundido == 3){
            pokemonDecorado.eliminarEstado(Estado.CONFUNDIDO);
        }
    }
}
