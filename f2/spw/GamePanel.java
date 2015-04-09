package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;
    private BufferedImage bi2;	
	Graphics2D big;
	Graphics2D big2;
	//Graphics2D hp;
	
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	ArrayList<Sprite> sprites2 = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 800, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		bi2 = new BufferedImage(400, 800, BufferedImage.TYPE_INT_ARGB);
		big2 = (Graphics2D) bi2.getGraphics();
		
		big.setBackground(Color.YELLOW);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		
		big.setColor(Color.RED);		
		big.drawString(String.format("%08d", reporter.getScore()), 300, 50);
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
		
		big2.clearRect(0, 0, 400, 600);
		
		big2.setColor(Color.GREEN);		
		big2.drawString(String.format("%08d", reporter.getScore()), 300, 50);
		for(Sprite s2 : sprites2){
			s2.draw(big2);
		}
		
		repaint();
		
		//hp.setColor(Color.RED);
		//hp.fillRect(10, 10, 200, 200);
	}

	
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
		
	}
	
	public void paint2(Graphics g2) {
		Graphics2D g2d2 = (Graphics2D) g2;
		g2d2.drawImage(bi2, null, 0, 0);
		
	}
	

}
