package tp1.clases.modelo;

public enum Estado {
    NORMAL(null),
    DORMIDO(new Dormido()),
    PARALIZADO(new Paralizado()),
    ENVENENADO(new Envenenado()),
    CONFUNDIDO(new Confundido());

    private EstadosComportamiento estado;

    Estado(EstadosComportamiento estado){
        this.estado = estado;
    }

    public EstadosComportamiento getEstado() {
        return estado;
    }
}