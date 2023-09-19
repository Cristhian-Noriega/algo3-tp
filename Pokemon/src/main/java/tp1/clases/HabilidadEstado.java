package tp1.clases;

public class HabilidadEstado extends Habilidad {
    final private Estado estado;

    public HabilidadEstado(String nombre, Integer usos, TipoPokemon.Tipo tipo, String info, Estado estado) {
        super(nombre, usos, tipo, info);
        this.estado = estado;
    }
    //comentario
    public Estado getEstado() {
        return estado;
    }
}