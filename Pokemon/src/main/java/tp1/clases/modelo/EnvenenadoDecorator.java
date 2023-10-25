package tp1.clases.modelo;

public class EnvenenadoDecorator extends PokemonDecorator {


    public EnvenenadoDecorator(Pokemon pokemon) {
        super(pokemon);
    }

    @Override
    public void aplicarEfectoEstado() {
        double danio = Constantes.cinco * pokemonDecorado.getVidaMax();
        pokemonDecorado.modificarVida((-1)*danio);
    }
}


