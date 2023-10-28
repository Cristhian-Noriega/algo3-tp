package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorHabilidadSinUsos;
import tp1.clases.errores.ErrorNoPuedeAtacarDormido;

import java.util.Optional;

public class DormidoDecorator extends PokemonDecorator {

    private int turnosDormido;

    public DormidoDecorator(Pokemon pokemon) {
        super(pokemon);
        this.turnosDormido = 0;
    }

    @Override
    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival) {
        Habilidad habilidad = pokemonDecorado.getHabilidades().get(numeroHabilidad);
        if (habilidad.sinUsosDisponibles()){
            return Optional.of(new ErrorHabilidadSinUsos(habilidad.getNombre()));
        }
        if (habilidad.getCategoria() == Categoria.ATAQUE) {
            return Optional.of(new ErrorNoPuedeAtacarDormido(this.getNombre()));
        } else {
            return super.usarHabilidad(numeroHabilidad, rival);
        }
    }

    public void aplicarEfectoEstado() {
        double probabibilidadDespertar = Constantes.veinticinco + Constantes.veinticinco * this.turnosDormido;
        if (Habilidad.probabilidad(probabibilidadDespertar)){
            super.eliminarEstado(Estado.DORMIDO);
        }
        this.turnosDormido++;
    }
}
