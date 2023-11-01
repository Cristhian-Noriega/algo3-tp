package tp1.clases.errores;

public class ErrorMismoEstado extends Error{
    public ErrorMismoEstado(String estado) {
        super(String.format("El pokemon actualmente ya está %s", estado));
    }
}
