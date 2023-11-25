package tp1.clases.modelo;

import tp1.clases.errores.*;
import tp1.clases.errores.Error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import tp1.clases.errores.Error;

import tp1.clases.errores.ErrorHabilidadSinUsos;
import tp1.clases.errores.ErrorIndiceFueraDeRango;
import tp1.clases.errores.ErrorMismoEstado;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

 public class Pokemon implements Serializable {
    private final String nombre;
    private final int nivel;
    private final List<Estado> estados;
    private final AdministradorDeEstados administradorDeEstados;
    private final Tipo tipo;
    private final List<Habilidad> habilidades;
    private final int vidaMax;
    private Integer vidaActual;
    private double velocidad;
    private double ataque;
    private double defensa;

    private final Integer id;

     @JsonCreator
     public Pokemon(@JsonProperty("nombre") String nombre, @JsonProperty("nivel") int nivel, @JsonProperty("tipo") Tipo tipo,
                    @JsonProperty("habilidades") List<Habilidad> habilidades, @JsonProperty("vidaMax") int vidaMax,
                    @JsonProperty("velocidad") double velocidad, @JsonProperty("ataque") double ataque,
                    @JsonProperty("defensa") double defensa, @JsonProperty("id") Integer id) {
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
         this.id = id;
     }

    public Optional<Error> usarHabilidad(Habilidad habilidad, Pokemon rival, AdministradorDeClima administradorDeClima){
        if (!this.habilidades.contains(habilidad)) {
            return Optional.of(new ErrorHabilidadErronea(this.nombre, habilidad.getNombre()));
        }

        if (habilidad.sinUsosDisponibles()){
            return Optional.of(new ErrorHabilidadSinUsos(habilidad.getNombre()));
        }
        Boolean pudoUsarse = this.administradorDeEstados.usarHabilidadEstados(habilidad, this);
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

    public Integer getVida(){
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

    public Integer getId(){
         return this.id;
    }
}