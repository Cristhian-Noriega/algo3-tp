package tp1.clases.controlador;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.eventos.PokemonSeleccionadoEvent;
import tp1.clases.modelo.*;

import java.io.IOException;
import java.util.List;


public class ControladorMenuPokemon implements Controlador, SubscriptorTurno, SubscriptorEscena {
    public HBox botonConfirmar;
    public HBox botonCancelar;
    public Label labelTexto;
    @FXML private VBox contenedorPokemonActual;
    @FXML private HBox contenedorBotonVolver;
    @FXML private StackPane contenedorPrincipal;
    @FXML private VBox contenedorPokemon;

    private Batalla batalla;
    private int escenaAnterior;
    private int escenaActual;
    private Pokemon pokemonElegido;

    @Override
    public void inicializar(Batalla batalla) {
        this.batalla = batalla;
        this.batalla.getAdministradorTurnos().agregarSubscriptor(this);
        this.actualizar();

        contenedorBotonVolver.setOnMouseEntered(event -> {
            contenedorBotonVolver.setStyle("-fx-border-color:#e77a00;-fx-background-color: #bb50bb; -fx-border-radius: 3%; -fx-border-width: 5;");
        });
        contenedorBotonVolver.setOnMouseExited(event -> {
            contenedorBotonVolver.setStyle("-fx-border-color: #721572; -fx-background-color: #bb50bb; -fx-border-radius: 3%; -fx-border-width: 5;");
        });
        contenedorBotonVolver.setOnMouseClicked(this::handleMouseOnClickVolver);

        botonConfirmar.setOnMouseEntered(event -> {
            botonConfirmar.setStyle("-fx-border-color:#e77a00;-fx-background-color: #bb50bb; -fx-border-width: 3;");
        });
        botonConfirmar.setOnMouseExited(event -> {
            botonConfirmar.setStyle("-fx-border-color: #721572; -fx-background-color: #bb50bb;  -fx-border-width: 2.5;");
        });
        botonConfirmar.setOnMouseClicked(this::handleMouseOnClickConfirmar);

        botonCancelar.setOnMouseEntered(event -> {
            botonCancelar.setStyle("-fx-border-color:#e77a00;-fx-background-color: grey; -fx-border-width: 3;");
        });
        botonCancelar.setOnMouseExited(event -> {
            botonCancelar.setStyle("-fx-border-color: black; -fx-background-color: grey; -fx-border-width: 2.5;");
        });
        botonCancelar.setOnMouseClicked(this::handleMouseOnClickCancelar);
        deshabilitarBotonesConfirmacion();
    }

    public void actualizar() {
        deshabilitarBotonesConfirmacion();
        this.contenedorPokemonActual.getChildren().clear();
        this.contenedorPokemon.getChildren().clear();
        this.setPokemones(this.batalla.getPokemonesJugadorActual());
    }

    public void setPokemones(List<Pokemon> pokemones) {
        Pokemon pokemonActual = this.batalla.getJugadorActual().getPokemonActual();
        for (Pokemon pokemon : pokemones) {

            String ruta = "/Vistas/cartel-opcion-pokemon";

            if (pokemon == pokemonActual) {
                ruta += "-actual";
            }
            ruta += ".fxml";

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));

                Pane cartelPokemon = loader.load();

                ControladorCartelPokemon controladorCartel = loader.getController();
                controladorCartel.inicializar(pokemon, this);
                controladorCartel.setDatosPokemon();
                controladorCartel.setEstados();

                if (pokemon == pokemonActual) {
                    contenedorPokemonActual.getChildren().add(cartelPokemon);

                }else{
                    contenedorPokemon.getChildren().add(cartelPokemon);
                }

                if (pokemon.estaMuerto() && this.escenaAnterior != Escena.MENU_ITEMS.ordinal()){
                    cartelPokemon.setDisable(true);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        contenedorPokemonActual.setDisable((this.escenaAnterior == Escena.MENU_PRINCIPAL.ordinal()) || (escenaAnterior == Escena.PANTALLA_EFECTO.ordinal()));
    }

    public ControladorMenuPokemon getControlador() {
        return this;
    }

    @FXML private void handleMouseOnClickVolver(MouseEvent event){
        if (escenaAnterior == Escena.MENU_ITEMS.ordinal()){
            this.contenedorPrincipal.fireEvent(new CambioDeEscenaEvent(Escena.MENU_ITEMS.ordinal()));
        }else{
            this.contenedorPrincipal.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
        }
    }

    @FXML private void handleMouseOnClickConfirmar(MouseEvent event){
        this.contenedorPrincipal.fireEvent(new PokemonSeleccionadoEvent(this.pokemonElegido));
        this.contenedorPrincipal.fireEvent(new CambioDeEscenaEvent(Escena.PANTALLA_EFECTO.ordinal()));
    }

    @FXML private void handleMouseOnClickCancelar(MouseEvent event){
        this.actualizar();
    }

    private void deshabilitarBotonesConfirmacion(){
        botonCancelar.setOpacity(0);
        botonCancelar.setDisable(true);

        botonConfirmar.setOpacity(0);
        botonConfirmar.setDisable(true);

        contenedorBotonVolver.setOpacity(1);
        contenedorBotonVolver.setDisable(false);

        contenedorPokemon.setDisable(false);
        contenedorPokemonActual.setDisable(false);

        this.labelTexto.setStyle("-fx-font-size: 48.0; -fx-font-family: '000webfont';");
        this.labelTexto.setText("Seleccione un Pokemon");
    }

    public void habilitarBotonesConfirmacion(Pokemon pokemon){
        botonCancelar.setOpacity(1);
        botonCancelar.setDisable(false);

        botonConfirmar.setOpacity(1);
        botonConfirmar.setDisable(false);

        contenedorBotonVolver.setOpacity(0);
        contenedorBotonVolver.setDisable(true);

        contenedorPokemon.setDisable(true);
        contenedorPokemonActual.setDisable(true);

        this.pokemonElegido = pokemon;
        this.labelTexto.setStyle("-fx-font-size: 40.0; -fx-font-family: '000webfont';");
        this.labelTexto.setText("Has seleccionado a " + pokemon.getNombre());
    }

    @Override
    public void Update() {
        this.actualizar();
    }

    @Override
    public void Update(int escena) {
        this.escenaAnterior = this.escenaActual;
        this.escenaActual = escena;
        this.actualizar();
    }

}
