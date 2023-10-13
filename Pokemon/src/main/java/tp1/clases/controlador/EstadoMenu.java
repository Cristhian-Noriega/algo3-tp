package tp1.clases.controlador;

import tp1.clases.vista.OpcionMenu;

public abstract class EstadoMenu {
    protected EstadoMenu estadoAnterior;

    protected EstadoMenu estadoActual;

    public abstract String retroceder();
    public abstract String concretar();
    public abstract void mostrarAcciones();
}
