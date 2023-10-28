package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorHabilidadSinUsos;

import java.util.Optional;

public class HabilidadEstado extends Habilidad {
    final private Estado estado;

    public HabilidadEstado(String nombre, Integer usos, Tipo tipo, String info, Estado estado) {
        super(nombre, usos, tipo, info, Categoria.ESTADO);
        this.estado = estado;
    }

    @Override
    public Optional<Error> usar() {
        if (this.sinUsosDisponibles()){
            return Optional.of(new ErrorHabilidadSinUsos(this.nombre));
        }

        this.pokemonRival.setEstado(this.estado);
        System.out.println(this.pokemonRival.getNombre() + " ha sido " + this.estado.toString());

        this.restarUso();
        return Optional.empty();
    }
}