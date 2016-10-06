import java.util.ArrayList;
import java.util.Collections;
public class Clareira {
	public float Dificuldade;
	public float fator;
	public float facSum;
	public boolean[] hasCandy;
	public int position;
	public Clareira(float dif){
		Dificuldade = dif;
		fator = this.Dificuldade;
		facSum = 0;
		hasCandy = new boolean[5];
		hasCandy[0] = false;
		hasCandy[1] = false;
		hasCandy[2] = false;
		hasCandy[3] = false;
		hasCandy[4] = false;
	}
	public boolean wasVisited(){
		for(int i = 0; i < 5; i++){
			if(hasCandy[i] == true){
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Clareira> unvisitedGlades(){
		ArrayList<Clareira> unvGlades = new ArrayList<Clareira>();
		for(Clareira c : Mapa.getGlades()){
			if(!c.wasVisited()){
				unvGlades.add(c);
			}
		}
		return unvGlades;
	}
	public void updateFactor(float f){
		
		facSum+=f;
		fator = this.Dificuldade/facSum;
	}
}
