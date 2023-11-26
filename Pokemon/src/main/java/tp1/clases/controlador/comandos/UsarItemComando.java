    package tp1.clases.controlador.comandos;

    import tp1.clases.errores.Error;
    import tp1.clases.modelo.Batalla;
    import tp1.clases.modelo.Item;
    import tp1.clases.modelo.Pokemon;

    import java.util.Optional;

    public class UsarItemComando implements Comando {

        private final Batalla batalla;
        private Item item;
        private Pokemon pokemon;

        public UsarItemComando(Batalla batalla) {
            this.batalla = batalla;
        }

        public void definirPokemon(Pokemon pokemon){
            this.pokemon = pokemon;
        }

        public void definirOpcion(Item op){
            this.item = op;
        }

        public Optional<Error> ejecutar() {
            return this.batalla.usarItem(this.item, this.pokemon);
        }
    }
