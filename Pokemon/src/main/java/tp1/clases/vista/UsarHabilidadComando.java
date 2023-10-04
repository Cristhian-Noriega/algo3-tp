package tp1.clases.vista;

import tp1.clases.errores.Error;
import tp1.clases.modelo.Batalla;

import java.util.Optional;

public class UsarHabilidadComando implements Comando{

    private final Batalla batalla;

    private int habilidad;

    public UsarHabilidadComando(Batalla batalla) {
        this.batalla = batalla;
    }


    @Override
    public void definirOpcion(int op){
        this.habilidad = op;
    }

    public Optional<Error> ejecutar(){
        Optional<Error> err = this.batalla.usarHabilidad(this.habilidad, this.batalla.getJugadorSiguiente());
//        if (err.isEmpty()) {
//            //System.out.print()
//            //TODO: mostrar mensaje de habilidad
//
//        }
        return err;
    }
}
