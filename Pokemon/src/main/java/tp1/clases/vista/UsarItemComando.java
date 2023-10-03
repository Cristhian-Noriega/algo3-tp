package tp1.clases.vista;

import tp1.clases.modelo.Batalla;

public class UsarItemComando implements Comando {

    private final Batalla batalla;
    private int item; //no se si esta bien esto

    public UsarItemComando(Batalla batalla) {
        this.batalla = batalla;
    }

    @Override
    public void definirOpcion(int op){
        this.item = op;
    }
    public void ejecutar() {
        this.batalla.usarItem(this.item);

    }
}
