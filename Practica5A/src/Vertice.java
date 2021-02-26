
//Clase vértice

import java.util.LinkedList;

public class Vertice{
  LinkedList<Vertice> Lvecinos; //Lista de vecinos
  Vertice p = null; //Padre del vértice
  int id; //Identificador.
  String name; //Nombre del vértice.
  boolean reviewed = false;
  
  /**
   * Constructor de la clase vértice.
   * 
   */
  public Vertice(int ident){
    id = ident;
    Lvecinos = new LinkedList<>();
    name = "v"+ident;
  }
  
  
  @Override
  public boolean equals(Object o){
    boolean ret = false;
    if(o instanceof Vertice){
      Vertice comp = (Vertice)o;
      if(comp.id == this.id){
        ret = true;
      }
    }
    return ret;
  }
  
  /**
   * Agrega un vecino a este vértice
   */
  public void agregaVecino(Vertice vec){
    if(!Lvecinos.contains(vec)){
      Lvecinos.add(vec);
    }
  }
  
  @Override
  public String toString(){
    return name;
  }
}
