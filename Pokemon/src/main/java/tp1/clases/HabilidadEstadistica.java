package tp1.clases;

public class HabilidadEstadistica extends Habilidad {
    public enum Estadisticas {
        BAJAR_VELOCIDAD, BAJAR_ATAQUE, BAJAR_DEFENSA, SUBIR_VELOCIDAD, SUBIR_ATAQUE, SUBIR_DEFENSA, SUBIR_VIDA
    }
    final private Estadisticas estadistica;
    public HabilidadEstadistica(String nombre, Integer usos, Tipo tipo, String info, Estadisticas estadistica) {
        super(nombre, usos, tipo, info);
        this.estadistica = estadistica;
    }

    public Estadisticas getEstadistica() {
        return estadistica;
    }
}
