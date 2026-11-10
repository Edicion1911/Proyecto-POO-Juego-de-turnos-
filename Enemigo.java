public class Enemigo extends Combatiente implements IDefendible
{
    private boolean protegiendo;
    private int ventaja;

    public Enemigo (String nombre, int vida, int ventaja)
    {
        super(nombre, vida, vida, 5, 15);
        this.protegiendo = false;
        this.ventaja= ventaja;
    }

    public int getVentaja()
    {
        return this.ventaja;
    }

    @Override 
    public void protegerse()
    {
        this.protegiendo = true;
    }

    @Override 
    public void recibirDanio(int danio)
    {
        if (protegiendo)
            {
                danio -= 3;
                protegiendo = false;
            }
        switch (ventaja) 
        {
            //Jaime
            case 1:   
                break;
            
            //Barristan
            case 2:
                danio -= 2;
                break;

            //Arthur Dayne
            case 3:
                danio -= 2;   
                break; 
        
        }
        
        if (danio < 0)
        {
         danio = 0;
        }

        super.recibirDanio(danio);
    }

    public int calcularDanio()
    {
        int danioNuevo = atacar();
        switch (ventaja) 
        {
            case 2: //BARRISTAN
                double probabilidad = Math.random();
                if(probabilidad < 0.15)
                {
                danioNuevo += 5; 
                System.out.println("¡GOLPE CRÍTICO! +5 DE DAÑO");
                }
                break;
            case 3://ATHUR DAYNE    
                danioNuevo += 3;
                break;
                
            default:
                break;
        }

        return danioNuevo;
    }


}
