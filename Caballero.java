public class Caballero extends Combatiente {

    private int casa;
    private int cantidadPociones;
    private int usosDagas;
    private int cargasFuria;

    public Caballero(String nombre, int casa) {
        super(nombre, 100,100, 10, 20);

        this.casa = casa;
        this.cantidadPociones = 3;
        this.usosDagas = 5;
        this.cargasFuria= 0;
    }


    public void setCasa(int casa)
    {
        this.casa = casa;
    }

    public int getCasa()
    {
        return this.casa;
    }

    public void setCantidadPociones(int cantidadPociones)
    {
        this.cantidadPociones = cantidadPociones;
    }

    public int getCantidadPociones()
    {
        return this.cantidadPociones;
    }

    public void setUsosDagas (int usosDagas)
    {
        this.usosDagas = usosDagas;
    }

    public int getUsosDagas ()
    {
        return this.usosDagas;
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
                double Probabilidad = Math.random();
                if(Probabilidad < 0.15)
                {
                danioNuevo = atacar() + 5; 
                System.out.println("¡GOLPE CRÍTICO! +5 DE DAÑO");
                }
                break;

            //BARATHEON
            case 4:
               danioNuevo = atacar() + (cargasFuria * 3);
               cargasFuria= 0;
               break;

            default:
                System.out.println("No es una casa existente");
                break;
            
    }
    return danioNuevo;

}


}
