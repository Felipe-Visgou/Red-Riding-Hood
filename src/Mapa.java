import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Mapa {
	
	/* Variáveis do Objeto */
   static int[] matrixMapa;
   private static ArrayList<Clareira> Clareiras; // Lista de Clareiras
   private static ArrayList<Doce> Doces; // Lista de Doces 
   private static Cesta Bascket;
   /* Construtor */
   
    public Mapa() throws IOException {

        matrixMapa = new int[1681];
        Clareiras = new ArrayList<Clareira>();
        Doces = new ArrayList<Doce>();
        loadGlade();
        loadCandy();
        loadInstance();
        Bascket = new Cesta();
    }
    
    /* Métodos */
    
    /* Retorna o tempo total gasto nas clareiras */
    public float gladeCandy(){
		
    	int qtdGlades = Mapa.Clareiras.size();
    	int qtdCandies = Mapa.Doces.size();
    	int candyCount = 5*qtdCandies - 1;
    	int unvisitedGlades;
    	int i=0;
    	float factor;	

    	
    	while(candyCount > 0){
    		
    		Collections.sort(Mapa.Clareiras, new Comparator<Clareira>(){
    			public int compare(Clareira c1, Clareira c2){
    				if(c1.fator > c2.fator) return -1;
    				if(c1.fator < c2.fator) return 1;
    				return 0;
    			}
    		});
    		
    		/* Caso 1: O numero de clareiras nao visitadas e igual ao numero de doces que tenho*/
    		if(Clareira.unvisitedGlades().size() == candyCount){
    			for(Clareira c : Clareira.unvisitedGlades()){
    				factor = Bascket.giveCandy(c);
    				c.updateFactor(factor);
    				candyCount--;
    			}
    			return sumTime();
    		}
    		/* * * * * * * PAREI AQUI *  * * * * */
    		/* Caso 2: Eu tenho mais doces que clareiras nao visitadas*/
    		i = 0;
    		factor = Bascket.giveCandy(Mapa.Clareiras.get(i));
    		while(factor < 0){
    			factor = Bascket.giveCandy(Mapa.Clareiras.get(++i));
    		}
			Mapa.Clareiras.get(i).updateFactor(factor);
			candyCount--;
    	}
    	orderGlades();
    	return sumTime();	
    }
    
    /* Lê de um arquivo as dificuldades das clareiras */
   private static void loadGlade()throws IOException{
    	 Scanner s = null;
         try {
         	s  = new Scanner(new BufferedReader(new FileReader("glade.txt")));
         	// Adiciona as clareiras com as dificuldades do arquivo de clareiras
         	while(s.hasNextInt())
         	{
         		Clareira c = new Clareira((float)s.nextInt());
         		Clareiras.add(c);
         	}
         } finally {
             s.close(); // Fecha o scanner
         }
    }
   
   /* Lê de um arquivo o fator de apreciação dos 5 doces */
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
            		matrixMapa[i*41 + j] = inputStream.read();
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
				System.out.printf("%c ", matrixMapa[i*41 + j]);
			}
			System.out.printf("\n");
		}

	}
	
	/* Métodos de get variáveis da Classe Mapa */
	public static ArrayList<Clareira> getGlades(){
		return Mapa.Clareiras;
	}
	public static ArrayList<Doce> getCandies(){
		return Mapa.Doces;
	}
	public static Cesta getBascket(){
		return Bascket;
	}
	public static int[] getMatrix(){
		return matrixMapa;
	}
	/* Retorna o somatório dos tempos gastos em cada clareira*/
	public float sumTime(){
		
		float sum = 0;
		for(Clareira c : Mapa.Clareiras){
			System.out.println(c.fator);
			sum+=c.fator;
		}
		return sum;
	}
	private void orderGlades(){
		Collections.sort(Mapa.Clareiras, new Comparator<Clareira>(){
			public int compare(Clareira c1, Clareira c2){
				if(c1.fator > c2.fator) return -1;
				if(c1.fator < c2.fator) return 1;
				return 0;
			}
		});
	}
}
