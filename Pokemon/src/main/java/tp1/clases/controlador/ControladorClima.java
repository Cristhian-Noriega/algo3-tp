package tp1.clases.controlador;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControladorClima {
    private final ObjectProperty<Image> imagenProperty = new SimpleObjectProperty<>();
    private StringProperty climaProperty = new SimpleStringProperty();;
    @FXML public Label labelClima;
    @FXML public ImageView imagenClima;

    public ControladorClima() {}

    public void inicializar(String clima) {
        this.labelClima.textProperty().bind(climaProperty);
        this.imagenClima.imageProperty().bind(this.imagenProperty);
        this.setClimaProperty(clima);
        this.setImagen(clima);
    }

    public void setClimaProperty(String climaProperty) {
        this.climaProperty.set(String.join(" ", climaProperty.split("_")));
    }

    public void setImagen(String clima) {
        Image imagen = new Image(Archivos.getRutaAbsolutaImagenes("CLIMA_" + clima + ".png"));
        imagenProperty.set(imagen);
    }

    public void actualizar(String clima) {
        setClimaProperty(clima);
        setImagen(clima);
    }
}
