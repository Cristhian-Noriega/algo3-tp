package tp1.clases.controlador;

public abstract class Menu {

    protected CategoriaMenu categoria;

    public abstract void mostrarOpciones();

    public abstract int cantidadOpciones();

    public abstract CategoriaMenu getCategoria();

}
