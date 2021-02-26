/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 *  Clase Escritura para poder escribir en el nuevo archivo de salida
 * @author familia
 */
public class Escritura {
    
    public Escritura(){}
    /*
    Metodo para escribir en el archivo de salida
    */
    public void escribir(LinkedList<String> lista,String archivo) throws IOException{
    String s = "";
    int i = 0;
    for(String a: lista){
    if(i == lista.size()  -1 ){
    s += a+"\n";
    }else{
        s += a + ",";
    }
    i++;
    }
        try (BufferedWriter w = new BufferedWriter(new FileWriter("Salida"+archivo))) {
            w.write(s);
        }
    }
    
}
