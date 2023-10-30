package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.util.Optional;

public class Envenenado implements EstadosComportamiento{


    @Override
    public void aplicarEfecto(Pokemon pokemon) {
        double danio = Constantes.cinco * pokemon.getVidaMax();
        pokemon.modificarVida((-1)*danio);
        System.out.printf("%s ha perdido vida por estar envenenado\n",pokemon.getNombre());
    }
    @Override
    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival){
        return Optional.empty();
    }
}
