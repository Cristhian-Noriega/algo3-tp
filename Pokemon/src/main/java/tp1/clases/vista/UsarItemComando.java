package tp1.clases.vista;

import tp1.clases.errores.Error;
import tp1.clases.modelo.Batalla;

import java.util.Optional;

public class UsarItemComando implements Comando {

    private final Batalla batalla;
    private int item;

    public UsarItemComando(Batalla batalla) {
        this.batalla = batalla;
    }

    @Override
    public void definirOpcion(int op){
        this.item = op;
    }
    public Optional<Error> ejecutar() {
        return this.batalla.usarItem(this.item);
    }
}
