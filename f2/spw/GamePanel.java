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
	private BufferedImage bi3;
	private BufferedImage hp;
	Graphics2D big;
	Graphics2D big2;
	Graphics2D big3;
	Graphics2D hpbar;
	
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	ArrayList<Sprite> sprites2 = new ArrayList<Sprite>();
	ArrayList<Sprite> sprites3 = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 800, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		bi2 = new BufferedImage(400, 800, BufferedImage.TYPE_INT_ARGB);
		big2 = (Graphics2D) bi2.getGraphics();
		bi3 = new BufferedImage(400, 800, BufferedImage.TYPE_INT_ARGB);
		
		hp = new BufferedImage(400, 800, BufferedImage.TYPE_INT_ARGB);
		hpbar = (Graphics2D) hp.getGraphics();
		
		big.setBackground(Color.YELLOW);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		big.setColor(Color.GRAY);	
        //big2.setColor(Color.RED);		
		big.drawString(String.format("SCORE : %08d", reporter.getScore()), 270, 50);
	    big.drawString(String.format("LIFE : %01d", reporter.getDead()), 20, 50);
		
		
		
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
		
		
		for(Sprite s2 : sprites2){
			s2.draw(big2);
		}
		
		repaint();
		
		for(Sprite s3 : sprites3){
			s3.draw(big3);
		}
		
		repaint();
		
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
	
	public void paint3(Graphics g3) {
		Graphics2D g2d3 = (Graphics2D) g3;
		g2d3.drawImage(bi3, null, 0, 0);
		
	}
	

}
