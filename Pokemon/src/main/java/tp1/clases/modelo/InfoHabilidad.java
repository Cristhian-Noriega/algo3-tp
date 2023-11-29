package tp1.clases.modelo;

import tp1.clases.errores.Error;

public class InfoHabilidad {
    private boolean pudoUsarHabilidad;
    private Estado estadoLimitante;
    private Categoria categoria;
    private double danio;
    private boolean beneficiadoPorClima;
    private Estado estadoModificado;
    private Estadisticas estadisticaModificada;
    private JugadorEnum jugadorAfectado;
    private Clima climaModificado;

    public InfoHabilidad(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Estado getEstadoLimitante() {
        return estadoLimitante;
    }

    public JugadorEnum getJugadorAfectado() {
        return jugadorAfectado;
    }

    public double getDanio() {
        return danio;
    }

    public Estado getEstadoModificado() {
        return estadoModificado;
    }

    public Estadisticas getEstadisticaModificada() {
        return estadisticaModificada;
    }

    public Clima getClimaModificado() {
        return climaModificado;
    }


    public void setEstadoLimitante(Estado estadoLimitante) {
        this.estadoLimitante = estadoLimitante;
    }

    public void setJugadorAfectado(JugadorEnum jugadorAfectado) {
        this.jugadorAfectado = jugadorAfectado;
    }

    public boolean sePudoUsarHabilidad() {
        return pudoUsarHabilidad;
    }

    public void setPudoUsarHabilidad(boolean pudoUsarHabilidad) {
        this.pudoUsarHabilidad = pudoUsarHabilidad;
    }

    public void setDanio(double danio) {
        this.danio = danio;
    }

    public void setBeneficiadoPorClima(boolean beneficiadoPorClima) {
        this.beneficiadoPorClima = beneficiadoPorClima;
    }

    public void setEstadoModificado(Estado estadoModificado) {
        this.estadoModificado = estadoModificado;
    }

    public void setEstadisticaModificada(Estadisticas estadisticaModificada) {
        this.estadisticaModificada = estadisticaModificada;
    }

    public void setClimaModificado(Clima climaModificado) {
        this.climaModificado = climaModificado;
    }

    public boolean beneficiadoPorClima() {
        return beneficiadoPorClima;
    }
}