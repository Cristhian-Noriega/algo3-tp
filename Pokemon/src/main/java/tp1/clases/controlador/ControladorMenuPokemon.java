package tp1.clases.controlador;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tp1.clases.eventos.CambioDeEscenaEvent;
import tp1.clases.modelo.*;

import java.io.IOException;
import java.util.List;


public class ControladorMenuPokemon implements Controlador, Subscriptor, SubscriptorEscena {
    @FXML public VBox contenedorPokemonActual;
    @FXML public HBox contenedorBotonVolver;
    @FXML public StackPane contenedorPrincipal;
    @FXML public VBox contenedorPokemon;
    private Batalla batalla;
    private int escenaAnterior;
    private int escenaActual;

    @Override
    public void inicializar(Batalla batalla) {
        this.batalla = batalla;
        this.batalla.getAdministradorTurnos().agregarSubscriptor(this);
        List<Pokemon> pokemones = this.batalla.getJugadorActual().getListaPokemones();

        contenedorPokemon.getChildren().clear();
        contenedorPokemonActual.getChildren().clear();
        this.setPokemones(pokemones);

        contenedorBotonVolver.setOnMouseEntered(this::handleMouseEntered);
        contenedorBotonVolver.setOnMouseExited(this::handleMouseExited);
        contenedorBotonVolver.setOnMouseClicked(this::handleMouseOnClick);
    }

    public void actualizar() {
        contenedorPokemon.getChildren().clear();
        contenedorPokemonActual.getChildren().clear();
        this.setPokemones(this.batalla.getPokemonesJugadorActual());
    }

    public void setPokemones(List<Pokemon> pokemones){
        Boolean focusAplicado = false;
        contenedorPokemon.getChildren().clear();
        for (Pokemon pokemon : pokemones) {

            String ruta = "/cartel-opcion-pokemon";
            if (pokemon == this.batalla.getJugadorActual().getPokemonActual()) {
                ruta += "-actual";
            }
            ruta += ".fxml";

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));

                Pane cartelPokemon = loader.load();

                ControladorCartelPokemon controladorCartel = loader.getController();
                controladorCartel.inicializar(pokemon);
                controladorCartel.setDatosPokemon();
                controladorCartel.setEstados();

                if (pokemon == this.batalla.getJugadorActual().getPokemonActual()) {
                    contenedorPokemonActual.getChildren().add(cartelPokemon);

                }else{
                    contenedorPokemon.getChildren().add(cartelPokemon);

//                    if (!focusAplicado){
  //                      controladorCartel.handleMouseEntered(null);
    //                    focusAplicado = true;
      //              }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        contenedorPokemonActual.setDisable((this.escenaAnterior == Escena.MENU_PRINCIPAL.ordinal()) || (escenaAnterior == Escena.PANTALLA_EFECTO.ordinal()));

        contenedorPokemon.getChildren().get(0).setStyle("-fx-border-color:#e77a00; -fx-border-radius: 3%; -fx-border-width: 5;");
        contenedorPokemon.setOnMouseEntered(event -> {
            contenedorPokemon.getChildren().get(0).setStyle("-fx-border-color:black; -fx-border-radius: 3%; -fx-border-width: 3;");
        });
    }

    public ControladorMenuPokemon getControlador() {
        return this;
    }

    public void handleMouseEntered(MouseEvent event) {
        contenedorBotonVolver.setStyle("-fx-border-color:#e77a00;-fx-background-color: #bb50bb; -fx-border-radius: 3%; -fx-border-width: 5;");
    }

    public void handleMouseExited(MouseEvent event) {
        contenedorBotonVolver.setStyle("-fx-border-color: #721572; -fx-background-color: #bb50bb; -fx-border-radius: 3%; -fx-border-width: 5;");
    }

    @FXML
    private void handleMouseOnClick(MouseEvent event){
        if (escenaAnterior == Escena.MENU_ITEMS.ordinal()){
            this.contenedorPrincipal.fireEvent(new CambioDeEscenaEvent(Escena.MENU_ITEMS.ordinal()));
        }else{
            this.contenedorPrincipal.fireEvent(new CambioDeEscenaEvent(Escena.MENU_PRINCIPAL.ordinal()));
        }
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
