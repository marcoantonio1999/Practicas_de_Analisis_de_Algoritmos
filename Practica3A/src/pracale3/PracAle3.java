/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pracale3;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static pracale3.PracAle3.cambia;
/**
 *Practica3 
 * La siguiente practica implementa los algoritmos de busqueda Binaria, Exponencial,Interpolación
 * e implementa un metodo para generar listas ordenadas
 * @author marco
 */
public class PracAle3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        Imprimir lista1 en caso de querer ver las listas ordenadas con el metodo toString 
        */
        List<String> lista1 = new ArrayList<String>();
        lista1 = cambia((ArrayList<int[]>) generadorListas()) ;
       //System.out.print(lista1.toString());
        
     //scanner para la entrada de los argumentos
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
       
        String a,b,c;
        
       a = obtieneArchi(x,0);
       b = obtieneArchi(x,1);
       c = obtieneArchi(x,2);
        archivo ab = new archivo();
        String s1 = ab.leerTxt(a); 
        
        int indice = Integer.parseInt(b);
      
     
      int S[] = llenarArrg(s1);
      
      if(c.equals("Secuencial"))
          System.out.println(Bsecuencial(indice,S));
      if(c.equals("Binaria"))
          System.out.println(busquedaBinaria(indice,S));
      if(c.equals("Exponencial"))
          System.out.println(BExponencial(indice,S));
      if(c.equals("Interpolacion"))
          System.out.println(Binterpolacion(indice,S));
       }
   /*
    Metodo para obtener las partes correspondientes a la entrada del programa
    es decir, depende la opcion, obtiene el archivo, el indice y la busqueda a realizar
    */
        public static String obtieneArchi(String x,int o) {
         String [] parts = x.split(" ");
         String part1 = parts[0];
         String part2 = parts[1];
         String part3 = parts[2];
         if (o == 0)return part1;
         if (o == 1)return part2;
         if (o == 2)return part3;
         return null;
         
             }
      
/*
        Metodo para llenar el arreglo correspondiente a los números existentes en el archivo
                */
    private static int[] llenarArrg(String s1) {
        String[] temp = s1.split(" ");
        int[] s = new int[temp.length];
        for(int i = 0; i < temp.length; i++){
            s[i] = Integer.parseInt(temp[i]);
        }
        System.out.println(Arrays.toString(s));
        return s;
    }
        
       
    
    
/*
    
    algoritmo búsqueda binaria 
    */
    
     public static int  busquedaBinaria(int z , int X [])
     {
	   	
         int n = X.length;
  int c,inf=0,sup=n-1;
   while(inf<=sup){
     c=(sup+inf)/2;
     if(X[c]==z) return c;
     else if(z < X[c] ){
        sup=c-1;
     }
     else {
       inf=c+1;
     }
   }
   return -1;
		   
     }
     /*
     Algoritmo de búsqueda secuencial
     */
  public static int Bsecuencial(int z, int[] X ){
       int i = 1;
	    do {
	   	
		   
		   if(X[i]==z)
		   return i;
		   else i = i+1;
	   }while (i< X.length);
            
	   return -1;
    }
  /*
  Algortimo  de busqueda exponencial
  */
   public static int BExponencial(  int z,int[] X) {

       // If x is present at firt location itself 
        if (X[0] == z) 
            return 0; 
      
        // Find range for binary search by 
        // repeated doubling 
        int i = 1; 
        while (i < X.length && X[i] <= z) 
            i = i*2; 
      
        // Call binary search for the found range. 
        return Arrays.binarySearch(X, i/2,  
                                   Math.min(i, X.length), z); 
   }
      /*
   
   Búsqueda por Interpolación
   */
        
     public static int Binterpolacion(int z, int[] X){
     int izq,der,i;
     izq = 1;
     der = X.length-1;
     while ((X[der]>=z)&&(z>X[izq])){
      i = izq +(((z-X[izq])*(der-izq))/(X[der]-X[izq]));
      if(z>X[i] )
          izq = i;
      else if(z< X[i])
          der = i-1;
      else izq = i;
      }
     if (X[izq]==z )
         return izq;
     return -1;
     }   
  
     /*
     Metodo que genera un arreglo de mil elementos con números aleatorios de 0 a 10000
     */
     public static  int[] generadorArrg(){
     int[] X =  new int[1000];
     for(int i=0;i<1000;i++){
     
     X[i] = (int) (Math.random()*10000 )+1;
     
     }
        return X;
     }
     /*
     Metodo que genera 1000 listas ordenadas (como el archivo de entrada) 
     */
     public static  List<int[]> generadorListas(){
     
         List<int[]> lista = new ArrayList<int[]>();
         for(int i = 0; i < 1000 ;i++){
         lista.add(shellSort(generadorArrg()));
         }
     
         
         return lista;
         
     }
     /*
     Metodo de ordenamiento shell Sort para ordenar cada lista
     */
     public static int[] shellSort(int[] X){      
         for ( int increment = X.length / 2; increment > 0; increment = (increment == 2 ? 1 : (int) Math.round(increment / 2.2))){          
         for (int i = increment; i < X.length; i++)  {              
         for (int j = i; j >= increment && X[j - increment] >    X[j]; j -= increment){                  
         int temp = X[j];
     X[j] = X[j - increment];
     X[j - increment] = temp;
     }         
             }      
         }
         return X;
     }
     
     /*
     Metodo cambia que solo se utiliza en caso de querer ver lass listas ordenadas ordenadas ya que si las
     imprimos asi, las imprimira en codigo ascii 
     */
     public static List<String> cambia(ArrayList<int[]> lista){
     List<String> lista1 = new ArrayList<String>();
      for(int i = 0; i < lista.size();i++){
     lista1.add(Arrays.toString(lista.get(i)));
     }
      return lista1;
     } 
     

    
     
 }

