import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

public class Main extends JFrame {
	
    public Main() {
        
        initUI();
    }
    
    private void initUI() {
        
    	Instancia I = new Instancia();
        add(I);
        
        setSize(830, 880);
        setResizable(true);
        
        setTitle("Red Riding Hood");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Mapa m = new Mapa();

		m.printInstance();
		m.printGlade();
		m.printCandy();
		
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
        		Main ex = new Main();
        		ex.setVisible(true);
            }
        });
		


	}


}
