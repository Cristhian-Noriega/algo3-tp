package tp1.clases;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import tp1.clases.modelo.Batalla;
import tp1.clases.modelo.Item;

import java.io.IOException;
import java.util.List;

public class ControladorMenuItems {

    @FXML
    private VBox buttonContainer;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label itemDescriptionLabel;

    @FXML
    private TextArea itemDescriptionTextArea;

    private Item itemseleccionado;
    private Batalla batalla;

    public void initializeItemsList(ObservableList<Item> items) {

        for (Item item : items) {
            Button button = new Button(item.getNombre());
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
            String nombreItem = item.getNombre();

            Image imagen = new Image("file:/home/cristhian/Descargas/" + nombreItem + ".png");
             button.setGraphic(new ImageView(imagen));



            button.setOnMouseEntered(event -> {
                System.out.println("item: " + nombreItem);
                itemDescriptionTextArea.setText(item.getDescripcion());
//                Image image = new Image();
//                itemImageView.setImage(image)
            });

            button.setOnMouseExited(event -> {
                itemDescriptionTextArea.clear();
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
        System.out.println("Haz seleccionado: " + item.getNombre());

        this.itemseleccionado = item;
        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(10));
        Button btnYes = new Button("Usar");
        Button btnNo = new Button("Cancelar");

        buttonBar.getButtons().addAll(btnYes,btnNo);

        borderPane.setBottom(buttonBar);


    }

}
