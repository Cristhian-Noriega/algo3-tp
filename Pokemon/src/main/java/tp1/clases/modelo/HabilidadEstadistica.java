package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorHabilidadSinUsos;

import java.util.Optional;

public class HabilidadEstadistica extends Habilidad {
    final private Estadisticas estadistica;
    final private boolean contraRival;

    final private double porcentaje;
    public HabilidadEstadistica(String nombre, Integer usos, Tipo tipo, String info, Estadisticas estadistica, boolean contraRival) {
        super(nombre, usos, tipo, info, Categoria.ESTADISTICA);
        this.estadistica = estadistica;
        this.contraRival = contraRival;
        this.porcentaje = Constantes.veinticinco;
    }

    public boolean isContraRival() {
        return contraRival;
    }

    @Override
    public Optional<Error> usar(Pokemon propio, Pokemon ajeno) {
        if (this.sinUsosDisponibles()){
            return Optional.of(new ErrorHabilidadSinUsos(this.nombre));
        }
        if (this.isContraRival()) {
            this.modificarEstadistica(ajeno, (-1));
            System.out.println(ajeno.getNombre() + " ha disminuido su " + this.estadistica.toString().toLowerCase());
        } else {
            this.modificarEstadistica(propio, 1);
            System.out.println(propio.getNombre() + " ha aunmentdo su " + this.estadistica.toString().toLowerCase());
        }
        super.usos -= 1;
        return Optional.empty();
    }

    private void modificarEstadistica(Pokemon pokemon, int modificador) {
        switch (this.estadistica) {
            case VELOCIDAD -> pokemon.modificadorVelocidad((int) (pokemon.getVelocidad() * this.porcentaje * modificador));
            case VIDA -> pokemon.modificarVida((int) (pokemon.getVidaMax() * this.porcentaje * modificador));
            case ATAQUE -> pokemon.modificarAtaque((int) (pokemon.getAtaque() * this.porcentaje * modificador));
            case DEFENSA -> pokemon.modificarDefensa((int) (pokemon.getDefensa() * this.porcentaje * modificador));
        }
    }
}