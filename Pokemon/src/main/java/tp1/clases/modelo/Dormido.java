package tp1.clases.modelo;

public class Dormido implements EstadoComportamiento {
    private int turnosDormido;

    public Dormido(int turnosDormido){
        this.turnosDormido = turnosDormido;
    }

    @Override
    public int getTurnos(){
        return turnosDormido;
    }

    @Override
    public void aplicarEfecto(Pokemon pokemon) {
        double probabibilidadDespertar = Constantes.probabilidadDespertar + Constantes.probabilidadDespertar * this.turnosDormido;
        if ((this.turnosDormido > 0) && (Random.probabilidad(probabibilidadDespertar))){
            pokemon.eliminarEstado(Estado.DORMIDO);
            this.turnosDormido = 0;
            return;
        }
        this.turnosDormido++;
    }

    @Override
    public Boolean usarHabilidad(Habilidad habilidad, Pokemon pokemon) {
        habilidad.restarUso();
        return false;
    }

}
