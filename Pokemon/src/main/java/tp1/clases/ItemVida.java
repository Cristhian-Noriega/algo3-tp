package tp1.clases;

public class ItemVida extends Item{

    public enum CantVida{
        VEINTE, CINCUENTA, CIEN, REVIVIR;
    }

    private final CantVida vida;

    public ItemVida(String nombre, CantVida vida){
        super.nombre = nombre;
        this.vida = vida;
    }

    public void usar(Pokemon pokemon){
        switch (this.vida){
            case REVIVIR  && pokemon.estaMuerto():
                pokemon.modificarVida(pokemon.getVidaMaxima);
                System.out.println("¡Pokemon " + pokemon.getNombre() + " ha revivido!!");
            case REVIVIR -> System.out.println("El pokemon " + pokemon.getNombre() + " no esta muerto, no se puede usar el Item " + this.nombre);
            default:
                pokemon.modificarVida(this.vida);
                System.out.println("¡" + this.nombre + " ha sido usada!");
                System.out.println("Vida actual de " + pokemon.getNombre() + ": " + pokemon.getVida());
        }
    }

}
