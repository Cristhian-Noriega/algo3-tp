package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorMismoClima;

import java.util.Optional;

public class HabilidadClima extends Habilidad {
    private final Clima clima;
    public HabilidadClima(String nombre, Integer usos, Tipo tipo, String info, Clima clima) {
        super(nombre, usos, tipo, info, Categoria.CLIMA);
        this.clima = clima;
    }

    @Override
    public Optional<Error> usar() {
        if (this.getClimaActual() == this.clima) {
            return Optional.of(new ErrorMismoClima(this.clima.toString().toLowerCase()));
        }
        this.administradorDeClima.cambiarClima(this.clima);
        System.out.println(this.pokemonAtacante.getNombre() + " ha cambiado el clima a " + this.clima.toString().toLowerCase());

        this.restarUso();
        return Optional.empty();
    }
}