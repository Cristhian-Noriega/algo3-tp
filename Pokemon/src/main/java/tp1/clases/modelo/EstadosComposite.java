package tp1.clases.modelo;

import java.util.ArrayList;
import java.util.List;

public class EstadosComposite extends PokemonDecorator{
    private List<PokemonDecorator> decoradores;

    public EstadosComposite(Pokemon pokemon){
        super(pokemon);
        this.decoradores = new ArrayList<>();
    }

    public void agregarDecorator(PokemonDecorator decorador){
        if (!decoradores.contains(decorador)) {
            decoradores.add(decorador);
        }
    }

    public void eliminarDecorador(PokemonDecorator decorator){
        decoradores.remove(decorator);
    }
    public void aplicarEfectoEstado(){
        for (PokemonDecorator decorador: decoradores){
            decorador.aplicarEfectoEstado();
        }
    }
}
