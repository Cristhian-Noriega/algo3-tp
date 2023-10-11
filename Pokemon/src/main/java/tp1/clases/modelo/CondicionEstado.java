package tp1.clases.modelo;

import tp1.clases.controlador.ControladorEstados;

public interface CondicionEstado {
    void aplicarEfecto(Pokemon pokemon, ControladorEstados controladorEstados);
}
