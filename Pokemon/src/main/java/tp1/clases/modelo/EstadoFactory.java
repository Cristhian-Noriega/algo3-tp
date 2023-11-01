package tp1.clases.modelo;

public class EstadoFactory {
    public static EstadosComportamiento crearEstado(Estado estado) {
        return switch (estado) {
            case DORMIDO -> new Dormido(0);
            case PARALIZADO -> new Paralizado(0);
            case ENVENENADO -> new Envenenado(0);
            case CONFUNDIDO -> new Confundido(0);
            default ->
                    null;
        };
    }
}
