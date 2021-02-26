/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pracale3;
import java.io.*;
/**
 *
 * @author marco
 */
public class archivo {
    public String leerTxt(String direccion){
        String texto = "";
        
        try{
        BufferedReader bf = new BufferedReader(new FileReader(direccion));
        String temp = "";
        String bfRead;
        while((bfRead = bf.readLine()) != null ){
        temp = temp + bfRead;
        }
        texto = temp;
        }catch(Exception e){
        System.out.println("No se contrarooon archivos");
        } 
    return texto;
    }
    
}
