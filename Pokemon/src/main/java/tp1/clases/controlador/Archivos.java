package tp1.clases.controlador;

import tp1.clases.Inicializador;

import java.io.File;
import java.util.Objects;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class Archivos {

    public static String getRutaAbsolutaImagenes(String archivo) {
        return "file:" + new File("src/main/resources/Imagenes/" + archivo).getAbsoluteFile();
    }

}
