import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Mapa {
	
	/* Variáveis do Objeto */
    int[][]matrixMapa;
    ArrayList<Clareira> Clareiras; // Lista de Clareiras
    /* Construtor */
    public Mapa() throws IOException {

        FileReader inputStream = null;
        matrixMapa = new int[41][41];
        Clareiras = new ArrayList<Clareira>();
        Scanner s = null;
        int i,j;
        try {
            inputStream = new FileReader("instance.txt");
            for(i = 0; i < 41; i++){
            	for(j = 0; j < 41; j++){
            		matrixMapa[i][j] = inputStream.read();
            	}
            	inputStream.read(); // Ignora CR
            	inputStream.read(); // Ignora \n
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        try{
        	s  = new Scanner(new BufferedReader(new FileReader("glade.txt")));
        	i = 0;
        	// Adiciona as clareiras com as dificuldades do arquivo de clareiras
        	while(s.hasNextInt())
        	{
        		Clareira c = new Clareira(s.nextInt());
        		Clareiras.add(c);
        	}
        }
        finally{
        	s.close(); // Fecha o scanner
        }
    }
    /* Métodos */
    public float gladeTime(){
		
    	return 0;
   
    }
}
