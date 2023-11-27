package tp1.clases.controlador;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ControladorEfectoInicio {

    @FXML
    private ImageView ash;
    @FXML
    private ImageView brook;
    @FXML
    private Label texto;
    @FXML
    private ImageView bolasAsh;
    @FXML
    private ImageView bolasBrook;


    void inicializar(){

        this.ash.setTranslateX(-200);
        this.brook.setTranslateX(200);
        this.bolasAsh.setTranslateX(200);
        this.bolasBrook.setTranslateX(-200);

        TranslateTransition transitionAsh = new TranslateTransition(Duration.seconds(1), ash);
        TranslateTransition transitionBrook = new TranslateTransition(Duration.seconds(1), brook);
        TranslateTransition transitionBolasAsh = new TranslateTransition(Duration.seconds(1), bolasAsh);
        TranslateTransition transitionBolasBrook = new TranslateTransition(Duration.seconds(1), bolasBrook);

        transitionAsh.setToX(0);
        transitionBrook.setToX(0);
        transitionBolasAsh.setToX(0);
        transitionBolasBrook.setToX(0);

        transitionAsh.play();
        transitionBrook.play();
        transitionBolasAsh.play();
        transitionBolasBrook.play();

        texto.setOpacity(0);
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

    public void pasarPantalla(){
        this.ash.setTranslateX(0);
        this.brook.setTranslateX(0);
        this.bolasAsh.setTranslateX(0);
        this.bolasBrook.setTranslateX(0);

        TranslateTransition transitionAsh = new TranslateTransition(Duration.seconds(1), ash);
        TranslateTransition transitionBrook = new TranslateTransition(Duration.seconds(1), brook);
        TranslateTransition transitionBolasAsh = new TranslateTransition(Duration.seconds(1), bolasAsh);
        TranslateTransition transitionBolasBrook = new TranslateTransition(Duration.seconds(1), bolasBrook);

        transitionAsh.setToX(-300);
        transitionBrook.setToX(300);
        transitionBolasAsh.setToX(300);
        transitionBolasBrook.setToX(-300);

        transitionAsh.play();
        transitionBrook.play();
        transitionBolasAsh.play();
        transitionBolasBrook.play();

        //cambiar la escena
    }

}

