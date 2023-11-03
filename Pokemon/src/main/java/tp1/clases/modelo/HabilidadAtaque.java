package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.util.Optional;

public class HabilidadAtaque extends Habilidad {
    final protected Integer poder;
    public HabilidadAtaque(String nombre, Integer usos, Tipo tipo, Integer poder, String info) {
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
        if (this.getClimaActual().favorece(this.pokemonAtacante.getTipo())) {
            System.out.println(this.pokemonAtacante.getNombre() + " ha mejorado su ataque gracias al clima actual.");
            return danio + (danio * Constantes.modificacionPorClima);
        }
        return danio;
    }
    @Override
    public Optional<Error> usar() {
        double danio = calcularDanioAtaque();
        this.pokemonRival.modificarVida((-1)*danio);

        if (this.esEfectivo(danio)){
            System.out.println(this.pokemonAtacante.getNombre() + " ha atacado a " + this.pokemonRival.getNombre() + " ¡Qué eficaz!\n");
        } else {
            System.out.println("¡" + this.pokemonRival.getNombre() + " ni se inmuta!\n");
        }

        this.restarUso();
        return Optional.empty();
    }

    public boolean esEfectivo(double danio) {
        return (danio > 0 );
    }
}