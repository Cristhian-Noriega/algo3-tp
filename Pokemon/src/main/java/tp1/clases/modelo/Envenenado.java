package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.util.Optional;

public class Envenenado implements EstadosComportamiento{

    private int turnosEnvenenado;
    public Envenenado(int turnosEnvenenado){
        this.turnosEnvenenado = turnosEnvenenado;
    }
    @Override
    public void aplicarEfecto(Pokemon pokemon) {
        if (turnosEnvenenado != 0) {
            double danio = Constantes.cinco * pokemon.getVidaMax();
            pokemon.modificarVida((-1) * danio);
            System.out.printf("%s ha perdido vida por estar envenenado\n", pokemon.getNombre());
        }
        this.turnosEnvenenado++;
    }
    @Override
    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival){
        return Optional.empty();
    }
}
