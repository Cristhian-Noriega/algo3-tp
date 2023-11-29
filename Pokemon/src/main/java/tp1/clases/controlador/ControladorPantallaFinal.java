package tp1.clases.controlador;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Jugador;
import tp1.clases.modelo.Pokemon;

import java.util.Objects;

public class ControladorPantallaFinal implements Controlador{
    public ImageView fondo;
    public Label textoClixk;
    public Rectangle rectangulo;
    public VBox infoPokemones;
    @FXML private Label texto;
    @FXML private ImageView ganador;
    @FXML private GridPane gridPane;

    private Jugador jugador;
    private boolean clicksHecho = false;

    public void inicializar(Batalla batalla){
        this.jugador = batalla.getJugadores().get(0);
        setImagenesPokemones();
        setTexto(this.jugador.getNombre());
        setGanador(this.jugador.getNombre());
    }

    private void setImagenesPokemones() {
        int columnas = 3;
        int i = 0;
        for (Pokemon pokemon : this.jugador.getListaPokemones()){

            String path = "/Imagenes/pokemon/" + pokemon.getNombre() + ".gif";
            ImageView imageView = new ImageView(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource(path)))));
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);

            Timeline timeline = new Timeline();

            KeyFrame startFrame = new KeyFrame(Duration.ZERO,
                    new KeyValue(imageView.fitWidthProperty(), 50),
                    new KeyValue(imageView.fitHeightProperty(), 50));

            KeyFrame endFrame = new KeyFrame(Duration.seconds(2),
                    new KeyValue(imageView.fitWidthProperty(), 220),
                    new KeyValue(imageView.fitHeightProperty(), 180));

            timeline.getKeyFrames().addAll(startFrame, endFrame);
            timeline.play();
            gridPane.add(imageView, i % columnas, i / columnas);
            i++;
        }

    }

    private void setTexto(String nombre){
        texto.setOpacity(0);
        texto.setText("¡ENHORABUENA! ¡" + nombre + " ha ganado! ¡Felicitaciones!");
        Timeline timeline = new Timeline();

        for (int i = 0; i < texto.getText().length(); i++) {
            KeyFrame keyFrame =
                    new KeyFrame(
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

    private void setGanador(String nombre){
        String path = "/Imagenes/" + nombre + ".png";
        this.ganador.setImage(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource(path)))));

        Timeline timeline2 = new Timeline();
        KeyFrame startFrame = new KeyFrame(Duration.ZERO,
                new KeyValue(this.ganador.fitWidthProperty(), 50),
                new KeyValue(this.ganador.fitHeightProperty(), 50));

        KeyFrame endFrame = new KeyFrame(Duration.seconds(2),
                new KeyValue(this.ganador.fitWidthProperty(), 240),
                new KeyValue(this.ganador.fitHeightProperty(), 300));

        timeline2.getKeyFrames().addAll(startFrame, endFrame);
        timeline2.play();
    }

    @FXML
    private void handleOnMouseClicked() {
        if (!this.clicksHecho) {
            this.clicksHecho = true;
            Color initialColor = Color.web("#e3c57ff7");
            this.rectangulo.setFill(initialColor);
            this.rectangulo.setStroke(Color.WHITE);
            this.textoClixk.setOpacity(0);

            for (Pokemon pokemon : this.jugador.getListaPokemones()) {
                Text info = new Text(pokemon.getNombre() + " " + "  Nvl " + pokemon.getNivel() + "  Vida " + pokemon.getVida());
                info.setFont(new Font("Pokemon Classic Regular", 12));
                this.infoPokemones.getChildren().add(info);
                this.infoPokemones.getChildren().add(new Text(""));
            }
        }
    }
}
