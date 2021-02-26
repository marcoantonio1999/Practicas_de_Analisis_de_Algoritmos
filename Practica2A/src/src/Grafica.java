/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *Clase grafica
 * @author Marco Avila
 */

public class Grafica{

  private final LinkedList<Vertice> LV; // la lista de vertices
  private final LinkedList<String[]> LA;// la lista de aristas
  private final LinkedList<String> C; // mi conjunto para guardar el conjunto idependiente
  private final String archivo; // le erchico a leer
  /*
  Contructor de la clase Grafica
  */
 public Grafica(String archivo) throws IOException {
      this.archivo = archivo;
      Lectura lec = new Lectura(archivo);
     LV = new LinkedList<>();
      LA = lec.LA;
     C = new LinkedList<>();
     for(String e : lec.LV){
     Vertice v = new Vertice(e);
     LV.add(v);
     }
     LV.forEach((Vertice v) -> {
         lec.LA.forEach((e) -> {
             if(e[0].contains(v.getNom())){
                 LV.stream().filter((u) -> (e[1].contains(u.getNom()))).forEachOrdered((u) -> {
                     v.agregaV(u);
                 });
             }else if(e[1].contains(v.getNom())){
                 LV.stream().filter((u) -> (e[0].contains(u.getNom()))).forEachOrdered((V) -> v.agregaV(V));
                 
                 
             }
         });
      });


}
 /*
 nos regresa el número de vertices en la la grafica
 */
public int numVertices(){
    return LV.size();
    }
/*
obtiene el vertice por su nombre
*/
 public Vertice getVertice(String name){
	   Vertice vertex = new Vertice(name);
	    for(Vertice e: LV)
	    if(vertex.equals(e))
		    return e;
	   return null;
  }
 /*
 Metodo para agregar una arista
 */
public void agregaArista(String a1, String a2){
	   Vertice v1 = getVertice(a1);
	   Vertice v2 = getVertice(a2);
	    v1.agregaV(v2);
	    v2.agregaV(v1);
  }
/*
regresa el número de aristas
*/
public int numAristas(){
return LA.size();
}
/*
Obtiene el archivo
*/
public String getArchivo(){
return this.archivo;
}
/*
metodo toString para visualizar
*/
@Override
    public String toString(){
  	   String r = "";
           r = LV.stream().map((v) -> v.toString() + "->" + v.getVecinos().toString() + "\n").reduce(r, String::concat);
  	     return r;
    }
/*
    Metodo que saca el conjunto independiente Maximal
    */
public LinkedList<String> setInde(){
       LinkedList<String> max = new LinkedList<>();
	   LinkedList<Vertice> r = LV;
	     while(!r.isEmpty()){
	        Vertice v = r.remove();
	         max.add(v.getNom());
                 v.getVecinos().forEach((e) -> {
                     r.remove(e);
           });
	     }
	      return max;
    }
//Main de la Practica
public static void main(String[] args) throws IOException{
System.out.println("escribe el nombre del archivo de entrada");
    Scanner sc = new Scanner(System.in);
Grafica G = new Grafica(sc.nextLine());
LinkedList<String> conjuntoIndependiente = G.setInde();

Escritura e = new Escritura();
e.escribir(conjuntoIndependiente, G.getArchivo());

}

}
