package f2.spw;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("LOB MUN HAI DAI!!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 550);
		frame.getContentPane().setLayout(new BorderLayout());
		
		SpaceShip v = new SpaceShip(180, 450, 20, 20);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v);
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		
		engine.start();
		
		
		
		
	}
}
