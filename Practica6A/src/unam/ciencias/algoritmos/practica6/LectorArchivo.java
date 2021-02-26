package unam.ciencias.algoritmos.practica6;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

/**
* Método que lee el archivo .txt para obtener la gráfica
*/
public final class LectorArchivo{

    private final LinkedList<String> list;
    private final Grafica grafica;

    /**
    * Constructor de la clase
    *
    */
    public LectorArchivo(String archivo){
	    grafica = new Grafica();
	    list = new LinkedList<>();
      readArchive(archivo);
	    grafica.setMatriz(createGrafica());
    }

    /**
     * Método que obtiene la gráfica creada
     */
    public Grafica getGrafica(){
	    return grafica;
    }

    /**
    * Método que crea la gráfica con el archivo ya procesado
    */
    public int[][] createGrafica(){
	    String[] vertex = list.get(0).split(",");
	    for(String s: vertex)
	     grafica.agrega(Integer.parseInt(s));
	      int[][] matriz = new int[vertex.length][vertex.length];
	      for(int i = 1; i < list.size(); i++){
	         String[] edges = list.get(i).split(",");
	         int j = Integer.parseInt(edges[0]);
	         int k = Integer.parseInt(edges[1]);
	         matriz[j][k] = Integer.parseInt(edges[2]);
	         matriz[k][j] = Integer.parseInt(edges[2]);
	         grafica.addArista(j, k);
	      }
	       return matriz;
    }

    /**
    * Metodo que lee el archivo
    * 
    */
    private void readArchive(String archive){
      String f;
	    try(BufferedReader br = new BufferedReader(new FileReader("./Ejemplares/"+archive))){
	    while((f = br.readLine()) != null)
		    list.add(f);
	      br.close();
	    }catch(Exception e){
	       System.out.println("Problema en la entrada de datos");
	    }
    }
}
