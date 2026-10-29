public class Combatiente
{
    private String nombre;
    private int vida;
    private int vidaMaxima;
    private int danioMaximo;
    private int danioMinimo;

    public Combatiente (String nombre, int vida,int vidaMaxima, int danioMinimo,int danioMaximo)
    {
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
        this.danioMaximo = danioMaximo;
        this. danioMinimo = danioMinimo;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getDanioMaximo() {
        return danioMaximo;
    }

    public void setDanioMaximo(int danioMaximo) {
        this.danioMaximo = danioMaximo;
    }

    public int getDanioMinimo() {
        return danioMinimo;
    }

    public void setDanioMinimo(int danioMinimo) {
        this.danioMinimo = danioMinimo;
    }

    public void recibirDanio(int danio)
    {
        this.vida -= danio; //Daño aplicado a la vida
        if (this.vida < 0) //Evita que la vida este en negativo, en caso de que el daño sobrepase la vida
            {
                this.vida= 0;
            }
    }

    public boolean estaVivo() //Determina si esta vivo o no.
    {
       return this.vida > 0; 
    }

    public int atacar() 
    {
        int danio;

        danio = (int) (Math.random() * (this.danioMaximo - this.danioMinimo + 1)) + this.danioMinimo;
        //Calcula un daño aleatorio dependiendo del rango de daño del objeto, 
        //El +1 sirve para incluir el valor máximo dentro de los resultados posibles.
        return danio;

    }

    public void mostarEstado()
    {
        System.out.println(getNombre() + " tiene " + getVida() + " puntos de vida.");
    }

}