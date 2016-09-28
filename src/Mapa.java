import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Mapa {
	
	/* Variáveis do Objeto */
   static int[][]matrixMapa;
   static ArrayList<Clareira> Clareiras; // Lista de Clareiras
   static ArrayList<Doce> Doces; // Lista de Doces
   /* Construtor */
    public Mapa() throws IOException {

        matrixMapa = new int[41][41];
        Clareiras = new ArrayList<Clareira>();
        Doces = new ArrayList<Doce>();
        loadGlade();
        loadCandy();
        loadInstance();
    }
    /* Métodos */
    public float gladeTime(){
		
    	/* Função Hard|*/
    	return 0;
   
    }
   private static void loadGlade()throws IOException{
    	 Scanner s = null;
         try {
         	s  = new Scanner(new BufferedReader(new FileReader("glade.txt")));
         	// Adiciona as clareiras com as dificuldades do arquivo de clareiras
         	while(s.hasNextInt())
         	{
         		Clareira c = new Clareira(s.nextInt());
         		Clareiras.add(c);
         	}
         } finally {
             s.close(); // Fecha o scanner
         }
    }
    private static void loadCandy()throws IOException{
    	Scanner s = null;
    	try {
         	s  = new Scanner(new BufferedReader(new FileReader("Candy.txt")));
         	// Adiciona os doces com seus respectivos fatores de apreciação
         	Doce d = new Doce("Torta de Amoras",s.nextFloat()); Doces.add(d);
         	d = new Doce("Cupcakes de Marshmallow",s.nextFloat()); Doces.add(d);
         	d = new Doce("Bolo de Chocolate",s.nextFloat()); Doces.add(d);
         	d = new Doce("Brigadeiro",s.nextFloat()); Doces.add(d);
         	d = new Doce("Docede Coco",s.nextFloat()); Doces.add(d);
         } finally {
             s.close(); // Fecha o scanner
         }
    }
    private static void loadInstance() throws IOException{
    	FileReader inputStream = null;
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
        
    }
	public  void printGlade() {
	
		/* Imprime a lista de Clareiras */
		int i=0;
		Iterator<Clareira> I = Clareiras.iterator();
		while(I.hasNext()){
			System.out.println(Clareiras.get(i).Dificuldade);
			i++;
			I.next();
		}
	}
	public  void printCandy() {
		
		/* Imprime a lista de Doces */
		int i=0;
		Iterator<Doce> I = Doces.iterator();
		while(I.hasNext()){
			System.out.println(Doces.get(i).nome + " " + Doces.get(i).fator + " " + "("+Doces.get(i).qtd+")");
			i++;
			I.next();
		}
	}
	public  void printInstance() {
		
		/* Imprime a Instancia do Mapa */
		int i,j;
		for(i = 0; i < 41; i++){
			for(j=0; j < 41; j++){
				System.out.printf("%c ", matrixMapa[i][j]);
			}
			System.out.printf("\n");
		}

	}
}
