package tp1.clases;

import com.fasterxml.jackson.databind.ObjectMapper;
import tp1.clases.modelo.Jugador;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Finalizador {
    private final Jugador ganador;
    private final Jugador perdedor;

    public Finalizador(Jugador ganador, Jugador perdedor){
            this.ganador = ganador;
            this.perdedor = perdedor;
    }

    public void crearJsonPartida() throws IOException {
        ResultadosJugador resultadosGanador = new ResultadosJugador(this.ganador.getNombre(), true, this.ganador.getMapCantidadItems(), this.ganador.getListaPokemones());
        ResultadosJugador resultadosPerdedor = new ResultadosJugador(this.perdedor.getNombre(), false, this.perdedor.getMapCantidadItems(), this.perdedor.getListaPokemones());
        ArrayList<ResultadosJugador> resultados = new ArrayList<>();
        resultados.add(resultadosGanador);
        resultados.add(resultadosPerdedor);

        String path = "resultados/informe" + horaActual() + ".json";
        ObjectMapper objectMapper = new ObjectMapper();
        // Serializacion
        objectMapper.writeValue(new File(path), resultados);

    }

    private static String horaActual(){
        String timeZoneId = "America/Argentina/Buenos_Aires";
        ZoneId zoneId = ZoneId.of(timeZoneId);
        LocalTime currentTime = LocalTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_ss");
        String formattedTime = currentTime.format(formatter);

        return formattedTime;
    }
}
