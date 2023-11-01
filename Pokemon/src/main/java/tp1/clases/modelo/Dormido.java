package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorNoPuedeAtacarDormido;
import tp1.clases.errores.ErrorNoPuedeUsarHabilidadParalizado;

import java.util.Optional;

public class Dormido implements EstadosComportamiento {

    private int turnosDormido;

    public Dormido(int turnosDormido){
        this.turnosDormido = turnosDormido;
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
    public Boolean usarHabilidad(int numeroHabilidad, Pokemon pokemon) {
        Habilidad habilidad = pokemon.getHabilidades().get(numeroHabilidad);
        System.out.printf("%s no puede atacar estando dormido\n", pokemon.getNombre());
        habilidad.restarUso();
        return false;
    }
}
