package tp1.clases.errores;

public class ErrorItemNoValido extends Error{
    public ErrorItemNoValido(String item){
        super(String.format("El item %s elegido no esta disponible", item));
    }
}
