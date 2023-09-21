package tp1.clases;

public abstract class Habilidad {
    //atributos:
    final protected String nombre;
    final protected Integer usos;
    final protected TipoPokemon.Tipo tipo;
    final protected String info;

    //constructor:
    public Habilidad(String nombre, Integer usos, TipoPokemon.Tipo tipo, String info) {
        this.nombre = nombre;
        this.usos = usos;
        this.tipo = tipo;
        this.info = info;
    }

    //getters:
    public String getNombre(){
        return nombre;
    }

    public Integer getUsos(){
        return usos;
    }

    public TipoPokemon.Tipo getTipo() {
        return tipo;
    }

    public String getInfo() {
        return info;
    }
}
