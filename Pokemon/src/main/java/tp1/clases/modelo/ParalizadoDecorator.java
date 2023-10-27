package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorNoPuedeUsarHabilidadParalizado;

import java.util.Optional;

public class ParalizadoDecorator extends PokemonDecorator{
    public ParalizadoDecorator(Pokemon pokemon){
        super(pokemon);
    }

    @Override
    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival) {
        if (!Habilidad.probabilidad(Constantes.probabilidadParalizado)) {
            return Optional.of(new ErrorNoPuedeUsarHabilidadParalizado(this.getNombre()));
        } else {
            return super.usarHabilidad(numeroHabilidad, rival);
        }
    }

    public void aplicarEfectoEstado() { //dummy
    }
}
