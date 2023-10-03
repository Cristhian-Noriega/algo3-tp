package tp1.clases.modelo;

import tp1.clases.errores.Error;

import java.util.Optional;

public class HabilidadAtaque extends Habilidad {
    final protected Integer poder;

    public HabilidadAtaque(String nombre, Integer usos, Tipo tipo, Integer poder, String info) {
        super(nombre, usos, tipo, info);
        this.poder = poder;
    }

    public Integer getPoder() {
        return poder;
    }

    public int calcularDanioAtaque(Pokemon atacante, Pokemon defensor) {
        int nivelAtacante = atacante.getNivel();
        int ataqueAtacante = atacante.getAtaque();
        int defensaDefensor = defensor.getDefensa();
        double tipoAtaqueEfectividad = Efectividad.getEfectividad(atacante.getTipo().ordinal(), defensor.getTipo().ordinal());
        double mismoTipo = (atacante.getTipo() == this.tipo)? 1.5: 1;
        int random = (int) (Math.random()*(255-217+1)+217) / 255;
        int danio = (int) ((((2 * nivelAtacante * this.poder * (ataqueAtacante / defensaDefensor)) / 5 + 2)   / 50 ) * tipoAtaqueEfectividad * mismoTipo * random);

        return danio;
    }

    @Override
    public Error usar(Pokemon propio, Pokemon ajeno) {
        int danio = calcularDanioAtaque(propio, ajeno);
        return ajeno.modificarVida((-1)*danio);
    }
}