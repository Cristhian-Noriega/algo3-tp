package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorHabilidadSinUsos;

import java.util.Optional;

public class HabilidadClima extends Habilidad2 {
    private final Clima clima;
    public HabilidadClima(String nombre, Integer usos, Tipo tipo, String info, Categoria categoria, Clima clima) {
        super(nombre, usos, tipo, info, categoria);
        this.clima = clima;
    }

    @Override
    public Optional<Error> usar() {
        if (this.sinUsosDisponibles()) {
            return Optional.of(new ErrorHabilidadSinUsos(this.getNombre()));
        }
        if (this.pokemonAtacante.puedeUsarHabilidad()) {
            this.administradorDeClima.cambiarClima(this.clima);
            System.out.println(this.pokemonAtacante.getNombre() + " ha cambiado el clima a " + this.clima.toString());
        } else {
            System.out.println(this.pokemonAtacante.getNombre() + " no puede utilizar habilidad por su estado"); //TODO: mejorar este mensaje
        }
        this.restarUso();
        return Optional.empty();
    }
}
