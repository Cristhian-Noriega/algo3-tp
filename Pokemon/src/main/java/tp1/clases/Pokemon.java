package tp1.clases;

import java.util.List;
import java.util.stream.Collectors;

public class Pokemon {
    private final String nombre;
    private int nivel;
    private HabilidadEstado.Estado estado;
    private final Tipo tipo;
    private final List<Habilidad> habilidades; //no estoy seguro si las habilidades del pokemon pueden variar(cambiar en la partida)
    private int vidaMax;
    private int vidaActual;
    private int velocidad;
    private int ataque;
    private int defensa;
    private Boolean estaKO;


    public Pokemon(String nombre, int nivel, Tipo tipo,
                   List<Habilidad> habilidades, int vidaMax, int velocidad, int ataque, int defensa) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
        this.habilidades = habilidades;
        this.vidaMax = vidaMax;
        this.vidaActual = vidaMax;
        this.velocidad = velocidad;
        this.ataque = ataque;
        this.defensa = defensa;
        this.estaKO = false;
    }


    public String verHabilidades() {
        return "Habilidades de " + this.nombre + ":\n" + generarCadenaHabilidades(this.habilidades);
    }

    public String verAtaques() {
        return "Ataques de " + this.nombre + ":\n" +
                habilidades.stream()
                        .filter(habilidad -> habilidad instanceof HabilidadAtaque)
                        .map(habilidad -> habilidad.getNombre() + "-" + habilidad.getInfo())
                        .collect(Collectors.joining("\n"));
    }

    private String generarCadenaHabilidades(List<Habilidad> habilidades) {
        return habilidades.stream()
                .map(habilidad -> habilidad.getNombre() + "-" + habilidad.getInfo())
                .collect(Collectors.joining("\n"));
        // hay que probarlo, no se si anda xd
    }

    public int atacar(HabilidadAtaque ataque, Pokemon enemigo) {
        if (ataque.getUsos() == 0) {
            throw new NoHayMasAtaquesException(); //estaria bueno tener una clase/interfaz excepciones y manejar todas ahi, o algo asi
        }

        Tipo tipoAtaque = ataque.getTipo();
        float efectividad = ataque.calcularEfectividad(tipoPokemon, enemigo.obtenerTipo());
        int danio = ataque.calcularDanio(nivelAtacante, ataqueAtacante);
        int defensaEnemigo = enemigo.getDefensa();

        enemigo.recibirDanio(danio);
        ataque.usarAtaque();

        return danio;
    }

    public void recibirDanio(int danio) {
        vidaActual -= danio;
        if (this.estaMuerto()) {
            this.vidaMax = 0;
            this.estaKO = true;
        }
    }

    public void usarItem(Item item){
        item.usarItem(this);
    }

    public boolean estaMuerto() {
        return this.vidaActual <= 0;
    }

    //supongo que estara bien tener los metodos de modificascion de stats en pokmeon ?)
    public void aumentarAtaque(int aumento) {
        this.ataque += aumento;
    }

    public void disminuirAtaque(int disminucion) {
        this.ataque -= disminucion;
    }

    public void aumentarDefensa(int aumento) {
        this.defensa += aumento;
    }
    public void disminuirDefensa(int disminucion) {
        this.defensa -= disminucion;
    }

    public void aumentarVelocidad(int aumento) { this.velocidad += aumento; }

    public void disminuirVelocidad(int disminucion) { this.velocidad -= disminucion; }
    public void setEstado(HabilidadEstado.Estado estado) {
        this.estado = estado;
    }

    public List<Habilidad> getHabilidades() { return this.habilidades; }

    public Tipo getTipo() {
        return this.tipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getNivel() {
        return this.nivel;
    }

    public int getVida(){
        return this.vidaActual;
    }

    public int getAtaque(){
        return this.ataque;
    }
    public int getDefensa(){
        return this.defensa;
    }

    public int getVelocidad(){
        return this.velocidad;
    }

    public HabilidadEstado.Estado getEstado() {
        return this.estado;
    }

}



