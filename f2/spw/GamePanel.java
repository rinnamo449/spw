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
		bi2 = new BufferedImage(400, 800, BufferedImage.TYPE_INT_ARGB);
		big2 = (Graphics2D) bi2.getGraphics();
		bi3 = new BufferedImage(400, 800, BufferedImage.TYPE_INT_ARGB);
		
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
        //big2.setColor(Color.RED);		
	    big.drawString(String.format("SCORE : %08d", reporter.getScore()), 270, 30);
	    big.drawString(String.format("LIFE : %01d", reporter.getDead()), 20, 50);
		if(reporter.getStop() == 0 )
		big.drawString(String.format("LIFEBOSS : %01d", reporter.getLifeboss()), 270, 50);
		if(reporter.getStop() == 0 )
		big.drawString(String.format("BLUE ENEMY APPEAR!!", reporter.getLifeboss()), 120, 300);
	    
		//hpbar
		for(int j = 1; j <= reporter.getDead() ; j++){
		    if(j%2 == 0){
			big.fillRect(20+((j-1)*3),70,5,20);
			}
		}
		/* //hpbar
		if( reporter.getDead() >= 3 && reporter.getDead() < 6 )
		big.fillRect(20,70,5,20);
		else if( reporter.getDead() >= 6 && reporter.getDead() < 9 ){
		big.fillRect(20,70,5,20);
		big.fillRect(27,70,5,20);}
		else if( reporter.getDead() >= 9 && reporter.getDead() < 12 ){
		big.fillRect(20,70,5,20);
		big.fillRect(27,70,5,20);
		big.fillRect(34,70,5,20);}
		else if( reporter.getDead() >=12 && reporter.getDead() < 15 ){
		big.fillRect(20,70,5,20);
		big.fillRect(27,70,5,20);
		big.fillRect(34,70,5,20);
		big.fillRect(41,70,5,20);}
		else if( reporter.getDead() >=15 && reporter.getDead() < 18 ){
		big.fillRect(20,70,5,20);
		big.fillRect(27,70,5,20);
		big.fillRect(34,70,5,20);
		big.fillRect(41,70,5,20);
		big.fillRect(48,70,5,20);}
		else if( reporter.getDead() >=18 && reporter.getDead() < 20 ){
		big.fillRect(20,70,5,20);
		big.fillRect(27,70,5,20);
		big.fillRect(34,70,5,20);
		big.fillRect(41,70,5,20);
		big.fillRect(48,70,5,20);
		big.fillRect(55,70,5,20);}
		else if( reporter.getDead() == 20){
		big.fillRect(20,70,5,20);
		big.fillRect(27,70,5,20);
		big.fillRect(34,70,5,20);
		big.fillRect(41,70,5,20);
		big.fillRect(48,70,5,20);
		big.fillRect(55,70,5,20);
		big.fillRect(62,70,5,20);}
		 */
		 
		//hpBoss
	  if( reporter.getStop() == 0 ){
		//System.out.println(reporter.getLifeboss());
		for(int i = 1; i <= reporter.getLifeboss() ; i++){
		    if(i%2 == 0){
			big.fillRect(265+((i-1)*3),70,5,20);
			}
		}
		/* if( reporter.getLifeboss() >= 3 && reporter.getLifeboss() < 6 )
		big.fillRect(250,70,5,20);
		else if( reporter.getLifeboss() >= 6 && reporter.getLifeboss() < 9 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);}
		else if( reporter.getLifeboss() >= 9 && reporter.getLifeboss() < 12 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);
		big.fillRect(264,70,5,20);}
		else if( reporter.getLifeboss() >=12 && reporter.getLifeboss() < 15 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);
		big.fillRect(264,70,5,20);
		big.fillRect(271,70,5,20);}
		else if( reporter.getLifeboss() >=15 && reporter.getLifeboss() < 18 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);
		big.fillRect(264,70,5,20);
		big.fillRect(271,70,5,20);
		big.fillRect(278,70,5,20);}
		else if( reporter.getLifeboss() >=18 && reporter.getLifeboss() < 21 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);
		big.fillRect(264,70,5,20);
		big.fillRect(271,70,5,20);
		big.fillRect(278,70,5,20);
		big.fillRect(285,70,5,20);}
		else if( reporter.getLifeboss() >= 21 && reporter.getLifeboss() < 24 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);
		big.fillRect(264,70,5,20);
		big.fillRect(271,70,5,20);
		big.fillRect(278,70,5,20);
		big.fillRect(285,70,5,20);
		big.fillRect(292,70,5,20);}
		else if( reporter.getLifeboss() >= 24 && reporter.getLifeboss() < 27 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);
		big.fillRect(264,70,5,20);
		big.fillRect(271,70,5,20);
		big.fillRect(278,70,5,20);
		big.fillRect(285,70,5,20);
		big.fillRect(292,70,5,20);
		big.fillRect(299,70,5,20);}
		else if( reporter.getLifeboss() >= 27 && reporter.getLifeboss() < 30 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);
		big.fillRect(264,70,5,20);
		big.fillRect(271,70,5,20);
		big.fillRect(278,70,5,20);
		big.fillRect(285,70,5,20);
		big.fillRect(292,70,5,20);
		big.fillRect(299,70,5,20);
		big.fillRect(306,70,5,20);}
		else if( reporter.getLifeboss() >= 30 && reporter.getLifeboss() < 33 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);
		big.fillRect(264,70,5,20);
		big.fillRect(271,70,5,20);
		big.fillRect(278,70,5,20);
		big.fillRect(285,70,5,20);
		big.fillRect(292,70,5,20);
		big.fillRect(299,70,5,20);
		big.fillRect(306,70,5,20);
		big.fillRect(313,70,5,20);}
		else if( reporter.getLifeboss() >= 33 && reporter.getLifeboss() < 36 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);
		big.fillRect(264,70,5,20);
		big.fillRect(271,70,5,20);
		big.fillRect(278,70,5,20);
		big.fillRect(285,70,5,20);
		big.fillRect(292,70,5,20);
		big.fillRect(299,70,5,20);
		big.fillRect(306,70,5,20);
		big.fillRect(313,70,5,20);
		big.fillRect(320,70,5,20);}
		else if( reporter.getLifeboss() >= 36 && reporter.getLifeboss() < 39 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);
		big.fillRect(264,70,5,20);
		big.fillRect(271,70,5,20);
		big.fillRect(278,70,5,20);
		big.fillRect(285,70,5,20);
		big.fillRect(292,70,5,20);
		big.fillRect(299,70,5,20);
		big.fillRect(306,70,5,20);
		big.fillRect(313,70,5,20);
		big.fillRect(320,70,5,20);
		big.fillRect(327,70,5,20);}
		else if( reporter.getLifeboss() >= 39 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);
		big.fillRect(264,70,5,20);
		big.fillRect(271,70,5,20);
		big.fillRect(278,70,5,20);
		big.fillRect(285,70,5,20);
		big.fillRect(292,70,5,20);
		big.fillRect(299,70,5,20);
		big.fillRect(306,70,5,20);
		big.fillRect(313,70,5,20);
		big.fillRect(320,70,5,20);
		big.fillRect(327,70,5,20);
		big.fillRect(334,70,5,20);}
		else if( reporter.getLifeboss() == 40 ){
		big.fillRect(250,70,5,20);
		big.fillRect(257,70,5,20);
		big.fillRect(264,70,5,20);
		big.fillRect(271,70,5,20);
		big.fillRect(278,70,5,20);
		big.fillRect(285,70,5,20);
		big.fillRect(292,70,5,20);
		big.fillRect(299,70,5,20);
		big.fillRect(306,70,5,20);
		big.fillRect(313,70,5,20);
		big.fillRect(320,70,5,20);
		big.fillRect(327,70,5,20);
		big.fillRect(334,70,5,20);
		big.fillRect(341,70,5,20);} */
	}
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
