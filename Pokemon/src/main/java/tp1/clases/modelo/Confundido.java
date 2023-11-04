package tp1.clases.modelo;

public class Confundido implements EstadoComportamiento {

    private int turnosConfundido;

    public Confundido(int turnosConfundido){
        this.turnosConfundido = turnosConfundido;
    }

    @Override
    public void aplicarEfecto(Pokemon pokemon) {
        if (this.turnosConfundido == Constantes.turnosParaCurarseEstadoConfundido){
            pokemon.eliminarEstado(Estado.CONFUNDIDO);
            this.turnosConfundido = 0;
            return;
        }
        this.turnosConfundido++;
    }

    @Override
    public Boolean usarHabilidad(int numeroHabilidad, Pokemon pokemon) {
        boolean seAutolesiona = Random.probabilidad(Constantes.probabilidadDeAutolesionarse);
        if (seAutolesiona){
            double danio = Constantes.porcentajeDeAutolesionConfundido * pokemon.getVidaMax();
            pokemon.modificarVida((-1) * danio);
            System.out.printf("%s esta cofundido y se autolesiono\n", pokemon.getNombre());
        }
        return true;
    }
    @Override
    public int getTurnos() {
        return turnosConfundido;
    }
}
