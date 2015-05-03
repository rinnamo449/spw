package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.IOException;
import java.io.File;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;
    private BufferedImage bi2;	
	private BufferedImage bi3;
	private BufferedImage hp;
	private BufferedImage bg;
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
		hp = new BufferedImage(400, 800, BufferedImage.TYPE_INT_ARGB);
		hpbar = (Graphics2D) hp.getGraphics();
		big.setBackground(Color.BLACK);
		
		try{
			bg = ImageIO.read(new File("f2/spw/Background.png"));
		}
		catch(IOException e){
		}
	}

	public void updateGameUI(GameReporter reporter){
	
	    big.clearRect(0, 0, 400, 600);
		big.drawImage(bg, 0, 0, 400, 600, null);
		big.setColor(Color.YELLOW);	
		
		big.drawString(String.format("Single shot [PRESS Q]"), 5, 500);	
		big.drawString(String.format("Double shot [PRESS W]"), 250, 500);
	    big.drawString(String.format("SCORE : %08d", reporter.getScore()), 270, 30);
		big.drawString(String.format("Multi Quick : %d",reporter.getSkillE()), 20, 30);
		big.drawString(String.format("StunGun : "), 135, 30);
	    big.drawString(String.format("LIFE : %01d", reporter.getDead()), 20, 50);
		
		if( reporter.getSkillR() == 1){
			big.drawString(String.format("Ready!!"), 195, 30);
			}
		else if( reporter.getSkillR() == 0){
			big.drawString(String.format("======="), 195, 30);
			}
		
		if( reporter.getStopgun() == 0 ){
			big.drawString(String.format("STUN!!"), 160, 80);
			}
		if(reporter.getStop() == 0 ){
			big.drawString(String.format("LIFEBOSS : %01d", reporter.getLifeboss()), 270, 50);
			}
		if(reporter.getStop() == 0 ){
			big.drawString(String.format("ENEMY'S BOSS APPEAR!!", reporter.getLifeboss()), 120, 300);
			}
		if(reporter.getSkillE() <= 20 && reporter.getSkillE() != 0){
			big.drawString(String.format("Multiple Quick shot is ready!! [PRESS E]"), 90, 270);
	        }
		if(reporter.getSkillR()== 1 ){
			big.drawString(String.format("Stun Gun is ready!! [PRESS R]"), 110, 240);
	        }	
			
		//hpbar//
		for(int j = 1; j <= reporter.getDead() ; j++){
		    if(j%2 == 0){
			  big.fillRect(20+((j-1)*3),70,5,20);
			}
		}
		
	    //hpBoss//
	    if( reporter.getStop() == 0 ){
		    for(int i = 1; i <= reporter.getLifeboss() ; i++){
		       if(i%2 == 0){
			     big.fillRect(265+((i-1)*3),70,5,20);
			   }
		    }
	    }
		
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
		
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
		
	}
}
