package tp1.clases.modelo;

public class EnvenenadoDecorator extends PokemonDecorator {


    public EnvenenadoDecorator(Pokemon pokemon) {
        super(pokemon);
    }

    public void aplicarEfectoEstado() {
        double danio = Constantes.cinco * pokemonDecorado.getVidaMax();
        super.modificarVida((-1)*danio);
        System.out.printf("%s ha perdido vida por estar envenenado\n",this.pokemonDecorado.getNombre());
    }
}


