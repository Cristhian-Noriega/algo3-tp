package tp1.clases.modelo;

public class AdministradorDeEstadosv2 {

    private Batalla batalla;

    public AdministradorDeEstadosv2(Batalla batalla) {
        this.batalla = batalla;
    }

    public void aplicarEfectoEstado(){
        Pokemon pokemonJugadorActual = this.batalla.getJugadorActual().getPokemonActual();
        Pokemon pokemonJugadorSiguiente = this.batalla.getJugadorSiguiente().getPokemonActual();
        pokemonJugadorActual.aplicarEfectoEstados();
        pokemonJugadorSiguiente.aplicarEfectoEstados();
    }
}
