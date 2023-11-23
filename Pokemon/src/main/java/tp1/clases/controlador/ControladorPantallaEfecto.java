package tp1.clases.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tp1.clases.errores.Error;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.modelo.*;


import java.util.ArrayList;
import java.util.Optional;

public class ControladorPantallaEfecto implements Controlador{
    private Batalla batalla;
    private Habilidad habilidadSeleccionada;
    private ArrayList<Pokemon> pokemones;
    private String texto = "Efecto";
    private StringProperty textoProperty = new SimpleStringProperty(this.texto);
    @FXML public Label labelTexto;
    @FXML public ControladorCampo campoController;
    @FXML public Pane pane;

    public ControladorPantallaEfecto() {
        this.pokemones = new ArrayList<>();
    }

    public void inicializar(Batalla batalla) {
        System.out.println("batalla en pantalla efecto " + batalla);
        this.batalla = batalla;
        this.setPokemones();
        this.campoController.inicializar(batalla);
        this.labelTexto.setWrapText(true);
    }

    public void actualizar(Batalla batalla) {
        this.batalla = batalla;
        this.setPokemones();
        this.labelTexto.textProperty().bind(textoProperty);
        System.out.println(pokemones.get(0).getHabilidades() + "   " + pokemones.get(1).getNombre());
        double vidaAntRival = pokemones.get(1).getVida();
        Optional<Error> err = this.batalla.usarHabilidad(this.habilidadSeleccionada, this.batalla.getJugadorSiguiente());
        if (err.isEmpty()) {
            this.setTextoProperty(pokemones.get(0).getNombre() + " " + this.habilidadSeleccionada.getInfo().toLowerCase());
            if (this.habilidadSeleccionada.getCategoria() != Categoria.ESTADISTICA) {
                this.campoController.aplicarParpadeo(JugadorEnum.RIVAL);
                this.campoController.animarVida((pokemones.get(1).getVida() - vidaAntRival) / pokemones.get(1).getVidaMax());
            }
            this.campoController.actualizar();
            this.pane.setOnMouseClicked(this::cambiarMenuPrincipal);
        }
    }

    private void setPokemones() {
        this.pokemones = new ArrayList<>();
        for (Jugador jugador: this.batalla.getJugadores()) {
            this.pokemones.add(jugador.getPokemonActual());
        }
    }

    public void cambiarMenuPrincipal(MouseEvent event) {
        this.batalla.cambiarTurno();
        //this.controladorVentana.cambiarEscena(Escena.MENU_PRINCIPAL.ordinal());
        this.labelTexto.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
    }

    public void setTextoProperty(String textoProperty) {
        this.textoProperty.set(textoProperty);
    }

    public void setHabilidadSeleccionada(Habilidad habilidad) {
        this.habilidadSeleccionada = habilidad;
    }
}
