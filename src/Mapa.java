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
    
    public float gladeTime(int arg0, int arg1,int arg2,int arg3,int arg4,int arg5, ArrayList<Doce> doces, ArrayList<Clareira> glades){
		
    	/* Função Hard*/
    	/* TEMPO = Dificuldade da Clareira/SUM(fator de apreciação dos doces fornecidos)*/
   
    	
    	/*
    	 * arg0 = numero da clareira (0,2,...,9)
    	 * arg0-5 = quantidade de cada doce oferecida (0,1,...,5)
    	 *  ArrayList<Doce> doces = Lista de Doces a ser manipulada
    	 */
    	
    	float factSum = 0, Tempo;
    	factSum += arg1*(doces.get(0).fator);
    	factSum += arg2*(doces.get(1).fator);
    	factSum += arg3*(doces.get(2).fator);
    	factSum += arg4*(doces.get(3).fator);
    	factSum += arg5*(doces.get(4).fator);
    	
    	Tempo = glades.get(arg0).Dificuldade/factSum;
    	
    	return Tempo;
   
    }
    
    /* Lê de um arquivo as dificuldades das clareiras */
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
   
   /* Lê de um arquivo o fator de apreciação dos 10 doces */
    private static void loadCandy()throws IOException{
    	Scanner s = null;
    	try {
         	s  = new Scanner(new BufferedReader(new FileReader("Candy.txt")));
         	// Adiciona os doces com seus respectivos fatores de apreciação
         	Doce d = new Doce("Torta de Amoras",s.nextFloat()); Doces.add(d);
         	d = new Doce("Cupcakes de Marshmallow",s.nextFloat()); Doces.add(d);
         	d = new Doce("Bolo de Chocolate",s.nextFloat()); Doces.add(d);
         	d = new Doce("Brigadeiro",s.nextFloat()); Doces.add(d);
         	d = new Doce("Doce de Coco",s.nextFloat()); Doces.add(d);
         } finally {
             s.close(); // Fecha o scanner
         }
    }
    
    /* Lê de um arquivo a instância de todo mapa */
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
    
    /* Imprime a lista stática de Clareiras */
	public  void printGlade() {
	
		int i=0;
		Iterator<Clareira> I = Clareiras.iterator();
		while(I.hasNext()){
			System.out.println(Clareiras.get(i).Dificuldade);
			i++;
			I.next();
		}
	}
	
	/* Imprime a lista stática de Doces */
	public  void printCandy() {

		int i=0;
		Iterator<Doce> I = Doces.iterator();
		while(I.hasNext()){
			System.out.println(Doces.get(i).nome + " " + Doces.get(i).fator + " " + "("+Doces.get(i).qtd+")");
			i++;
			I.next();
		}
	}
	
	/* Imprime a Instancia do Mapa */
	public  void printInstance() {
		
		int i,j;
		for(i = 0; i < 41; i++){
			for(j=0; j < 41; j++){
				System.out.printf("%c ", matrixMapa[i][j]);
			}
			System.out.printf("\n");
		}

	}
}
