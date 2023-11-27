package tp1.clases.controlador;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import tp1.clases.modelo.Jugador;
import tp1.clases.modelo.Pokemon;

import java.util.List;
import java.util.Objects;

public class ControladorPantallaFinal {

    @FXML
    private Label texto;
    @FXML
    private ImageView poke1;
    @FXML
    private ImageView poke2;
    @FXML
    private ImageView poke3;
    @FXML
    private ImageView poke4;
    @FXML
    private ImageView poke5;
    @FXML
    private ImageView poke6;
    @FXML
    private ImageView ganador;

    private List<Pokemon> pokemones;

    public void inicializar(Jugador ganador){
        this.pokemones = ganador.getListaPokemones();
        setImagenesPokemones();

        String path = "/imagenes/" + ganador.getNombre() + ".png";
        this.ganador.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource(path)))));


        texto.setOpacity(0);
        texto.setText("¡ENHORABUENA! ¡" + ganador.getNombre() + " ha ganado! ¡Felicitaciones!");
        Timeline timeline = new Timeline();

        for (int i = 0; i < texto.getText().length(); i++) {
            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds(0.1*i),
                    new KeyValue(texto.textProperty(), texto.getText().substring(0, i + 1))
            );
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(texto.getText().length()*0.1),
                new KeyValue(texto.opacityProperty(), 1)
        ));

        timeline.play();
    }

    private void setImagenesPokemones(){
        String path = "/imagenes/" + this.pokemones.get(0).getNombre() + ".gif";
        poke1.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource(path)))));

        path = "/imagenes/" + this.pokemones.get(1).getNombre() + ".gif";
        poke2.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource(path)))));

        path = "/imagenes/" + this.pokemones.get(2).getNombre() + ".gif";
        poke3.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource(path)))));

        path = "/imagenes/" + this.pokemones.get(3).getNombre() + ".gif";
        poke4.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource(path)))));

        path = "/imagenes/" + this.pokemones.get(4).getNombre() + ".gif";
        poke5.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource(path)))));

        path = "/imagenes/" + this.pokemones.get(5).getNombre() + ".gif";
        poke6.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource(path)))));
    }
}
