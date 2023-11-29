package tp1.clases.modelo;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import tp1.clases.errores.Error;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class Habilidad implements Serializable, Cloneable{
    final protected String nombre;
    final protected Integer id;
    protected Integer usos;
    final protected Tipo tipo;
    final protected String info;
    final protected Categoria categoria;
    protected AdministradorDeClima administradorDeClima;
    protected Pokemon pokemonAtacante;
    protected Pokemon pokemonRival;
    protected InfoHabilidad infoHabilidad;

    @JsonCreator
    public Habilidad(@JsonProperty("nombre") String nombre, @JsonProperty("usos") Integer usos, @JsonProperty("tipo") Tipo tipo,
                     @JsonProperty("info") String info, @JsonProperty("categoria") Categoria categoria,
                     @JsonProperty("id") Integer id) {
        this.nombre = nombre;
        this.usos = usos;
        this.tipo = tipo;
        this.info = info;
        this.categoria = categoria;
        this.id = id;
        this.infoHabilidad = new InfoHabilidad(categoria);
    }

    public InfoHabilidad getInfoHabilidad() {
        return infoHabilidad;
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

    public Categoria getCategoria() {
        return this.categoria;
    }

    public Integer getId(){
        return this.id;
    }

    public Clima getClimaActual() {
        return this.administradorDeClima.getClimaActual();
    }

    public void setAdministradorDeClima(AdministradorDeClima administradorDeClima) {
        this.administradorDeClima = administradorDeClima;
    }

    public void setPokemonAtacante(Pokemon pokemonAtacante) {
        this.pokemonAtacante = pokemonAtacante;
    }

    public void setPokemonRival(Pokemon pokemonRival) {
        this.pokemonRival = pokemonRival;
    }

    public void setAmbiente(AdministradorDeClima administradorDeClima, List<Pokemon> pokemonesActuales) {
        this.setAdministradorDeClima(administradorDeClima);
        this.setPokemonAtacante(pokemonesActuales.get(0));
        this.setPokemonRival(pokemonesActuales.get(1));
    }

    public abstract Optional<Error> usar();

    public boolean sinUsosDisponibles() {
        return this.usos < 1;
    }

    public void restarUso() {
        this.usos -= 1;
    }


    @Override
    public Habilidad clone() throws CloneNotSupportedException {
        return (Habilidad) super.clone();
    }
}