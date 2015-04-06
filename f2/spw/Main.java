package f2.spw;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("LOB MUN HAI DAI!!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 550);
		frame.getContentPane().setLayout(new BorderLayout());
		
		SpaceShip v = new SpaceShip(180, 450, 10, 20);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp, v);
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		
		
		SpaceShip v2 = new SpaceShip(80, 450, 10, 20);
		GameEngine engine2 = new GameEngine(gp, v2);
		frame.addKeyListener(engine2);
		frame.setVisible(true);
		
		SpaceShip v3 = new SpaceShip(280, 450, 10, 20);
		GameEngine engine3 = new GameEngine(gp, v3);
		frame.addKeyListener(engine3);
		frame.setVisible(true);
		
		engine.start();
		
		
		
		
	}
}
