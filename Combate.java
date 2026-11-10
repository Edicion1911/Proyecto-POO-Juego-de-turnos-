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

    public boolean procesarTurno()
    {
        boolean consumeTurno = false;
        int opcion;

        mostarOpciones();
        opcion = sc.nextInt();
        
        switch (opcion) {
            case 1:
                int danio = caballero.calcularDanio();
                enemigo.recibirDanio(danio);
                consumeTurno = true;
                break;
            case 2:
                caballero.protegerse();
                consumeTurno = true;
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

        if (probabilidad < 0.7)
            {
                int danio = enemigo.calcularDanio();
                caballero.recibirDanio(danio);
                System.out.println(enemigo.getNombre() + " le ha causado " + danio + " daño a " + caballero.getNombre());
            }
        else 
            {
                enemigo.protegerse();
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

    }




}
