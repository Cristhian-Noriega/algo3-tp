//package tp1.clases.modelo;
//
//import tp1.clases.errores.Error;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//public abstract class PokemonDecorator extends Pokemon {
//    protected Pokemon pokemonDecorado;
//
//    public PokemonDecorator(Pokemon pokemon){
//        super(pokemon.getNombre(),
//                pokemon.getNivel(),
//                pokemon.getTipo(),
//                pokemon.getHabilidades(),
//                pokemon.getVidaMax(),
//                pokemon.getVelocidad(),
//                pokemon.getAtaque(),
//                pokemon.getDefensa());
//        this.pokemonDecorado = pokemon;
//        this.vidaActual = pokemon.getVida();
//        this.estados = pokemon.getEstados();
//        this.decoradores = pokemon.getDecoradores();
//
//    }
//
//
//    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival) {
//        return pokemonDecorado.usarHabilidad(numeroHabilidad, rival);
//    }
//
//    public abstract void aplicarEfectoEstado();
//
////    public void removeDecorator(Class<? extends PokemonDecorator> decoratorClass) {
////        if (decoratorClass.isInstance(this)) {
////            this.pokemonDecorado = this.pokemonDecorado.getPokemonDecorado();
////        } else if (this.pokemonDecorado instanceof PokemonDecorator) {
////            ((PokemonDecorator) this.pokemonDecorado).removeDecorator(decoratorClass);
////        }
////    }
//
//    private void setPokemonDecorado(Pokemon pokemon) {
//        this.pokemonDecorado = pokemon;
//    }
//}
