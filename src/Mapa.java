import java.io.FileReader;
import java.io.IOException;

public class Mapa {
	
	/* Variáveis do Objeto */
    int[][]matrixMapa;
    
    /* Construtor */
    public Mapa() throws IOException {

        FileReader inputStream = null;
        matrixMapa = new int[41][41];
        int i,j;
        try {
            inputStream = new FileReader("instance.txt");
            for(i = 0; i < 41; i++){
            	for(j = 0; j < 41; j++){
            		matrixMapa[i][j] = inputStream.read();
            	}
            	inputStream.read();
            	inputStream.read();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
    /* Métodos */
    
}
