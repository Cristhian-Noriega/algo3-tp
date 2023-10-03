package tp1.clases.vista;

import tp1.clases.modelo.Batalla;

public class UsarHabilidadComando implements Comando{

    private final Batalla batalla;

    private int habilidad;

    public UsarHabilidadComando(Batalla batalla) {
        this.batalla = batalla;
    }


    @Override
    public void definirOpcion(int op){
        this.habilidad = op;
    }

    public void ejecutar(){
        this.batalla.usarAtaque(this.habilidad);
    }
}
