package tp1.clases.modelo;

import tp1.clases.errores.Error;

import tp1.clases.errores.ErrorHabilidadSinUsos;
import tp1.clases.errores.ErrorIndiceFueraDeRango;
import tp1.clases.errores.ErrorMismoEstado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

 public class Pokemon {
    private final String nombre;
    private final int nivel;
    private final List<Estado> estados;
    private final AdministradorDeEstados administradorDeEstados;
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
         this.estados.add(Estado.NORMAL);
         this.administradorDeEstados = new AdministradorDeEstados(this);
         this.habilidades = habilidades;
         this.vidaMax = vidaMax;
         this.vidaActual = vidaMax;
         this.velocidad = velocidad;
         this.ataque = ataque;
         this.defensa = defensa;
     }

    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival, AdministradorDeClima administradorDeClima){
        if (numeroHabilidad < 0 || numeroHabilidad >= this.habilidades.size()) {
            return Optional.of(new ErrorIndiceFueraDeRango());
        }

        Habilidad habilidad = this.habilidades.get(numeroHabilidad);
        if (habilidad.sinUsosDisponibles()){
            return Optional.of(new ErrorHabilidadSinUsos(habilidad.getNombre()));
        }

        Boolean pudoUsarse = this.administradorDeEstados.usarHabilidadEstados(numeroHabilidad, this);
        if(!pudoUsarse){
            return Optional.empty();
        }

        habilidad.setAmbiente(administradorDeClima, List.of(this, rival));
        return habilidad.usar();
    }

    public void aplicarEfectoEstados(){
         this.administradorDeEstados.aplicarEfectoEstados();
    }

    public boolean estaMuerto() {
        return this.vidaActual <= 0;
    }

    public void modificarAtaque(double modificador) { this.ataque += modificador; }

    public void modificarDefensa(double modificador) {this.defensa += modificador; }

    public void modificadorVelocidad(double modificador) { this.velocidad += modificador; }

    public Optional<Error> setEstado(Estado estado) {
        if (estados.contains(estado)){
            return Optional.of(new ErrorMismoEstado(estado.name()));
        }
        if ((estado == Estado.NORMAL) || (estados.contains(Estado.NORMAL))){
            estados.clear();
        }
        estados.add(estado);
        this.administradorDeEstados.setEstado(estado);
        System.out.println(this.getNombre() + " ahora esta " + estado.name().toLowerCase());
        return Optional.empty() ;
    }


    public void modificarVida(double modificador) {
        double nuevoValor = this.vidaActual + modificador;
        if (nuevoValor <= 0.0) {
            nuevoValor = 0;
        }
        this.vidaActual = (int) Math.min(nuevoValor, (double) vidaMax);
    }


    public void eliminarEstado(Estado estado) {
        this.administradorDeEstados.eliminarEstado(estado);
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

    public String getEstadosString() {
        return this.estados.stream()
                .map(Enum::name)
                .collect(Collectors.joining(" "));
    }

    public List<Estado> getEstados(){
        return this.estados;
    }
}