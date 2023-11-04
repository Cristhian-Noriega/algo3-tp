package tp1.clases.modelo;

public class Envenenado implements EstadoComportamiento {

    private int turnosEnvenenado;
    public Envenenado(int turnosEnvenenado){
        this.turnosEnvenenado = turnosEnvenenado;
    }
    @Override
    public void aplicarEfecto(Pokemon pokemon) {
        if (turnosEnvenenado != 0) {
            double danio = Constantes.porcentajeDeEnvenamiento * pokemon.getVidaMax();
            pokemon.modificarVida((-1) * danio);
            System.out.printf("%s ha perdido vida por estar envenenado\n", pokemon.getNombre());
        }
        this.turnosEnvenenado++;
    }
    @Override
    public Boolean usarHabilidad(int numeroHabilidad, Pokemon rival){
        return true;
    }

    @Override
    public int getTurnos(){
        return turnosEnvenenado;
    }
}
