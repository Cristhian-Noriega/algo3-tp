package tp1.clases.controlador;

import tp1.clases.modelo.*;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

public class MensajesPantallaEfecto {

    public static String mostrarResultado(InfoHabilidad infoHabilidad, String habilidad, String pokemonAtacante) {
        String resultado = pokemonAtacante + " ha usado la habilidad " + habilidad + ". ";
        switch (infoHabilidad.getCategoria()) {
            case ATAQUE -> resultado += mensajeAtaque(infoHabilidad.getDanio(), infoHabilidad.beneficiadoPorClima());
            case CLIMA -> resultado += mensajeResultadoClima(infoHabilidad.getClimaModificado());
            case ESTADO -> resultado += mensajeResultadoEstado(infoHabilidad.getEstadoModificado());
            case ESTADISTICA -> resultado += mensajeResultadoEstadistica(infoHabilidad.getEstadisticaModificada(), infoHabilidad.getJugadorAfectado());
        }
        return resultado;
    }

    public static String mensajeAtaque(double danio, boolean beneficiado) {
        String resultado = "";
        if (beneficiado) {
            resultado += "El ataque ha sido beneficiado por el clima actual. ";
        }
        if (danio > 0) {
            return resultado + "¡Qué eficaz!";
        } else {
            return resultado + "No parece hacer mucho efecto.";
        }
    }

    public static String mensajeResultadoClima(Clima clima) {
        return "Ahora el clima es " + clima.name().toLowerCase();
    }

    public static String mensajeResultadoEstado(Estado estado) {
        return "Ahora el enemigo se encuentra " + estado.name().toLowerCase();
    }

    public static String mensajeResultadoEstadistica(Estadisticas estadisticas, JugadorEnum jugadorAfectado) {
        if (jugadorAfectado == JugadorEnum.ACTUAL) {
            return "Su " + estadisticas.name().toLowerCase() + " ha aumentado";
        } else {
            return "La estadistica '" + estadisticas.name().toLowerCase() + "' del rival ha disminuido";
        }
    }

    public static Queue<String> encolarMensajes(InfoTurno infoTurno) {
        Queue<String> colaMensajes = new ArrayDeque<String>();

        for (Pokemon pokemon: infoTurno.getPokemonesAfectadosPorClima()) {
            colaMensajes.add(pokemon.getNombre() + " fue afectado por el clima actual y su vida actual ha disminuido.");
        }

        for (Pokemon pokemon: infoTurno.getPokemonesEnvenenados()) {
            colaMensajes.add(pokemon.getNombre() + " perdió vida por estar envenenado.");
        }

        for (Map.Entry<Pokemon, Estado> entrada: infoTurno.getEstadosReseteados().entrySet()){
            colaMensajes.add(entrada.getKey().getNombre() + " perdió el estado " +  entrada.getValue().name().toLowerCase());
        }
        return colaMensajes;
    }


}
