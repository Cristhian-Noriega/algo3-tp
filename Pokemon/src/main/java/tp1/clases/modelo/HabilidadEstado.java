package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorHabilidadSinUsos;

import java.util.Optional;

public class HabilidadEstado extends Habilidad {
    final private Estado estado;

    public HabilidadEstado(String nombre, Integer usos, Tipo tipo, String info, Estado estado) {
        super(nombre, usos, tipo, info);
        this.estado = estado;
    }

    // no se usa
    public Estado getEstado() {
        return estado;
    }

    @Override
    public Optional<Error> usar(Pokemon propio, Pokemon ajeno) {
        if (!this.quedanUsos()){
            return Optional.of(new ErrorHabilidadSinUsos(this.nombre));
        }
        return ajeno.setEstado(this.estado);
    }
}