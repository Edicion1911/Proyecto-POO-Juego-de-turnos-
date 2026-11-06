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
        this.protegiendo= true;
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


}
