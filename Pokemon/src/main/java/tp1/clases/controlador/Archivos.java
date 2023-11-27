package tp1.clases.controlador;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

public class Archivos {

    public static String getRutaAbsolutaImagenes(String archivo) {
        return Objects.requireNonNull(Archivos.class.getResource("/Imagenes/" + archivo)).toString();
    }
}
