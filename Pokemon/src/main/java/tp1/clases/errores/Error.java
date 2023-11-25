package tp1.clases.errores;

public abstract class Error {
    private String mensaje;
    public Error(String mensaje) {
        this.mensaje = mensaje ;
    }


    public String mostrar() {
        return this.mensaje;
    }

}
