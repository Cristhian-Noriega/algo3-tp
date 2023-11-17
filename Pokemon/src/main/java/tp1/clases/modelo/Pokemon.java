package tp1.clases.modelo;

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
    private final HashMap<Estado, EstadoComportamiento> estadosComportamientos;
    private final List<Estado> estadosParaEliminar;
    private final Tipo tipo;
    private final List<Habilidad> habilidades;
    private final int vidaMax;
    private int vidaActual;
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
         this.estadosParaEliminar = new ArrayList<>();
         this.estadosComportamientos = new HashMap<>();
         this.estadosComportamientos.put(Estado.NORMAL, null);
         this.habilidades = habilidades;
         this.vidaMax = vidaMax;
         this.vidaActual = vidaMax;
         this.velocidad = velocidad;
         this.ataque = ataque;
         this.defensa = defensa;
         this.id = id;
     }

    public Optional<Error> usarHabilidad(int numeroHabilidad, Pokemon rival, AdministradorDeClima administradorDeClima){
        if (numeroHabilidad < 0 || numeroHabilidad >= this.habilidades.size()) {
            return Optional.of(new ErrorIndiceFueraDeRango());
        }

        Habilidad habilidad = this.habilidades.get(numeroHabilidad);
        if (habilidad.sinUsosDisponibles()){
            return Optional.of(new ErrorHabilidadSinUsos(habilidad.getNombre()));
        }

        Boolean pudoUsarse = this.usarHabilidadEstados(numeroHabilidad, this);
        if(!pudoUsarse){
            return Optional.empty();
        }

        habilidad.setAmbiente(administradorDeClima, List.of(this, rival));
        return habilidad.usar();
    }

    private Boolean usarHabilidadEstados(int numeroHabilidad, Pokemon pokemon) {
        for (Estado estado : this.estados) {
            EstadoComportamiento estadoComportamiento = this.estadosComportamientos.get(estado);
            if (estadoComportamiento != null) {
                boolean puedeUsarHabilidad = estadoComportamiento.usarHabilidad(numeroHabilidad, pokemon);
                if (!puedeUsarHabilidad) {
                    return false;
                }
            }
        }
        return true;
    }

    public void aplicarEfectoEstados(){

        for (Estado estado: this.estados){
            EstadoComportamiento estadoComportamiento = this.estadosComportamientos.get(estado);
            if (estadoComportamiento != null) {
                estadoComportamiento.aplicarEfecto(this);

            }
        }
        this.estados.removeAll(this.estadosParaEliminar);
        this.estadosParaEliminar.clear();
        if (this.estados.isEmpty()){
            this.estados.add(Estado.NORMAL);
        }
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

        EstadoComportamiento estadoComportamiento = estadosComportamientos.get(estado);
        if (estadoComportamiento == null) {
            estadoComportamiento = EstadoFacotory.crearEstado(estado);
            estadosComportamientos.put(estado, estadoComportamiento);
        }
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
        this.estadosParaEliminar.add(estado);
        System.out.println(this.nombre + " ha dejado de estar " + estado.name().toLowerCase());
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

    public Integer getId(){
         return this.id;
    }
}