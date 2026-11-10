public class Caballero extends Combatiente implements IDefendible
{

    private final int casa;
    private int cantidadPociones;
    private int cargasFuria;
    private boolean protegiendo;
    private int[] pociones;

    public Caballero(String nombre, int casa) {
        super(nombre, 100,100, 10, 20);

        this.casa = casa;
        this.cantidadPociones = 3;
        this.cargasFuria= 0;
        this.pociones = new int[3];
        for (int i = 0; i < pociones.length; i++) 
        {
            pociones[i]= 1;
        }
        this.protegiendo= false;
        
    }


    public int getCasa()
    {
        return this.casa;
    }


    public int getCantidadPociones()
    {
        return this.cantidadPociones;
    }
    

    //Metódo de acumula las cargas para ventaja BARATHEON
    public void acumularFuria() 
    {

       if (this.cargasFuria < 2) 
        {
        this.cargasFuria++;
        System.out.println("¡La furia Baratheon aumenta!");
        } else 
        {
        System.out.println("¡Has alcanzado la furia máxima!");
        }

    }

    public int calcularDanio()
    {
        int danioNuevo= 0;
        switch(casa)
        {
            
            //STARK
            case 1:
                //DESVENTAJA de daño
                danioNuevo= atacar() - 2;
                break;

            //LANNISTER    
            case 2:
                //BUFF de daño
                danioNuevo = atacar() + 2;
                break;
            
            //TARGARYEN    
            case 3:
                //Probabilidad de critico
                danioNuevo = atacar();
                double probabilidad = Math.random();
                if(probabilidad < 0.15)
                {
                danioNuevo += 5; 
                System.out.println("¡GOLPE CRÍTICO! +5 DE DAÑO");
                }
                break;

            //BARATHEON
            case 4:
               danioNuevo = atacar() + (cargasFuria * 3);
               cargasFuria= 0;
               break;

            //Caballero Errante
            default:
                danioNuevo = atacar();
                break;
            
    }
    return danioNuevo;

    }
    
    @Override 
    public void recibirDanio(int danio)
    {
        if (protegiendo) //El método protegerse() activa esta condición
        {
        danio -= 3;
        protegiendo = false;
        }
        switch(casa)
        {   //STARK
            case 1:
                //Recibe 2 puntos menos de daño
                danio -= 2;
                break;
            //LANNISTER
            case 2:
                //recibe 2 puntos mas de daño
                danio += 2;
                break;  

        }
        // Esto es opcional pero es una proteccion por si se cambia algo del juego.
        //Ejemplo: Si STARK recibe 1 de daño, la reduccion de 2 puntos de daño, haria que recuperara vida en el metodo padre.
        if (danio < 0)
            {
                danio = 0;
            }

        //Llamo al metodo de la clase padre para que guarde el daño aplicado en este metodo, y lo aplique en su metodo.
        super.recibirDanio(danio);
    } 

    @Override 
    public void protegerse()
    {
        this.protegiendo = true;
        if (casa == 4) //Si la casa es Baratheon y se protege, acumula furia
            {
                acumularFuria();
            }

    }

    public boolean usarPocion()
    {
    boolean consumirPocion;
    
        if (this.cantidadPociones > 0) //Se comprueba si hay pociones disponibles
            {
                if (getVida() < getVidaMaxima()) //Se comprueba si el caballero no tiene la vida al maximo
                    {
                        recuperarVida(25);//Recupera 25 de vida!
                        System.out.println("¡Has recuperado 25 de vida!");
                        for (int i = 0; i < pociones.length; i ++)
                            {
                                if (pociones [i] == 1) // Comprueba si hay una pocion en el array 
                                {
                                   pociones [i]= 0; //Una posicion de la array devolvera 0.
                                   this.cantidadPociones --;//Pocion consumida
                                   break; //Evita que se consuma todas las pociones disponibles
                                } 
                            }
                        consumirPocion = true;
                    }
            
                else           
                {
                    System.out.println("¡Tienes la vida al Máximo!");
                    consumirPocion = false;
                }

            }
        else
            {
                System.out.println("¡No tienes pociones de vida disponibles!");
                consumirPocion = false;
            } 
            
    return consumirPocion;
    }

    public void mostrarInventario()
    {
        System.out.println("=== INVENTARIO ===");

        for (int i = 0; i < pociones.length; i++)
        {
           
            if (pociones[i] == 1)
            {
              System.out.println("1 Poción de vida"); // Imprimira este mensaje en las posiciones de array que sean iaguales a 1

            }
        }
        System.out.println("Pociones de vida disponibles: " + cantidadPociones);
    
    }


}
