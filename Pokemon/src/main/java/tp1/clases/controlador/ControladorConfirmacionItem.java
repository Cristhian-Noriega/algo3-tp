package tp1.clases.controlador;


import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.eventos.ItemSeleccionadoEvent;
import tp1.clases.modelo.Item;

import java.util.Objects;

public class ControladorConfirmacionItem {

    @FXML private Label itemSeleccionado;

    @FXML private ImageView imagenItemSeleccionado;

    private ControladorMenuItems controladorMenuItems;

    private Item item;


    public void inicializar(Item item){
        this.item = item;
        this.setInfoItem(this.item.getNombre());
    }

    public void setInfoItem(String nombreItem){
        this.itemSeleccionado.setText("Has seleccionado " + nombreItem );
        String imagenPath = "/Imagenes/items/" + nombreItem + ".png";
        Image imagen = new Image(Objects.requireNonNull(getClass().getResource(imagenPath)).toString());
        this.imagenItemSeleccionado.setImage(imagen);
    }

    public void handleMouseClickedCancelar(MouseEvent mouseEvent){
        if (controladorMenuItems != null) {
            controladorMenuItems.onAccionCancelada();
        }
    }

    public void handleMouseClickedUsar(MouseEvent mouseEvent){
        this.itemSeleccionado.fireEvent(new ItemSeleccionadoEvent(this.item));
        CambioDeEscenaEvent evento = new CambioDeEscenaEvent(Escena.MENU_POKEMONES.ordinal());
        this.itemSeleccionado.fireEvent(evento);
        controladorMenuItems.onAccionCancelada();
    }

    public void setControladorMenuItems(ControladorMenuItems controladorMenuItems){
        this.controladorMenuItems = controladorMenuItems;
    }

}
