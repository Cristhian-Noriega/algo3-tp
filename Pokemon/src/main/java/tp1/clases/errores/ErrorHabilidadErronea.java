package tp1.clases.errores;

public class ErrorHabilidadErronea extends Error{

    public ErrorHabilidadErronea(String habilidad, String pokemon) {
        super(pokemon + " no posee la habilidad " + habilidad);
    }
}
