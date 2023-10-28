package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.util.Optional;

public abstract class Habilidad {
    final protected String nombre;
    protected Integer usos;
    final protected Tipo tipo;
    final protected String info;
    final protected Categoria categoria;

    public Habilidad(String nombre, Integer usos, Tipo tipo, String info, Categoria categoria) {
        this.nombre = nombre;
        this.usos = usos;
        this.tipo = tipo;
        this.info = info;
        this.categoria = categoria;
    }

    public String getNombre(){
        return nombre;
    }

    public Integer getUsos(){
        return usos;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getInfo() {
        return info;
    }

    public abstract Optional<Error> usar(Pokemon propio, Pokemon ajeno);

    public boolean sinUsosDisponibles() {
        return this.usos < 1;
    }

    public static boolean probabilidad(double probabilidad) {
        double rand =  Math.random();
        return rand <= probabilidad;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void restarUso() { this.usos--;}

}