package tp1.clases.modelo;

public class HabilidadEstadistica extends Habilidad {
    final private Estadisticas estadistica;
    final private boolean contraRival;
    public HabilidadEstadistica(String nombre, Integer usos, Tipo tipo, String info, Estadisticas estadistica, boolean contraRival) {
        super(nombre, usos, tipo, info);
        this.estadistica = estadistica;
        this.contraRival = contraRival;
    }

    public Estadisticas getEstadistica() {
        return estadistica;
    }

    public boolean isContraRival() {
        return contraRival;
    }
}