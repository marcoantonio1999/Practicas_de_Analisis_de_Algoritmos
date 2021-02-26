
import java.util.LinkedList;
//import org.graphstream.graph.*;
//import org.graphstream.graph.implementations.SingleGraph;

public class Grafica {
    LinkedList<Vertice> LV; //Lista de vértices.
    boolean dirigida; //Verdadero si y solo si la gráfica es dirigida.
  
    /**
     * Constructor de la clase gráfica.
     * 
     **/
    public Grafica(boolean dir){
        dirigida = dir;
        LV = new LinkedList<>();
    }

    /**
    * Función que recibe un id y devuelve el vértice que tiene dicho id. Si no existe devuelve null.
    * 
    */
    public Vertice getVerticeId(int ident){
        Vertice ret = null;
        for(Vertice v:LV){
            if(ident == v.id){
                ret = v;
            }
        }
        return ret;
    }
 
    /**
    * Función que agrega una arista.
    * 
    */
    public void agregaArista(int i, int j){
        Vertice vert_i = getVerticeId(i);
        Vertice vert_j = getVerticeId(j);
        vert_i.agregaVecino(vert_j);
    
        if(!dirigida){
            vert_j.agregaVecino(vert_i);
        }
    }
  
    /**
    * Agrega un vértice a la gráfica
    * 
    */
    public void agregaVertice(int ident){
        Vertice nuevo = new Vertice(ident);
        LV.add(nuevo);
    }

    /**
    * Obtiene el orden(número de vértices) de la gráfica.
    */
    public int getOrden(){
        return LV.size();
    }
    /*
    Metodo que hace la busqueda por profundidad
    */
public void DFS(Vertice v, boolean[] visitados){
visitados[v.id] = true;

        System.out.print(v.name); 
        this.LV.stream().filter((e) -> (!visitados[e.id])).forEachOrdered((e) -> {
            DFS(e,visitados);
        });
  }
  /*
Metodo para sacar las componentes conexas
*/
public void componentesConexas(){
boolean[] visitados =  new boolean[this.LV.size()];
this.LV.stream().filter((v) -> (!visitados[v.id])).map((v) -> {
    DFS(v,visitados);
            return v;
        }).forEachOrdered((_item) -> {
            System.out.println();
        });

}        

    
    
    
    @Override
    public String toString(){
        String salida = "";
        salida = LV.stream().map((v) -> v.toString()+"->"+v.Lvecinos.toString()+"\n").reduce(salida, String::concat);
        return salida;
    }
}


