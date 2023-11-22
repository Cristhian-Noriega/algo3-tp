package tp1.clases.controlador;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tp1.clases.modelo.Item;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ControladorMenuItems{

    @FXML
    private VBox botonesItems;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label itemDescriptionLabel;

    @FXML
    private ImageView imagenItem;


    public void inicializar(List<Item> items, Map<String, Long> cantidadItems) {

        for (Item item : items) {
            String nombreItem = item.getNombre();
            Long cantidad = cantidadItems.get(nombreItem);
            Button boton = new Button(item.getNombre() + "     x" + cantidad);
            boton.setMaxWidth(Double.MAX_VALUE);
            boton.setMaxHeight(Double.MAX_VALUE);
            Image imagen = new Image("file:/home/cristhian/Descargas/" + nombreItem + ".png");
            botonesItems.getChildren().add(boton);

            boton.setOnMouseEntered(event -> {setInfoItem(item.getDescripcion(),imagen);});

            boton.setOnMouseClicked(event ->{cambiarMenuItemsConfirmacion(event, item);});
        }
        Button boton = new Button("Volver");
        boton.setMaxWidth(Double.MAX_VALUE);
        boton.setMaxHeight(Double.MAX_VALUE);
        botonesItems.getChildren().add(boton);

        boton.setOnMouseEntered(event -> {
            itemDescriptionLabel.setText("Cerrar mochila");
            Image imagen = new Image("file:/home/cristhian/Descargas/volver.png");
            imagenItem.setImage(imagen);
        });
    }
    private void cambiarMenuItemsConfirmacion(Event evento, Item item){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("cartelMenuItems.fxml"));
            BorderPane root = loader.load();
            ControladorCartelItem controladorCartelItem = loader.getController();
//            controladorCartelItem.inicializar(this.borderPane);

            Scene escenaConfirmacionItem = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) evento.getSource()).getScene().getWindow();
            stage.setScene(escenaConfirmacionItem);
//            borderPane.setBottom(itemInfoPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInfoItem(String descripcionItem, Image imagen){
        itemDescriptionLabel.setText(descripcionItem);
        imagenItem.setImage(imagen);
    }
}

