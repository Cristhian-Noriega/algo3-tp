package tp1.clases.errores;

public abstract class Error {
    private String mensaje;
    public Error(String mensaje) {
        this.mensaje = mensaje;
    }

    public void mostrar() {
        System.out.println(mensaje) ;

    }

}
