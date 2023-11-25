package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.util.Optional;

public class HabilidadEstadistica extends Habilidad {
    final private Estadisticas estadistica;
    final private boolean contraRival;

    final private double porcentaje;
    public HabilidadEstadistica(String nombre, Integer usos, Tipo tipo, String info, Estadisticas estadistica, boolean contraRival, Integer id) {
        super(nombre, usos, tipo, info, Categoria.ESTADISTICA, id);
        this.estadistica = estadistica;
        this.contraRival = contraRival;
        this.porcentaje = Constantes.porcentajeDeModificacion;
    }

    public boolean isContraRival() {
        return contraRival;
    }

    @Override

    public Optional<Error> usar() {
        if (this.isContraRival()) {
            this.modificarEstadistica(this.pokemonRival, (-1));
            System.out.println(this.pokemonRival.getNombre() + " ha disminuido su " + this.estadistica.toString().toLowerCase());
        } else {
            this.modificarEstadistica(this.pokemonAtacante, 1);
            System.out.println(this.pokemonAtacante.getNombre() + " ha aunmentdo su " + this.estadistica.toString().toLowerCase());
        }

        this.restarUso();
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