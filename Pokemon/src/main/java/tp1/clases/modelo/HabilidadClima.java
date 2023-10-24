package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorHabilidadSinUsos;

import java.util.Optional;

public class HabilidadClima extends Habilidad{
    private final Clima clima;
    public HabilidadClima(String nombre, Integer usos, Tipo tipo, String info, Categoria categoria, Clima clima) {
        super(nombre, usos, tipo, info, categoria);
        this.clima = clima;
    }

    @Override
    public Optional<Error> usar(Pokemon propio, Pokemon ajeno) {
        if (this.sinUsosDisponibles()) {
            return Optional.of(new ErrorHabilidadSinUsos(this.getNombre()));
        }
        
        return Optional.empty();
    }
}
