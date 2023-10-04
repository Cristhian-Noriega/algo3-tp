package tp1.clases.vista;

public enum OpcionMenu {

    VOLVER_ATRAS("Volver Atrás"),
    VER_CAMPO("Ver Campo"),
    VER_POKEMONES("Ver Pokemones"),
    VER_ITEM("Ver Item"),
    VER_HABILIDAD("Ver habilidad"),
    RENDIRSE("Rendirse");

    private final String descripcion;

    OpcionMenu(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public static OpcionMenu getAccion(int op) {
        for (OpcionMenu accion: OpcionMenu.values()) {
            if (accion.ordinal() == op) {
                return accion;
            }
        }
        return null;
    }

}