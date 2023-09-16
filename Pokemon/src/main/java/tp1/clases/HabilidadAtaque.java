package tp1.clases;

public class HabilidadAtaque extends Habilidad {
    final protected Integer poder;
    public HabilidadAtaque(String nombre, Integer usos, Tipo tipo, Integer poder, String info) {
        super(nombre, usos, tipo, info);
        this.poder = poder;
    }

}
