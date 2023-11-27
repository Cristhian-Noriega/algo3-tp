package tp1.clases.controlador;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.modelo.Item;

import java.util.Objects;

public class ControladorConfirmacionItem {

    @FXML
    private Button botonUsar;

    @FXML
    private Button botonCancelar;

    @FXML
    private Label itemSeleccionado;

    @FXML
    private ImageView imagenItemSeleccionado;

    private CancelarAccionListener cancelarAccionListener;

    private Item item;

    private BorderPane borderPane;

    public void inicializar(Item item, BorderPane borderPane){
        this.item = item;
        this.setInfoItem(this.item.getNombre());
        this.borderPane = borderPane;
        System.out.println(item.getNombre());
    }

    public void setInfoItem(String nombreItem){
        this.itemSeleccionado.setText("Has seleccionado " + nombreItem );
        String imagenPath = "/Imagenes/items/" + nombreItem + ".png";
        Image imagen = new Image(Objects.requireNonNull(getClass().getResource(imagenPath)).toString());
        this.imagenItemSeleccionado.setImage(imagen);
    }

    public void handleMouseClickedCancelar(MouseEvent mouseEvent){
        if (cancelarAccionListener != null) {
            cancelarAccionListener.onCancelAction();
        }
    }

    public void handleMouseClickedUsar(MouseEvent mouseEvent){
        this.borderPane.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
    }

    public void setCancelActionListener(CancelarAccionListener listener){
        this.cancelarAccionListener = listener;
    }

}
