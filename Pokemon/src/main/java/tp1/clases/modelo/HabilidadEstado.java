package tp1.clases.modelo;

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
    public void usar(Pokemon propio, Pokemon ajeno) {
     ajeno.setEstado(this.estado);
    }
}