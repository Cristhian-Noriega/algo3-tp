package tp1.clases.vista;

public interface Comando {
    void ejecutar();

    default void definirOpcion(int op){

    }
}
