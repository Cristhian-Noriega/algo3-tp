package tp1.clases.vista;

public enum OpcionMenu {
    VER_CAMPO(1, "Ver Campo"),
    VER_POKEMONES(2,"Ver Pokemones"),
    VER_ITEM(3,"Ver Item"),
    VER_HABILIDAD(4, "Ver habilidad"),
    RENDIRSE(5, "Rendirse");

    private int opcion;
    private String descripcion;


    OpcionMenu(int opcion, String descripcion){
        this.opcion = opcion;
        this.descripcion = descripcion;
    }

    public int getOpcion() {
        return opcion;
    }


    public String getDescripcion(){
        return descripcion;
    }

    public static OpcionMenu getAccion(int op) {
        for (OpcionMenu accion: OpcionMenu.values()) {
            if (accion.getOpcion() == op) {
                return accion;
            }
        }
        return null;
    }


}