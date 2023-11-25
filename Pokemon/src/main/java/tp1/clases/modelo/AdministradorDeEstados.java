package tp1.clases.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdministradorDeEstados {

    private Pokemon pokemon;
    private final HashMap<Estado, EstadoComportamiento> estadosComportamientos;
    private List<Estado> estadosParaEliminar;


    public AdministradorDeEstados(Pokemon pokemon){
        this.pokemon = pokemon;
        this.estadosComportamientos = new HashMap<>();
        this.estadosComportamientos.put(Estado.NORMAL, null);
        this.estadosParaEliminar = new ArrayList<>();
    }


    public void setEstado(Estado estado){
        EstadoComportamiento estadoComportamiento = estadosComportamientos.get(estado);
        if (estadoComportamiento == null) {
            estadoComportamiento = EstadoFacotory.crearEstado(estado);
            estadosComportamientos.put(estado, estadoComportamiento);
        }
    }

    public Boolean usarHabilidadEstados(Habilidad habilidad, Pokemon pokemon) {
        for (Estado estado : pokemon.getEstados()) {
            EstadoComportamiento estadoComportamiento = this.estadosComportamientos.get(estado);
            if (estadoComportamiento != null) {
                boolean puedeUsarHabilidad = estadoComportamiento.usarHabilidad(habilidad, pokemon);
                if (!puedeUsarHabilidad) {
                    return false;
                }
            }
        }
        return true;
    }


    public void aplicarEfectoEstados(){

        for (Estado estado: this.pokemon.getEstados()){
            EstadoComportamiento estadoComportamiento = this.estadosComportamientos.get(estado);
            if (estadoComportamiento != null) {
                estadoComportamiento.aplicarEfecto(this.pokemon);

            }
        }
        this.pokemon.getEstados().removeAll(this.estadosParaEliminar);
        this.estadosParaEliminar.clear();
        if (this.pokemon.getEstados().isEmpty()){
            this.pokemon.setEstado(Estado.NORMAL);
        }
    }

    public void eliminarEstado(Estado estado){
        this.estadosParaEliminar.add(estado);
        System.out.println(pokemon.getNombre() + " ha dejado de estar " + estado.name().toLowerCase());
    }
}
