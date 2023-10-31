    package tp1.clases.controlador.comandos;

    import tp1.clases.errores.Error;
    import tp1.clases.modelo.Batalla;

    import java.util.Optional;

    public class UsarItemComando implements Comando {

        private final Batalla batalla;
        private int item;
        private int pokemon;

        public UsarItemComando(Batalla batalla) {
            this.batalla = batalla;
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
            return this.batalla.usarItem(this.item, this.pokemon);
        }
    }
