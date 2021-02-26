

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;
/*
Clase Matriz donde en la seccion de abajo se hace el problema de adoquinamiento
*/
public class Matriz{

    int[][] mat; 
    LinkedList<Color> colors; 
      private int size;
   // contructor de la matriz a realizar   
    public Matriz(int size){
    	if(checkSize(size)){
        this.mat = new int[size][size];
        this.size = size;
        this.colors =  new LinkedList<>();
        }else{System.exit(0);
    }
    }
    /**
    *rellena la lista de colores.
    * @param n: número de colores a agregar
    **/
    public void colorRand(int n){
	Random ran = new Random();
        int r,g,b;
        for(int i=0;i<n;i++){
            r = ran.nextInt(256);
            g = ran.nextInt(256);
            b = ran.nextInt(256);
            colors.add(new Color(r,g,b));
        }
    }
    //checa el tamaño 
    private boolean checkSize(int num){
        if(num <= 0){
            return false;
        }else{
            while(num > 1){
                if(num % 2 != 0){
                    return false;
                }else{
                    num = num / 2;
                }
            }
            return true;
        }
    }
    
     private int[][] remove(int[][] cord, int quadrant){
        int[][] c = new int[3][2];
        int j = 1;
        int k = 0;
        for(int[] i : cord){
            if(j != quadrant){
                c[k] = i;
                k++;
            }
            j++;
        }
        return c;
    }
    private int[][] center(int[][] sq){
        int[] c00 = new int[2];
        int[] c02 = new int[2];
        int[] c03 = new int[2];
        int[] c04 = new int[2];
        int x = sq.length / 2;
        int[][] cent = new int[4][2];
        c00[0] = x - 1;
        c00[1] = x - 1;
        c02[0] = x - 1;
        c02[1] = x;
        c03[0] = x;
        c03[1] = x - 1;
        c04[0] = x;
        c04[1] = x;
        cent[0] = c00;
        cent[1] = c02;
        cent[2] = c03;
        cent[3] = c04;
        return cent;
    }
    public int[] consT(int[] a, int[] b) {
        int a1 = a.length;
        int b2 = b.length;

        @SuppressWarnings("unchecked")
        int[] c = new int[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a1);
        System.arraycopy(b, 0, c, a1, b2);

        return c;
    }
private int quadrantSerch(int[][] sq, int size){
        for(int i = 0; i < sq.length; i++){
            for(int j = 0; j < sq.length; j++){
                if(sq[i][j] != 0){
                    return selectionQuadrant(i, j, size);
                }
            }
        }
        return 0;
    }
 public int[][] markL(int[][] sq, int cuadrantes){
        Random rn = new Random();
        int r = rn.nextInt(200);
        int y = sq.length / 2;
        int[][] matrix = sq;
        if(r == 1 || r == 0){
            r = 2;
        }
        int[][] mat1;
        mat1 = remove(center(matrix), cuadrantes);
        for(int[] i : mat1){
            matrix[i[0]][i[1]] = r;
        }
        if(matrix.length == 2){
            return matrix;
        }else{
            int[][] k2 = new int[y][y];
            for(int i = 0; i < y; i++){
                int[] k = new int[y];
                System.arraycopy(matrix[i], 0, k, 0, y);
                k2[i] = k;
            }
            int[][] k3 = new int[y][y];
            for(int i = 0; i < y; i++){
                int[] k = new int[y];
                for(int j = y; j < matrix.length; j++){
                    k[j - y] = matrix[i][j];
                }
                k3[i] = k;
            }
            int[][] k4 = new int[y][y];
            for(int i = y; i < matrix.length; i++){
                int[] k = new int[y];
                System.arraycopy(matrix[i], 0, k, 0, y);
                k4[i - y] = k;
            }
            int[][] k5 = new int[y][y];
            for(int i = y; i < matrix.length; i++){
                int[] k = new int[y];
                for(int j = y; j < matrix.length; j++){
                    k[j - y] = matrix[i][j];
                }
                k5[i - y] = k;
            }
            int c1 = quadrantSerch(k2, y);
            int c2 = quadrantSerch(k3, y);
            int c3 = quadrantSerch(k4, y);
            int c4 = quadrantSerch(k5, y);
            int[][] m1 = markL(k2, c1);
            int[][] m2 = markL(k3, c2);
            int[][] m3 = markL(k4, c3);
            int[][] m4 = markL(k5, c4);
            int[][] m5 = new int[m1.length + m2.length][m1.length + m2.length];
            for(int i = 0; i < m5.length / 2; i++){
                m5[i] = consT(m1[i], m2[i]);
            }
            for(int i = m5.length / 2; i < m5.length; i++){
                m5[i] = consT(m3[i - m5.length / 2], m4[i - m5.length / 2]);
            }
            return m5;
        }
    }
 
 private int selectionQuadrant(int r1, int r2, int size){
        int cuadrante;
        if(r1 < size / 2 && r2 < size / 2) {
            cuadrante = 1;
        }else if(r1 < size / 2 && r2 >= size / 2){
            cuadrante = 2;
        }else if(r1 >= size / 2 && r2 < size / 2) {
            cuadrante = 3;
        }else{
            cuadrante = 4;
        }
        return cuadrante;
    }
  public int[][] squareEspecial(){
        Random ran = new Random();
        int r1 = ran.nextInt(this.size);
        int r2 = ran.nextInt(this.size);
        if(this.mat[r1][r2] == 0){
            this.mat[r1][r2] = 1;
        }else{
            squareEspecial();
        }
        int cuadrantes = selectionQuadrant(r1, r2, this.size);
        return markL(this.mat, cuadrantes);
    }
  
  public static void main1(String[] args){
        Matriz a = new Matriz(Integer.parseInt(args[0]));
        int[][] x;
        x = a.squareEspecial();
    }
    /**
    * Función que llena la matriz con números(el índice del color).
    **/
    public void llenaMatriz(){
	int lim = colors.size();
	int k = 0;
	for(int i=0;mat.length>=i;i++){
            for(int j=0;j<mat[0].length;j++){
		mat[j][i] = k % lim;
		k++;
		}
	    }
	}
}
