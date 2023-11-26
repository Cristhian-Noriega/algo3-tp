//package tp1.clases.controlador;
//
//import javafx.animation.FadeTransition;
//import javafx.animation.PauseTransition;
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import tp1.clases.modelo.Batalla;
//import tp1.clases.modelo.Item;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//public class ControladorJugar {
//
//    private Stage stage;
//
//    private Batalla batalla;
//
//    @FXML
//    private void handleJugarButtonClick(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("items-view.fxml"));
//            Parent menuItems = loader.load();
//
//            ControladorMenuItems controladorMenuItems = loader.getController();
//            if (controladorMenuItems != null) {
//                controladorMenuItems.inicializar(this.batalla.getItemsJugadorActual(), this.batalla.getMapItemsJugadorActual());
//            } else {
//                System.out.println("El controlador de menu items es null");
//            }
//
//            Scene itemsScene = new Scene(menuItems);
//            stage.setScene(itemsScene);
//        } catch (IOException error) {
//            error.printStackTrace();
//        }
//    }
//    public void setStage(Stage stage) {
//        this.stage = stage;
//    }
//
//    public void setBatalla(Batalla batalla) {
//        this.batalla = batalla;
//    }
//}
//
