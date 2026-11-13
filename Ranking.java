public class Ranking
{
    private int[] puntuaciones;
    private String [] nombres;
    private int cantidadRegistros;

    public Ranking()
    {
        this.nombres = new String [10];
        this.puntuaciones = new int[10];
        this.cantidadRegistros = 0;
    }

    public void registrarPuntuacion(String nombre, int puntuacion) {

    // Comprobar si el ranking está lleno
    if (cantidadRegistros == 10) {

        // Si no supera la última puntuación, no entra
        if (puntuacion <= puntuaciones[9]) {
            System.out.println("No has conseguido entrar en el Ranking.");
            return;
        }
    }

    // Buscar la posición donde debe entrar el jugador
    int posicion = cantidadRegistros;

    for (int i = 0; i < cantidadRegistros; i++) {
        if (puntuacion > puntuaciones[i]) {
            posicion = i;
            break;
        }
    }

    // Si el ranking está lleno, descartamos al último
    if (cantidadRegistros == 10) {
        cantidadRegistros--;
    }

    // Desplazar los jugadores hacia la derecha
    for (int i = cantidadRegistros; i > posicion; i--) {
        nombres[i] = nombres[i - 1];
        puntuaciones[i] = puntuaciones[i - 1];
    }

    // Insertar al nuevo jugador
    nombres[posicion] = nombre;
    puntuaciones[posicion] = puntuacion;

    cantidadRegistros++;

    System.out.println("¡Puntuación registrada correctamente!");
}

public void mostrarRanking() {

    System.out.println("\n===== RANKING =====");

    // Comprobar si hay puntuaciones registradas
    if (cantidadRegistros == 0) {
        System.out.println("Todavía no hay puntuaciones registradas.");
        return;
    }

    // Recorrer los registros existentes
    for (int i = 0; i < cantidadRegistros; i++) {
        System.out.println((i + 1) + ". " + nombres[i] + " - " + puntuaciones[i] + " puntos");
    }

    System.out.println("===================");
}
}


