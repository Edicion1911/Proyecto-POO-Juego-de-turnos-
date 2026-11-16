
import java.util.Scanner;

public class Juego {

    private int puntuacionFinal;
    private Scanner sc;
    private Ranking ranking;

    public Juego() {
        this.puntuacionFinal = 0;
        this.sc = new Scanner(System.in);
        this.ranking = new Ranking();

        ranking.cargarRanking();
    }

    public void mostrarMenu() {
        System.out.println("====== Menú Principal ======\n");
        System.out.println("1) Nueva Partida.");
        System.out.println("2) Ayuda.");
        System.out.println("3) Ranking");
        System.out.println("4) Salir.\n");
        System.out.println("=============================");
    }

    public void mostrarIntroduccion() {
        System.out.println("\n=== EL DESPERTAR DEL CABALLERO DE LOS SIETE REINOS ===");
        System.out.println("\nLos Siete Reinos atraviesan una época de oscuridad...");
        System.out.println("Una amenaza se aproxima...");
        System.out.println("Tu aventura comienza ahora.\n");
    }

    public void mostrarVentajas() {
        System.out.println("1. CASA STARK:");
        System.out.println("- Causa 2 puntos menos de daño y recibe 2 puntos menos de daño.\n");

        System.out.println("2. CASA LANNISTER:");
        System.out.println("- Causa 2 puntos mas de daño, pero recibe 2 puntos mas de daño.\n");

        System.out.println("3. CASA TARGARYEN:");
        System.out.println("- Tiene un 15% de probabilidad de realizar un golpe critico que causa 5 puntos adicionales de daño.\n");

        System.out.println("4. CASA BARATHEON:");
        System.out.println("- Acumula cargas de furia al protegerse.");
        System.out.println("- Puede almacenar hasta 2 cargas, cada una aumenta en 3 puntos el siguiente ataque.\n");

        System.out.println("CABALLERO ERRANTE:");
        System.out.println("- No posee ventajas ni desventajas especiales.\n");

    }

    public void mostrarAyuda() {
        System.out.println("========== AYUDA ==========\n");

        System.out.println("OBJETIVO:");
        System.out.println("Derrota a los tres caballeros enemigos y demuestra que eres digno de convertirte en un heroe.\n");

        System.out.println("COMBATE:");
        System.out.println("1. Atacar: Realiza un ataque con daño aleatorio.");
        System.out.println("2. Protegerse: Reduce en 3 puntos el daño del siguiente ataque recibido.");
        System.out.println("3. Usar pocion: Recupera hasta 25 puntos de vida.");
        System.out.println("4. Consultar inventario: Muestra las pociones disponibles.");
        System.out.println("5. Abandonar: Termina el combate actual y guarda los puntos conseguidos en el Ranking.\n");

        System.out.println("PERSONAJE:");
        System.out.println("- Comienzas con 100 puntos de vida.");
        System.out.println("- Dispones de 3 pociones de vida.");
        System.out.println("- Cada pocion recupera hasta 25 puntos de vida.");
        System.out.println("- Cada ataque causa entre 10 y 20 puntos de daño");
        System.out.println("- Puedes iniciar con una casa entre Stark, Lannister, Targaryen y Baratheon. ");
        System.out.println("- Cada casa cuenta con su ventaja propia.\n");

        System.out.println("ENEMIGOS:");
        System.out.println("- Sir Jaime Lannister: 40 puntos de vida.");
        System.out.println("- Sir Barristan Selmy: 60 puntos de vida.");
        System.out.println("- Sir Arthur Dayne: 80 puntos de vida\n");

        System.out.println("=== CASAS Y SUS VENTAJAS ===\n");
        mostrarVentajas();

        System.out.println("=== ENEMIGOS Y SUS VENTAJAS ===\n");

        System.out.println("JAIME LANNISTER:");
        System.out.println("- 40 puntos de vida. No posee una ventaja especial adicional.\n");

        System.out.println("BARRISTAN SELMY:");
        System.out.println("- 60 puntos de vida. Reduce 2 puntos del daño recibido.");
        System.out.println("- Tiene un 15% de probabilidad de realizar un golpe critico de +5 de daño.\n");

        System.out.println("ARTHUR DAYNE:");
        System.out.println("- 80 puntos de vida. Reduce 2 puntos del daño recibido.");
        System.out.println("- Aumenta su daño de ataque en 3 puntos.\n");

        System.out.println("- Todos los enemigos pueden protegerse y reducir 3 puntos del siguiente ataque recibido.\n");

        System.out.println("PUNTUACION:");
        System.out.println("- Obtienes 100 puntos por cada enemigo derrotado.");
        System.out.println("- Al finalizar la aventura, se suman los puntos de vida restantes.\n");

        System.out.println("CONSEJO:");
        System.out.println("- Administra tus pociones y utiliza la defensa en el momento adecuado.\n");
        System.out.println("============================");
    }

