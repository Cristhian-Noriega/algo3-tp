package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.util.Optional;

public abstract class PokemonDecorator extends Pokemon {
    protected Pokemon pokemonDecorado;

    public PokemonDecorator(Pokemon pokemon){
        super(pokemon.getNombre(),
                pokemon.getNivel(),
                pokemon.getTipo(),
                pokemon.getHabilidades(),
                pokemon.getVidaMax(),
                pokemon.getVelocidad(),
                pokemon.getAtaque(),
                pokemon.getDefensa());
        this.pokemonDecorado = pokemon;
        this.estado = pokemon.getEstado();
    }

    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival) {
        return pokemonDecorado.usarHabilidad(numeroHabilidad, rival);
    }

    public void aplicarEfectoEstado(){
        pokemonDecorado.aplicarEfectoEstado();
    }
}
