package tp1.clases.modelo;

import java.util.List;

public class Pokemon {
    private final String nombre;
    private final int nivel;
    private Estado estado;
    private final Tipo tipo;
    private final List<Habilidad> habilidades;
    private int vidaMax;
    private int vidaActual;
    private int velocidad;
    private int ataque;
    private int defensa;

    public Pokemon(String nombre, int nivel, Tipo tipo,
                   List<Habilidad> habilidades, int vidaMax, int velocidad, int ataque, int defensa) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
        this.habilidades = habilidades;
        this.vidaMax = vidaMax;
        this.vidaActual = vidaMax;
        this.velocidad = velocidad;
        this.ataque = ataque;
        this.defensa = defensa;
    }


    public boolean estaMuerto() {
        return this.vidaActual <= 0;
    }

    public void modificarAtaque(int modificador) { this.ataque += modificador; }

    public void modificarDefensa(int modificador) {this.defensa += modificador; }

    public void modificadorVelocidad(int modificador) { this.velocidad += modificador; }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void modificarVida(int modificador) { this.vidaActual += modificador; }

    public List<Habilidad> getHabilidades() { return this.habilidades; }

    public String getNombre() {
        return this.nombre;
    }
    public Tipo getTipo() { return this.tipo; }

    public int getNivel() {
        return this.nivel;
    }

    public int getVida(){
        return this.vidaActual;
    }

    public int getVidaMax() { return this.vidaMax; }

    public int getAtaque(){
        return this.ataque;
    }
    public int getDefensa(){
        return this.defensa;
    }

    public int getVelocidad(){
        return this.velocidad;
    }

    public Estado getEstado() {
        return this.estado;
    }
}