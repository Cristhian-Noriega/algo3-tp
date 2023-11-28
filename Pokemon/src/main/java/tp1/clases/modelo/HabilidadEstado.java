package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorMismoEstado;

import java.util.Optional;

public class HabilidadEstado extends Habilidad {
    final private Estado estado;

    public HabilidadEstado(String nombre, Integer usos, Tipo tipo, String info, Estado estado, Integer id) {
        super(nombre, usos, tipo, info, Categoria.ESTADO, id);
        this.estado = estado;
        this.infoHabilidad.setEstadoModificado(estado);
        this.infoHabilidad.setJugadorAfectado(JugadorEnum.RIVAL);
    }

    @Override
    public Optional<Error> usar() {
        if (this.pokemonRival.getEstados().contains(this.estado)) {
            Error error = new ErrorMismoEstado(this.estado.toString().toLowerCase());
            return Optional.of(error);
        }
        this.pokemonRival.setEstado(this.estado);
        this.restarUso();
        return Optional.empty();
    }
}