package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorMismoEstado;

import java.util.Optional;

public class HabilidadEstado extends Habilidad {
    final private Estado estado;

    public HabilidadEstado(String nombre, Integer usos, Tipo tipo, String info, Estado estado) {
        super(nombre, usos, tipo, info, Categoria.ESTADO);
        this.estado = estado;
    }

    @Override
    public Optional<Error> usar() {
        if (this.pokemonRival.getEstados().contains(this.estado)) {
            return Optional.of(new ErrorMismoEstado(this.estado.toString().toLowerCase()));
        }
        this.pokemonRival.setEstado(this.estado);
        this.restarUso();
        return Optional.empty();
    }
}