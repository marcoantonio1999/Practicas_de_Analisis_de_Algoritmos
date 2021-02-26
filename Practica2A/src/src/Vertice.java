/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.LinkedList;

/**
 * clase vertice que simula el vertice de la grafica
 * @author Marco Avila
 */
public class Vertice {
private final String  nombre;
private final LinkedList<Vertice> LVecinos;
 /*
Contructor unico de la clase vertice
*/   
public Vertice(String nombre) {
    this.nombre = nombre;
    LVecinos = new LinkedList<>();
    }
   /*
Nos regresa el nombre del vertice
*/
    public String getNom(){
    return nombre;
    }
    /*
    Nos regresa la lista de vecinos
    */
    public LinkedList<Vertice> getVecinos(){
    return LVecinos;
    }
    /*
    Agrega un vertice a la lista de vecinos
    */
    public void agregaV(Vertice V){
    LVecinos.add(V);
    }   

}
