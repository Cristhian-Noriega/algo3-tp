package tp1.clases.controlador.comandos;

import tp1.clases.errores.Error;
import tp1.clases.modelo.AdministradorDeClima;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Habilidad;
import tp1.clases.modelo.Pokemon;

import java.util.Optional;

public class UsarHabilidadComando implements Comando {
    private Pokemon pokemonActual;
    private Pokemon pokemonRival;
    private AdministradorDeClima administradorDeClima;
    private Habilidad habilidad;

    public UsarHabilidadComando(Pokemon pokemonActual, Pokemon pokemonRival, AdministradorDeClima administradorDeClima, Habilidad habilidad) {
        this.pokemonActual = pokemonActual;
        this.pokemonRival = pokemonRival;
        this.administradorDeClima = administradorDeClima;
        this.habilidad = habilidad;
    }

    public Optional<Error> ejecutar(){
        return pokemonActual.usarHabilidad(this.habilidad, pokemonRival, administradorDeClima);
    }
}
