package tp1.clases.modelo;

public class Paralizado implements EstadoComportamiento {
    private int turnosParalizado;

    public Paralizado(int turnosParalizado){
        this.turnosParalizado = turnosParalizado;
    }

    @Override
    public void aplicarEfecto(Pokemon pokemon) {}

    @Override
    public Boolean usarHabilidad(Habilidad habilidad, Pokemon pokemon){
        if (!Random.probabilidad(Constantes.probabilidadParalizado)) {
            habilidad.restarUso();
            return false;
        }
        return true;
    }

    @Override
    public int getTurnos(){
        return turnosParalizado;
    }
}


