package tp1.clases;

public class HabilidadEstado extends Habilidad {
    //comentario2
    public enum Estado {
        DORMIDO, PARALIZADO, ENVENENADO
    }
    final private Estado estado;

    public HabilidadEstado(String nombre, Integer usos, Tipo tipo, String info, Estado estado) {
        super(nombre, usos, tipo, info);
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }
}