    public Caballero crearCaballero() {
        System.out.println("==== Crea tu Personaje ====\n");
        String nombre;
        do {
            System.out.println("Introduce el nombre de tu caballero:");
            nombre = sc.nextLine();

            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
            }

        } while (nombre.isEmpty()); //No permite agregar un nombre en blanco.

        System.out.println("¡Muy bien Sir " + nombre + "!");
        System.out.println("¡Elige la casa que quieres pertenecer!\n");

        mostrarVentajas();
        System.out.print("Tu selección: ");

        while (!sc.hasNextInt()) {
            System.out.println("Error: debes introducir un número.");
            sc.nextLine();

            System.out.print("Tu selección: ");
        }//permite solo numeros

        int ventaja = sc.nextInt();
        sc.nextLine();

        switch (ventaja) {
            case 1:
                System.out.println("¡Mucha Suerte en tu camino, Sir " + nombre + " Stark!\n");
                break;
            case 2:
                System.out.println("¡Mucha Suerte en tu camino, Sir " + nombre + " Lannister!\n");
                break;
            case 3:
                System.out.println("¡Mucha Suerte en tu camino, Sir " + nombre + " Targaryen!\n");
                break;
            case 4:
                System.out.println("¡Mucha Suerte en tu camino, Sir " + nombre + " Baratheon!\n");
                break;
            default:
                break;
        }
        Caballero caballero = new Caballero(nombre, ventaja);
        return caballero;
    }

    public void Iniciar() {
        int opcion;
        boolean controlador = true;
        do {
            mostrarMenu();

            while (!sc.hasNextInt()) {
                System.out.println("Error: debes introducir un número.");
                sc.nextLine();

                System.out.print("Tu selección: ");
            } // permite solo numeros

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    puntuacionFinal = 0;
                    mostrarIntroduccion();
                    Caballero caballero = crearCaballero();

                    Enemigo jaime = new Enemigo("Sir Jaime Lannister", 40, 1);
                    Enemigo barristan = new Enemigo("Sir Barristan Selmy", 60, 2);
                    Enemigo arthur = new Enemigo("Sir Arthur Dayne", 80, 3);

                    Combate combateJaime = new Combate(caballero, jaime);

                    System.out.println("¡Te has encontrado un Caballero en tu viaje!");
                    System.out.println("¡Sir Jaime Lanniter! ¡El Mararreyes!");

                    combateJaime.simularCombate();// PRIMER COMBATE

                    if (caballero.estaVivo() && !jaime.estaVivo()) // SEGUNDO COMBATE
                    {
                        System.out.println("Sigues tu viaje, y te sorprende como adversario...");
                        System.out.println("¡Sir Barristan Selmy! ¡El Bravo!");

                        Combate combateBarristan = new Combate(caballero, barristan);

                        combateBarristan.simularCombate();

                        if (caballero.estaVivo() && !barristan.estaVivo()) //TERCER COMBATE
                        {
                            System.out.println("Has llegado al ultimo lugar de tu viaje por los 7 reinos");
                            System.out.println("Como ulitmo contricante, tendrás que derrotar a...");
                            System.out.println("¡Sir Arthur Dayne! ¡La espada del amanecer!");

                            Combate combateArthur = new Combate(caballero, arthur);

                            combateArthur.simularCombate();

                            if (caballero.estaVivo() && !arthur.estaVivo()) //FIN AVENTURA!
                            {
                                System.out.println("\n¡HAS DERROTADO A LOS 3 CABALLEROS MAS PODEROSOS!");
                                System.out.println("¡ERES DIGNO DE LOS 7 REINOS!\n");
                            }
                        }
                    }

                    // Sumar puntos por enemigos derrotados
                    if (!jaime.estaVivo()) {
                        puntuacionFinal += 100;
                    }

                    if (!barristan.estaVivo()) {
                        puntuacionFinal += 100;
                    }

                    if (!arthur.estaVivo()) {
                        puntuacionFinal += 100;
                    }

                    // Sumar la vida restante, independientemente del resultado
                    puntuacionFinal += caballero.getVida();

                    // Registrar puntuación
                    System.out.println("\nTu puntuación final es: " + puntuacionFinal);

                    ranking.registrarPuntuacion(caballero.getNombre(), puntuacionFinal);

                    // Guardar ranking en XML
                    ranking.guardarRanking();

                    break;

                case 2:
                    mostrarAyuda();
                    System.out.println("\n pulsa ENTER para salir de nuevo al Menú Pincipal.");
                    sc.nextLine();
                    break;
                case 3:
                    ranking.mostrarRanking();

                    System.out.println("\nPulsa ENTER para volver al menú principal");
                    sc.nextLine();
                    break;

                case 4:
                    controlador = false;
                    break;
            }

        } while (controlador);
    }

}
