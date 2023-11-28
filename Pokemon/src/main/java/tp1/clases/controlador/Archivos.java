package tp1.clases.controlador;

import java.util.Objects;

public class Archivos {

    public static String getRutaAbsolutaImagenes(String archivo) {
        return Objects.requireNonNull(Archivos.class.getResource("/Imagenes/" + archivo)).toString();
    }
}
