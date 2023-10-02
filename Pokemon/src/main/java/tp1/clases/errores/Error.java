package tp1.clases.errores;

import javax.swing.*;

public abstract class Error {
    private String mensaje;

    public Error(String s) {
        this.mensaje = s;
    }

    public void mostrarError() {
        System.out.println(mensaje);
    }
}
