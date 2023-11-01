package tp1.clases.modelo;

public class DatosPokemon {
    private Integer id;
    private Integer vidaRestante;
    private String estado;

    public void DatosPokemon(Integer id, Integer vidaRestante, String estado){
        this.id = id;
        this.vidaRestante = vidaRestante;
        this.estado = estado;
    }
}
