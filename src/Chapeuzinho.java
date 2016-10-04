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
		
		if(counter == path.size()-1)
			try {
				Thread.sleep((long)1e+10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
}
