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
    public Optional<Error> usar(Pokemon propio, Pokemon ajeno) {
        if (this.sinUsosDisponibles()){
            return Optional.of(new ErrorHabilidadSinUsos(this.nombre));
        }
        super.usos -= 1;
        return ajeno.setEstado(this.estado);
    }
}