import java.util.ArrayList;
import java.util.LinkedList;

public class Cesta {
	LinkedList<LinkedList<Float>> candyBascket;
	public Cesta(){
		
		candyBascket = new LinkedList<LinkedList<Float>>();
		LinkedList<Float> f;
		Float fac;
		for(Doce d : Mapa.getCandies()){
			f = new LinkedList<Float>();
			for(int i = 0; i < d.qtd; i++){
				fac = new Float(d.fator);
				f.add(fac);
			}
			candyBascket.add(f);
		}
		candyBascket.peekLast().remove();
	}
	
	/* Dá o melhor doce disponível para a Clareira c*/
	public float giveCandy(Clareira c){
		float candyFactor;
		int i;
		for(i = 0; i < candyBascket.size(); i++){
			if(candyBascket.get(i).isEmpty()){
				continue;
			}else{
				if(c.hasCandy[i]){
					continue;	
				}
				else{
						break;
					}
			}
		}
		if(i == candyBascket.size())
			return -1;
		else{
			candyFactor = candyBascket.get(i).peek().floatValue();
			candyBascket.get(i).remove();
			c.hasCandy[i] = true;
			return candyFactor;
		}
	}
	public void printCesta(){
		int i;
		for(LinkedList<Float> d : candyBascket){
			for(Float d2 : d){
				System.out.println(d2);
			}
		}
	}
}
