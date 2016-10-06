import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Mapa {
	
	/* Variáveis do Objeto */
   static int[] matrixMapa;
   private static ArrayList<Clareira> Clareiras; // Lista de Clareiras
   private static LinkedList<Integer> gladePositions;
   private static ArrayList<Doce> Doces; // Lista de Doces 
   private static Cesta Bascket;
   private static ArrayList<Node> lMap;
   private static Node nInicio, nFim;
   /* Construtor */
   
    public Mapa() throws IOException {
    	
    	lMap = new ArrayList<Node>();
        matrixMapa = new int[1681];
        Clareiras = new ArrayList<Clareira>();
        Doces = new ArrayList<Doce>();
        gladePositions = new LinkedList<Integer>();
        loadInstance();
        loadGlade();
        loadCandy();
        
        setupMap();
        
        Bascket = new Cesta();
    }
    
    /* Métodos */
    
    /* Retorna o tempo total gasto nas clareiras */
    public static float gladeCandy(ArrayList<Integer> p){
		
    	int qtdGlades = Mapa.Clareiras.size();
    	int qtdCandies = Mapa.Doces.size();
    	int candyCount = 5*qtdCandies - 1;
    	int unvisitedGlades;
    	int i=0;
    	float factor;
    	
    	/* Non Visited Glades */
    	for(int j = 0, a = 0; j < p.size(); j++){
    		for(int b = 0; b < gladePositions.size(); b++){
    			if(p.get(j).intValue() == gladePositions.get(b).intValue()){
    				gladePositions.remove(b);
    				Mapa.Clareiras.get(a).position = p.get(j);
    				a++;
    			}
    		}
    	}
    	
    	Clareira g = searhGladeIn(0);
    	
    	while(g!= null){
    		Mapa.Clareiras.remove(g);
    		 g = searhGladeIn(0);
    	}
    	
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
    	int i,j, c = 0;
        try {
            inputStream = new FileReader("instance.txt");
            for(i = 0; i < 41; i++){
            	for(j = 0; j < 41; j++){
            		matrixMapa[i*41 + j] = inputStream.read();
            		if(matrixMapa[i*41 + j] == 'C'){
            			gladePositions.add(i*41 + j);
            		}
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
	public static void printGlade() {
	
		int i=0;
		Iterator<Clareira> I = Clareiras.iterator();
		while(I.hasNext()){
			System.out.println(Clareiras.get(i).Dificuldade + " : " + Clareiras.get(i).fator + "\n");
			i++;
			I.next();
		}
	}
	
	/* Imprime a lista stática de Doces */
	public  static void printCandy() {

		int i=0;
		Iterator<Doce> I = Doces.iterator();
		while(I.hasNext()){
			System.out.println(Doces.get(i).nome + " " + Doces.get(i).fator + " " + "("+Doces.get(i).qtd+")");
			i++;
			I.next();
		}
	}
	
	/* Imprime a Instancia do Mapa */
	public  static void printInstance() {
		
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
	public static LinkedList<Integer> getGladePositions(){
		return gladePositions;
	}
	public static ArrayList<Node> getLMap(){
		return lMap;
	}
	public static Node getStartNode(){
		return nInicio;
	}
	public static Node getEndNode(){
		return nFim;
	}
	/* Retorna o somatório dos tempos gastos em cada clareira*/
	public static float sumTime(){
		
		float sum = 0;
		for(Clareira c : Mapa.Clareiras){
			sum+=c.fator;
		}
		return sum;
	}
	private static void orderGlades(){
		Collections.sort(Mapa.Clareiras, new Comparator<Clareira>(){
			public int compare(Clareira c1, Clareira c2){
				if(c1.fator > c2.fator) return -1;
				if(c1.fator < c2.fator) return 1;
				return 0;
			}
		});
	}
	private static Clareira searhGladeIn(int pos){
		for(Clareira c : Mapa.Clareiras){
			if(c.position == pos)
				return c;
		}
		return null;
	}
	private static void setupMap(){
		   ArrayList<Integer> lTemp = new ArrayList<Integer>();
		   for (int i = 0; i < 41*41; i++){
		    
		    if(matrixMapa[i] == 'D'){
		     lMap.add(new Node(200));
		    }
		    else if(matrixMapa[i] == '.'){
		     lMap.add(new Node(1));
		    }
		    else if(matrixMapa[i] == 'G'){
		     lMap.add(new Node(5));
		    }
		    else if(matrixMapa[i] == 'C'){
		     lMap.add(new Node(0));
		    }
		    else if(matrixMapa[i] == 'I'){
		     lMap.add(new Node(0));
		     nInicio = lMap.get(i);
		    }
		    else if(matrixMapa[i] == 'F'){
		     lMap.add(new Node(0));
		     nFim = lMap.get(i);
		    }    
		    
		    lMap.get(i).setIndex(i);
		    
		    if (i - 41 > 0)
		     lMap.get(i).setNeighbor(i - 41);
		    if (i + 41 < 41*41)
		     lMap.get(i).setNeighbor(i + 41);
		    if (i - 1 > 0 && (i-1)%41 != 40)
		     lMap.get(i).setNeighbor(i - 1);
		    if (i + 1 < 41*41 && (i+1)%41 != 0)
		     lMap.get(i).setNeighbor(i + 1);
		   }
		 }
}
