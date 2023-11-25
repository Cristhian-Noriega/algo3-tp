package tp1.clases.controlador;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ControladorConfirmacionItem {
    @FXML
    private Label itemSeleccionado;

    @FXML
    private ImageView imagenItemSeleccionado;

    private CancelarAccionListener cancelarAccionListener;

    public void setInfoItem(String nombreItem){
        this.itemSeleccionado.setText("Haz seleccionado " + nombreItem );
        Image imagen = new Image("file:/home/cristhian/Descargas/" + nombreItem + ".png");
        this.imagenItemSeleccionado.setImage(imagen);
    }

    public void handleMouseClicked(MouseEvent mouseEvent){
        if (cancelarAccionListener != null) {
            cancelarAccionListener.onCancelAction();
        }

    }

    public void setCancelActionListener(CancelarAccionListener listener){
        this.cancelarAccionListener = listener;
    }

}
