    package tp1.clases.controlador.comandos;

    import tp1.clases.controlador.ControladorEstados;
    import tp1.clases.errores.Error;
    import tp1.clases.modelo.Batalla;
    import tp1.clases.modelo.Categoria;

    import java.util.Optional;

    public class UsarItemComando implements Comando {

        private final Batalla batalla;
        private final ControladorEstados controladorEstados;
        private int item;
        private int pokemon;

        public UsarItemComando(Batalla batalla, ControladorEstados controladorEstados) {
            this.batalla = batalla;
            this.controladorEstados = controladorEstados;
        }

        @Override
        public void definirPokemon(int pokemon){
            this.pokemon = pokemon;
        }

        @Override
        public void definirOpcion(int op){
            this.item = op;
        }

        public Optional<Error> ejecutar() {
            Optional<Error> err = this.batalla.usarItem(this.item, this.pokemon);
            if (err.isEmpty() && (this.batalla.getItemsJugadorActual().get(item).getCategoria() == Categoria.ESTADO)) {
                  this.controladorEstados.setTurnoInicial(this.batalla.getJugadorActual(), batalla.getTurno());
            }
            return err;
        }
    }
