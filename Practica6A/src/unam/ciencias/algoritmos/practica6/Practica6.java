package unam.ciencias.algoritmos.practica6;

import java.util.Scanner;

/**
 * Clase Principal
 * Main
 */
public class Practica6{

  public static void main(String[] args){
    int origin;
  	String archive;
	  Scanner sc =  new Scanner (System.in);
          
           archive = sc.nextLine();
	    
	    origin = Integer.parseInt(sc.nextLine());
	  LectorArchivo lector = new LectorArchivo(archive);
	  Grafica grafica = lector.getGrafica();
	  grafica = grafica.dijkstra(grafica, origin, grafica.getMatriz());
	  grafica.ruta(grafica);
    }
}
