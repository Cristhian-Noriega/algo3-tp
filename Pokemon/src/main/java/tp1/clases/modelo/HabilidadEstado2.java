package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorHabilidadSinUsos;

import java.util.Optional;

public class HabilidadEstado2 extends Habilidad2 {
    final private Estado estado;

    public HabilidadEstado2(String nombre, Integer usos, Tipo tipo, String info, Estado estado) {
        super(nombre, usos, tipo, info, Categoria.ESTADO);
        this.estado = estado;
    }

    @Override
    public Optional<Error> usar() {
        if (this.sinUsosDisponibles()){
            return Optional.of(new ErrorHabilidadSinUsos(this.nombre));
        }
        if (this.pokemonAtacante.puedeUsarHabilidad()) {
            this.pokemonRival.setEstado(this.estado);
            System.out.println(this.pokemonRival.getNombre() + " ha sido " + this.estado.toString());
        } else {
            System.out.println(this.pokemonAtacante.getNombre() + " no puede usar habilidad por su estado"); //TODO: mejorar este mensaje
        }

        this.restarUso();
        return Optional.empty();
    }
}