package tp1.clases.modelo;

import tp1.clases.errores.Error;

public class HabilidadEstado extends Habilidad {
    final private Estado estado;

    public HabilidadEstado(String nombre, Integer usos, Tipo tipo, String info, Estado estado) {
        super(nombre, usos, tipo, info);
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    @Override
    public Error usar(Pokemon propio, Pokemon ajeno) {
        return ajeno.setEstado(this.estado);
    }
}