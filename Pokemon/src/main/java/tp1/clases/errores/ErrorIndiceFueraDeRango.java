package tp1.clases.errores;

public class ErrorIndiceFueraDeRango extends Error{
    public ErrorIndiceFueraDeRango() {
        super("El número ingresado no pertenece a las opciones.");
    }
}
