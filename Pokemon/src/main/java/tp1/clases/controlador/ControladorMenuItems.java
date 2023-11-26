package tp1.clases.controlador;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Item;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ControladorMenuItems implements Controlador, CancelarAccionListener {
    @FXML
    private VBox botonesItems;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label itemDescriptionLabel;

    @FXML
    private ImageView imagenItem;

    private Pane bottomActual;

    public void inicializar(Batalla batalla) {
        List<Item> items = batalla.getItemsJugadorActual();
        Map<String, Long> cantidadItems = batalla.getMapItemsJugadorActual();
        this.setBotonesItems(items, cantidadItems);
        this.setBotonVolver();
    }

    private void setBotonesItems(List<Item> items, Map<String, Long> cantidadItems) {
        for (Item item : items) {
            String nombreItem = item.getNombre();
            Long cantidad = cantidadItems.get(nombreItem);
            Button boton = new Button(item.getNombre() + " x" + cantidad);
            this.botonesItems.getChildren().add(boton);
            boton.setMaxWidth(Double.MAX_VALUE);
            boton.setMaxHeight(Double.MAX_VALUE);
            boton.setOnMouseEntered(mouseEvent -> {setInfoItem(item.getNombre());});
            boton.setOnMouseClicked(mouseEvent -> {cambiarMenuItemsConfirmacion(mouseEvent, item);});
        }
    }

    private void setBotonVolver(){
        Button boton = new Button("Volver");
        boton.setMaxWidth(Double.MAX_VALUE);
        boton.setMaxHeight(Double.MAX_VALUE);
        boton.getStyleClass().add("boton-volver");
        botonesItems.getChildren().add(boton);
        this.setInfoBotonVolver();
        boton.setOnMouseEntered(mouseEvent -> {setInfoBotonVolver();});
        boton.setOnMouseClicked(this::cambiarMenuPrincipal);
    }

    private void setInfoBotonVolver(){
        String imagenPath = "/Imagenes/volver.png";
        Image imagen = new Image(Objects.requireNonNull(getClass().getResource(imagenPath)).toString());
        imagenItem.setImage(imagen);
        itemDescriptionLabel.setText("Cerrar mochila.");
    }

    private void cambiarMenuItemsConfirmacion(Event evento, Item item){
        this.deshabilitarBotones();
        this.bottomActual = (Pane) borderPane.getBottom();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("cartel-confirmacion-item.fxml"));
        Pane itemInfoPane;
        try {
            itemInfoPane = loader.load();
            ControladorConfirmacionItem controladorConfirmacion = loader.getController();
            controladorConfirmacion.setInfoItem(item.getNombre());
            controladorConfirmacion.setCancelActionListener(this);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        borderPane.setBottom(itemInfoPane);
    }

    public void setInfoItem(String nombreItem){
        String imagenPath = "/Imagenes/items/" + nombreItem + ".png";
        Image imagen = new Image(Objects.requireNonNull(getClass().getResource(imagenPath)).toString());
        imagenItem.setImage(imagen);
    }

    public void habilitarBotones() {
        for (Node node : botonesItems.getChildren()) {
            node.setDisable(false);
        }
    }
    private void deshabilitarBotones(){
        for (Node node: botonesItems.getChildren()){
            node.setDisable(true);
        }
    }

    @Override
    public void onCancelAction() {
        if (this.bottomActual != null) {
            borderPane.setBottom(bottomActual);
            habilitarBotones();
        }
    }

    public void cambiarMenuPrincipal(MouseEvent event) {
        this.borderPane.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
    }
}

