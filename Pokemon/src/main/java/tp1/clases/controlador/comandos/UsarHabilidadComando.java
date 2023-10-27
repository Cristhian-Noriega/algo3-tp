package tp1.clases.controlador.comandos;

import tp1.clases.controlador.ControladorEstados;
import tp1.clases.errores.Error;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Categoria;
import tp1.clases.modelo.Jugador;

import java.util.Optional;

public class UsarHabilidadComando implements Comando {

    private final Batalla batalla;
    private final ControladorEstados controladorEstados;
    private int habilidad;

    public UsarHabilidadComando(Batalla batalla, ControladorEstados controladorEstados) {
        this.batalla = batalla;
        this.controladorEstados = controladorEstados;
    }

    @Override
    public void definirOpcion(int op){
        this.habilidad = op;
    }

    public Optional<Error> ejecutar(){
        Optional<Error> err = this.batalla.usarHabilidad(this.habilidad, this.batalla.getJugadorSiguiente());
        if ((batalla.getHabilidadesPokemonActual().get(this.habilidad).getCategoria() == Categoria.ESTADO) && (err.isEmpty())) {
            Jugador jugadorSiguiente = this.batalla.getJugadorSiguiente();
//            controladorEstados.setTurnoInicial(jugadorSiguiente, batalla.getTurno());
        }
        return err;
    }
}
