package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorEstadoDistintoDeNormal;
import tp1.clases.errores.ErrorHabilidadSinUsos;
import tp1.clases.errores.ErrorIndiceFueraDeRango;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Pokemon {
    private final String nombre;
    private final int nivel;
    List<Estado> estados;
    private final Tipo tipo;
    private final List<Habilidad> habilidades;

    private final int vidaMax;
    int vidaActual;
    private double velocidad;
    private double ataque;
    private double defensa;

    public Pokemon(String nombre, int nivel, Tipo tipo,
                   List<Habilidad> habilidades, int vidaMax, double velocidad, double ataque, double defensa) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
        this.estados = new ArrayList<>();
        this.estados.add(Estado.NORMAL);
        this.habilidades = habilidades;
        this.vidaMax = vidaMax;
        this.vidaActual = vidaMax;
        this.velocidad = velocidad;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival){
        if (numeroHabilidad < 0 || numeroHabilidad >= this.habilidades.size()) {
            return Optional.of(new ErrorIndiceFueraDeRango());
        }
        Habilidad habilidad = this.habilidades.get(numeroHabilidad);
        if (habilidad.sinUsosDisponibles()){
            return Optional.of(new ErrorHabilidadSinUsos(habilidad.getNombre()));
        }
        return habilidad.usar(this, rival);
    }

    public boolean estaMuerto() {
        return this.vidaActual <= 0;
    }

    public void modificarAtaque(double modificador) { this.ataque += modificador; }

    public void modificarDefensa(double modificador) {this.defensa += modificador; }

    public void modificadorVelocidad(double modificador) { this.velocidad += modificador; }

    public Optional<Error> setEstado(Estado estado) {
        if (estado == Estado.NORMAL) {
            estados.clear();
            estados.add(estado);
        } else if (estados.contains(estado)){
            return Optional.of(new ErrorEstadoDistintoDeNormal(estado.name()));
        } else if (estados.contains(Estado.NORMAL)){
            estados.clear();
            estados.add(estado);
        }else{
            estados.add(estado);
        }
        System.out.println(this.getNombre() + " ahora esta " + estado.name());
        return Optional.empty() ;
    }

    public void aplicarEfectoEstado(){
    }

    public void modificarVida(double modificador) {
        double nuevoValor = this.vidaActual + modificador;
        if (nuevoValor <= 0.0){
            nuevoValor = 0;
        }
        this.vidaActual = (int) Math.min(nuevoValor, (double) vidaMax);
    }


    public boolean tieneEstado(Estado estado){
        return this.estados.contains(estado);
    }

    public void eliminarEstado(Estado estado){
        this.estados.remove(estado);
        System.out.println(this.nombre + " ha dejado de estar " + estado.name());
        if (this.estados.isEmpty()){
            this.estados.add(Estado.NORMAL);
        }
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
    public List<Estado> getEstados() {
        return this.estados;
    }

    public String getEstadosString() {
        return this.estados.stream()
                .map(Enum::name)
                .collect(Collectors.joining(" "));
    }
}