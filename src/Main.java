import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

public class Main extends JFrame {
	
    public Main() {
        
        initUI();
    }
    
    private void initUI() {
        
    	Instancia I = new Instancia();
    	I.updatePosition(6*20, 3*20, "right");
        add(I);
        
        setSize(830, 880);
        setResizable(false);
        
        setTitle("Red Riding Hood");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Mapa m = new Mapa();
		Main ex = new Main();
		ex.setVisible(true);
		m.printGlade();
		m.printCandy();
		m.printInstance();
		
		
		float f = m.gladeCandy();
		System.out.println("Acabou " + f);


	}

}
