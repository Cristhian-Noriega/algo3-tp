package tp1.clases.controlador;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Objects;

public class Archivos {

    public static String getRutaAbsolutaImagenes(String archivo) {
        return Objects.requireNonNull(Archivos.class.getResource("/Imagenes/" + archivo)).toString();
    }

    public static void reproducirSonido(String ruta, MediaPlayer mediaPlayer) {
        Media media = new Media(Objects.requireNonNull(Archivos.class.getResource(ruta)).toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.volumeProperty().set(1.0);
        mediaPlayer.play();
    }


}
