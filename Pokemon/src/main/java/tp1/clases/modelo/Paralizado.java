package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorNoPuedeUsarHabilidadParalizado;

import java.util.Optional;

public class Paralizado implements EstadosComportamiento{


    @Override
    public void aplicarEfecto(Pokemon pokemon) {
    }
    @Override
    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon pokemon){
        Habilidad habilidad = pokemon.getHabilidades().get(numeroHabilidad);
        if (!Habilidad.probabilidad(Constantes.probabilidadParalizado)) {
            habilidad.restarUso();
            return Optional.of(new ErrorNoPuedeUsarHabilidadParalizado(pokemon.getNombre()));
        }
        return Optional.empty();

    }
}


