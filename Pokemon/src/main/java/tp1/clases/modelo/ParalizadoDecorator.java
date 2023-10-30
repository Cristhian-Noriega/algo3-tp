//package tp1.clases.modelo;
//
//import tp1.clases.errores.Error;
//import tp1.clases.errores.ErrorHabilidadSinUsos;
//import tp1.clases.errores.ErrorNoPuedeUsarHabilidadParalizado;
//
//import java.util.Optional;
//
//public class ParalizadoDecorator extends PokemonDecorator{
//    public ParalizadoDecorator(Pokemon pokemon){
//        super(pokemon);
//
//    }
//
//    @Override
//    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival) {
//        Habilidad habilidad = pokemonDecorado.getHabilidades().get(numeroHabilidad);
//        if (habilidad.sinUsosDisponibles()){
//            return Optional.of(new ErrorHabilidadSinUsos(habilidad.getNombre()));
//        }
//        if (!Habilidad.probabilidad(Constantes.probabilidadParalizado)) {
//            habilidad.restarUso();
//            return Optional.of(new ErrorNoPuedeUsarHabilidadParalizado(this.getNombre()));
//        }
//        return super.usarHabilidad(numeroHabilidad, rival);
//
//    }
//
//    public void aplicarEfectoEstado(){
////        this.vidaActual = pokemonDecorado.getVida();
//        pokemonDecorado.aplicarEfectoEstado();}
//
//}