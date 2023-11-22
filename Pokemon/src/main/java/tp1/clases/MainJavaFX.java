package tp1.clases;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainJavaFX extends Application{
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("pantallaInicial.fxml"));

        Scene scene = new Scene(loader.load(), 600, 400);

        stage.setTitle("Pokemon");
        stage.setScene(scene);
        stage.show();
    }

}