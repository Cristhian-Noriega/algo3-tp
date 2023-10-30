package tp1.clases.modelo;

import java.util.List;

public enum Clima {
    SOLEADO(List.of(Tipo.FUEGO)),
    LLUVIA(List.of(Tipo.AGUA, Tipo.PLANTA)),
    TORMENTA_DE_ARENA(List.of(Tipo.TIERRA, Tipo.ROCA)),
    NIEBLA(List.of(Tipo.FANTASMA, Tipo.PSIQUICO)),
    TORMENTA_DE_RAYOS(List.of(Tipo.ELECTRICO)),
    HURACAN(List.of(Tipo.VOLADOR)),
    SIN_CLIMA(List.of((Tipo) null));

    private final List<Tipo> tiposFavorecidos;

    Clima(List<Tipo> tiposFavorecidos) {
        this.tiposFavorecidos = tiposFavorecidos;
    }

    public List<Tipo> getTiposFavorecidos() {
        return tiposFavorecidos;
    }

    public boolean favorece(Tipo tipo) {
        return this.tiposFavorecidos.contains(tipo);
    }

    public void lastimarPorClima(Pokemon pokemon) {
        if (this == Clima.HURACAN || this == Clima.TORMENTA_DE_ARENA || this == Clima.TORMENTA_DE_RAYOS) {
            if (!this.getTiposFavorecidos().contains(pokemon.getTipo())) {
                pokemon.modificarVida((-1) * (pokemon.getVidaMax() * Constantes.disminucionPorClima));
            }
        }
    }

    public static Clima getClimaRandom() {
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