package tp1.clases.modelo;

import java.util.List;

public enum Clima {
    SOLEADO(List.of(Tipo.FUEGO)),
    LLUVIA(List.of(Tipo.AGUA, Tipo.PLANTA)),
    TORMENTA_DE_ARENA(List.of(Tipo.TIERRA, Tipo.ROCA)),
    NIEBLA(List.of(Tipo.FANTASMA, Tipo.PSIQUICO)),
    TORMENTA_DE_RAYOS(List.of(Tipo.ELECTRICO)),
<<<<<<< HEAD
    HURACAN(List.of(Tipo.VOLADOR)),
    SIN_CLIMA(null);
=======
    HURACAN(List.of(Tipo.VOLADOR));
>>>>>>> 4f5c1277241255de37e7d486195a94fc4e99bf77

    private final List<Tipo> tiposFavorecidos;

    Clima(List<Tipo> tiposFavorecidos) {
        this.tiposFavorecidos = tiposFavorecidos;
    }

    public List<Tipo> getTiposFavorecidos() {
        return tiposFavorecidos;
    }

<<<<<<< HEAD
    public double mejorarAtaque(Tipo tipo, double danio) {
        if (this.tiposFavorecidos.contains(tipo)) {
=======
    public double mejorarAtaque(Pokemon pokemon, double danio) {
        if (this.tiposFavorecidos.contains(pokemon.getTipo())) {
>>>>>>> 4f5c1277241255de37e7d486195a94fc4e99bf77
            return (danio * Constantes.modificacionPorClima) + danio;
        }
        return danio;
    }

<<<<<<< HEAD
    public void lastimarPorClima(Pokemon pokemon) {
=======
    public void lastimar(Pokemon pokemon) {
>>>>>>> 4f5c1277241255de37e7d486195a94fc4e99bf77
        if (this == Clima.HURACAN || this == Clima.TORMENTA_DE_ARENA || this == Clima.TORMENTA_DE_RAYOS) {
            if (!this.getTiposFavorecidos().contains(pokemon.getTipo())) {
                pokemon.modificarVida((-1) * (pokemon.getVidaMax() * Constantes.disminucionPorClima));
            }
        }
    }

<<<<<<< HEAD
    public static Clima getClimaRandom() {
=======
    public static Clima getClima() {
>>>>>>> 4f5c1277241255de37e7d486195a94fc4e99bf77
        if (Random.probabilidad(Constantes.probabilidadConClima)) {
            int clima = Random.getRandom(0, 6);
            for (Clima c: Clima.values()) {
                if (c.ordinal() == clima) {
                    return c;
                }
            }
        }

        return null;
    }
}
