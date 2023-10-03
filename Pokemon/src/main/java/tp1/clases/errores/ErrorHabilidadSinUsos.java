package tp1.clases.errores;

public class ErrorHabilidadSinUsos extends Error{

    public ErrorHabilidadSinUsos(String habilidad) {
        super(String.format("La habilidad '%s' no tiene usos disponibles.", habilidad));
    }
}
