public class Caballero extends Combatiente {

    private int casa;
    private int cantidadPociones;
    private int usosDagas;

    public Caballero(String nombre, int casa) {
        super(nombre, 100,100, 10, 20);

        this.casa = casa;
        this.cantidadPociones = 3;
        this.usosDagas = 5;
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

    public int calcularDanio()
    {
        int danioNuevo;
        int danioRecibidoNuevo= 0;
        switch(this.casa)
        {
            
            //STARK
            case 1:
                danioNuevo= atacar() - 2;
                danioRecibidoNuevo -= 2;
                
                




        }
        return recibirDanio(danioRecibidoNuevo);
    }




}
