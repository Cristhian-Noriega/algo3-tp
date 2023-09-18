package tp1.clases;

import java.util.List;

public class Pokemon {
    private String nombre;
    private int nivel;
    private HabilidadEstado.Estado estado;
    private Tipo tipo;
    private List<Habilidad> habilidades;
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
        this.vidaActual = vidaMax; // La vida actual al inicio es igual a la vida max
        this.velocidad = velocidad;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public int atacar(HabilidadAtaque ataque, Pokemon enemigo) {
        if !ataque.getUsos == 0 { //cada ataque puede usarse x cantidad de veces
            throw new NoHayMasAtaquesException(); //estaria bueno tener una clase/interfaz excepciones y manejar todas ahi, o algo asi
        }

        Tipo tipoAtaque = ataque.getTipo();
        float efectividad = ataque.calcularEfectividad(tipoPokemon, enemigo.obtenerTipo());
        int danio = ataque.calcularDanio(nivelAtacante, ataqueAtacante);
        int defensaEnemigo = enemigo.obtenerDefensa();

        enemigo.recibirDanio(danio);
        ataque.usarAtaque();

        return danio;

    }

    public int recibirDanio(int danio){
        vidaActual -= danio;
        if this.estaMuerto(){
            //hay que manejar la logica de cuando ya no tiene vida

        }
        return danio;
    }

    public void setEstado(HabilidadEstado.Estado estado) {
        this.estado = estado;
    }



    public void usarItem(Item item){
        if !this.puedeUsarItem(item) {
            throw new NoPuedeUsarItemException();
        }
        item.usarItem(this);
    }

    public void actualizarEstado() {

    }

    public List<Habilidad> verHabilidades() {

    }
    

    public Tipo getTipo() {
        return this.tipo;
    }

    public String getNombre() { return this.nombre;}

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

    public boolean estaMuerto() {
        return this.vidaActual == 0;
    }

    public Estado getEstado() { return this.estado; }

    public boolean puedeUsarItem(Item item){
        //tengo que codearlo :P
    }
}



