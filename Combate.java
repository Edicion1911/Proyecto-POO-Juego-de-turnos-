import java.util.Scanner;

public class Combate 
{
    private Enemigo enemigo;
    private Caballero caballero;
    private Scanner sc;

    public Combate(Caballero caballero, Enemigo enemigo)
    {
    this.caballero = caballero;
    this.enemigo = enemigo;
    } 

    public void mostrarEstadoCombate()
    {
        System.out.println("=== ESTADO DEL COMBATE ===");

        caballero.mostrarEstado();
    
        enemigo.mostrarEstado();
    }

    public void mostarOpciones()
    {
        System.out.println("=== TU TURNO ===");
        System.out.println();
        System.out.println("1. Atacar");
        System.out.println("2. Protegerse");
        System.out.println("3. Usar Poción de vida");
        System.out.println("4. Consultar Inventario");
        System.out.println("5. Abandonar Combate");
        System.out.println();
        System.out.println("================");

    }

    public void procesarTurno()
    {
        
    }




}
