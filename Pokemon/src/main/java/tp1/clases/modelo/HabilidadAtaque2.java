package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorHabilidadSinUsos;

import java.util.Optional;

public class HabilidadAtaque2 extends Habilidad2 {
    final protected Integer poder;

    public HabilidadAtaque2(String nombre, Integer usos, Tipo tipo, Integer poder, String info) {
        super(nombre, usos, tipo, info, Categoria.ATAQUE);
        this.poder = poder;
    }

    private double calcularDanioAtaque() {
        double nivelAtacante = this.pokemonAtacante.getNivel();
        double ataqueAtacante = this.pokemonAtacante.getAtaque();
        double defensaDefensor = this.pokemonRival.getDefensa();
        double tipoAtaqueEfectividad = Efectividad.getEfectividad(this.pokemonAtacante.getTipo().ordinal(), this.pokemonRival.getTipo().ordinal());
        double mismoTipo = (this.pokemonAtacante.getTipo() == this.tipo)? 1.5: 1;
        double random = (double) ((Math.random()*(Constantes.maxRandom+1-Constantes.minRandom))+Constantes.minRandom)/Constantes.maxRandom;
        double critico = Random.probabilidad(Constantes.probabilidadDeCritico) ? 2: 1;
        double danio = (double) ((((2 * nivelAtacante * this.poder * (ataqueAtacante / defensaDefensor)) / 5 + 2)   / 50 ) * tipoAtaqueEfectividad * mismoTipo * random * critico);
        if (this.climaActual.favorece(this.pokemonAtacante.getTipo())) {
            return danio + (danio * Constantes.modificacionPorClima);
        }
        return danio;
    }

    @Override
    public Optional<Error> usar() {
        if (this.sinUsosDisponibles()){
            return Optional.of(new ErrorHabilidadSinUsos(this.nombre));
        }

        if (this.pokemonAtacante.puedeUsarHabilidad()) {
            double danio = calcularDanioAtaque();
            this.pokemonRival.modificarVida((-1)*danio);

            if (this.esEfectivo()){
                System.out.println("¡Qué eficaz!\n");
            } else {
                System.out.println("¡" + this.pokemonRival.getNombre() + "ni se inmuta!\n");
            }
        } else {
            System.out.println(this.pokemonAtacante.getNombre() + " no puede usar la habilidad por su estado"); //TODO: esto se puede personalizar mas pero bueno
        }

        this.restarUso();
        return Optional.empty();
    }

    public boolean esEfectivo() {
        return (this.calcularDanioAtaque() > 0 );
    }
}