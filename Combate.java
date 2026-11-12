import java.util.Scanner;

public class Combate 
{
    private Enemigo enemigo;
    private Caballero caballero;
    private Scanner sc;
    private boolean continuarCombate;

    public Combate(Caballero caballero, Enemigo enemigo)
    {
    this.caballero = caballero;
    this.enemigo = enemigo;
    this.sc= new Scanner(System.in);
    this.continuarCombate = true;
    } 

    public void mostrarEstadoCombate()
    {
        System.out.println("=== ESTADO DEL COMBATE ===\n");
        System.out.println(caballero.getNombre() + ": \n");
        caballero.mostrarEstado();
        System.out.println("");
        System.out.println(enemigo.getNombre() + " : \n");
        enemigo.mostrarEstado();
        System.out.println("\n===========================");
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

    public boolean procesarTurno()
    {
        boolean consumeTurno = false;
        int opcion;

        mostarOpciones();
        opcion = sc.nextInt();
        
        switch (opcion) {
            case 1:
                int danio = caballero.calcularDanio();
                int danioRecibido= enemigo.recibirDanio(danio);
                consumeTurno = true;
                System.out.println(caballero.getNombre() + ": \n");
                System.out.println(caballero.getNombre() + " le ha causado " + danioRecibido + " puntos de daño a " + enemigo.getNombre());
                System.out.println("");
                break;
            case 2:
                caballero.protegerse();
                consumeTurno = true;
                System.out.println(caballero.getNombre() + ": \n");
                System.out.println(caballero.getNombre() + " se ha protegido para el siguiente ataque.");
                break;
            case 3:        
                consumeTurno = caballero.usarPocion();
                break;
            case 4:
                caballero.mostrarInventario(); 
                break;
            case 5:
                continuarCombate = false;
                break;
            default:
                System.out.println("No es una opción valida, intenta de nuevo!");
                break;

        }

        return consumeTurno;
    }

    public void turnoEnemigo()
    {
        double probabilidad = Math.random();

        if (probabilidad < 0.7) //70% de probabilidad de atacar
            {
                int danio = enemigo.calcularDanio();
                int danioRecibido= caballero.recibirDanio(danio);
                System.out.println(enemigo.getNombre() + ": \n");
                System.out.println(enemigo.getNombre() + " le ha causado " + danioRecibido + " puntos de daño a " + caballero.getNombre());
            }
        else  //30% de probabilidad de protegerse
            {
                enemigo.protegerse();
                System.out.println(enemigo.getNombre() + ": \n");
                System.out.println(enemigo.getNombre() + " ha decidido protegerse en este turno." );
            }    

    }

    public void simularCombate()
    {
        continuarCombate = true;
        while (caballero.estaVivo() && enemigo.estaVivo() && continuarCombate) 
        {
             mostrarEstadoCombate();

             boolean consumeTurno = procesarTurno();

             if (consumeTurno && enemigo.estaVivo() && caballero.estaVivo())
                {
                    turnoEnemigo();
                }
        }
        mostrarEstadoCombate();
        finalizarCombate();

    }

    public void finalizarCombate()
    {
        if (!caballero.estaVivo())
        {
            System.out.println("\n¡Has sido derrotado!\n");
        }
        else if (!enemigo.estaVivo())
        {
            System.out.println("\n¡Has derrotado a " + enemigo.getNombre() + "! ¡Felicidades!\n");
        }
        else if(!continuarCombate)
        {
            System.out.println("\n¡Has abandonado el combate!\n");
        }
    }

}
