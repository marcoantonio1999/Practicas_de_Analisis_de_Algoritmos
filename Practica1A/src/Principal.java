

import java.awt.Color;
import processing.core.PApplet;
import java.util.Scanner;
/*
Clase Principal 
Author Marco Avila
*/
public class Principal extends PApplet{

    
    private int [][] s ;
    public static int tmatriz; 
    Matriz ejemplar;

    //Función para configuración inicial de la ventana.
    @Override
    public void settings(){
        size(tmatriz*21,tmatriz*21);
    }
    
    //Función para configuraciones iniciales del programa.
    @Override
    public void setup(){
        background (255, 255, 255); 
        ejemplar = new Matriz(tmatriz); 
        ejemplar.colorRand(200); 
        s = ejemplar.squareEspecial();
       
        colorea();
    }
    
    //Dibuja de forma cíclica.
    @Override
    public void draw(){
    }
    
    //Pinta las casillas.
    public void colorea(){
       
        float CasillaSize = 20;

        for(int i=0;i<s.length;i++){

            for(int j=0;j<s.length;j++){
                int indC = s[i][j];
                if(indC == 1){
                    fill(1);
                }else{
                    Color tempC = ejemplar.colors.get(indC);
                    fill(tempC.getRed(),tempC.getGreen(),tempC.getBlue());
                }

                rect(i*CasillaSize,j*CasillaSize,CasillaSize,CasillaSize);
            }
        }
    }
    //main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el lado de la matriz");
        tmatriz = sc.nextInt();
        
        PApplet.main("Principal");
    }
    
}

