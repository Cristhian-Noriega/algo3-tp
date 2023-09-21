package tp1.clases;

import java.util.List;
import java.util.stream.Collectors;

public class Pokemon {
    private final String nombre;
    private final int nivel;
    private Estado estado;
    private final Tipo tipo;
    private final List<Habilidad> habilidades;
    private int vidaMax;
    private int vidaActual;
    private int velocidad;
    private int ataque;
    private int defensa;
    
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
    }

    public int atacar(Integer indiceAtaque, Pokemon enemigo) {
        Habilidad ataque = this.habilidades.get(indiceAtaque); // agregue esto para que el Jugador seleccione numero, habilidad como tipo solo se acceden desde clase Pokemon xq es qn las tiene. (emi)

        if (ataque.getUsos() == 0) {
            throw new NoHayMasAtaquesException();
        }

        Tipo tipoAtaque = ataque.getTipo();
        float efectividad = ataque.calcularEfectividad(tipoPokemon, enemigo.obtenerTipo()); //hay que ver si la efectividad la ponemos en ataque o batalla
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
        }
    }

    public boolean estaMuerto() {
        return this.vidaActual <= 0;
    }

    public void modificarAtaque(int modificador) { this.ataque += modificador; }

    public void modificarDefensa(int modificador) {this.defensa += modificador; }

    public void modificadorVelocidad(int modificador) { this.velocidad += modificador; }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void modificarVida(int aumento) { this.vidaActual += aumento; }

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

    public int getVidaMax() { return this.vidaMax; }

    public int getAtaque(){
        return this.ataque;
    }
    public int getDefensa(){
        return this.defensa;
    }

    public int getVelocidad(){
        return this.velocidad;
    }

    public Estado getEstado() {
        return this.estado;
    }
}



