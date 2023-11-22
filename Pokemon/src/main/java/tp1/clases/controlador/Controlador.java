package tp1.clases.controlador;

import tp1.clases.modelo.Batalla;

public interface Controlador {
    void inicializar(Batalla batalla);
    void actualizar(Batalla batalla);
    void actualizarCampo(Batalla batalla);
    void setControladorVentana(ControladorVentana controladorVentana);
}
