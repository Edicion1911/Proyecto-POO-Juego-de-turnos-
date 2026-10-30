public class Caballero extends Combatiente {

    private String casa;
    private int cantidadPociones;
    private int usosDagas;

    public Caballero(String nombre, String casa) {
        super(nombre, 100,100, 10, 20);

        this.casa = casa;
        this.cantidadPociones = 3;
        this.usosDagas = 5;
    }

    
}
