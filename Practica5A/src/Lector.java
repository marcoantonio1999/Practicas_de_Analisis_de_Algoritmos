
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Lector{

    LinkedList<String> line;

    //Constructor del lector.
    public Lector(){
        line = new LinkedList<>();
    }

    /**
    * Método que lee línea a línea el archivo que recibe.
    */
    public void lee(String archivo){
        try{
            FileReader fr1 = new FileReader(archivo);
            try (BufferedReader br1 = new BufferedReader(fr1)) {
                String linea = br1.readLine(); 
                while(linea!=null){ 
                    line.add(linea);
                    linea = br1.readLine();
                }
            } 
        }catch(IOException e){System.err.println("Archivo no encontrado");}
    }

    /**
    * Método que crea una gráfica con el archivo que leyó.
    * 
    */
    public Grafica creaGrafica(){
        Grafica grafica = new Grafica(false);
        String[] verts = line.get(0).split(","); 
        
        for (String vert : verts) {
            int idv = Integer.parseInt(vert);
            grafica.agregaVertice(idv);
        }

       
        for(int i=1;i<line.size();i++){
            String[] ari = line.get(i).split(",");
            int id0 = Integer.parseInt(ari[0]);
            int id1 = Integer.parseInt(ari[1]);
            grafica.agregaArista(id0,id1);
        }

        return grafica;
    }
}



