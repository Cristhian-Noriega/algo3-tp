package tp1.clases.errores;

public class ErrorEstadoDistintoDeNormal extends Error{
    public ErrorEstadoDistintoDeNormal(String estado) {
        super(String.format("El pokemon actualmente ya está %s, no puede recibir otro estado", estado));
    }
}
