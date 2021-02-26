
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import java.util.Scanner;
import org.graphstream.ui.view.Viewer;
public class Principal{
    /**
    * Método que recibe un objeto tipo Grafica y la dibuja.
    * @param g: gráfica que recibe.
    */
    public void dibujaGrafica(Grafica g){
        Graph graph = new SingleGraph("Ventana"); 
        g.LV.stream().map((v) -> {
            graph.addNode(v.name);
            return v;
        }).forEachOrdered((v) -> {
            graph.getNode(v.name).addAttribute("ui.label", v.name); 
        });

       
        if(g.dirigida){ 
            g.LV.forEach((v) -> {
                v.Lvecinos.forEach((u) -> {
                    String nombreArista = v.name+","+u.name;
                    graph.addEdge(nombreArista, v.name, u.name,true);
                });
            });
        }else{ 
            g.LV.forEach((v) -> {
                v.Lvecinos.forEach((u) -> {
                    String nombreArista = v.name+","+u.name;
                    String nombreArista2 = u.name+","+v.name;
                    if (graph.getEdge(nombreArista2) == null) {
                        
                        graph.addEdge(nombreArista, v.name, u.name);
                    }
                });
            });
        }

        Viewer display = graph.display(); 
    }

    public static void main(String[] args){
        System.out.println("ingrese el archivo que dea hacer DFS y ver la grafica");
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        
        Lector l = new Lector();
        l.lee(a); 
        Grafica g1 = l.creaGrafica(); 
        Principal pr = new Principal();

        /**
        * En esta sección deben ejecutar su algoritmo para calcular las componentes conexas.
        * Después deben imprimir cada componente separada por un salto de línea.
        */

        pr.dibujaGrafica(g1); //Se dibuja la gráfica en pantalla
        g1.componentesConexas();
    }
}




