package tp1.clases;

public class HabilidadAtaque extends Habilidad {
    //comentario3 ccsd
    final protected Integer poder;
    public HabilidadAtaque(String nombre, Integer usos, Tipo tipo, Integer poder, String info) {
        super(nombre, usos, tipo, info);
        this.poder = poder;
    }

}
