import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

public void mostrarRanking() 
{

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

public void guardarRanking()
{
    try 
    {
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
        
        DocumentBuilder constructor = fabrica.newDocumentBuilder();

        Document documento = constructor.newDocument();

        Element raiz = documento.createElement("ranking");

        documento.appendChild(raiz);


        for (int i = 0; i < cantidadRegistros; i++)
            {
            Element jugador = documento.createElement("jugador");

            raiz.appendChild(jugador);

            Element nombre = documento.createElement("nombre");

            nombre.setTextContent(nombres[i]);

            jugador.appendChild(nombre);

            Element puntuacion = documento.createElement("puntuacion");

            puntuacion.setTextContent(String.valueOf(puntuaciones[i]));

            jugador.appendChild(puntuacion);  
            }
    
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        DOMSource origen = new DOMSource(documento);

        StreamResult destino = new StreamResult(new File("ranking.xml"));

        transformer.transform(origen, destino);

        System.out.println("Ranking guardado correctamente.");
        

    } catch (Exception e) {
        System.out.println("Error al guardar el ranking: " + e.getMessage());
    }
}
   
public void cargarRanking()
{
    try
    {
        File archivo = new File("ranking.xml");

        // Comprobrueba si existe el archivo
        if (!archivo.exists())
        {
            return;
        }
        cantidadRegistros = 0;

        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();

        fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

        DocumentBuilder constructor = fabrica.newDocumentBuilder();

        Document documento = constructor.parse(archivo);

        documento.getDocumentElement().normalize();

        //Lee los jugadores
        NodeList jugadores = documento.getElementsByTagName("jugador");

        for (int i = 0; i < jugadores.getLength(); i++)
        {
            Node nodo = jugadores.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE)
            {
                Element jugador = (Element) nodo;

                String nombre = jugador.getElementsByTagName("nombre")
                                       .item(0).getTextContent();

                int puntuacion = Integer.parseInt(
                    jugador.getElementsByTagName("puntuacion")
                           .item(0).getTextContent()
                );

                registrarPuntuacion(nombre, puntuacion);
            }
        }

        System.out.println("Ranking cargado correctamente.");

    }
    catch (Exception e)
    {
        System.out.println("Error al cargar el ranking: " + e.getMessage());
    }
    

}

}


