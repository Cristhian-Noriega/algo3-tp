package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorEstadoDistintoDeNormal;
import tp1.clases.errores.ErrorIndiceFueraDeRango;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pokemon {
    private final String nombre;
    private final int nivel;
    private Estado estado;
    private final List<Estado> estados;
    private final Tipo tipo;
    private final List<Habilidad> habilidades;

    private final int vidaMax;
    private int vidaActual;
    private double velocidad;
    private double ataque;
    private double defensa;

    public Pokemon(String nombre, int nivel, Tipo tipo,
                   List<Habilidad> habilidades, int vidaMax, double velocidad, double ataque, double defensa) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
        this.estados = new ArrayList<>();
        this.habilidades = habilidades;
        this.vidaMax = vidaMax;
        this.vidaActual = vidaMax;
        this.velocidad = velocidad;
        this.ataque = ataque;
        this.defensa = defensa;
        this.estado = Estado.NORMAL;
    }

    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival){
        if (numeroHabilidad < 0 || numeroHabilidad >= this.habilidades.size()) {
            return Optional.of(new ErrorIndiceFueraDeRango());
        }
        Habilidad habilidad = this.habilidades.get(numeroHabilidad);

        return habilidad.usar();
    }

    public boolean estaMuerto() {
        return this.vidaActual <= 0;
    }

    public void modificarAtaque(double modificador) { this.ataque += modificador; }

    public void modificarDefensa(double modificador) {this.defensa += modificador; }

    public void modificadorVelocidad(double modificador) { this.velocidad += modificador; }

    public Optional<Error> setEstado(Estado estado) {
        if ((this.estado != Estado.NORMAL) && (estado != Estado.NORMAL)) {
            return Optional.of(new ErrorEstadoDistintoDeNormal(this.estado.name()));
        }
        this.estado = estado;
        System.out.println(this.getNombre() + " ha cambiado su estado a \n" + estado.toString().toLowerCase());
        return Optional.empty() ;
    }

    public void modificarVida(double modificador) {
        double nuevoValor = this.vidaActual + modificador;
        if (nuevoValor <= 0.0){
            nuevoValor = 0;
        }
        this.vidaActual = (int) Math.min(nuevoValor, (double) vidaMax);
    }

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

    public double getAtaque(){
        return this.ataque;
    }

    public double getDefensa(){
        return this.defensa;
    }

    public double getVelocidad(){
        return this.velocidad;
    }

    public Estado getEstado() {
        return this.estado;
    }
}