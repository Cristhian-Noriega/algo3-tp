package tp1.clases.modelo;

import tp1.clases.errores.Error;
import tp1.clases.errores.ErrorHabilidadSinUsos;

import java.util.Optional;

public class HabilidadAtaque extends Habilidad {
    final protected Integer poder;

    public HabilidadAtaque(String nombre, Integer usos, Tipo tipo, Integer poder, String info) {
        super(nombre, usos, tipo, info);
        this.poder = poder;
    }

    // no se usa
    public Integer getPoder() {
        return poder;
    }

    private double calcularDanioAtaque(Pokemon atacante, Pokemon defensor) {

        int maxRandom =  255;
        int minRandom = 217;

        double nivelAtacante = atacante.getNivel();
        double ataqueAtacante = atacante.getAtaque();
        double defensaDefensor = defensor.getDefensa();
        double tipoAtaqueEfectividad = Efectividad.getEfectividad(atacante.getTipo().ordinal(), defensor.getTipo().ordinal());
        double mismoTipo = (atacante.getTipo() == this.tipo)? 1.5: 1;
        double random = (double) ((Math.random()*(maxRandom+1-minRandom))+minRandom)/maxRandom;

        double danio = (double) ((((2 * nivelAtacante * this.poder * (ataqueAtacante / defensaDefensor)) / 5 + 2)   / 50 ) * tipoAtaqueEfectividad * mismoTipo * random);

        return danio;
    }

    @Override
    public Optional<Error> usar(Pokemon propio, Pokemon ajeno) {
        if (this.sinUsosDisponibles()){
            return Optional.of(new ErrorHabilidadSinUsos(this.nombre));
        }
        double danio = calcularDanioAtaque(propio, ajeno);
        System.out.println("daño: " + danio);
        ajeno.modificarVida((-1)*danio);
        return Optional.empty();
    }

    public boolean esEfectivo(Pokemon atacante, Pokemon ajeno) { //estoy probando cosas :)
        return (this.calcularDanioAtaque(atacante, ajeno) > 0 );
    }
}