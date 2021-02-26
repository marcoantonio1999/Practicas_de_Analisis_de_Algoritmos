package unam.ciencias.algoritmos.practica6;

import java.util.LinkedList;

/**
 * Clase gráfica.
 * 
 */
public class Grafica{
  private final LinkedList<Vertice> LV;
    private int[][] matriz;

    /**
     * Constructor 
     */
    public Grafica() {
	    LV = new LinkedList<>();
    }
    
    
protected class Vertice implements Comparable<Vertice>{

	// El identificador 
	public int id;
	// El elemento del vértice 
	public String name;
	// El valor si ya fue visitado 
	public boolean visited;
	// La lista de vecinos del vértice 
	public LinkedList<Vertice> LVc;
	// La distancia del vértice 
	public int dis;
	// Padre del vértice 
	public Vertice father;

	/**
	* Construye un nuevo vértice en la gráfica
	* 
	*/
	public Vertice(int id){
	    this.id = id;
	    this.name = "v" + id;
	    this.visited = false;
	    this.LVc = new LinkedList<>();
	    this.dis = Integer.MAX_VALUE;
	    this.father = null;
	}

	@Override
  public int compareTo(Vertice vertice){
	  return this.id - vertice.id;
	}

	@Override
  public String toString(){
	  return name;
	}
/**
     * Obtiene la lista de vértices de la gráfica
     */
    public LinkedList<Vertice> getVertices(){
	    return LV;
    }

	/**
	* Agrega un vértice a su lista de vecinos.
	* 
	*/
	public void addVecino(Vertice vertice){
	  if(!LVc.contains(vertice))
		LVc.add(vertice);
	}
    }

    
    /**
    * Agrega un nuevo elemento a la gráfica
    * 
    */
    public void agrega(int id){
	    Vertice v = new Vertice(id);
	    LV.add(v);
    }

    /**
    * Obtiene el vértice buscado.
    *
    */
    public Vertice getVertice(int id){
	    Vertice v = new Vertice(id);
	    for(Vertice e: LV)
	     if(v.compareTo(e) == 0)
		     return e;
	        return null;
    }

    /**
     * Agrega una arista
     * Se simula agregando los vértice en cada vecindad
     * de cada arista respectivamente
     * 
     */
    public void addArista(int idI, int idD){
	    Vertice vI = getVertice(idI);
	    Vertice vD = getVertice(idD);
	    vI.addVecino(vD);
	    vD.addVecino(vI);
    }

    @Override
    public String toString(){
	    String s = "";
            s = LV.stream().map((v) -> v.toString() + "->" + v.LVc.toString() + "\n").reduce(s, String::concat);
	     return s;
    }

    /**
     * Algoritmo Dijkstra
     * 
     */
    public Grafica dijkstra(Grafica grafica, int id, int[][] pound){
	    getVertice(id).dis = 0;
	    ColaBinomial<Vertice> tail = new ColaBinomial<>();
            grafica.LV.forEach((v) -> {
                tail.insert(v);
      });
	     while(!tail.isEmpty()){
	      Vertice u = tail.removeMin();
              u.LVc.stream().filter((Vertice v) -> v.dis > (u.dis + pound[u.id][v.id])).map((v) -> {
                  v.dis = u.dis + pound[u.id][v.id];
                    return v;
                }).forEachOrdered((v) -> {
                    v.father = u;
                });
	      }
	       return grafica;
    }

    /**
     * Imprime cada ruta desde un vértice origen
     * 
     */
    public void ruta(Grafica grafica){
        LV.forEach((v) -> {
            LinkedList<Vertice> r = new LinkedList<>();
            r.addFirst(v);
            Vertice p = v.father;
            while(p != null){
                r.addFirst(p);
                p = p.father;
            }
            System.out.println(v.toString() + ", "
                    + v.dis + " -> "
                    + r.toString());
      });
    }

    
    /**
     * Modifica la matriz creada
     * 
     */
    public void setMatriz(int[][] matriz){
	    this.matriz = matriz;
    }

    /**
     * Obtiene la matriz con los pesos de las aristas
     *
     */
     public int[][] getMatriz(){
	    return matriz;
     }
}
