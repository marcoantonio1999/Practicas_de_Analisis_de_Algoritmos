/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Clase Lectura para leer el archivo de entrada
 * @author Marco Avila
 */

public final class Lectura {
String[] LV;
LinkedList<String[]> LA;


public Lectura(String archivo) throws IOException{
LA = new LinkedList<>();
leer(archivo);
}
/*
Metodo para leer el archivo de entrada
*/
public void leer(String Archivo) throws IOException{
try(BufferedReader br = new BufferedReader(new FileReader(Archivo))){
String Linea;
for(int i = -1;(Linea = br.readLine()) != null;i++){
if(i == -1){
LV = Linea.trim().split("\\s*,\\s*");
}else{
        String[] LAristas = Linea.trim().split("\\s*,\\s*");
        LA.add(LAristas);
}
}
}catch(Exception e){
    System.err.print(e);
}
}

}
