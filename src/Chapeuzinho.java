import java.util.ArrayList;

public class Chapeuzinho {
	private int x,y;
	String pos;
	private ArrayList<Integer> path;
	private int counter = 0;
	
	public Chapeuzinho(ArrayList<Integer> p){
		path = p;
		pos = "back";
		x = 4; y = 36;
	}
	public void updatePosition(){
		
		int dx,dy;
		
		if(counter == path.size()){
			Fim();
			System.exit(1);
		}
		
		dx = path.get(counter)%41;
		dy = (int)path.get(counter)/41;
		if(x == dx){
			if(y < dy)
				pos = "front";
			else if(y > dy)
				pos = "back";
		}
		else{
			if(y == dy){
				if(x < dx)
					pos = "right";
				else if(x > dx)
					pos = "left";
			}
		}
		x = dx;
		y = dy;
		counter++;
	}
	public String getPos(){
		return pos;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getCounter(){
		return counter;
	}
	public ArrayList<Integer> getPath(){
		return path;
	}
	
	void Fim(){
		float f = Mapa.gladeCandy(path);
		int sum = 0;
		for(Integer I : path){
			sum += Mapa.getLMap().get(I.intValue()).getVal();
		}
		Mapa.printGlade();
		System.out.println("\n\n\n");
		System.out.println("Tempo das Clareiras achado : " + f);
		System.out.println("Tempo do Percurso : " + sum);
		System.out.println("Soma Total : " + (f + sum));
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
