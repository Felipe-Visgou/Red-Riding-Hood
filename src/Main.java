import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Mapa m = new Mapa();
	
		m.printGlade();
		m.printCandy();
		m.printInstance();
		
		System.out.println(m.gladeTime(0, 0, 1, 0, 0, 0));

	}

}
