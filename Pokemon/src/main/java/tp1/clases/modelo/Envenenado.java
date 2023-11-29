package tp1.clases.modelo;

public class Envenenado implements EstadoComportamiento {

    private int turnosEnvenenado;
    public Envenenado(int turnosEnvenenado){
        this.turnosEnvenenado = turnosEnvenenado;
    }
    @Override
    public void aplicarEfecto(Pokemon pokemon) {
        double danio = Constantes.porcentajeDeEnvenamiento * pokemon.getVidaMax();
        pokemon.modificarVida((-1) * danio);
        this.turnosEnvenenado++;
    }
    @Override
    public Boolean usarHabilidad(Habilidad habilidad, Pokemon rival){
        return true;
    }

    @Override
    public int getTurnos(){
        return turnosEnvenenado;
    }
}
