import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Instancia extends JPanel implements ActionListener  {
	
	private  Chapeuzinho hood;
	private ArrayList<Integer> p;
	private Timer timer;
    private final int DELAY = 75;
    public Instancia() {

        initBoard();
    }
    
    private void initBoard() {
        
        setFocusable(true);
        setBackground(Color.GREEN);

        p = Algorithm.AStar(Mapa.getLMap(), Mapa.getStartNode(), Mapa.getEndNode(), 41*41);

        hood = new Chapeuzinho(p);
        
        timer = new Timer(DELAY, this);
        timer.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

	private void doDrawing(Graphics g) {

		int it = 0;
		Graphics2D g2d = (Graphics2D) g;
        ImageIcon img1 = new ImageIcon("tree2.png");
        Image tree = img1.getImage();
        ImageIcon img3 = new ImageIcon("glade2.png");
        Image glade = img3.getImage();
        ImageIcon img4 = new ImageIcon("wood-branch.png");
        Image galho1 = img4.getImage();
        ImageIcon img5 = new ImageIcon("wood-branch2.png");
        Image galho2 = img5.getImage();
        ImageIcon img6;
        Image chapeu = null;
        ImageIcon img7 = new ImageIcon("house.png");
        Image house = img7.getImage();
        
        short[] hash = {0,1,0,0,0,1,1,1,1,1,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,1,1,1,
        		1,0,1,1,0,1,1,0,0,1,0,0,0,1,1,1,1,0,0,1,0,0,0,1,1,1,0,1,0,0,0,0,0,1,1,0,0,
        		0,1,0,0,1,0,1,1,1,1,0,0,1,0,0,1,0,1,0,0,1,1,0,1,0,1,0,1,1,0,1,1,1,1,1,1,1,
        		1,1,0,0,1,1,1,0,1,1,0,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,1,1,0,1,
        		1,1,0,0,1,0,0,1,0,0,0,0,1,1,1,0,1,1,1,0,0,0,0,0,0,1,0,1,1,1,0,1,0,0,0,1,1,0,
        		1,0,0,1,1,0,0,0,1,0,0,1,1,0,0,1,0,1,1,1,0,1,1,0,0,1,0,1,1,1,1,1,0,1,0,1,0,0,
        		0,0,0,1,1,0,0,0,0,0,1,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,
        		0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,1,1,0,1,0,1,0,0,0,0,0,0,0,0,0,1,1,0,1,1,
        		1,1,0,0,0,1,1,0,1,0,0,0,1,0,1,1,0,0,0,0,0,0,1,0,0,0,1,1,0,1,1,0,0,0,0,0,0,1,
        		1,1,1,0,1,0};
        int[] map = Mapa.getMatrix();
        
        
        for(int i = 0; i < 41; i++){
        	for(int j = 0; j < 41;j++){
        		if(map[j*41 + i] == 'D'){
            		g2d.drawImage(tree, i*20, j*20,23,23,this);
        		}
        		else{
        			if(map[j*41 + i] == 'C'){
            			g2d.drawImage(glade, i*20+4, j*20+7,13,13,this);
        			}
        			else{
        				if(map[j*41 + i] == 'G'){
        					if(hash[it] == 0)
        						g2d.drawImage(galho1, i*20, j*20,20,20,this);
        					else
        						g2d.drawImage(galho2, i*20, j*20,18,18,this);
        					it++;
        					}
        				else{
        					if(map[j*41 + i] == 'F')
        						g2d.drawImage(house, i*20-9, j*20,35,35,this);
        				}
        			}
        		}
        	}
        }
        switch(hood.getPos()){
        case "front":
            img6 = new ImageIcon("front.png");
            chapeu = img6.getImage();
            break;
        case "left":
            img6 = new ImageIcon("left.png");
            chapeu = img6.getImage();
            break;
        case "right":
            img6 = new ImageIcon("right.png");
            chapeu = img6.getImage();
            break;
        case "back":
            img6 = new ImageIcon("back.png");
            chapeu = img6.getImage();
            break;
        default: System.out.println("Posicao errada");
        }
        g2d.drawImage(chapeu, hood.getX()*20, hood.getY()*20,20,20,this);
        
        int count = hood.getCounter();
        int dx,dy;
        if(count > 1){
        	for(int k = 1; k < count; k++){
        		dx = (p.get(k-1)%41); dy = (int)(p.get(k-1)/41);
        		g2d.setColor(Color.RED);
        		g2d.drawOval(dx*20+4, (int)dy*20+4, 12, 12);
        		g2d.fillOval(dx*20+4, dy*20+4, 12, 12);
        	}
        } 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		hood.updatePosition();
		repaint();
		
	}
}
