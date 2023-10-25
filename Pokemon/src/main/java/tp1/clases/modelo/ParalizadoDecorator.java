package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorNoPuedeUsarHabilidad;

import java.util.Optional;

public class ParalizadoDecorator extends PokemonDecorator{
    public ParalizadoDecorator(Pokemon pokemon){
        super(pokemon);
    }

    @Override
    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival){
        if (!Habilidad.probabilidad(Constantes.probabilidadParalizado)){
            return Optional.of(new ErrorNoPuedeUsarHabilidad(this.getNombre()));
        }
        return super.usarHabilidad(numeroHabilidad, rival);
    }

    @Override
    public void aplicarEfectoEstado() { //dummy
    }
}
