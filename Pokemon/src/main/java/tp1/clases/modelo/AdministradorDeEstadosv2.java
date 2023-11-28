package tp1.clases.modelo;

import java.util.List;

public class AdministradorDeEstadosv2 {

    private Batalla batalla;

    public AdministradorDeEstadosv2(Batalla batalla) {
        this.batalla = batalla;
    }

    public void aplicarEfectoEstado(InfoTurno infoTurno){
        Pokemon pokemonJugadorActual = this.batalla.getJugadorActual().getPokemonActual();
        Pokemon pokemonJugadorSiguiente = this.batalla.getJugadorSiguiente().getPokemonActual();

        this.controlarEfectoEstados(pokemonJugadorActual, infoTurno);
        this.controlarEfectoEstados(pokemonJugadorSiguiente, infoTurno);
    }
    
    public void controlarEfectoEstados(Pokemon pokemon, InfoTurno infoTurno){
        if (pokemon.getEstados().contains(Estado.ENVENENADO)) {
            infoTurno.agregarPokemonEnvenenado(pokemon);
        }

        List<Estado> estadosPokemonActual = pokemon.getEstados();
        
        pokemon.aplicarEfectoEstados();

        resetearEstados(pokemon, estadosPokemonActual, infoTurno);
    }
    
    public void resetearEstados(Pokemon pokemon, List<Estado> estadosAnteriores, InfoTurno infoTurno) {
        for (Estado estado : estadosAnteriores) {
            if (!pokemon.getEstados().contains(estado)) {
                infoTurno.agregarEstadoReseteado(pokemon, estado);
            }
        }
    }

}