package tp1.clases.controlador;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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

import java.io.IOException;
import java.util.List;

public class ControladorMenuItems{

    @FXML
    private VBox buttonContainer;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label itemDescriptionLabel;

    @FXML
    private ImageView imagenItem;


    public void inicializarItems(List<Item> items) {

        for (Item item : items) {
            Button button = new Button(item.getNombre() + ("   x 2" ));
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
            String nombreItem = item.getNombre();
            Image imagen = new Image("file:/home/cristhian/Descargas/" + nombreItem + ".png");

            button.setOnMouseEntered(event -> {
                itemDescriptionLabel.setText(item.getDescripcion());
                imagenItem.setImage(imagen);
            });

            button.setOnAction(event -> {
                try {
                    onItemSelected(item);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            buttonContainer.getChildren().add(button);
        }
    }

    private void onItemSelected(Item item) throws IOException {
    }

}
